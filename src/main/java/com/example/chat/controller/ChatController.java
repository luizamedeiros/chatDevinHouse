package com.example.chat.controller;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.chat.model.dto.ChatMassage;
import com.example.chat.model.dto.CreateChatQueueDTO;

@RestController
@RequestMapping("/chat")
public class ChatController {

	@Autowired
	private RabbitAdmin rabbitAdmin;
	
	@Autowired
	private RabbitTemplate rabbitTemplate;

	
	@PostMapping
	public ResponseEntity<?> send(@RequestBody ChatMassage message){
		message.setSender("Luiza");
		message.setTarget("devin.chat.");
		rabbitTemplate.convertAndSend("amq.topic", "devin.chat.*", message);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	
	@PostMapping("/queue")
	public ResponseEntity<?> createQueue(@RequestBody CreateChatQueueDTO createQueue){
		Queue queue = new Queue(createQueue.getName(), false, false, false, null);
		rabbitAdmin.declareQueue(queue);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
}
