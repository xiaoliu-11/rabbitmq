package com.rabbitmq.workqueue;

import com.utils.RabbitmqUtil;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.AMQP.BasicProperties;
public class Customer1 {

	public static void main(String[] args) throws IOException {
		//获取连接对象
    Connection connection =	RabbitmqUtil.getConnection();
	final Channel channel = connection.createChannel();
	channel.basicQos(1);//每次只能消费一个消息
	channel.queueDeclare("work", true, false, false, null);
	channel.basicConsume("work", false, new DefaultConsumer(channel) {
		
		@Override
		public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
				throws IOException {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("消费者-1："+new String(body));
			 //手动确认
		    channel.basicAck(envelope.getDeliveryTag(), false);
		}
	});
	
	
	}
}
