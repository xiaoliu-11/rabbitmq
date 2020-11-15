package com.rabbitmq.helloworld;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
public class Producer {
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
        //发布消息
        /**
         * 参数1：交换机名称
         * 参数2：队列名称
         * 参数3：消息的额外设置
         * 参数4：消息的具体内容
         */
        channel.basicPublish("", "hello", null, "hello rabbitmq1".getBytes());
        channel.close();
        connection.close();   
    }
}