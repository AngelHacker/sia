package com.sia.ApplicationLogic;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import retrofit2.Call;

import com.amazonaws.services.dynamodbv2.document.Item;
import com.sia.Confidential.AccessKeys;
import com.sia.DynamoDB.GetErrorDetails;
import com.sia.havenondemand.HighlightTextResult;
import com.sia.havenondemand.Term;
import com.sia.havenondemand.TokenizeTextResult;
import com.sia.utils.Utils;

public class IssueInformationGatheringState extends IStateMachine {

	String message = "";
	private String issueType = "";
	private String code = "";
	private String affectedPart = "";
	private String extraInformation = "";
	public boolean isTokenizationNeeded = true;

	public String getIssueType() {
		return issueType;
	}

	public void setIssueType(String issueType) {
		this.issueType = issueType;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getAffectedPart() {
		return affectedPart;
	}

	public void setAffectedPart(String affectedPart) {
		this.affectedPart = affectedPart;
	}

	private Set<String> predefinedIssues = new HashSet<String>();
	private Set<String> impactedObjects = new HashSet<String>();

	public IssueInformationGatheringState() {
		predefinedIssues.add("issue");
		predefinedIssues.add("crash");
		predefinedIssues.add("trouble");
		predefinedIssues.add("exception");
		predefinedIssues.add("installation");
		predefinedIssues.add("speed");
		predefinedIssues.add("network");
		predefinedIssues.add("problem");
		predefinedIssues.add("error");
		predefinedIssues.add("bug");
		predefinedIssues.add("concern");
		predefinedIssues.add("virus");
		predefinedIssues.add("fault");
		predefinedIssues.add("slow");
		predefinedIssues.add("bad");
		predefinedIssues.add("worse");
		predefinedIssues.add("fail");
		predefinedIssues.add("hang");
		predefinedIssues.add("close");
		predefinedIssues.add("heat");
		predefinedIssues.add("upgrade");
		impactedObjects.add("cpu");
		impactedObjects.add("ram");
		impactedObjects.add("memory");
		impactedObjects.add("hardware");
		impactedObjects.add("screen");
		impactedObjects.add("CPU");
		impactedObjects.add("camera");
		impactedObjects.add("cam");
		impactedObjects.add("sensor");
		impactedObjects.add("gyroscope");
		impactedObjects.add("firmware");
	}

	@Override
	public void MoveToNextState() {

	}

	@Override
	public void CanSwitchToThisState(UserStateTrack userStateTrack) {

	}

	@Override
	public String GetMessage() {
		
		if(code!=null && !code.isEmpty()){
			
			Item item = new GetErrorDetails().get(code);
			if(item == null && issueType == null){
				UserStateTrack.currentState = new IssueState();
				message = "We are unable to fetch complete information. Please provide us more details about the issue.";
				return message;
			}
			
			else if(item!=null){
				message = "Issue=" + item.getString("ErrorName");
				message += "<br>SolutionLink="+item.getString("SolutionLink") ;
				message += "<br>Suggestion="+item.getString("Suggestion");
				
				Call<HighlightTextResult> call = UserStateTrack.service.highlightText(message, "SolutionLink", AccessKeys.HPKey);
				HighlightTextResult highlightTextResult;
				try {
					highlightTextResult = call.execute().body();
					UserStateTrack.currentState = new RepeatState();
					return Utils.formatHighlightedTextForAndroid(highlightTextResult.getText()) + "<br>" + UserStateTrack.currentState.GetMessage();
				} catch (IOException e) {
					e.printStackTrace();
					return message;
				}
			}
		}
		
		// This doesn't simply return the message
		return IssueHandler.HandleIssue(issueType, code, affectedPart, extraInformation, this);
	}

	

	@Override
	public String ProcessInput(String input) {
		// Update the 
		if(isTokenizationNeeded){
			Call<TokenizeTextResult> call = UserStateTrack.service.tokenizeText(input, false, AccessKeys.HPKey);
			try{
				TokenizeTextResult tokenizeTextResult = call.execute().body();
				List<Term> terms = tokenizeTextResult.getTerms();
				if(terms.size()==0){
					return "We couldn't understand the input. Please provide the input again";
				}
				this.extraInformation = terms.get(0).getTerm();
			}catch(Exception ex){
				ex.printStackTrace();
				return message;
			}
		}else{
			this.extraInformation = input;
			
		}
		
		return GetMessage();
	}

	@Override
	public void ExportParameterBag(Map<String, String> propertyBag) {
		if(propertyBag.containsKey("issueType")){
			this.issueType = propertyBag.get("issueType");
		}
		if(propertyBag.containsKey("affectedPart")){
			this.affectedPart = propertyBag.get("affectedPart");
		}
		if(propertyBag.containsKey("code")){
			this.code = propertyBag.get("code");
		}

	}

}
