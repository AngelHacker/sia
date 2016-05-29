package com.sia.ApplicationLogic;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

import com.amazonaws.services.dynamodbv2.document.Item;
import com.sia.Confidential.AccessKeys;
import com.sia.DynamoDB.GetTicketDetails;
import com.sia.havenondemand.HighlightTextResult;
import com.sia.havenondemand.TokenizeTextResult;
import com.sia.utils.Utils;

import retrofit2.Call;

public class TicketState extends IStateMachine {

	@Override
	public void MoveToNextState() {

	}

	@Override
	public void CanSwitchToThisState(UserStateTrack userStateTrack) {

	}

	@Override
	public String GetMessage() {
		return null;
	}

	@Override
	public void ExportParameterBag(Map<String, String> propertyBag) {

	}

	@Override
	public String ProcessInput(String input) {
		String response = "";

		// extract ticket id from user input - HavenOnDemand API: tokenizetext
		Call<TokenizeTextResult> call1 = UserStateTrack.service.tokenizeText(input, false, AccessKeys.HPKey);

		TokenizeTextResult tokenizeTextResult;
		try {
			tokenizeTextResult = call1.execute().body();
		} catch (IOException ex) {
			return "IOException: " + ex.toString();
		}

		if (tokenizeTextResult.getTerms().size() == 0) {
			return "Unable to process input. Please re-enter your ticket id correctly.";
		}

		String probableTicketId = tokenizeTextResult.getTerms().get(0).getTerm();

		// fetch ticket from database
		Item item = GetTicketDetails.get(probableTicketId);

		if (item == null) {
			String resp = "Invalid ticket ID " + probableTicketId + " . Please provide correct ticket ID.";

			// highlight ticket id in response - HavenOnDemand API: highlighttext
			Call<HighlightTextResult> call2 = UserStateTrack.service.highlightText(resp, probableTicketId, AccessKeys.HPKey);

			HighlightTextResult highlightTextResult = null;
			try {
				highlightTextResult = call2.execute().body();
			} catch (IOException ex) {
				// TODO:
			}

			if (highlightTextResult != null) {
				resp = Utils.formatHighlightedTextForAndroid(highlightTextResult.getText());
			}

			return resp;
		}

		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kolkata"));

		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);

		Date created;

		try {
			created = format.parse(item.getString("Date"));
		} catch (ParseException ex) {
			return "ParseException: " + ex.toString();
		}

		Date current = new Date();

		long duration = TimeUnit.MILLISECONDS.toHours(current.getTime() - created.getTime());

		// update ticket status to escalated - escalate only if ticket is more than 24 hours old
		if (duration < 24) {
			response += "Sorry, we are still working on the ticket (" + probableTicketId
					+ "). It usually takes around 24 hours for resolving a ticket, and your ticket was created "
					+ duration + " hours back. Please expect a response within first 24 hours after ticket creation.";
		} else {
			response += "Sorry, we seem to be taking longer than expected on the ticket (" + probableTicketId
					+ "). Let me escalate it for you. Please expect a response within next 24 hours.";
			// TODO: escalate
		}

		// highlight ticket id in response - HavenOnDemand API: highlighttext
		Call<HighlightTextResult> call2 = UserStateTrack.service.highlightText(response, probableTicketId, AccessKeys.HPKey);

		HighlightTextResult highlightTextResult = null;
		try {
			highlightTextResult = call2.execute().body();
		} catch (IOException ex) {
			// TODO:
		}

		if (highlightTextResult != null) {
			response = Utils.formatHighlightedTextForAndroid(highlightTextResult.getText());
		}

		// TODO: update in database

		UserStateTrack.currentState = new RepeatState();
		return response + " " + UserStateTrack.currentState.GetMessage();
	}
}
