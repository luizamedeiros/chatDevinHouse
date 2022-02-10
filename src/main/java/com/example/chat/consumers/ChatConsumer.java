package com.example.chat.consumers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.example.chat.model.dto.ChatMassage;

@Component
public class ChatConsumer {

	@RabbitListener(queues= "devin.chat.luiza.queue")
	public void getGugaMessage(ChatMassage msg) {
		System.out.println(msg);
	}
}
