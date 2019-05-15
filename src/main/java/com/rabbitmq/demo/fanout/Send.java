package com.rabbitmq.demo.fanout;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.demo.util.ConnectionUtil;

/**
 * @Description 生产者
 * @author zhouzhu
 * @create 2019-05-15 15:56
 */
public class Send {
    private final static String EXCHANGE_NAME="fanout_exchange_test";
    public static void main(String[] args) throws Exception{
        //获取到连接
        Connection connection = ConnectionUtil.getConnection();
        //获取通道
        Channel channel = connection.createChannel();
        //声明exchange，指定类型为fanout
        channel.exchangeDeclare(EXCHANGE_NAME,"fanout");
        String message="Hello everyone";
        //发布消息到exchange
        channel.basicPublish(EXCHANGE_NAME,"",null,message.getBytes());
        System.out.println("[生产者] Sent ‘"+message+"'");
        channel.close();
        connection.close();
    }
}
