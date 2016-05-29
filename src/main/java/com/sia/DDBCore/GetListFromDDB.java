package com.sia.DDBCore;
/*package com.checklistz.DDBCore;

import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;

public class GetListFromDDB {

	public Item getChecklist(Table table, String listId) {

		try {

			Item item = table.getItem("listId", listId);
			if(item==null)
			{
				System.out.println("GetListFromDDB:List not found");
				return null;
			}
				
			//System.out.println(item.toJSONPretty());
			return item;
			//return item.toJSONPretty();

		} catch (Exception e) {
			System.err.println("GetListFromDDB:GetItem failed.");
			e.printStackTrace();
		}
		return null;
	}
}
*/