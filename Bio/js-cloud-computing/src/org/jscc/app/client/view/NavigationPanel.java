package org.jscc.app.client.view;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Hyperlink;

public class NavigationPanel extends HorizontalPanel{
	
	HorizontalPanel navigation = new HorizontalPanel();
	
	private Hyperlink home = new Hyperlink("Home","home");
	private Hyperlink about = new Hyperlink("About","about");
	private Hyperlink results = new Hyperlink("results","results");
	private Hyperlink partner = new Hyperlink("Partner","partner");
	private Hyperlink contact = new Hyperlink("Contact","contact");
	
	
	
	public NavigationPanel(){
		
		this.setStyleName("navigationBackground");
		this.navigation.setStyleName("navigationForeground");
		buildNavigation();
		this.add(navigation);
		
	}
	
	public HasClickHandlers getHomeClickHandler(){
		return this.home;
	}
	
	public HasClickHandlers getAboutClickHandler(){
		return this.about;
	}

	public HasClickHandlers getResultsClickHandler(){
		return this.results;
	}
	
	public HasClickHandlers getPartnerClickHandler(){
		return this.partner;
	}
	
	public HasClickHandlers getContactClickHandler(){
		return this.contact;
	}
	
	public void buildNavigation(){
		
		this.navigation.add(home);
		this.navigation.add(about);
		this.navigation.add(results);
//		this.navigation.add(partner);
//		this.navigation.add(contact);
		
		
	}

}
