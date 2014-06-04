package org.jscc.common.client.rpc;

import java.util.ArrayList;
import java.util.Random;
import com.google.gwt.user.client.rpc.IsSerializable;
import com.gwtplatform.dispatch.shared.Result;

public class GetSequenceListResult implements Result, IsSerializable {
	
	private static final long serialVersionUID = -2622023903998894566L;
	private ArrayList<String> sequenceList;
	private String listID;
	
	
	public GetSequenceListResult() {}
	
	public ArrayList<String> getSequenceList(){
		return this.sequenceList;
	}
	
	public void setSequenceList(ArrayList<String> sequenceList){
		this.sequenceList = sequenceList;
	}
	
	public String getListID(){
		return this.listID;
	}
	
	public void setListID(String name){
		if(name.equals(null)){
			Random rn = new Random();
			short n = (short) rn.nextInt();
			this.listID = "List_"+n;
		}
		else{
			this.listID = name;
		}
	}
	
}
