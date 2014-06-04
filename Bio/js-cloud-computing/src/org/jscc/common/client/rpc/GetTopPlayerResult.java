package org.jscc.common.client.rpc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.gwtplatform.dispatch.shared.Result;

public class GetTopPlayerResult implements Result, IsSerializable {
	
	private static final long serialVersionUID = -2622023903998894566L;
	
	private int TOP_SCORER_LIST_SIZE = 3;
	private int TOTAL_SCORER_LIST_SIZE = 100;
	
	private ArrayList<Float> bestAlignmentTimesList = new ArrayList<Float>();;
	private ArrayList<String> bestAlignmentClientIdList = new ArrayList<String>();;
	
	
	
	
	public GetTopPlayerResult() {	
		
		 
		
	}	
	
	
	public int getTopPlayerListSize(){ return this.TOP_SCORER_LIST_SIZE; }
	
	public ArrayList<String> getBestAlignmentClientIdList(){return this.bestAlignmentClientIdList;};
	
	public ArrayList<Float> getBestAlignmentTimesList(){return this.bestAlignmentTimesList;};
	
	
	
	
	public void setBestAlignmentClientIdList(ArrayList<String> clientId){
		this.bestAlignmentClientIdList = clientId;
	}
	
	public void setBestAlignmentTimesList(ArrayList<Float> times){
		this.bestAlignmentTimesList = times;
	}
	
	
}
	