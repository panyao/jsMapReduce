package org.jscc.app.client.objectify;

import javax.persistence.Id;

import com.google.gwt.user.client.rpc.IsSerializable;

public class PlayerEntity implements IsSerializable{
	
	@Id Long id;
	private String clientId;
	private float userAlignmentTime;
	private int trialCounter;
	
	
	/**
	 * Empty no-arg constructor. Mandatory for Objectify entities.
	 */
	@SuppressWarnings("unused")
	private PlayerEntity() {}
	
	
	
	
	// constructor creating new list of best times etc.
	// Input userAlignmentTime actually not need!!
	public PlayerEntity(String clientId, float userAlignmentTime) {
			trialCounter = 1;
			this.clientId = clientId;
			this.userAlignmentTime = userAlignmentTime;		
	}

	
	
	
	
	
	public String getClientId(){return this.clientId;}
	
	public int getTrial(){ return this.trialCounter;}
	
	public Long getId() { return this.id; }
	
	public float getTime(){	return this.userAlignmentTime;}
	
	
	public void setTime(float time){
		this.userAlignmentTime = time;		
	}
	
	
	public void increaseTrialCounter(){
		this.trialCounter++;
	}

}
