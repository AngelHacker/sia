package com.sia.DDBCore;
/*package com.checklistz.DDBCore;

import java.util.HashMap;

import com.checklistz.Utilities.CommonSymbols;
import com.checklistz.Utilities.ErrorList;

import java.util.Map;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Expected;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;

public class AddNewUserInDDB {

	public boolean addUserToDatabase(String userId, String password, String email,
			String name) throws Exception {

		if (userId == null || password == null || email == null) {
			return false;
		}
		userId=userId.toLowerCase();
		email = email.toLowerCase();
		try {

			AmazonDynamoDBClient client = new AmazonDynamoDBClient();

			String tableName = "Profile";

			Map<String, AttributeValue> item = new HashMap<String, AttributeValue>();
			AttributeValue userIdAttribute = new AttributeValue(userId);
			item.put("userId", userIdAttribute);

			AttributeValue emailAttribute = new AttributeValue(email);
			item.put("email", emailAttribute);

			AttributeValue passwordAttribute = new AttributeValue(password);
			item.put("password", passwordAttribute);
			
			AttributeValue isEmailerifiedAttribute = new AttributeValue(CommonSymbols.FALSE);
			item.put("isEmailVerified", isEmailerifiedAttribute);

			if (name != null) {
				AttributeValue nameAttribute = new AttributeValue(name);
				item.put("name", nameAttribute);
			}

			Map<String, ExpectedAttributeValue> expected = new HashMap<String, ExpectedAttributeValue>();
			expected.put("userId", new ExpectedAttributeValue(false));
			expected.put("email", new ExpectedAttributeValue(false));

			PutItemRequest putItemRequest = new PutItemRequest()
					.withTableName(tableName).withItem(item)
					.withExpected(expected);

			client.putItem(putItemRequest);

			return true;
		} catch(ConditionalCheckFailedException e){
			return false;
		}
		catch (Exception e) {
			System.out.println("User creation failed");
			e.printStackTrace();
		}

		throw new Exception(ErrorList.INTERNAL_EXCEPTION);
	}
}
*/