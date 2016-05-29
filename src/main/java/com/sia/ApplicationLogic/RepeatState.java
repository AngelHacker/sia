package com.sia.ApplicationLogic;

import java.util.Map;

public class RepeatState extends IStateMachine {

	@Override
	public void MoveToNextState() {

	}

	@Override
	public void CanSwitchToThisState(UserStateTrack userStateTrack) {

	}

	@Override
	public String GetMessage() {
		return "Can I do anything else for you?";
	}

	@Override
	public String ProcessInput(String input) {
		if (input.equalsIgnoreCase("yes")) {
			UserStateTrack.currentState = new GreetingsState();
			return "How can I help you this time? \nPlease select one of the below options to proceed further.";
		} else if (input.equalsIgnoreCase("no")) {
			UserStateTrack.currentState = new FeedbackState();
			return UserStateTrack.currentState.GetMessage();
		} else {
			return "Please reply 'yes' or 'no'";
		}
	}

	@Override
	public void ExportParameterBag(Map<String, String> propertyBag) {

	}
}
