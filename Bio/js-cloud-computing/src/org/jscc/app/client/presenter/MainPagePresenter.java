package org.jscc.app.client.presenter;

import java.util.ArrayList;
import java.util.Random;

import org.jscc.app.client.util.BenchmarkUtil;
import org.jscc.common.client.rpc.GetSequenceListAction;
import org.jscc.common.client.rpc.GetSequenceListResult;
import org.jscc.common.client.rpc.GetTopPlayerAction;
import org.jscc.common.client.rpc.GetTopPlayerResult;
import org.jscc.common.client.rpc.PostAlignmentAction;
import org.jscc.common.client.rpc.PostAlignmentResult;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.gwtplatform.dispatch.client.DispatchAsync;
import com.gwtplatform.mvp.client.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealRootLayoutContentEvent;

@Singleton
public class MainPagePresenter extends
Presenter<MainPagePresenter.MyView, MainPagePresenter.MyProxy> {
	
	public static final String PLACE_NAME = "mainPage";
	private String clientID;
	DispatchAsync dispatchAsync;
	
	@ProxyStandard
	@NameToken(PLACE_NAME)
	public interface MyProxy extends ProxyPlace<MainPagePresenter> {}
	
//	@ContentSlot
//	public static final Type<RevealContentHandler<?>> TYPE_SetMainContent =
//		new Type<RevealContentHandler<?>>();
	
	public interface MyView extends View {
		
		HasClickHandlers getRetryButton();
		
		void repaintForCalculationRunning();
		void repaintForCalculationResult();
		void repaintForError();
		void repaintForTesting(String stringToPrint);
		void refreshTopPlayerTable(ArrayList<String> clientIds, ArrayList<Float> times);		
		
		void setContentPanelToWelcomeState();
		
		void refreshAllValues(
				String clientId,
				int trials,
				float fastestTime,
				String listId,
				int numberOfSequences
		);
		
		Widget asWidget();
		
	}
	
	
	@Inject
	public MainPagePresenter(
			EventBus eventBus,
			MyView view,
			MyProxy proxy,
			DispatchAsync dispatchAsync) {
		super(eventBus, view, proxy);
		this.dispatchAsync = dispatchAsync;
	}
	
	
	/**
	 * Necessary to reveal this presenter and its associated View in parent.
	 */
	@Override
	protected void revealInParent() {
		// set the Client ID
		setRandomClientID();
		
		// maybe calling methods necessary to start in onReset() or onReveal()
		// Dunno... trying...		
		//TODO really this this ???		
		
		RevealRootLayoutContentEvent.fire(this, this);
		
		refreshTobScorerTable();
		getView().setContentPanelToWelcomeState();
	}
	
	
	/**
	 * Add the alignment function to the button
	 */
	@Override
	protected void onBind() {
		super.onBind();
		
		getView().getRetryButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {	
				
				//Show the progress bar
				getView().repaintForCalculationRunning();
				
				
				//Align the Sequences
				doAlignmentOnServerAndGetSequenceList();
			}
		});
	}
	
	
	
	
	private void setRandomClientID(){
		
		
		Random random = new Random();
		short nextRandomInt = (short) random.nextInt();
		if(nextRandomInt < 0) nextRandomInt = (short) (0 - nextRandomInt);
		this.clientID = "Client_" + nextRandomInt;
		
	}
	
	
	
	
	
	/**
	 * Get a list of sequences from the server, then align them
	 */	
	private void doAlignmentOnServerAndGetSequenceList() {
		dispatchAsync.execute(
				new GetSequenceListAction(),
				new AsyncCallback<GetSequenceListResult>() {					
			
					
			public void onFailure(Throwable caught) {
				getView().repaintForError();
				System.out.println(caught);
			};

			
			public void onSuccess(GetSequenceListResult getSequenceListResult) {
				
				alignSequences(getSequenceListResult);				
			};
		});
	}
	
	
	
	
	
	
	/**
	 * Aligns a list of sequences against each other
	 * @param getSequenceListResult
	 */
	private void alignSequences(GetSequenceListResult getSequenceListResult){
		
		
		BenchmarkUtil benchmarkUtil = new BenchmarkUtil();
		benchmarkUtil.setSequences(getSequenceListResult.getSequenceList());
		benchmarkUtil.performAlignment();

		
		
		//send client information to server for printing
		postAlignmentAction(
				getSequenceListResult.getListID(),
				this.clientID,
				benchmarkUtil.getComputationTime(),
				benchmarkUtil.getTotalScore());
	}
	
	
	
	
	/**
	 * Whenever called, it refreshes the topscorer table in the MainView
	 */
	public void refreshTobScorerTable(){

		dispatchAsync.execute(
				new GetTopPlayerAction(),
				new AsyncCallback<GetTopPlayerResult>() {
					
					public void onFailure(Throwable caught) {				
						getView().repaintForError();				
						System.out.println(caught);
					};
					public void onSuccess(GetTopPlayerResult getTopPlayerResult) {
										
						getView().refreshTopPlayerTable(
								getTopPlayerResult.getBestAlignmentClientIdList(),
								getTopPlayerResult.getBestAlignmentTimesList());		
								
					
					};
		});
	}

	
	
	
	
	
	/**
	 * Sends the result of the alignment back to the server
	 * @param listID
	 * @param clientID
	 * @param time
	 * @param score
	 */
	public void postAlignmentAction(
			String listID,
			String clientID,
			float time,
			int score){
		
		
		dispatchAsync.execute(
				new PostAlignmentAction(clientID, time, listID, score),
				new AsyncCallback<PostAlignmentResult>() {					
					
					@Override
					public void onFailure(Throwable caught) {
						System.out.println(caught);
					}					
					
					public void onSuccess(PostAlignmentResult result) {		
						
						
												
						
						getView().refreshAllValues(
								result.getClientId(),
								result.getTrials(),
								result.getFastestTime(),
								result.getListId(),
								result.getNumberOfSequences()
						);						
						
						
						getView().repaintForCalculationResult();

						//Refresh top scorer table
						refreshTobScorerTable();
					}
				}
		);
		
		
	}	
}
