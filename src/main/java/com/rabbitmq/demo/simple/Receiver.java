package com.rabbitmq.demo.simple;

import com.rabbitmq.client.*;
import com.rabbitmq.demo.util.ConnectionUtil;

import java.io.IOException;
import java.io.StringReader;

/**
 * @author zhouzhu
 * 消息接收者
 */
public class Receiver {
    private final static String QUEUE_NAME="simple_queue";
    public static void main(String[] args) throws Exception{
        //获取连接
        Connection connection = ConnectionUtil.getConnection();
        //获取通道
        Channel channel = connection.createChannel();
        //声明队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        //定义队列的消费者
        DefaultConsumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg=new String(body);
                System.out.println("[接收者]："+msg);
            }
        };
        //监听队列、自动ack
        channel.basicConsume(QUEUE_NAME,true,consumer);
    }
}
