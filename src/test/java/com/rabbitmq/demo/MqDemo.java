package com.rabbitmq.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zhouzhu
 * @Description
 * @create 2019-05-16 14:13
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class MqDemo {
    @Autowired
    private AmqpTemplate amqpTemplate;
    @Test
    public void testSend()throws InterruptedException{
        String msg="hello,Spring boot amqp";
        amqpTemplate.convertAndSend("spring.test.exchange","a.b",msg);
        //等待10秒再结束
        Thread.sleep(10000);
    }
}
