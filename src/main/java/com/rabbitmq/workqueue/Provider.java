package com.rabbitmq.workqueue;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import com.utils.RabbitmqUtil;

public class Provider {
	
	public static void main(String[] args) throws IOException {
	    //获取连接对象
		Connection connection = RabbitmqUtil.getConnection();
	    //获取通道对象
		Channel channel = connection.createChannel();
	    //声明队列
		channel.queueDeclare("work", true, false, false, null);
		for(int i = 0; i < 20; i++) {
			//生产消息
			channel.basicPublish("", "work", null, (i+"hello work").getBytes());
			
		}
		//关闭资源
		RabbitmqUtil.closeConnAndChannel(channel, connection);
	
	}

}
