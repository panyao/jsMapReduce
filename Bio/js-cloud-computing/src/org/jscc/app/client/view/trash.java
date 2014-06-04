//package org.jscc.app.client.view;
//
//import java.util.ArrayList;
//
//import org.jscc.app.client.presenter.MainPagePresenter;
//
//import com.google.gwt.event.dom.client.HasClickHandlers;
//import com.google.gwt.user.client.ui.Button;
//import com.google.gwt.user.client.ui.DecoratorPanel;
//import com.google.gwt.user.client.ui.FlexTable;
//import com.google.gwt.user.client.ui.FlowPanel;
//import com.google.gwt.user.client.ui.HTML;
//import com.google.gwt.user.client.ui.HorizontalPanel;
//import com.google.gwt.user.client.ui.Image;
//import com.google.gwt.user.client.ui.Label;
//import com.google.gwt.user.client.ui.VerticalPanel;
//import com.google.gwt.user.client.ui.Widget;
//import com.google.inject.Inject;
//import com.gwtplatform.mvp.client.ViewImpl;
//
//
//public class MainView extends ViewImpl implements MainPagePresenter.MyView{
//	
//	//dynamic values
//	private float calculationTime = 0;
//	private int clientRank = -1;
//	private int trials;
//	private String clientId = "";
//	private String listId = "";
//	
//	// Static Panels
//	private final HTML titlePanel;
//	private final HorizontalPanel headerPanel;
//	private VerticalPanel mainPanel;
//	private VerticalPanel contentPanel;
//	private HorizontalPanel horizontalContentPanel;
//	
//	
//	private VerticalPanel clientDetailsPanel;
//	private VerticalPanel topPlayerTablePanel;
//	
//	
//	private final Image logo = new Image("img/logo.png");
//	private final Image spinner = new Image("img/ajax-loader_2.gif");
//	
//	// Functional Widgets
//	private Button retryButton;
//	
//	// Dynamic Widgets
//	private HorizontalPanel clientPerformancePanel;
//	private FlexTable topScorerTable;
//	private FlexTable detailsTable;
//	
//	// Constants
//	private int TOP_SCORER_TABLE_ROW_NUMBER = 3;
//	private int TOP_SCORER_TABLE_COL_NUMBER = 1;
//	
//	private final String HEADER_TITLE = "Developing the next generation " +
//			"distributed grid";
//	
//	private final String WELCOME_MESSAGE = "Help the scientific community by " +
//			"doing calculations and compete with your machine against players!";
//	
//	private final String CALCULATION_MESSAGE = "Lean back! Your computer is " +
//			"doing some really important calculations!";
//	
//	private final String THANK_YOU_MESSAGE = "Thank you very much! You just " +
//			"did some scientific computation for us!";
//	
//	private final String ASSISTANCE_MESSAGE = "You helped the scientific " +
//			"community to improve their further research a lot.";
//	
//	private final String YOUR_NAME = "Your current name is: ";
//
//	@Inject
//	public MainView(){
//		
//		//okay... most graphic structures are defined in the constructor
//		
//		//starting with headerPanel
//		titlePanel = new HTML();
//		headerPanel = new HorizontalPanel();
//		headerPanel.setStylePrimaryName("headerPanel");
//		titlePanel.setHTML("<h1>"+HEADER_TITLE+"</h1>");
//		headerPanel.add(logo);
//		headerPanel.add(titlePanel);
//		
//		//client performance panel at the top of content panel
//		clientPerformancePanel = new HorizontalPanel();
//		
//		//left box going into the twoColumnsPanel
//		//TODO: set table here, change only values in rfresh
//		detailsTable = new FlexTable();
//		clientDetailsPanel = new VerticalPanel();
//		clientDetailsPanel.setStylePrimaryName("contentBoxPanel");
//		clientDetailsPanel.add(detailsTable);
//		
//		//right box also going into the twoColumnsPanel
//		topScorerTable = new FlexTable();
//		topScorerTable.setText(0,0, "Pos.");
//		topScorerTable.setText(0,1, "Client ID");
//		topScorerTable.setText(0,2, "Time");
//		
//		
//		topPlayerTablePanel = new VerticalPanel();
//		topPlayerTablePanel.setStylePrimaryName("contentBoxPanel");
//		topPlayerTablePanel.add(topScorerTable);
//		
//		//two column box for the center of the content panel
//		horizontalContentPanel = new HorizontalPanel();
//		horizontalContentPanel.add(clientDetailsPanel);
//		horizontalContentPanel.add(topPlayerTablePanel);
//		
//		
//		
//		//retry button
//		retryButton = new Button();
//		retryButton.setStylePrimaryName("retryButton");
//		
//		
//		
//		//contentPanel
//		contentPanel = new VerticalPanel();
//		contentPanel.setStylePrimaryName("contentPanel");		
//		
//		
//		
//		//mainPanel aka connect everything
//		mainPanel = new VerticalPanel();
//		mainPanel.setStylePrimaryName("mainPanel");
//		mainPanel.add(headerPanel);
//		mainPanel.add(contentPanel);
//		
//		//refresh contentPanel to welcome state
//		//setContentPanelToWelcomeState();
//	}
//	
//	
//	
//	
//	private void setContentPanelToCalculationState(){
//		contentPanel.clear();
//		contentPanel.add(new HTML("<h2>"+CALCULATION_MESSAGE+"</h2>"));
//		contentPanel.add(spinner);
//	}
//	
//	
//	
//	
//	public void setContentPanelToWelcomeState() {
//		contentPanel.clear();
//		contentPanel.add(new HTML("<h2>Welcome!</h2>"));
//		contentPanel.add(new HTML("<h3>"+WELCOME_MESSAGE+"</h3>"));
//		
//		contentPanel.add(topPlayerTablePanel);
//		
//		this.retryButton.setText("Start");				
//		contentPanel.add(retryButton);
//		
//		
//	}
//	
//	private void setContentPanelToResultState(){
//		
//		
//		refreshPerformancePanel();		
//		contentPanel.clear();
//		contentPanel.add(new HTML("<h2>"+THANK_YOU_MESSAGE+"</h2>"));
//		contentPanel.add(clientPerformancePanel);
//				
//		
//		
//		refreshDetailsTable();
//		clientDetailsPanel.clear();
//		clientDetailsPanel.add(new HTML("<h2>Your Details</h2>"));
//		clientDetailsPanel.add(detailsTable);
//		
//		
//		
//		
//		topPlayerTablePanel.clear();
//		topPlayerTablePanel.add(new HTML("<h2>Top-Scorer</h2>"));
//		topPlayerTablePanel.add(topScorerTable);
//		
//		
//		
//		horizontalContentPanel.clear();
//		horizontalContentPanel.add(clientDetailsPanel);
//		horizontalContentPanel.add(topPlayerTablePanel);
//		
//		
//		
//		contentPanel.add(horizontalContentPanel);
//		retryButton.setText("Try again");
//		contentPanel.add(retryButton);
//	}
//	
//	private void setContentPanelToErrorState() {
//		this.contentPanel.clear();
//		contentPanel.add(new HTML("<h2>Sorry. The calculation could " +
//				"not be conducted. Please, try again.</h2>"));
//		retryButton.setText("Try again");
//		contentPanel.add(retryButton);
//	}
//	
//	private void setContentPanelToTestingState(String stringToPrint) {
//		this.contentPanel.clear();
//		this.contentPanel.add(new Label(stringToPrint));
//	}
//	
//	
//	
//	private void refreshPerformancePanel(){
//		clientPerformancePanel.clear();
//		
//			
//		
//		//TIME BOX
//		VerticalPanel timeBox = new VerticalPanel();
//		timeBox.setStyleName("performancePanelBox");
//		HTML headingTimeBox = new HTML("<h3>Calculation Time</h3>");
//		HTML valueTimeBox = new HTML(this.calculationTime+"ms");
//		timeBox.add(headingTimeBox);
//		timeBox.add(valueTimeBox);
//		
//		
//		
//		// CLIENT ID BOX
//		VerticalPanel clientIdBox = new VerticalPanel();
//		clientIdBox.setStyleName("performancePanelBox");
//		HTML headingClientBox = new HTML("<h3>Your ID</h3>");
//		HTML valueClientBox = new HTML(this.clientId);
//		clientIdBox.add(headingClientBox);
//		clientIdBox.add(valueClientBox);
//		
//		
//		
//		//SEQUENCE LIST ID BOX
//		VerticalPanel seqListIdBox = new VerticalPanel();
//		seqListIdBox.setStyleName("performancePanelBox");
//		HTML headingSeqListIdBox = new HTML("<h3>Sequence Set</h3>");
//		HTML valueSeqListIdBox = new HTML(this.listId);
//		seqListIdBox.add(headingSeqListIdBox);
//		seqListIdBox.add(valueSeqListIdBox);
//		
//		
//		
//		
//		
//		clientPerformancePanel.add(clientIdBox);
//		clientPerformancePanel.add(timeBox);
//		clientPerformancePanel.add(seqListIdBox);
//		
//		
//	}
//	
//	
//
//	@Override
//	public HasClickHandlers getRetryButtonClick() {
//		return this.retryButton;
//	}
//
//	@Override
//	public void refreshAllValues(
//			String clientId,
//			int trials,
//			float fastestTime,
//			String listId,
//			int numberOfSequences){		
//		
//		this.trials = trials;
//		this.clientId = clientId;
//		this.calculationTime  = fastestTime;
//		this.listId = listId;
//		this.clientRank = numberOfSequences;
//	}
//	
//	@Override
//	public void repaintForError() {
//		setContentPanelToErrorState();		
//	}
//
//	@Override
//	public void repaintForCalculationRunning() {
//		setContentPanelToCalculationState();
//	}
//
//	@Override
//	public void repaintForCalculationResult() {
//		setContentPanelToResultState();
//	}
//	
//	@Override
//	public Widget asWidget() {
//		return this.mainPanel;
//	}
//
//	@Override
//	public void repaintForTesting(String stringToPrint) {
//		setContentPanelToTestingState(stringToPrint);
//	}
//	
//	
//	
//	
//	
//	public void refreshTopPlayerTable(			
//			ArrayList<String> clientIds,
//			ArrayList<Float> times) {
//		
//		for(int i = 1; i < clientIds.size(); i++){
//			
//			topScorerTable.setText(i, 0, i+"");
//			topScorerTable.setText(i, 1, clientIds.get(i));
//			topScorerTable.setText(i, 2, times.get(i)+"");
//			
//		}
//		
//	}
//	
//
//}
