package com.rabbitmq.demo.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.demo.util.ConnectionUtil;

/**
 * @author zhouzhu
 * @Description
 * @create 2019-05-15 17:52
 */
public class Send {
    private final static String EXCHANG_NAME="topic_exchange_test";
    public static void main(String[] args) throws Exception{
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        // 声明exchange，指定类型为topic
        channel.exchangeDeclare(EXCHANG_NAME,"topic");
        String message="商品新增了，id=1001";
        // 发送消息，并且指定routing key 为：insert ,代表新增商品
        channel.basicPublish(EXCHANG_NAME,"item.insert",null,message.getBytes());
        System.out.println("[商品服务：] Sent '"+message+"'");
        channel.close();
        connection.close();
    }
}
