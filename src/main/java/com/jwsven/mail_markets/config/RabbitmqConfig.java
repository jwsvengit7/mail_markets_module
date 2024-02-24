package com.jwsven.mail_markets.config;



import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

import static com.jwsven.mail_markets.messanging.queue.Queue.*;


@Configuration
public class RabbitmqConfig {
    private  Map<String, Object> arguments = new HashMap<>(3);

    @Bean
    public Queue queue(){
        return new Queue(QUEUENAME,DURABILITY);
    }
    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(EXCHANGE);
    }
    @Bean
    public Binding binding(Queue queue ,DirectExchange directExchange){
        return BindingBuilder.bind(queue).to(directExchange).with(ROUTINGKEY);
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        return rabbitTemplate;
    }
    @Bean
    public Queue retryQueue() {
        arguments.put("x-dead-letter-exchange", EXCHANGE);
        arguments.put("x-dead-letter-routing-key", ROUTINGKEY);
        arguments.put("x-message-ttl", 5000);
        return queue();
    }


}
