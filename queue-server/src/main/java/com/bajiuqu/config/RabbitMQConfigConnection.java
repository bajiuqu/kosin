package com.bajiuqu.config;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class RabbitMQConfigConnection {

    @Autowired
    private PropertiesConfig propertiesConfig;

    private static ConnectionFactory connectionFactory;

    static {
        connectionFactory = new ConnectionFactory();
    }

    @Bean
    public Connection getConnection() {
        connectionFactory.setHost(propertiesConfig.getHost());
        connectionFactory.setPort(propertiesConfig.getPort());
        connectionFactory.setUsername(propertiesConfig.getUsername());
        connectionFactory.setPassword(propertiesConfig.getPassword());
        connectionFactory.setVirtualHost(propertiesConfig.getVirtualHost());
        connectionFactory.setConnectionTimeout(1000 * 5);
        Connection connection = null;
        try {
            connection = connectionFactory.newConnection();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("RabbitMQ 创建链接失败, " + e.getMessage());
        }
        return connection;
    }

}
