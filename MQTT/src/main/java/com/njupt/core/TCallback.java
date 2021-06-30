package com.njupt.core;


import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;


 

/**  
 * 回调类
 */    
public class TCallback implements MqttCallback {  
	
    public void connectionLost(Throwable cause) {  
        // 连接丢失，可以做重连
        System.out.println("连接丢失");
        System.out.println(cause);
    }  

    public void deliveryComplete(IMqttDeliveryToken token) {
        System.out.println("deliveryComplete---------" + token.isComplete());  
    }

    public void messageArrived(String topic, MqttMessage message) throws Exception {
        // subscribe完成，消息会在这里
    	
        System.out.println("订阅主题 : " + topic);
        System.out.println("订阅qos: " + message.getQos());
        System.out.println("订阅消息内容: " + new String(message.getPayload()));

        //每接收到一个主题，开启一个线程
        DoParse doParse = new DoParse(topic,message);
        new Thread(doParse).start();
      
    } 
     
}
