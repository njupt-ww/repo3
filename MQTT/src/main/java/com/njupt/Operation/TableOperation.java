package com.njupt.Operation;

import com.njupt.Utils.JDBCUtil;
import com.njupt.Utils.TopicUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import java.util.List;

public class TableOperation {

    private static QueryRunner qr = new QueryRunner(JDBCUtil.getDataSource());

    public static void operation(String topic, MqttMessage message){

        //获取订阅的所有主题
        List<List<String>> topics = TopicUtil.getSubscribeTopics();
        //获取订阅的主题0
        String topic01 = topics.get(0).get(0);
        //System.out.println("topic01 : " + topic01);
        //获取订阅的主题1
        String topic02 = topics.get(1).get(0);
        //获取订阅的主题2
        String topic03 = topics.get(2).get(0);
        //获取订阅的主题3
        String topic04 = topics.get(3).get(0);
        //System.out.println(topic04);



        //如果是主题0操作t_device_param表
        if(topic.equals(topic01)) {
            ParamTableOperation.operation(qr,message);
        }else if(topic.equals(topic02)) {
            //如果是主题1操作t_device_data_1表
            DataTableOperation.operation(qr,message);
        }else if(topic.equals(topic03)){
            //如果是主题2操作t_device_data_2表
            ChaZuoOperation.operation(qr,message);
        }else if(topic.equals(topic04)){
            //如果是主题3操作t_route_history表
            RouteTableOperation.operation(qr,message);
        }

    }

}
