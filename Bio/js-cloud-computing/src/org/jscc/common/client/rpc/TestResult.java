package org.jscc.common.client.rpc;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.gwtplatform.dispatch.shared.Result;

public class TestResult implements Result, IsSerializable{
	
	private String returnString;

	public TestResult() {}
	

	public String getReturnString() {
		return this.returnString;
	}
	
	public void setReturnString(String returnString) {
		this.returnString = returnString;
	}
	
	
}
