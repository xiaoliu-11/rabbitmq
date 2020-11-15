package com.rabbitmq.Fanout;

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
			    //将通道声明指定的交换机
				//参数1：交换机的名称
				//参数2：交换机的类型。fanout的类型，广播类型。
				channel.exchangeDeclare("logs", "fanout");
				//发送消息
				channel.basicPublish("logs", "", null, "fanout type message".getBytes());
				//释放资源
				RabbitmqUtil.closeConnAndChannel(channel, connection);
	}
	
}
