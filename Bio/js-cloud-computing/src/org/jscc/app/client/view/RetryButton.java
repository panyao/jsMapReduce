package org.jscc.app.client.view;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;

public class RetryButton extends Button {

	
	public RetryButton(){
		
		this.setStylePrimaryName("retryButton");
		
	}

	
	public HasClickHandlers getRetryButton() {
		return this;
	}
	
}
