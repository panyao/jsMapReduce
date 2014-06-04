package org.jscc.app.client.view;




import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;

public class HeaderPanel extends HorizontalPanel{
	
	
	String HEADLINE = "Developing the next generation distributed grid";
	
	private final Image logo = new Image("img/logo.png");
	
	public HeaderPanel(){
	
		this.setStylePrimaryName("headerPanel");
		this.add(logo);
		this.add(new HTML("<h1>"+HEADLINE+"</h1>"));	
	
	}	
	
	
}
