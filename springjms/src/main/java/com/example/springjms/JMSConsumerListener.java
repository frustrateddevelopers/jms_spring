package com.example.springjms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class JMSConsumerListener {
	
	private String destination;
	
	@JmsListener(destination = "")
	public void recieveMessage(Message mesg){
		String messageData = null;
		System.out.println("Received message " + mesg);
		String response = null;
		if(mesg instanceof TextMessage) {
			TextMessage textMessage = (TextMessage)mesg;
			try {
				messageData = textMessage.getText();
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(messageData);
		}
	}

}
