package com.nexon.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class GreetingController {

	@Autowired
	private SimpMessagingTemplate template;


    @MessageMapping("/hello/{number}")
    public void greeting(@DestinationVariable(value = "number") int number, HelloMessage message) throws Exception {
    	System.out.println(number);
    	template.convertAndSend("/topic/greetings/" + number, new Greeting("THIS IS SUBSCRIBE!"));
    }

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new Greeting("Hello, " + message.getName() + "!");
    }
    
}