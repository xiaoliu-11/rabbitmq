package com.rabbitmq.workqueue;

import com.utils.RabbitmqUtil;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.AMQP.BasicProperties;
public class Customer2 {

	public static void main(String[] args) throws IOException {
		//获取连接对象
    Connection connection =	RabbitmqUtil.getConnection();
	final Channel channel = connection.createChannel();
	channel.basicQos(1);
	channel.queueDeclare("work", true, false, false, null);
	channel.basicConsume("work", false, new DefaultConsumer(channel) {
		
		@Override
		public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
				throws IOException {
			    System.out.println("消费者-2："+new String(body));
			    //手动确认
			    //参数1：确认队列中的哪个具体的消息
			    //参数2：是否开始多个消息同时确认
			    channel.basicAck(envelope.getDeliveryTag(), false);
		
		}
	});
	
	
	}
}
