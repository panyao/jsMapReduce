package org.jscc.app.client.view;

import java.util.ArrayList;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;

public class HighscorePanel extends VerticalPanel{
	
	
	private FlexTable topScorerTable;
	private String HEADLINE = "Highscores";
	
	
	public HighscorePanel(){		
		
		this.setStylePrimaryName("highscorePanel");
		
		topScorerTable = new FlexTable();		
		topScorerTable.addStyleName("highscoreTable");
		topScorerTable.setHTML(0,0, "<b>Pos.</b>");
		topScorerTable.setHTML(0,1, "<b>Client ID</b>");
		topScorerTable.setHTML(0,2, "<b>Time</b>");
		
		this.add(new HTML("<h2>"+HEADLINE+"</h2>"));
		this.add(topScorerTable);
		
	}
	
	
	public void refreshTopPlayerTable(			
			ArrayList<String> clientIds,
			ArrayList<Float> times) {
		
		for(int i = 0; i < clientIds.size(); i++){
			
			topScorerTable.setText(i+1, 0, (i+1)+"");
			topScorerTable.setText(i+1, 1, clientIds.get(i));
			topScorerTable.setText(i+1, 2, times.get(i)+"");
			
		}
		
	}
	
	
	
	
	
	
	
}
