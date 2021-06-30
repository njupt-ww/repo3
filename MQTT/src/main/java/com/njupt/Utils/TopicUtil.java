package com.njupt.Utils;

import com.njupt.test.MqttSubscribe;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class TopicUtil {
    //读取配置文件
    public static Properties pros = null;

    private static String path;


    static {
        try {

            path = MqttSubscribe.path1;

            //ClassLoader c1 = TopicUtil.class.getClassLoader();
//            InputStream in = c1.getResourceAsStream("topic.properties");
            //InputStream in = TopicUtil.class.getClassLoader().getResourceAsStream(path);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            //InputStream InputStream = new BufferedInputStream(new FileInputStream(new File(path)));
            pros = new Properties();
            pros.load(bufferedReader);
        } catch (IOException e) {
            throw new ExceptionInInitializerError("读取配置文件出错");
//            e.printStackTrace();
        }
    }

    /**
     * 获取broker地址
     * @return
     */
    public static String getBroker(){
        String broker = pros.getProperty("broker");
        return broker;
    }

    /**
     * 获取客户端id
     * @return
     */
    public static String getClientId(){
        return pros.getProperty("ClientId");
    }


    /**
     * 获取主题
     * @return 主题列表
     */
    public static List<List<String>> getPublishTopics(){

        List<List<String>> topics = new ArrayList<List<String>>();
        for(int i=0;i<1;i++){
            List<String> topic = new ArrayList<String>();
            String publishTopic = pros.getProperty("publishTopic"+i);
            topic.add(publishTopic);
            String QosOfPublishTopic = pros.getProperty("QosOfPublishTopic"+i);
            topic.add(QosOfPublishTopic);
//            String publishTopicMessage = pros.getProperty("publishTopicMessage"+i);
//            topic.add(publishTopicMessage);
            topics.add(topic);
        }

        return topics;
    }

    public static List<List<String>> getSubscribeTopics(){
        List<List<String>> topics = new ArrayList<List<String>>();
        for(int i=0;i<4;i++){
            List<String> topic = new ArrayList<String>();
            String subscribeTopic = pros.getProperty("subscribeTopic"+i);
            topic.add(subscribeTopic);
            String QosOfSubscribeTopic = pros.getProperty("QosOfSubscribeTopic"+i);
            topic.add(QosOfSubscribeTopic);
            topics.add(topic);
        }
        return topics;
    }
}
