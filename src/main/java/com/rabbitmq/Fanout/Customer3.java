package com.rabbitmq.Fanout;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.utils.RabbitmqUtil;
public class Customer3 {

	public static void main(String[] args) throws IOException {
		//获取连接对象
		Connection connection = RabbitmqUtil.getConnection();
	    Channel channel = 	connection.createChannel();
	     //通道绑定交换机
	    channel.exchangeDeclare("logs", "fanout");
	     //这里只需要一个临时队列就可以了额。
	    String queueName = channel.queueDeclare().getQueue();
	    // 绑定交换机和队列
	    channel.queueBind(queueName, "logs", "");
	    //消费消息
	    channel.basicConsume(queueName, true, new DefaultConsumer(channel) 
	    {
	    	@Override
	    	public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
	    			throws IOException {
	    		    System.out.println("消费者3："+new String(body));
	    	}
	    	
	    });
	
	}
	
}
