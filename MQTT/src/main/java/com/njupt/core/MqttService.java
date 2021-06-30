package com.njupt.core;


import com.njupt.Utils.TopicUtil;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;



public class MqttService {

    //初始化连接，获取连接客户端
    private static MqttClient client;


    static {
        String broker = TopicUtil.getBroker();
        String clientId = TopicUtil.getClientId();
        try{
            //新建客户端
            client = new MqttClient(broker, clientId, new MemoryPersistence());
            //连接设置
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            //设置回调
            client.setCallback(new TCallback());
            client.connect(connOpts);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    //发布主题
    public static void publish(String topic,int qos,String payload){

        try{
            //设置消息的qos和内容
            MqttMessage message = new MqttMessage();
            message.setQos(qos);
            message.setPayload(payload.getBytes());
            //发布主题
            client.publish(topic,message);
            System.out.println("topic:"+topic+"send successfully");
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("topic:"+topic+"send failed");
        }
    }

    //订阅主题
    public static void subscribe(String[] topic,int[] qos) {
        try {
            //订阅主题
            client.subscribe(topic,qos);
        } catch(MqttException e) {
            e.printStackTrace();
        }

    }
}
