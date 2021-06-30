package com.njupt.core;

import org.eclipse.paho.client.mqttv3.MqttMessage;

import com.njupt.Utils.ParseMessage;

public class DoParse extends Thread{
	
	private String topic;
	private MqttMessage message;
	public DoParse(String topic,MqttMessage message) {
		this.topic = topic;
		this.message = message;
	}
	
	@Override
	public void run() {
		ParseMessage.parseMessage(topic, message);		
	}
		   
		
}


