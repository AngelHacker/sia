package com.sia.DDBCore;
/*package com.checklistz.DDBCore;

import java.util.List;
import java.util.Map;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.checklistz.Utilities.CommonSymbols;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.UpdateItemOutcome;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.amazonaws.services.dynamodbv2.document.utils.NameMap;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.checklistz.Utilities.EncryptDecrptPassword;

public class UpdateProfile {
	
	DynamoDB dynamoDB = new DynamoDB(new AmazonDynamoDBClient(
			new ProfileCredentialsProvider()));

	String tableName = "Profile";
	Table table = dynamoDB.getTable(tableName);

	public boolean updateEmailVerification(String value, String userId) {
		if (value == null)
			value = CommonSymbols.FALSE;
		if (userId == null)
			return false;
		
		userId=userId.toLowerCase();
		
		try {
			

			UpdateItemSpec updateItemSpec = new UpdateItemSpec()
					.withPrimaryKey("userId", userId)
					.withUpdateExpression("set #na = :val1")
					.withNameMap(new NameMap().with("#na", "isEmailVerified"))
					.withValueMap(new ValueMap().withString(":val1", value));
			
			UpdateItemOutcome outcome = table.updateItem(updateItemSpec);
			return true;
		} catch (Exception e) {
			return false;
		}

	}
	
	public boolean updatePassword(String userId,String password){
		if(userId==null || password==null)
			return false;
		
		userId=userId.toLowerCase();
		
		try{
			String encrptedPassword=new EncryptDecrptPassword().encrypt(password);
			
			UpdateItemSpec updateItemSpec = new UpdateItemSpec()
			.withPrimaryKey("userId", userId)
			.withUpdateExpression("set #na = :val1")
			.withNameMap(new NameMap().with("#na", "password"))
			.withValueMap(new ValueMap().withString(":val1", encrptedPassword));
	
	UpdateItemOutcome outcome = table.updateItem(updateItemSpec);
	return true;
			
			
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	
	public boolean updateToken(String userId, List<Map<String, String>> token) {
		if (userId == null || token == null) {
			return false;
		}
		try {
			UpdateItemSpec updateItemSpec = new UpdateItemSpec()
					.withPrimaryKey("userId", userId)
					.withUpdateExpression("set #na = :val1")
					.withNameMap(new NameMap().with("#na", "token"))
					.withValueMap(new ValueMap().withList(":val1", token));

			UpdateItemOutcome outcome = table.updateItem(updateItemSpec);
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean updateImageUrl(String userId, String imageUrl) {
		if (userId == null || imageUrl == null)
			return false;

		userId = userId.toLowerCase();

		try {
			UpdateItemSpec updateItemSpec = new UpdateItemSpec()
					.withPrimaryKey("userId", userId)
					.withUpdateExpression("set #na = :val1")
					.withNameMap(new NameMap().with("#na", "imageUrl"))
					.withValueMap(new ValueMap().withString(":val1", imageUrl));

			UpdateItemOutcome outcome = table.updateItem(updateItemSpec);
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
}
*/