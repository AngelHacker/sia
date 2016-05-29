package com.sia.ApplicationLogic;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.*;

import com.sia.Confidential.AccessKeys;
import com.sia.havenondemand.*;

public class UserStateTrack {
	
	private static String baseUrl = "https://api.havenondemand.com";
	private static String apiKey = AccessKeys.HPKey;
	private static Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(JacksonConverterFactory.create()).build();
	public static HavenOnDemandService service = retrofit.create(HavenOnDemandService.class);

	private static List<String> chatHistory = new ArrayList<String>();
	public static IStateMachine currentState = new GreetingsState();

	public static String getMessage(boolean isChatStart, String input){
		String response;

		if(isChatStart){
			chatHistory = new ArrayList<String>();
			UserStateTrack.currentState = new GreetingsState();

			chatHistory.add("input:" + input);
			response =  UserStateTrack.currentState.GetMessage();
			return response;
		}

		chatHistory.add("input:" + input);

		response = UserStateTrack.currentState.ProcessInput(input);
		chatHistory.add("response:" + response);
		return response;
	}
}