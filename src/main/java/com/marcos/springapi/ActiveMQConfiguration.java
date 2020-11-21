package com.marcos.springapi;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;

import javax.jms.Queue;

@EnableJms
@Configuration
public class ActiveMQConfiguration {
    @Bean
    public Queue createQueue(){
        return new ActiveMQQueue("local.inmemory.queue");
    }
}