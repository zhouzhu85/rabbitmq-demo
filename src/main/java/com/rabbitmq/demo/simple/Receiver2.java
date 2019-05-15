package com.rabbitmq.demo.simple;

import com.rabbitmq.client.*;
import com.rabbitmq.demo.util.ConnectionUtil;

import java.io.IOException;

/**
 * @author zhouzhu
 * 手动操作ack
 */
public class Receiver2 {
    public final static String QUEUE_NAME="simple_queue";
    public static void main(String[] args)throws Exception{
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        DefaultConsumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg=new String(body);
                System.out.println("[消费者2]："+msg);
                //手动ack
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        };
        //监听队列
        channel.basicConsume(QUEUE_NAME,false,consumer);
    }
}
