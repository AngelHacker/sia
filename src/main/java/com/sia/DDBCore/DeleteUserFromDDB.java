package com.sia.DDBCore;
/*package com.checklistz.DDBCore;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DeleteItemOutcome;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.DeleteItemSpec;
import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;
import com.amazonaws.services.dynamodbv2.model.InternalServerErrorException;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughputExceededException;
import com.amazonaws.services.dynamodbv2.model.ResourceNotFoundException;
import com.amazonaws.services.dynamodbv2.model.ReturnValue;
import com.checklistz.Utilities.ErrorList;
public class DeleteUserFromDDB {

	*//**
	 * 
	 * @param table
	 * @param userId
	 * @return
	 *//*
	public String deleteUser(String userId) {

		if (userId == null )
			return ErrorList.INVALID_USER;
		userId=userId.toLowerCase();
		try {

			DynamoDB dynamoDB = new DynamoDB(new AmazonDynamoDBClient(
					new ProfileCredentialsProvider()));

			String tableName = "Profile";
			Table table = dynamoDB.getTable(tableName);

			DeleteItemSpec deleteItemSpec = new DeleteItemSpec()
					.withPrimaryKey("userId", userId).withReturnValues(
							ReturnValue.ALL_OLD);

			DeleteItemOutcome outcome = table.deleteItem(deleteItemSpec);

			
			 * if (outcome == null) {
			 * 
			 * return null; }
			 
			// Check the response.

			return null;

		} catch (ResourceNotFoundException e) {
			return ErrorList.INTERNAL_EXCEPTION;
		} catch (ConditionalCheckFailedException e) {
			return ErrorList.INTERNAL_EXCEPTION;
		} catch (InternalServerErrorException e) {
			return ErrorList.INTERNAL_EXCEPTION;
		} catch (ProvisionedThroughputExceededException e) {
			return ErrorList.INTERNAL_EXCEPTION;
		}
	}
}
*/