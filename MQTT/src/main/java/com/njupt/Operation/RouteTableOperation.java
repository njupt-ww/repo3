package com.njupt.Operation;

import com.njupt.bean.RouteHistory;
import org.apache.commons.dbutils.QueryRunner;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RouteTableOperation {

    public static void operation(QueryRunner qr, MqttMessage message){

        String topicMessage = new String(message.getPayload());

        String[] messages = topicMessage.split(",");

        //设置日期格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //获取当前时间的字符串类型
        String date = sdf.format(new Date());
        //sdf将字符串转化成java.util.Date
        Date time=null;
        try {
            time = sdf.parse(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String devid = messages[0];
        int devtype = Integer.parseInt(messages[1]);
        String agentid = messages[2];
        String controllerid = messages[3];



        RouteHistory routeHistory = new RouteHistory(time,devid,devtype,agentid,controllerid);

        String sql = "insert into t_route_history(time,devid,devtype,agentid,controllerid) values(?,?,?,?,?)";

        try {
            Object[] params = {routeHistory.getTime(), routeHistory.getDevid(), routeHistory.getDevType(), routeHistory.getAgentid(), routeHistory.getControllerid()};
            int row = qr.update(sql, params);
            //System.out.println("insert t_device_data_1 successfully");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("insert t_device_data_1 failed");
        }
    }
}
