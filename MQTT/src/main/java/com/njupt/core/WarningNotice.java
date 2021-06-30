package com.njupt.core;


import com.njupt.Utils.JDBCUtil;
import com.njupt.Utils.TopicUtil;
import com.njupt.bean.AlarmHistory;
import com.njupt.bean.ChaZuoData;
import com.njupt.bean.TemHumData;
import com.njupt.bean.DeviceParam;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WarningNotice {


    private static QueryRunner qr = new QueryRunner(JDBCUtil.getDataSource());

    //定义list集合，存放历史报警设备devId
    private static List<String> historyDevid = new ArrayList<String>();

    /**
     * 判断传感器温度是否超过阈值，超过指定阈值则发布主题
     * @param
     */
    public static void warningOfSensor(TemHumData temHumData){
        float threshold = 27;
        publishTopic(temHumData,threshold);
    }

    /**
     * 判断插座温度是否过高，超过指定阈值则发布主题
     * @param
     */
    public static void warningOfChaZuo(ChaZuoData chaZuoData){
        float threshold = 25;
        publishTopic(chaZuoData,threshold);
    }

    /**
     * 获取报警器对应的devId，设置为主题
     * @return
     */
    public static String getTopic(){
        //将报警器的devId作为主题，获取报警器的devId
        String topic = "";
        //查询报警器对应的devId
        try{
            String sql = "select * from t_device_param where DevType=3";
            DeviceParam dev = qr.query(sql,new BeanHandler<DeviceParam>(DeviceParam.class));
            //System.out.print(dev);
            topic = dev.getDevid();
            //System.out.print(topic);

        } catch(SQLException e){
            e.printStackTrace();
        }
        return topic;
    }

    /**
     * 获取/ 主题
     * @return
     */
    public static String getPublishTopic(){
        // 获取/device/alarm主题
        List<List<String>> publishTopics = TopicUtil.getPublishTopics();
        String publishTopic = publishTopics.get(0).get(0);
        return publishTopic;
    }


    public static void publishTopic(Object obj,float threshold){
        int qos = 0;
        String topic = WarningNotice.getTopic();
        // 获取/device/alarm主题
        String publishTopic = WarningNotice.getPublishTopic();
        float temperature = 0.0f;
        Date time = new Date();
        String devid = "";
        String devname = "";
        String fieldname = "温度";
        String fieldvalue = "";
        String message = "";
        AlarmHistory alarmHistory = null;
        if(obj instanceof TemHumData){
            temperature = ((TemHumData) obj).getTemperature();
            devid = ((TemHumData) obj).getDevid();
            try{
                String querySql = "select * from t_device_param where devid =? ";
                //System.out.println(devid);
                Object[] params = {devid};
                Object dev = qr.query(querySql,new BeanHandler<DeviceParam>(DeviceParam.class),params);
                //System.out.println(((DeviceParam)dev));
                devname = ((DeviceParam)dev).getDevName();
                //System.out.println(devname);
                fieldvalue =Float.toString(((TemHumData) obj).getTemperature());
                message = devname+"的"+fieldname+"过高("+fieldvalue+"℃)"+"，请及时处理！";
            }catch(Exception e){
                e.printStackTrace();
            }

        }else if(obj instanceof ChaZuoData){
            temperature = ((ChaZuoData) obj).getTemperature();
            devid = ((ChaZuoData) obj).getDevid();
            try{
                String querySql = "select * from t_device_param where devid = ?";
                Object[] params = {devid};
                Object dev = qr.query(querySql,new BeanHandler<DeviceParam>(DeviceParam.class),params);
                devname = ((DeviceParam)dev).getDevName();
            }catch(Exception e){
                e.printStackTrace();
            }
            fieldvalue =Float.toString(((ChaZuoData) obj).getTemperature());
            message = devname+"的"+fieldname+"过高("+fieldvalue+"℃)"+"，请及时处理！";
        }
        if(temperature>threshold){
            //判断历史记录中是否存在devId
            if(!historyDevid.contains(devid)){
                //如果不存在就插入当前设备id,并且发送报警信息
                historyDevid.add(devid);
                MqttService.publish(topic,qos,"on");
                //生成历史数据记录
                alarmHistory = new AlarmHistory(time,devid,devname,fieldname,fieldvalue,message);
                //向前端页面发送主题，主题名为/device/alarm
                MqttService.publish(publishTopic,qos, alarmHistory.getDevid()+","+ alarmHistory.getDevname()+","+ alarmHistory.getFieldname()+","+ alarmHistory.getFieldvalue()+","+ alarmHistory.getMessage());
                //插入历史数据
                insertHistory(alarmHistory);
            }else{
                //如果存在什么都不做
            }
        }else{
            if(historyDevid.contains(devid)){
                historyDevid.remove(devid);
            }
            //没有报警设备，发送主题，关闭报警
            if(historyDevid.isEmpty()){
                MqttService.publish(topic,qos,"off");
            }
        }
    }

    /**
     * 将历史记录插入t_alarm_history表
     * @param alarmHistory 历史记录
     */
    public static void insertHistory(AlarmHistory alarmHistory){
        String sql = "insert into t_alarm_history(time,devid,devname,fieldname,fieldvalue,message) values(?,?,?,?,?,?)";
        try {
            Object[] params = {alarmHistory.getTime(), alarmHistory.getDevid(), alarmHistory.getDevname(), alarmHistory.getFieldname(), alarmHistory.getFieldvalue(), alarmHistory.getMessage()};
            int row = qr.update(sql, params);
            //System.out.println("insert t_alarm_history successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            //System.out.println("insert t_alarm_history failed!");
        }

    }

}
