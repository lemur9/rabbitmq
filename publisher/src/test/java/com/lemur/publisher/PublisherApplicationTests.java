package com.lemur.publisher;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class PublisherApplicationTests {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testSendMessage2SimpleQueue() throws Exception {
        String queueName = "simple.queue";
        String message = "hello, spring amqp";
        rabbitTemplate.convertAndSend(queueName, message);
    }

    @Test
    public void testSendMessage2WorkQueue() throws Exception {
        String queueName = "simple.queue";
        String message = "hello, spring amqp";
        for (int i = 0; i < 50; i++) {
            rabbitTemplate.convertAndSend(queueName, message + i);
            Thread.sleep(20);
        }
    }

    @Test
    public void testSendMessage2FanoutExchange() throws Exception {
        String exchangeName = "lemur.fanout";
        String message = "hello, every one";
        rabbitTemplate.convertAndSend(exchangeName, "", message);
    }

    @Test
    public void testSendMessage2DirectExchange1() throws Exception {
        String exchangeName = "lemur.direct";
        String message = "hello, blue";
        rabbitTemplate.convertAndSend(exchangeName, "blue", message);
    }

    @Test
    public void testSendMessage2DirectExchange2() throws Exception {
        String exchangeName = "lemur.direct";
        String message = "hello, yellow";
        rabbitTemplate.convertAndSend(exchangeName, "yellow", message);
    }

    @Test
    public void testSendMessage2DirectExchange3() throws Exception {
        String exchangeName = "lemur.direct";
        String message = "hello, red";
        rabbitTemplate.convertAndSend(exchangeName, "red", message);
    }

    @Test
    void contextLoads() {
    }

}
