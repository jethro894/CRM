package com.crm.ass3.rmq;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.ShutdownSignalException;

public class Receiver implements Runnable{
	private static final String EXCHANGE_NAME = "topic_logs";
	Connection connection;
	Channel channel;
	String[] filter;
	
	public Receiver(String[] filter) throws IOException {
		this.filter = filter;
	}
	
	public void init() throws IOException{
		ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        connection = factory.newConnection();
        channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, "topic");
	}
	
	public void receive() throws IOException, ShutdownSignalException, ConsumerCancelledException, InterruptedException{
		String queueName = channel.queueDeclare().getQueue();
		if (this.filter.length < 1){
            System.err.println("Usage: ReceiveLogsTopic [binding_key]...");
            return;
        }

        for(String bindingKey : this.filter){
            channel.queueBind(queueName, EXCHANGE_NAME, bindingKey);
        }
        
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        QueueingConsumer consumer = new QueueingConsumer(channel);
        channel.basicConsume(queueName, true, consumer);

        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            String routingKey = delivery.getEnvelope().getRoutingKey();

            System.out.println(" [x] Received '" + routingKey + "':'" + message + "'");
        }
	}
	
	public void close() throws IOException{
		channel.close();
		connection.close();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			this.init();
			this.receive();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ShutdownSignalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ConsumerCancelledException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
