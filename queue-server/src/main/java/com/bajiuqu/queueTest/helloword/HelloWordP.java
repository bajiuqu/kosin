package com.bajiuqu.queueTest.helloword;

import com.bajiuqu.config.RabbitMQConfigConnection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/HelloWordC")
public class HelloWordP {

    @Autowired
    private RabbitMQConfigConnection rabbitMQConfigConnection;

    private static String queueName_1 = "hello_word";

    private static StringBuffer stringBuffer = new StringBuffer();

    private static AtomicInteger atomicInteger = null;

    static {
        atomicInteger = new AtomicInteger();
    }

    @RequestMapping("/provider")
    public String provider() {
        Connection connection = rabbitMQConfigConnection.getConnection();
        try {
            Channel channel = connection.createChannel();
            /**
             * 通道绑定对应消息队列
             * 参数1: 队列名称 没有队列时，会去自动创建名为 hello 的队列。
             * 参数2: 是否持久化，只是持久化了队列，队列中未消费的消息并不会持久化
             * 参数3: 是否独占队列，决定此通道是否可以被其他连接所绑定
             * 参数4: 是否自动删除，队列中的消息被消费了之后，队列是否自动删除
             * 参数5: 其他属性
             */
            channel.queueDeclare(queueName_1, false, false, false, null);
            /**
             * 发布消息
             * 参数1: 交换机
             * 参数2: 队列名称
             * 参数3: 发布消息的属性  用来持久化队列中未消费的消息(MessageProperties.PERSISTENT_TEXT_PLAIN)
             * 参数4: 发布的内容
             */
            channel.basicPublish("", queueName_1, null, "Hello Word".getBytes());
            channel.close();
        } catch (IOException | TimeoutException ioException) {
            ioException.printStackTrace();
            return "添加消息失败";
        }
        return "添加消息成功: " + atomicInteger.incrementAndGet() + "条！";
    }

}
