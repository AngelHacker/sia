package com.sia.DynamoDB;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.sia.Confidential.AccessKeys;

public class DBHelper {
	public static DynamoDB dynamoDB = new DynamoDB(new AmazonDynamoDBClient(AccessKeys.dynamoAwsCreds));
}
