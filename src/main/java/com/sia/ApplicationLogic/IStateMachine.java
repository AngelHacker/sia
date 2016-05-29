package com.sia.ApplicationLogic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sia.havenondemand.HavenOnDemandService;

public abstract class IStateMachine {
	List<IStateMachine> nextStates = new ArrayList<IStateMachine>();
	IStateMachine previousState;
	UserStateTrack userStateTrack;
	
	public abstract void MoveToNextState();
	public abstract void CanSwitchToThisState(UserStateTrack userStateTrack);
	public abstract String GetMessage();
	public abstract String ProcessInput(String input);
	public abstract void ExportParameterBag(Map<String,String> propertyBag);
	
	protected IStateMachine AddNewNextState(IStateMachine state){
		this.nextStates.add(state);
		return this;
	}

	public void SetUserStateTrack(UserStateTrack userStateTrack){
		this.userStateTrack = userStateTrack;
	}
}
