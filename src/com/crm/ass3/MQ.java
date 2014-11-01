package com.crm.ass3;

import java.util.List;
import java.util.Map.Entry;

import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import com.amazonaws.services.sqs.model.DeleteMessageRequest;
import com.amazonaws.services.sqs.model.DeleteQueueRequest;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageRequest;

public class MQ {
	public static AmazonSQS sqs;
	
	public String createQueue(String name){
		System.out.println("Creating a new SQS queue called MyQueue.\n");
        CreateQueueRequest createQueueRequest = new CreateQueueRequest(name);
        String myQueueUrl = sqs.createQueue(createQueueRequest).getQueueUrl();
        return myQueueUrl;
	}
	
	public void listQueues(){
		System.out.println("Listing all queues in your account.\n");
        for (String queueUrl : sqs.listQueues().getQueueUrls()) {
            System.out.println("  QueueUrl: " + queueUrl);
        }
        System.out.println();
	}
	
	public void pushMessage(String myQueueUrl, String message){
		System.out.println("Sending a message to MyQueue.\n");
        sqs.sendMessage(new SendMessageRequest(myQueueUrl, message));
	}
	
	public List<Message> receiveMessages(String myQueueUrl){
		System.out.println("Receiving messages from MyQueue.\n");
        ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(myQueueUrl);
        List<Message> messages = sqs.receiveMessage(receiveMessageRequest).getMessages();
        for (Message message : messages) {
            System.out.println("  Message");
            System.out.println("    MessageId:     " + message.getMessageId());
            System.out.println("    ReceiptHandle: " + message.getReceiptHandle());
            System.out.println("    MD5OfBody:     " + message.getMD5OfBody());
            System.out.println("    Body:          " + message.getBody());
            for (Entry<String, String> entry : message.getAttributes().entrySet()) {
                System.out.println("  Attribute");
                System.out.println("    Name:  " + entry.getKey());
                System.out.println("    Value: " + entry.getValue());
            }
        }
        System.out.println();
        return messages;
	}
	
	public String popMessage(String myQueueUrl){
		ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(myQueueUrl);
		List<Message> messages = sqs.receiveMessage(receiveMessageRequest).getMessages();
		String message = messages.get(0).getBody();
		System.out.println("Deleting a message.\n");
        String messageRecieptHandle = messages.get(0).getReceiptHandle();
        sqs.deleteMessage(new DeleteMessageRequest(myQueueUrl, messageRecieptHandle));
        return message;
	}
	
	public void deleteQueue(String myQueueUrl){
		System.out.println("Deleting the test queue.\n");
        sqs.deleteQueue(new DeleteQueueRequest(myQueueUrl));
	}
	
	public MQ(){
		sqs = new AmazonSQSClient(new ClasspathPropertiesFileCredentialsProvider());
		Region usWest2 = Region.getRegion(Regions.US_WEST_2);
		sqs.setRegion(usWest2);
	}
}
