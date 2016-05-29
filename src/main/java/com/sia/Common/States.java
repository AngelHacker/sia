package com.sia.Common;

import com.sia.ApplicationLogic.*;

public class States {

	public IStateMachine Greetings = new GreetingsState();
	public IStateMachine Issue = new IssueState();
	public IStateMachine Ticket = new TicketState();
	public IStateMachine Purcharse = new PurchaseState();
	public IStateMachine IssueInformationGatheringState = new IssueInformationGatheringState();
	public IStateMachine FeedbackState = new FeedbackState();
	// public IStateMachine Information = new InformationState();
}
