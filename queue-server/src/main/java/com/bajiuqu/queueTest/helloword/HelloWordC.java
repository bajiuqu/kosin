package com.bajiuqu.queueTest.helloword;

import com.bajiuqu.config.RabbitMQConfigConnection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DeliverCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/HelloWordC")
public class HelloWordC {

    @Autowired
    private RabbitMQConfigConnection rabbitMQConfigConnection;

    private static String queueName_1 = "hello_word";

    @RequestMapping("/consumer")
    public void consumer() throws IOException {

        Connection connection = rabbitMQConfigConnection.getConnection();

        Channel channel = connection.createChannel();
        channel.queueDeclare(queueName_1, false, false, false, null);

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String body = new String(delivery.getBody(), "UTF-8");
            System.out.println("接收到消息：【" + body + "】");
        };
        channel.basicConsume(queueName_1, true, deliverCallback, consumerTag -> {});
    }

}
