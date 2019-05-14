package com.rabbitmq.demo.util;


import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author zhouzhu
 * rabbitmq连接工具类
 */
public class ConnectionUtil {
    public static Connection getConnection() throws Exception{
        //创建连接工厂类
        ConnectionFactory connectionFactory=new ConnectionFactory();
        //设置服务地址
        connectionFactory.setHost("192.168.56.200");
        //设置端口
        connectionFactory.setPort(5672);
        //设置账号信息、用户名、密码、vhost
        connectionFactory.setVirtualHost("/");
        connectionFactory.setUsername("leyou");
        connectionFactory.setPassword("123456");
        //通过工厂类获取连接
        Connection connection = connectionFactory.newConnection();
        return connection;
    }
}
