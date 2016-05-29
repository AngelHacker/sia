package com.sia.ApplicationLogic;

import java.io.IOException;
import java.util.Map;

import com.sia.Confidential.AccessKeys;
import com.sia.havenondemand.AnalyzeSentimentResult;

import retrofit2.Call;

public class FeedbackState extends IStateMachine {

	public FeedbackState() {

	}

	@Override
	public void MoveToNextState() {

	}

	@Override
	public void CanSwitchToThisState(UserStateTrack userStateTrack) {

	}

	@Override
	public String GetMessage() {
		return "Okay. Please take a moment to rate our service. How good/bad would you rate?";
	}

	@Override
	public String ProcessInput(String input) {
		String response = "";

		// extract sentiment from user input - HavenOnDemand API:
		// analyzesentiment
		Call<AnalyzeSentimentResult> call = userStateTrack.service.analyzeSentiment(input, AccessKeys.HPKey);

		AnalyzeSentimentResult analyzeSentimentResult;
		try {
			analyzeSentimentResult = call.execute().body();
		} catch (IOException ex) {
			return "IOException: " + ex.toString();
		}

		String sentiment = analyzeSentimentResult.getAggregate().getSentiment();

		switch (sentiment) {
		case "positive":
			response = "We are happy we could help you out :)";
			UserStateTrack.currentState = new GreetingsState();
			break;
		case "negative":
			response = "We are really sorry we were unable to help you out this time :( . We will try to do better in future :)";
			UserStateTrack.currentState = new GreetingsState();
			break;
		default:
			response = "Seems you did not like our service. We will try to do better in future :)";
			UserStateTrack.currentState = new GreetingsState();
			break;
		}

		return response;
	}

	@Override
	public void ExportParameterBag(Map<String, String> propertyBag) {

	}

}