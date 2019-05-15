package com.rabbitmq.demo.simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.demo.util.ConnectionUtil;

/**
 * @author zhouzhu
 * 消息发送者
 */
public class Send {
    private final static String QUEUE_NAME="simple_queue";

    public static void main(String[] args)throws Exception{
        //获取连接
        Connection connection = ConnectionUtil.getConnection();
        //从连接中创建通道，使用通道才能完成消息相关的操作
        Channel channel = connection.createChannel();
        //创建队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        //消息内容
        String message="hello world";
        //向指定的队列中发送消息
        channel.basicPublish("",QUEUE_NAME,null,message.getBytes());
        System.out.println("发送消息："+message);
        //关闭通道和连接
        channel.close();
        connection.close();
    }
}
