package org.jscc.app.client.view;

import java.util.ArrayList;

import org.jscc.app.client.presenter.MainPagePresenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;


public class MainView extends ViewImpl implements MainPagePresenter.MyView{
	
	
	// Parent
	ScrollPanel bodyPanel = new ScrollPanel();
	// Static Widgets
	private NavigationPanel navigationPanel = new NavigationPanel();
	private HeaderPanel headerPanel = new HeaderPanel();	
	private final Image spinner = new Image("img/ajax-loader_2.gif");	
	
	
	
	private String histogramm = 
			"<img src=\"http://chart.apis.google.com/" +
			"chart?chxl=1:|PC+Win7+2.4+Ghz+Dual+Intel+" +
			"Core2%09+NA+(Native+JVM)|PC+Win7+2.4+Ghz+Dual+" +
			"Intel+Core2%09+Chrome+5|PC+Win7+2.4+Ghz+Dual+" +
			"Intel+Core2%09+Safari+5|PC+Win7+2.4+Ghz+Dual+" +
			"Intel+Core2%09+Firefox+3.6|PC+Win7+2.4+Ghz+" +
			"Dual+Intel+Core2%09+IE+8%09|Mac+2.13+Ghz+Dual" +
			"+Core+MacOS+10.6.4+Chrome+5|Mac+2.13+Ghz+Dual+" +
			"Core+MacOS+10.6.4++Safari+5|Mac+2.13+Ghz+Dual+" +
			"Core+MacOS+10.6.4++Firefox+3.6|Netbook+1.6+GHz+" +
			"Atom+WinXP+Chrome+5|iPad+iOS+3.1+Safari+5&amp;" +
			"chxp=1,95,85,75,65,55,45,35,25,15,5&amp;" +
			"chxr=0,0,8000&amp;chxs=0,676767,11.5,0,lt," +
			"676767|1,224499,11.5,0,lt,676767&amp;chxtc=" +
			"0,10|1,100&amp;chxt=x,y&amp;chbh=a,1,7&amp;chs" +
			"=720x262&amp;cht=bhg&amp;chco=3072F3&amp;chds=5" +
			",8000&amp;chd=t:7,140,400,1400,4000,200,250,2000" +
			",800,8000&amp;chdl=running+time+in+ms&amp;chg=5,5&" +
			"amp;chma=|5&amp;chtt=Speed+Comparision&amp;chts=" +
			"676767,13.5&amp;nonsense=something_that_ends_with.png\")";
	
	// Functional Widgets
	private RetryButton retryButton = new RetryButton();
	

	// Dynamic Widgets
	private VerticalPanel mainPanel;
	public ContentPanel contentPanel = new ContentPanel();
	public ClientDetailsPanel clientDetailsPanel = new ClientDetailsPanel();
	public ClientPerformancePanel clientPerformancePanel = new ClientPerformancePanel();
	public HighscorePanel highscorePanel = new HighscorePanel();
	

	
	// Constants	
	private final String WELCOME_MESSAGE = "Help the scientific community!";
	
	private final String CALCULATION_MESSAGE = "Lean back! Your computer is " +
			"doing some really important calculations!";
	
	private final String THANK_YOU_MESSAGE = "Thank you very much! You just " +
			"did some scientific computation for us!";
	
	private final String ASSISTANCE_MESSAGE = "You helped the scientific " +
			"community to improve their further research a lot.";
	
	
	@Inject
	public MainView(){		

		//	mainPanel aka connect everything		
		
		mainPanel = new VerticalPanel();
		mainPanel.setStylePrimaryName("mainPanel");
		setNavigationClickHandler();
		mainPanel.add(navigationPanel);
		mainPanel.add(headerPanel);
		mainPanel.add(contentPanel);
		
		bodyPanel.add(mainPanel);
	}
	
	

	@Override
	public Widget asWidget() {
		return this.bodyPanel;
	}

	
	@Override
	public HasClickHandlers getRetryButton() {
		return this.retryButton;
	}
	

	
	public void setNavigationClickHandler(){

		this.navigationPanel.getHomeClickHandler().addClickHandler(
				new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				setContentPanelToWelcomeState();				
			}
		});
		
		this.navigationPanel.getAboutClickHandler().addClickHandler(
				new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				setContentPanelToAboutState();				
			}
		});

		this.navigationPanel.getResultsClickHandler().addClickHandler(
				new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				repaintForCalculationResult();				
			}
		});
	}
	
	
	
	public void setContentPanelToWelcomeState() {
		contentPanel.clear();
		contentPanel.setHeadline("Welcome!");
		contentPanel.add(new HTML(
				"<p>" +
				"Our goal is to boundle computational power from all over the world to solve scientific " +
				"problems in the field of bioinformatics." +
				"</p>" +
				"<p>" +
				"Javascript-Cloud-Computing (or short JSCC) is a brandnew grid-computing approach " +
				"that combines modern internet technology with scientific software-frameworks to one rich-internet-application." +			
				"To be more precise, we cross-compiled scientific algorithms (like Needleman-Wunsch)" +
				" from the Biojava-Project from Native Java to Javascript Code using the Google Webtool Kit.<br />" +
				"This way it is possible to participate simply by visiting this website."+				
				"</p>"));
		
		VerticalPanel vPanel = new VerticalPanel();
		vPanel.add(new HTML("<h2>"+WELCOME_MESSAGE+"</h2>"));
		
		
		HorizontalPanel hPanel = new HorizontalPanel();
		
		hPanel.add(new HTML(
				"<ul>" +
				"<li>participate in solving problems in bioinformatics</li>" +
				"<li>compete with your machine against other players</li>" +
				"<li>no installation required</li>" +
				"<li>no registration required</li>"+
				"</ul>"));
		hPanel.add(retryButton);
		
		this.retryButton.setText("Start");
		
		
		
		vPanel.add(hPanel);
		vPanel.add(highscorePanel);
		
		contentPanel.add(vPanel);
		
		
		
	}
	
	
	
		
	public void setContentPanelToAboutState(){
		contentPanel.clear();
		contentPanel.setHeadline("About");
		contentPanel.add(new HTML(
				"<h3>Who are we?</h3>" +
				"<p>" +
				 "The core team is (in alphabetical order): " +
					"<ul>" +
						"<li>" + 
						"Raphael Bauer, PhD (Freie Universit&auml;t of Berlin)" +
						"</li>" +
						"<li>" + 
						"Nico G&uuml;ttler, BSc. (Freie Universit&auml;t of Berlin)" +						
						"</li>" +
						"<li>" + 
						"Andreas Prlic, PhD (Skagg School of Pharmacy, San Diego Supercomputer Center, San Diego, USA)" +						
						"</li>" +
						"<li>" +
						"Marcus Schroeder, BSc. (Freie Universit&auml;t of Berlin)"+
						"</li>" +
					"</ul>" +
					"During the project we are working in close cooperation with Prof. Phil Bourne " +
					" from the Skagg School of Pharmacy in San Diego and Prof. Knut Reinert from the" +
					" Freie Universit&auml;t of Berlin." +
				"</p>" +
				"<h3>What happens on this website?</h3>" +
				"<p>" +
				"When you hit the \"Start\"-Button our server sends biological data to" +
				" your machine, e.g. three simple protein-sequences. Directly after that," +
				" your computer starts to align these sequences against each other with the" +
				" well-known Needleman-Wunsch algorithm. The implementation comes from the" +
				" Biojava-Project which we cross-compiled from Native Java to Javascript. " +
				"Time is taken for the whole alignment process (without sending data). " +
				"Since the protein-sequences do not change it is possible to record the " +
				"fastest times."+
				"</p>" +
				"<h3>Why are you doing this?</h3>" +
				"<p>" + 
							
				"Until now this benchmark-website is some kind of feasibility study or proof " +
				"of concept. But our goal is to boundle the power of home pcs to find solutions" +
				" for computational expensive problems in the field of bioinformatics (for" +
				" example the aligning of sequences or structures). And we want to make it " +
				"as comfortable as possible for you participate. That is why no registration " +
				"or installation will be required." + 
				"The main idea is that we take the existing implementation of scientific " +
				"algorithms from the Biojava Project and the observation that the overall " +
				"perfomance of Javascript in browsers is continously increasing. So far " +
				"javascript is at minimum twenty times slower (look figure below) than " +
				"native java but we expect the difference to vanish over time." +
				"</p>" +
				"<p>" +
				histogramm +
				"</p>"
		));
	}
	
	
	
	
	@Override
	public void repaintForError() {
		this.contentPanel.clear();
		
		contentPanel.setHeadline("Sorry. The calculation could " +
				"not be conducted. Please, try again.");
		
		retryButton.setText("Try again");
		contentPanel.add(retryButton);	
	}

	
	
	
	
	
	
	@Override
	public void repaintForCalculationRunning() {

		contentPanel.clear();
		contentPanel.setHeadline(CALCULATION_MESSAGE);
		contentPanel.add(spinner);
		
	}



	@Override
	public void repaintForCalculationResult() {

		contentPanel.clear();		
		contentPanel.setHeadline(THANK_YOU_MESSAGE);
		contentPanel.add(clientPerformancePanel);
				
		
		clientDetailsPanel.refreshPanel();
		
				
		HorizontalPanel horizontalContentPanel = new HorizontalPanel();		
		horizontalContentPanel.add(clientDetailsPanel);
		horizontalContentPanel.add(highscorePanel);
		
		
		contentPanel.add(horizontalContentPanel);
		retryButton.setText("Try again");
		contentPanel.add(retryButton);
	}
	
	
	
	
	
	
	
	
	@Override
	public void repaintForTesting(String stringToPrint) {
		this.contentPanel.clear();
		this.contentPanel.add(new Label(stringToPrint));
	}









	@Override
	public void refreshTopPlayerTable(ArrayList<String> clientIds,
			ArrayList<Float> times) {			
		
			this.highscorePanel.refreshTopPlayerTable(clientIds, times);
		
	}




	




	@Override
	public void refreshAllValues(String clientId, int trials,
			float fastestTime, String listId, int numberOfSequences) {
		
		this.clientPerformancePanel.refreshAllValues(clientId, fastestTime, listId);
		this.clientDetailsPanel.refreshAllValues(clientId, trials, fastestTime, listId, numberOfSequences);
	}
	

}
