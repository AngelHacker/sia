package com.sia.ApplicationLogic;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

import com.sia.Common.States;



public class GreetingsState extends IStateMachine {
	States states;
	String message = "";
	String input;
	
	Set<String> nextStateOptions = new HashSet<String>();
	public GreetingsState() {
		nextStateOptions.add("issue");
		nextStateOptions.add("ticket");
		nextStateOptions.add("purchase");
	}

	@Override
	public void MoveToNextState() {
		if(input.equals("issue")){
			this.message = "What kind of issue are you facing?";
			UserStateTrack.currentState = new IssueState();
		}else if(input.equals("ticket")){
			this.message = "Please provide the ticket number for further processing.";
			UserStateTrack.currentState = new TicketState();
		}else{
			this.message = "Please tell us how can we help you.";
			UserStateTrack.currentState = new PurchaseState();
		}

	}

	@Override
	public void CanSwitchToThisState(UserStateTrack userStateTrack) {

	}

	@Override
	public String GetMessage() {
		this.message = "Hi, This is your personal support representative, Sia. \nPlease select one of the below options to proceed further.";
		return message;
	}

	@Override
	public String ProcessInput(String input) {
		input = input.toLowerCase();
		if(!nextStateOptions.contains(input)){
			this.message = "Please select one of the below before proceeding further.";
			return message;
		}
		
		this.input = input;
		
		this.MoveToNextState();
		
		return message;
	}

	@Override
	public void ExportParameterBag(Map<String, String> propertyBag) {
		
	}

}
