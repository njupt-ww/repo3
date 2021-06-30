package com.njupt.Utils;

import com.njupt.Operation.TableOperation;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class ParseMessage {
	public static void parseMessage(String topic, MqttMessage message){
		TableOperation.operation(topic,message);
    }
}
