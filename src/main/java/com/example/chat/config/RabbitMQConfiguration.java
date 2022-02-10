package com.example.chat.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

	
	@Bean
	public RabbitAdmin getRabbitAdmin(ConnectionFactory connectionFactory) {
		return new RabbitAdmin(connectionFactory);
	}
	
	@Bean
	public MessageConverter jsonMessageConverter() {
	    return new Jackson2JsonMessageConverter();
	}
	
	@Bean
	public Queue queue() {
		return new Queue("devin.chat.luiza.queue", false);
	}

	@Bean
	Binding binding() {
		return new Binding("devin.chat.luiza.queue", 
				Binding.DestinationType.QUEUE, 
				"amq.topic", "devin.chat.*", null);
	}

}
