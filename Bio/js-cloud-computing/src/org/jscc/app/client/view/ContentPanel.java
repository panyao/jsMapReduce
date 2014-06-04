package org.jscc.app.client.view;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ContentPanel extends VerticalPanel{

	
	private HTML headline = new HTML();	
	
	
	public ContentPanel(){
		this.setStylePrimaryName("contentPanel");
		this.add(this.headline);
	}
	
	
	public void setHeadline(String headline){
		this.remove(this.headline);
		this.headline = new HTML("<h2>"+headline+"</h2>");
		this.add(this.headline);				
		
	}	
	
	
}
