package com.sia.ApplicationLogic;

import java.util.Calendar;
import java.util.Random;

import com.sia.DynamoDB.AddNewTicket;

public class IssueHandler {

	public static String HandleIssue(String issueType, String code,
			String affectedPart, String extraInformation, IssueInformationGatheringState state) {
		String output = "";
		boolean isNextStateMovedToRepeat = false;
		state.isTokenizationNeeded = true;
		switch (issueType) {
		case "issues":
		case "issue":
			output = "Please provide what kind of issue you are facing";
			break;
		case "crashing":
		case "crashed":
		case "crashes":
		case "crash":
			if(extraInformation.equals("")){
				output = "Do you see any error while it crashes?";
			}else if(extraInformation.toLowerCase().equals("yes")){
				output = "Please provide the exception code";
			}else{				
				String ticketId = "TKT" + Calendar.getInstance().getTimeInMillis();
				String customerId = "CUST0001";
				String description = "System is craching ";
				String errorCode = extraInformation;
				String orderId = "ABCD1234";
				String productId= "Prod0091";
				String serialNumber = "Prod0001T23928429";
				
				boolean isTicketSuccessful = AddNewTicket.addUserToDatabase(ticketId, customerId, description, errorCode, orderId, productId, serialNumber);
				if(isTicketSuccessful){
					output = "We have created ticket " + ticketId + " for you. Please upload the logs on the ticket so that our engineers can help you further.";
					
				}else{
					output = "We are facing some issue and failed to create the ticket. Please try again after some time.";
				}
				
				isNextStateMovedToRepeat = true;
				
			}
			break;
		case "trouble":
		case "troubles":
			output = "Please tell us what kind of trouble you are facing with the system";
			UserStateTrack.currentState = new IssueState();
			break;
		case "exception":
		case "exceptions":
			output = "Please provide a screenshot of the exception screen or type the complete error message";
			UserStateTrack.currentState = new IssueState();
			break;
		case "installation":
		case "installations":
			output = "Please tell us what issue you are facing with the installation.";
			UserStateTrack.currentState = new IssueState();
			break;
		case "speed":
		case "speeds":
			if(!extraInformation.equals("")){
				//ToDo: Do the search here and provide the output
				isNextStateMovedToRepeat = true;
				break;
			}
			
			output = "Which section is giving latency issue?";
			break;
		case "network":
		case "problem":
		case "problems":
			if(!extraInformation.equals("")){
				//ToDo: Lookup and return
				isNextStateMovedToRepeat = true;
				break;
			}
			output = "Please provide details about the issue";
			break;
		case "error":
		case "errors":
			output = "Please provide a screenshot of the exception screen or type the complete error message";
			UserStateTrack.currentState = new IssueState();
			break;
		case "bug":
		case "bugs":
			if(!extraInformation.equals("")){
				String ticketId = "TKT" + Calendar.getInstance().getTimeInMillis();
				String customerId = "CUST0001";
				String description = extraInformation;
				String errorCode = "";
				String orderId = "ABCD1234";
				String productId= "Prod0091";
				String serialNumber = "Prod0001T23928429";
				
				boolean isTicketSuccessful = AddNewTicket.addUserToDatabase(ticketId, customerId, description, errorCode, orderId, productId, serialNumber);
				if(isTicketSuccessful){
					output = "We have created ticket " + ticketId + " for you. You can track it using our chat portal.";
					
				}else{
					output = "We are facing some issue and failed to create the ticket. Please try again after some time.";
				}
				isNextStateMovedToRepeat = true;
				break;
			}
			state.isTokenizationNeeded = false;
			output = "Please provide the details. We will log a ticket which can be tracked";
			break;
		case "concern":
		case "concerns":
			if(!extraInformation.equals("")){
				String ticketId = "TKT" + Calendar.getInstance().getTimeInMillis();
				String customerId = "CUST0001";
				String description = extraInformation;
				String errorCode = "";
				String orderId = "ABCD1234";
				String productId= "Prod0091";
				String serialNumber = "Prod0001T23928429";
				
				boolean isTicketSuccessful = AddNewTicket.addUserToDatabase(ticketId, customerId, description, errorCode, orderId, productId, serialNumber);
				if(isTicketSuccessful){
					output = "We have created ticket " + ticketId + " for you. You can track it using our chat portal.";
					
				}else{
					output = "We are facing some issue and failed to create the ticket. Please try again after some time.";
				}
				isNextStateMovedToRepeat = true;
				break;
			}
			
			state.isTokenizationNeeded = false;
			output = "Please provide us the details and we will provide necessary information";
			break;
		case "virus":
		case "viruses":
			
			output = "Please use some effective antiviruse to get rid of virus";
			isNextStateMovedToRepeat = true;
			break;
		case "fault":
		case "faults":
			output = "Please provide a screenshot of the exception screen or type the complete error message";
			UserStateTrack.currentState = new IssueState();
			break;
		case "slow":
		case "slows":
			if(!extraInformation.equals("")){
				String ticketId = "TKT" + Calendar.getInstance().getTimeInMillis();
				String customerId = "CUST0001";
				String description = extraInformation;
				String errorCode = "";
				String orderId = "ABCD1234";
				String productId= "Prod0091";
				String serialNumber = "Prod0001T23928429";
				
				boolean isTicketSuccessful = AddNewTicket.addUserToDatabase(ticketId, customerId, description, errorCode, orderId, productId, serialNumber);
				if(isTicketSuccessful){
					output = "We have created ticket " + ticketId + " for you. You can track it using our chat portal.";
					output += "Please provide the event logs for the device " + affectedPart  + " at the ticket ";
					
				}else{
					output = "We are facing some issue and failed to create the ticket. Please try again after some time.";
				}

				isNextStateMovedToRepeat = true;
				break;
			}

			output = "Which part is running slow?";
			break;
			
		case "bad":			
		case "worse":
			if(!extraInformation.equals("")){
				String ticketId = "TKT" + Calendar.getInstance().getTimeInMillis();
				String customerId = "CUST0001";
				String description = extraInformation;
				String errorCode = "";
				String orderId = "ABCD1234";
				String productId= "Prod0091";
				String serialNumber = "Prod0001T23928429";
				
				boolean isTicketSuccessful = AddNewTicket.addUserToDatabase(ticketId, customerId, description, errorCode, orderId, productId, serialNumber);
				if(isTicketSuccessful){
					output = "We have created ticket " + ticketId + " for you. You can track it using our chat portal.";
					output += "Our engineer will get back to you with more information.";
					
				}else{
					output = "We are facing some issue and failed to create the ticket. Please try again after some time.";
				}

				isNextStateMovedToRepeat = true;
				break;
			}
			state.isTokenizationNeeded = false;
			output = "";
			break;
		case "fail":
		case "fails":
			output = "Please provide a screenshot of the exception screen or type the complete error message";
			UserStateTrack.currentState = new IssueState();
			break;
		case "hang":
		case "hangs":
		case "close":
		case "closes":
		case "heat":
		case "heats":
			String ticketId = "TKT" + Calendar.getInstance().getTimeInMillis();
			String customerId = "CUST0001";
			String description = extraInformation;
			String errorCode = "";
			String orderId = "ABCD1234";
			String productId= "Prod0091";
			String serialNumber = "Prod0001T23928429";
			
			boolean isTicketSuccessful = AddNewTicket.addUserToDatabase(ticketId, customerId, description, errorCode, orderId, productId, serialNumber);
			if(isTicketSuccessful){
				output = "We have created ticket " + ticketId + " for you. You can track it using our chat portal.";
				output += "Our engineer will get back to you with more information.";
				
			}else{
				output = "We are facing some issue and failed to create the ticket. Please try again after some time.";
			}

			isNextStateMovedToRepeat = true;
			break;
		case "upgrade":
		case "upgrades":
			//ToDo: Search and look for upgrade documentation

			isNextStateMovedToRepeat = true;
		}
		
		if(isNextStateMovedToRepeat){
			UserStateTrack.currentState = new RepeatState();
			output = output + "<br>" + UserStateTrack.currentState.GetMessage();
		}
		
		return output;
	}
	
}
