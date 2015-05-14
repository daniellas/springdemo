package com.recruitiva.demo.model;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.recruitiva.demo.entity.OrderItem;
import com.recruitiva.demo.entity.ShopOrder;
import com.recruitiva.demo.repository.OrderRepository;

@Service
public class DefaultSeller implements Seller {

    @Autowired
    MailSender mailSender;

    @Autowired
    Cart cart;

    @Autowired
    OrderRepository orderRepo;

    @Value("${shop.email}")
    String shopEmail;

    @Override
    public CartContent acceptOrder(OrderData data) {
        ShopOrder order = new ShopOrder();
        CartContent content = cart.getContent();

        if (content.getArticles().isEmpty()) {
            throw new OrderException("Koszyk nie może być pusty");
        }

        order.setClientEmail(data.getClientEmail());
        order.setAddress(data.getAddress());
        order.setDate(new Date());
        order.setQuantity(content.getQuantity());
        order.setValue(content.getValue());
        for (CartItem item : content.getArticles().values()) {
            OrderItem orderItem = new OrderItem();

            orderItem.setArticle(item.getArticle());
            orderItem.setPrice(item.getArticle().getPrice());
            orderItem.setQuantity(item.getQuantity());
            orderItem.setValue(item.getValue());
            order.getItems().add(orderItem);
        }
        orderRepo.save(order);
        notify(data);

        return cart.purge();
    }

    private void notify(OrderData data) {
        String orderDescription = orderDescription(data, cart.getContent());
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(data.getClientEmail());
        message.setFrom(shopEmail);
        message.setSubject("Twoje zamówienie");
        message.setText(orderDescription);
        mailSender.send(message);

        message = new SimpleMailMessage();
        message.setTo(shopEmail);
        message.setFrom(shopEmail);
        message.setSubject("Nowe zamówienie");
        message.setText(orderDescription);
        mailSender.send(message);
    }

    private String orderDescription(OrderData data, CartContent content) {
        StringBuilder builder = new StringBuilder("Szczegóły zamówienia\n");

        builder.append("Adres e-mail klienta: ");
        builder.append(data.getClientEmail());
        builder.append("\n");
        builder.append("Adres dostawy: ");
        builder.append(data.getAddress());
        builder.append("\n\n");
        builder.append("Towary");
        builder.append("\n");
        for (CartItem item : content.getArticles().values()) {
            builder.append(item.getArticle().getName());
            builder.append(",ilość: ");
            builder.append(item.getQuantity());
            builder.append(",cena: ");
            builder.append(item.getArticle().getPrice());
            builder.append(",wartość: ");
            builder.append(item.getValue());
            builder.append("\n");
        }
        builder.append("Razem: ilość: ");
        builder.append(content.getQuantity());
        builder.append(", wartość: ");
        builder.append(content.getValue());

        return builder.toString();
    }

    @Override
    public List<ShopOrder> orders() {
        return orderRepo.findAll(new Sort(new Order(Direction.DESC, "id")));
    }
}
