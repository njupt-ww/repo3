package com.njupt.Operation;

import com.njupt.bean.DeviceParam;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.sql.SQLException;
import java.util.List;

public class ParamTableOperation {

    public static void operation(QueryRunner qr,MqttMessage message){
        String topicMessage = new String(message.getPayload());
        String[] messages = topicMessage.split(",");
        String devid = messages[0];
        String devName  = messages[1];
        String ipAddr = messages[2];
        int devType = Integer.parseInt(messages[3]);
        String comType  = messages[4];
        int duration = Integer.parseInt(messages[5]);
        int devState = Integer.parseInt(messages[6]);
        int dataFiledCount  = Integer.parseInt(messages[7]);
        String dataFiledDesc = messages[8];
        //将数据封装到ParamBean对象中
        DeviceParam deviceParam = new DeviceParam(devid,devName,ipAddr,devType,comType,duration,devState,dataFiledCount,dataFiledDesc);
        try {
            //先判断devId是否存在，如果存在，则执行更新操作，否则，插入数据
            String querySql = "select * from t_device_param";
            List<Object> ids = qr.query(querySql,new ColumnListHandler<Object>("DevId"));
            boolean isExist  = false;
            for(Object id:ids){
                if(id.equals(devid)){
                    isExist = true;
                    break;
                }
            }
            //设备id已经存在
            if(isExist){
                String updateSql = "update t_device_param set ipAddr=?,devType=?,comType=?,duration=?,devState=?,dataFiledCount=?,dataFiledDesc=? where devid=?";
                Object[] params = {deviceParam.getIpAddr(),deviceParam.getDevType(),deviceParam.getComType(),deviceParam.getDuration(),deviceParam.getDevState(),deviceParam.getDataFiledCount(),deviceParam.getDataFiledDesc(),deviceParam.getDevid()};
                int row = qr.update(updateSql, params);
                //System.out.println("update t_device_param successfully");
            }else{
                //设备id不存在,不是合法设备，不做处理，直接丢弃
            }
        } catch (SQLException e) {
            e.printStackTrace();
            //System.out.println("update t_device_param failed");
        }
    }


}
