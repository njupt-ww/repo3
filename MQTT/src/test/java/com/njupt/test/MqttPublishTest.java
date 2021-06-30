package com.njupt.test;

import com.njupt.core.MqttService;

public class MqttPublishTest {

	public static void main(String[] args) {
		MqttService publish = new MqttService();
		publish.publish("t_device_param",0,"b827eb432255/Node4/192.168.1.2/1/WIFI/5/0/0/Node4: rec and send data of tem3 and hum3.");

	}

}
