package com.utils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
public class Test {
	 public static void main(String[] args) throws IOException, TimeoutException {
       //通过工具类获取连接对象
		Connection connection =  RabbitmqUtil.getConnection();
	 
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
        //发布消息
        /**
         * 参数1：交换机名称
         * 参数2：队列名称
         * 参数3：消息的额外设置
         * 参数4：消息的具体内容
         */
        channel.basicPublish("", "hello", null, "hello rabbitmq".getBytes());
       
		//调用工具类关闭连接
        RabbitmqUtil.closeConnAndChannel(channel, connection);
	 }
}