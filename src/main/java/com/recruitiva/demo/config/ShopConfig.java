package com.recruitiva.demo.config;

import java.util.Properties;

import javax.sql.DataSource;
import javax.validation.ValidatorFactory;

import liquibase.integration.spring.SpringLiquibase;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.context.WebApplicationContext;

import com.recruitiva.demo.model.SessionCart;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = { "com.recruitiva.demo.repository" })
@ComponentScan(basePackages = { "com.recruitiva.demo.model" })
@PropertySource("classpath:shop.properties")
public class ShopConfig {

    @Value("${mail.debug}")
    String mailDebug;

    @Value("${shop.email}")
    String shopEmail;

    @Value("${shop.email.password}")
    String shopEmailPassword;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.INTERFACES)
    public SessionCart cart() {
        return new SessionCart();
    }

    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL).build();
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();

        adapter.setShowSql(true);
        adapter.setGenerateDdl(true);
        adapter.setDatabase(Database.HSQL);

        return adapter;
    }

    @Bean
    public JpaTransactionManager transactionManager() throws ClassNotFoundException {
        JpaTransactionManager transactionManager = new JpaTransactionManager();

        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

        return transactionManager;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws ClassNotFoundException {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();

        factoryBean.setDataSource(dataSource());
        factoryBean.setPackagesToScan("com.recruitiva.demo.entity");
        factoryBean.setJpaVendorAdapter(jpaVendorAdapter());

        return factoryBean;
    }

    @Bean
    public ValidatorFactory validatorFactory() {
        return new LocalValidatorFactoryBean();
    }

    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        MethodValidationPostProcessor bean = new MethodValidationPostProcessor();

        bean.setValidatorFactory(validatorFactory());

        return bean;
    }

    @Bean
    public SpringLiquibase liquibase() {
        SpringLiquibase bean = new SpringLiquibase();

        bean.setDataSource(dataSource());
        bean.setChangeLog("classpath:db-changelog.xml");

        return bean;
    }

    @Bean
    public MailSender mailSender() {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();

        sender.setHost("smtp.gmail.com");
        sender.setPort(25);
        sender.setUsername(shopEmail);
        sender.setPassword(shopEmailPassword);

        Properties props = new Properties();

        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", mailDebug);

        sender.setJavaMailProperties(props);

        return sender;
    }
}
