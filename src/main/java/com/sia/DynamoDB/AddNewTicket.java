package com.sia.DynamoDB;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import com.sia.Confidential.AccessKeys;

public class AddNewTicket {

	public static boolean addUserToDatabase(String ticketId, String customerId, String description,
			String errorCodeId, String orderId, String productId, String serialNumber) {

		ticketId = ticketId.toLowerCase();
		orderId = orderId.toLowerCase();
		
		try {

			AmazonDynamoDBClient client = new AmazonDynamoDBClient(AccessKeys.dynamoAwsCreds);

			String tableName = "SiaTicketMaster";

			Map<String, AttributeValue> item = new HashMap<String, AttributeValue>();
			AttributeValue ticketIdAttribute = new AttributeValue(ticketId);
			item.put("TicketID", ticketIdAttribute);

			AttributeValue CallIDAttribute = new AttributeValue("CL000001");
			item.put("CallID", CallIDAttribute);

			AttributeValue CustomerIDAttribute = new AttributeValue(customerId);
			item.put("CustomerID", CustomerIDAttribute);
			
			 Date dNow = new Date( );
		      SimpleDateFormat ft = 
		      new SimpleDateFormat ("E yyyy.MM.dd 'at' hh:mm:ss a zzz");

			
			AttributeValue dateAttribute = new AttributeValue(ft.format(dNow));
			item.put("Date", dateAttribute);

			AttributeValue DescriptionAttribute = new AttributeValue(description);
			item.put("Description", DescriptionAttribute);
			
			AttributeValue ErrorCodeIDAttribute = new AttributeValue(errorCodeId);
			item.put("ErrorCodeID", ErrorCodeIDAttribute);
			AttributeValue EscalationPriorityAttribute = new AttributeValue("P2");
			item.put("EscalationPriority", EscalationPriorityAttribute);
			AttributeValue ExpertService = new AttributeValue("True");
			item.put("ExpertService", ExpertService);
			AttributeValue OrderID = new AttributeValue(orderId);
			item.put("OrderID", OrderID);
			AttributeValue ProductID = new AttributeValue(productId);
			item.put("ProductID", ProductID);
			AttributeValue SerialNumber = new AttributeValue(serialNumber);
			item.put("SerialNumber", SerialNumber);
			AttributeValue TicketType = new AttributeValue("Bug");
			item.put("TicketType", TicketType);
			

			PutItemRequest putItemRequest = new PutItemRequest()
					.withTableName(tableName).withItem(item);

			client.putItem(putItemRequest);

			return true;
		} catch(ConditionalCheckFailedException e){
			return false;
		}
		catch (Exception e) {
			System.out.println("User creation failed");
			e.printStackTrace();
		}

		return false;
	}
}
