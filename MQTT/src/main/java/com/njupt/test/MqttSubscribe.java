package com.njupt.test;



import com.njupt.Utils.TopicUtil;
import com.njupt.core.MqttService;

import java.util.List;

public class MqttSubscribe {

	public static String path1 ;
	public static String path2 ;

	public static void main(String[] args) {
		try{
			path1 = args[0];
			path2 = args[1];
		}catch(Exception e){
			System.out.println("输入参数有误：格式为：java -jar xxx.jar path1 path2");
			e.printStackTrace();
		}

		//获取订阅主题集合
		List<List<String>> topics = TopicUtil.getSubscribeTopics();
		//获取订阅主题名称
		String[] topic = new String[4];
		int[] qos = new int[4];
		for(int i=0;i<4;i++){
			//System.out.println(topics.get(i).get(0));
			topic[i] =  topics.get(i).get(0);
			qos[i] = Integer.parseInt(topics.get(i).get(1));
			//System.out.println(topics.get(i).get(1));
		}
		MqttService subscribe = new MqttService();
		subscribe.subscribe(topic,qos);


		//xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
		//和客户端建立连接，获取消息内容

		//publishTopic();
	}

	public static void publishTopic(){
		boolean[] flag = new boolean[2];
		flag[0] = true;
		if(flag[0]){
			MqttService.publish("/application/type1",0,"视频");
		}
		if(flag[1]){
			MqttService.publish("/application/type2",0,"视频");
		}
	}



}
