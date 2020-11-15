package com.rabbitmq.helloworld;

import com.rabbitmq.client.*;
import com.rabbitmq.client.AMQP.BasicProperties;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer {
	private static final String HOST = "127.0.0.1";
	public static void main(String[] args) throws IOException, TimeoutException {

		//创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //设置连接的主机
        factory.setHost(HOST);
        //设置端口号，java端默认5672
        factory.setPort(5672);
        //设置连接哪个虚拟主机
        factory.setVirtualHost("/ems");
        //设置访问该虚拟主机的用户名和密码
        factory.setUsername("ems");
        factory.setPassword("ems");
        //获取连接对象
        Connection connection = factory.newConnection();
        //获取连接中通道
        Channel channel = connection.createChannel();
        /**
         * 参数1：队列的名称，如果不存在自动创建。
         * 参数2：是否要持久化
         * 参数3：是否独占队列
         * 参数4：是否在消费完成之后自动删除队列
         * 参数5：额外附加参数
         */
        channel.queueDeclare("hello", false, false, false, null);
        //消费hello队列中的消息
        //参数1：要消费的队列名称
        //参数2：开始消息的自动确认机制
        //参数3：消费时的回调接口    
		channel.basicConsume("hello", true, new DefaultConsumer(channel) {
			@Override//最后一个参数就是从队列中取出的消息。
			public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
					throws IOException {
				
			System.out.println("消息:"+ new String(body));
			}
		});
//        channel.close();
//        connection.close(); 
	}
}