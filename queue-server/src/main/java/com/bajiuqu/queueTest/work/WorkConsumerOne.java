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
@RequestMapping("/workConsumerOne")
public class WorkConsumerOne {

    @Autowired
    private RabbitMQConfigConnection rabbitMQConfigConnection;

    private static AtomicInteger atomicInteger = null;

    private String queueName_2 = "task_queue";

    @RequestMapping("/workConsumerOne")
    public void workConsumerOne() throws IOException {
        Connection connection = rabbitMQConfigConnection.getConnection();
        // 创建通道
        Channel channel = connection.createChannel();
        // 消费消息之前确定队列存在
        channel.queueDeclare(queueName_2, true, false, false, null);

        channel.basicQos(1);
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String body = new String(delivery.getBody(), "UTF-8");
            System.err.println("消费消息One：【" + body + "】");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                // 手动确认
                channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false); // 需要将 basicConsume()；第二个参数设置成false
            }

        };
        /**
         * 参数1：要消费的队列名称
         * 参数2：是否自动确认：true-> 只要消费者拿到消息，不管是否消费成功，都是消费成功。
         * 参数3：消息消费成功的回调函数
         * 参数4：当消费者被取消时回调
         */
        channel.basicConsume(queueName_2, false, deliverCallback, consumerTag -> {
            System.out.println(consumerTag);
        });
    }

}
