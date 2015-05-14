package com.recruitiva.demo.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

import com.recruitiva.demo.entity.OrderItem;
import com.recruitiva.demo.entity.ShopOrder;
import com.recruitiva.demo.repository.OrderRepository;

public class DefaultSeller implements Seller {

    Cart cart;

    OrderRepository orderRepo;

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

        return cart.purge();
    }

    @Override
    public List<ShopOrder> orders() {
        return orderRepo.findAll(new Sort(new Order(Direction.DESC, "id")));
    }
}
