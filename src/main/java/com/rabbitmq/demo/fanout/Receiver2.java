package com.rabbitmq.demo.fanout;

import com.rabbitmq.client.*;
import com.rabbitmq.demo.util.ConnectionUtil;

import java.io.IOException;

/**
 * @author zhouzhu
 * @Description
 * @create 2019-05-15 16:15
 */
public class Receiver2 {
    private final static String QUEUE_NAME="fanout_exchange_queue_2";
    private final static String EXCHANG_NAME="fanout_exchange_test";
    public static void main(String[] args) throws Exception{
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        channel.queueBind(QUEUE_NAME,EXCHANG_NAME,"");
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                // body 即消息体
                String msg = new String(body);
                System.out.println(" [消费者2] received : " + msg + "!");
            }
        };
        channel.basicConsume(QUEUE_NAME,true,consumer);
    }
}
