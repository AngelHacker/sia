package com.sia.DynamoDB;

import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;

public class GetTicketDetails {

	public static Item get(String ticketId) {
		if (ticketId == null) {
			return null;
		}

		ticketId = ticketId.toUpperCase();
		String tableName = "SiaTicketMaster";
		try {
			Table table = DBHelper.dynamoDB.getTable(tableName);
			Item item = table.getItem("TicketID", ticketId);

			if (item == null) {
				System.out.println("GetTicketDetails: Ticket not found");
				return null;
			}

			return item;

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
}
