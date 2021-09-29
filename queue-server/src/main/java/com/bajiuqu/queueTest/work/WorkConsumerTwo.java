package com.bajiuqu.queueTest.work;

import com.bajiuqu.config.RabbitMQConfigConnection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DeliverCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/WorkConsumerTwo")
public class WorkConsumerTwo {

    @Autowired
    private RabbitMQConfigConnection rabbitMQConfigConnection;

    private static AtomicInteger atomicInteger = null;

    private String queueName_2 = "task_queue";

    @RequestMapping("/workConsumerTwo")
    public void workConsumerTwo() throws IOException {
        Connection connection = rabbitMQConfigConnection.getConnection();
        // 创建通道
        Channel channel = connection.createChannel();
        // 每次只消费一条消息
        channel.basicQos(1);
        /**
         * 参数1：队列名称
         * 参数2：是否持久化，持久化只是持久化队，队列中的消息不会持久化
         * 参数3：是否独占队列
         * 参数4：消息消费完成后，是否自动删除消息
         */
        channel.queueDeclare(queueName_2, true, false, false, null);

        DeliverCallback deliverCallback = (consumertag, delivery) -> {
            String body = new String(delivery.getBody(), "UTF-8");
            System.out.println("消费消息Two：【" + body + "】");
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        };
        channel.basicConsume(queueName_2, false, deliverCallback, consumerTag -> {});
    }

}
