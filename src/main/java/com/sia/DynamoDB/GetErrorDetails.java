package com.sia.DynamoDB;

import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;

public class GetErrorDetails {

	public Item get(String errorCode) {
		try{
			if(errorCode==null){
				return null;
			}
		errorCode = errorCode.toUpperCase();
		String tableName = "SiaErrorMaster";
		Table table = DBHelper.dynamoDB.getTable(tableName);
		Item item = table.getItem("ErrorID", errorCode);
		
		if(item==null)
		{
			System.out.println("GetErrorDEtails: Error not found");
			return null;
		}
		
		return item;
		
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
}
