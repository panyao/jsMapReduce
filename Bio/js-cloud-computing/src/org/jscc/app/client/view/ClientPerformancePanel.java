package org.jscc.app.client.view;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ClientPerformancePanel extends HorizontalPanel {
	
	float calculationTime;
	String clientId;
	String listId;
	
	
	public ClientPerformancePanel(){
		refreshPanel();
		this.setStyleName("clientPerfomancePanel");
	}
	
	public void refreshPanel(){
		
		this.clear();
		//TIME BOX
		VerticalPanel timeBox = new VerticalPanel();
		timeBox.setStyleName("performancePanelBox");
		HTML headingTimeBox = new HTML("<h3>Calculation Time</h3>");
		HTML valueTimeBox = new HTML(this.calculationTime+"ms");
		timeBox.add(headingTimeBox);
		timeBox.add(valueTimeBox);
		
		
		
		// CLIENT ID BOX
		VerticalPanel clientIdBox = new VerticalPanel();
		clientIdBox.setStyleName("performancePanelBox");
		HTML headingClientBox = new HTML("<h3>Your ID</h3>");
		HTML valueClientBox = new HTML(this.clientId);
		clientIdBox.add(headingClientBox);
		clientIdBox.add(valueClientBox);
		
		
		
		//SEQUENCE LIST ID BOX
		VerticalPanel seqListIdBox = new VerticalPanel();
		seqListIdBox.setStyleName("performancePanelBox");
		HTML headingSeqListIdBox = new HTML("<h3>Sequence Set</h3>");
		HTML valueSeqListIdBox = new HTML(this.listId);
		seqListIdBox.add(headingSeqListIdBox);
		seqListIdBox.add(valueSeqListIdBox);
		
		
		
		
		
		this.add(clientIdBox);
		this.add(timeBox);
		this.add(seqListIdBox);
		
	}	
	
	public void refreshAllValues(
			String clientId,		
			float fastestTime,
			String listId){		
		
		
		this.clientId = clientId;
		this.calculationTime  = fastestTime;
		this.listId = listId;
		refreshPanel();
	}
	
}
