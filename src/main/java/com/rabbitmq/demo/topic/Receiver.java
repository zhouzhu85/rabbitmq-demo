package com.rabbitmq.demo.topic;

import com.rabbitmq.client.*;
import com.rabbitmq.demo.util.ConnectionUtil;

import java.io.IOException;

/**
 * @author zhouzhu
 * @Description
 * @create 2019-05-15 17:56
 */
public class Receiver {
    private final static String QUEUE_NAME = "topic_exchange_queue_1";
    private final static String EXCHANGE_NAME = "topic_exchange_test";
    public static void main(String[] args) throws Exception{
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        //第二参数是开启交换机持久化
        channel.queueDeclare(QUEUE_NAME,true,false,false,null);

        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"item.insert");
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"item.delete");
        DefaultConsumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                // body 即消息体
                String msg = new String(body);
                System.out.println(" [消费者1] received : " + msg + "!");
            }
        };
        channel.basicConsume(QUEUE_NAME,true,consumer);
    }
}
