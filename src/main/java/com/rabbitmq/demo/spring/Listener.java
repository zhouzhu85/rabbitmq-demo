package com.rabbitmq.demo.spring;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author zhouzhu
 * @Description 消费者监听
 * @create 2019-05-16 14:02
 */
@Component
public class Listener {
    /**
     * 一个方法代表一个消费者
     * @param msg
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "spring.test.queue",durable ="true"),
            exchange = @Exchange(
                    value = "spring.test.exchange",
                    ignoreDeclarationExceptions = "true",
                    type = ExchangeTypes.TOPIC
            ),key = {"#.#"}))
    public void listen(String msg){
        System.out.println("接收到消息："+msg);
    }

}
