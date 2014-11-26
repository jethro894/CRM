package rmq;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;


public class Emitter{
	private static final String EXCHANGE_NAME = "topic_logs";
	Connection connection;
	Channel channel;
	
	public Emitter() throws IOException{
		this.init();
	}
	
	public void init() throws IOException{
		ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        connection = factory.newConnection();
        channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, "topic");
	}
	
	public void publish(String routingKey, String message) throws IOException{
        channel.basicPublish(EXCHANGE_NAME, routingKey, null, message.getBytes());
        System.out.println(" [x] Sent '" + routingKey + "':'" + message + "'");
	}
	
	public void close() throws IOException{
		channel.close();
		connection.close();
	}

}
