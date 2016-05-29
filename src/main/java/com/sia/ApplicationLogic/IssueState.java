package com.sia.ApplicationLogic;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.sia.Confidential.AccessKeys;
import com.sia.havenondemand.Term;
import com.sia.havenondemand.TokenizeTextResult;

import retrofit2.Call;

public class IssueState extends IStateMachine{
	
	String message = "";
	String issueType = "";
	String code = "";
	String affectedPart = "";
	boolean isErrorFound = false;
	boolean isIssueTypeFound = false;
	boolean isAffectedPartFound = false;
	private Set<String> predefinedIssues = new HashSet<String>();
	private Set<String> impactedObjects = new HashSet<String>();
	
	public IssueState(){
		predefinedIssues.add("issue");
		predefinedIssues.add("issues");
		predefinedIssues.add("crash");
		predefinedIssues.add("crashed");
		predefinedIssues.add("crashing");
		predefinedIssues.add("crashes");
		predefinedIssues.add("trouble");
		predefinedIssues.add("troubles");
		predefinedIssues.add("exception");
		predefinedIssues.add("exceptions");
		predefinedIssues.add("installation");
		predefinedIssues.add("installations");
		predefinedIssues.add("speed");
		predefinedIssues.add("network");
		predefinedIssues.add("problem");
		predefinedIssues.add("problems");
		predefinedIssues.add("error");
		predefinedIssues.add("errors");
		predefinedIssues.add("bug");
		predefinedIssues.add("bugs");
		predefinedIssues.add("concern");
		predefinedIssues.add("concerns");
		predefinedIssues.add("virus");
		predefinedIssues.add("viruses");
		predefinedIssues.add("fault");
		predefinedIssues.add("faults");
		predefinedIssues.add("slow");
		predefinedIssues.add("bad");
		predefinedIssues.add("worse");
		predefinedIssues.add("fail");
		predefinedIssues.add("fails");
		predefinedIssues.add("hang");
		predefinedIssues.add("hangs");
		predefinedIssues.add("close");
		predefinedIssues.add("closes");
		predefinedIssues.add("heat");
		predefinedIssues.add("upgrade");
		predefinedIssues.add("upgrades");
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
		UserStateTrack.currentState = new IssueInformationGatheringState();	
		Map<String, String> propertyBag= new HashMap<String, String>();
		propertyBag.put("issueType", issueType);
		if(isAffectedPartFound){
			propertyBag.put("affectedPart", affectedPart);
		}
		
		if(isErrorFound){
			propertyBag.put("code", code);
		}
		
		UserStateTrack.currentState.ExportParameterBag(propertyBag);
	}

	@Override
	public void CanSwitchToThisState(UserStateTrack userStateTrack) {
		
	}

	@Override
	public String GetMessage() {
		return this.message;
	}

	@Override
	public String ProcessInput(String input) {
		input = input.toLowerCase();
		Call<TokenizeTextResult> call = UserStateTrack.service.tokenizeText(input, false, AccessKeys.HPKey);
		try {
			TokenizeTextResult tokenizeTextResult = call.execute().body();
			List<Term> terms = tokenizeTextResult.getTerms();
			for(int i =0;i<terms.size() && i<5 ;i++){
				String term = terms.get(i).getTerm().toLowerCase();
				if(!isIssueTypeFound){
					checkIfIssueExists(term);
				}
				
				if(!isAffectedPartFound){
					checkIfAffectedPartsFound(term);
				}
				
				if(!isErrorFound){
					ExtractErrorCode(term);
				}
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(isIssueTypeFound || isErrorFound){
			MoveToNextState();
			return UserStateTrack.currentState.GetMessage();
		}
		
		message = "We are unable to fetch complete information. Please provide us more details about the issue.";
		return message;
	}
	
	private void checkIfIssueExists(String word){
		if(predefinedIssues.contains(word))
		{
			issueType = word.toLowerCase();
			isIssueTypeFound = true;
		}
		return;
	}
	
	private void checkIfAffectedPartsFound(String word){
		if(affectedPart.contains(word))
		{
			affectedPart = word;
			isAffectedPartFound = true;
		}
		return;
	}
	
	private void ExtractErrorCode(String word){
		try{
			int exception = Integer.parseInt(word);
			code = word;
			isErrorFound = true;
		}catch(Exception e){
			return;
		}
	}


	@Override
	public void ExportParameterBag(Map<String, String> propertyBag) {
		
	}
}