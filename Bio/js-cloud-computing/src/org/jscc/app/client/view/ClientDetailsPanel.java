package org.jscc.app.client.view;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ClientDetailsPanel extends VerticalPanel{
	
	private final String HEADLINE = "Your Details";
	private String clientId;
	private float calculationTime;
	private int clientRank;
	private int trials;
	private String listId;
	
	FlexTable detailsTable;
		
	
	public ClientDetailsPanel(){
		
		detailsTable = new FlexTable();
		this.setStylePrimaryName("clientDetailsPanel");
		this.add(new HTML("<h2>"+HEADLINE+"</h2>"));
		this.add(detailsTable);
		
	}	
	
	

	
	public void refreshPanel(){
		detailsTable.clear();
		detailsTable.setText(0, 0, "Your ID");
		detailsTable.setText(0, 1, this.clientId);
		detailsTable.setText(1, 0, "Your fastest perfomance");
		detailsTable.setText(1, 1, "" + this.calculationTime);
		detailsTable.setText(2, 0, "Your number of trials");
		detailsTable.setText(2, 1, ""+this.trials);
	}
	
	
	public void refreshAllValues(
			String clientId,
			int trials,
			float fastestTime,
			String listId,
			int numberOfSequences){		
		
		this.trials = trials;
		this.clientId = clientId;
		this.calculationTime  = fastestTime;
		this.listId = listId;
		this.clientRank = numberOfSequences;
		refreshPanel();
	}
	
}
