package com.njupt.Operation;

import com.njupt.bean.ChaZuoData;
import com.njupt.core.WarningNotice;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class ChaZuoOperation {

    public static void operation(QueryRunner qr, MqttMessage message){

        String topicMessage = new String(message.getPayload());
        String[] messages = topicMessage.split(",");

        String devid = messages[0];
        String date = messages[1];
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //sdf将字符串转化成java.util.Date
        Date time=null;
        try {
            time = sdf.parse(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String ipAddr = messages[2];
        float temperature = Float.parseFloat(messages[3]);
        float humidity = Float.parseFloat(messages[4]);
        float current = Float.parseFloat(messages[5]);
        float voltage = Float.parseFloat(messages[6]);
        int onOff = Integer.parseInt(messages[7]);
        ChaZuoData chaZuoData = new ChaZuoData(devid,time,ipAddr,temperature,humidity,current,voltage,onOff);


        try {
            //向t_device_data_2表中插入数据
            String sql01 = "insert into t_device_data_2(devId,time,ipAddr,temperature,humidity,current,voltage,onOff) values(?,?,?,?,?,?,?,?)";
            Object[] params01 = {chaZuoData.getDevid(), chaZuoData.getTime(), chaZuoData.getIpAddr(), chaZuoData.getTemperature(), chaZuoData.getHumidity(), chaZuoData.getCurrent(), chaZuoData.getVoltage(), chaZuoData.getOnOff()};
            int row = qr.update(sql01, params01);

            //先判断devId是否存在，如果存在，则执行更新操作，否则，插入数据
            String querySql = "select * from data_2_memory";
            List<Object> ids = qr.query(querySql,new ColumnListHandler<Object>("DevId"));
            boolean isExist  = false;
            for(Object id:ids){
                if(id.equals(devid)){
                    isExist = true;
                    break;
                }
            }
            //向 data_2_memory 内存中插入最新数据
            if(isExist){
                //devId已经存在,更新操作
                String sql02 = "update data_2_memory set Time=?,IpAddr=?,Temperature=?,Humidity=?,current=?,voltage=?,onOff=?";
                Object[] params02 = { chaZuoData.getTime(), chaZuoData.getIpAddr(), chaZuoData.getTemperature(), chaZuoData.getHumidity(), chaZuoData.getCurrent(), chaZuoData.getVoltage(), chaZuoData.getOnOff()};
                int row02 = qr.update(sql02, params02);
            }else{
                //devId不存在,插入操作
                String sql03 = "insert into data_2_memory(DevId,Time,IpAddr,Temperature,Humidity,current,voltage,onOff) values(?,?,?,?,?,?,?,?)";
                Object[] params03 = {chaZuoData.getDevid(), chaZuoData.getTime(), chaZuoData.getIpAddr(), chaZuoData.getTemperature(), chaZuoData.getHumidity(), chaZuoData.getCurrent(), chaZuoData.getVoltage(), chaZuoData.getOnOff()};
                int row03 = qr.update(sql03, params03);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            //System.out.println("insert t_device_data_2 failed");
        }
        //报警检查
       WarningNotice.warningOfChaZuo(chaZuoData);
    }
}
