package com.njupt.test;


import com.mysql.jdbc.StringUtils;
import com.njupt.Utils.JDBCUtil;
import com.njupt.Utils.ParamUtil;
import com.njupt.Utils.TopicUtil;
import com.njupt.core.MqttService;


import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class MqttSubscribeTest {



	public static void main(String[] args) {

//		String path1 = args[0];
//		String path2 = args[1];

		String path1 = "F:/test/topic.properties";
 		String path2 = "F:/test/db.properties";




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




	}



}
