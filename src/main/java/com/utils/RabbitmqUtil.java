package com.utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RabbitmqUtil {

    private static ConnectionFactory factory;
    static {
    	//放在静态代码块中，因为连接工场是重量级资源，在类加载时执行一次即可。
                 	// 创建连接工厂
    			    factory = new ConnectionFactory();
    				// 设置连接的主机
    				factory.setHost("127.0.0.1");
    				// 设置端口号，java端默认5672
    				factory.setPort(5672);
    				// 设置连接哪个虚拟主机
    				factory.setVirtualHost("/ems");
    				// 设置访问该虚拟主机的用户名和密码
    				factory.setUsername("ems");
    				factory.setPassword("ems");	
    }
    
	// 定义提供连接对象的方法
	public static Connection getConnection() {
		try {
			// 返回连接对象
			return factory.newConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	//关闭连接的方法
	
	public static void closeConnAndChannel(Channel channel,Connection connection) {
		try {
			if(channel != null) channel.close();
			if(connection != null) connection.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
