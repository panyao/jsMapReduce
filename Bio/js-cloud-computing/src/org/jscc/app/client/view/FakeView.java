//package org.jscc.app.client.view;
//
//import java.util.ArrayList;
//
//import com.google.gwt.event.dom.client.HasClickHandlers;
//import com.google.gwt.user.client.ui.FlowPanel;
//import com.google.gwt.user.client.ui.Label;
//import com.google.gwt.user.client.ui.VerticalPanel;
//import com.google.gwt.user.client.ui.Widget;
//import com.google.inject.Inject;
//import com.gwtplatform.mvp.client.ViewImpl;
//import org.jscc.app.client.presenter.MainPagePresenter;
//
//public class FakeView extends ViewImpl implements MainPagePresenter.MyView {
//	
//	FlowPanel mainPanel;
//	VerticalPanel inputPanel;
//	
//	@Inject
//	public FakeView() {
//		mainPanel = new FlowPanel();
//		inputPanel = new VerticalPanel();
//		inputPanel.add(new Label("Fake View here."));
//		mainPanel.add(inputPanel);
//	}
//
//
//	@Override
//	public Widget asWidget() {
//		return mainPanel;
//	}
//
//
//	@Override
//	public HasClickHandlers getRetryButtonClick() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//
//	@Override
//	public void repaintForCalculationRunning() {
//		// TODO Auto-generated method stub
//		
//	}
//
//
//	@Override
//	public void repaintForCalculationResult() {
//		// TODO Auto-generated method stub
//		
//	}
//
//
//	@Override
//	public void refreshAllValues(int calculationTime, int gameRank,
//			int numberOfSequences, int[] topThree, Boolean inTopTen,
//			String clientId, String listId) {
//		// TODO Auto-generated method stub
//		
//	}
//
//
//	@Override
//	public void repaintForError() {
//		// TODO Auto-generated method stub
//		
//	}
//
//
//	@Override
//	public void repaintForTesting(String stringToPrint) {
//		// TODO Auto-generated method stub
//		
//	}
//
//
//	@Override
//	public void refreshTopPlayerTable(ArrayList<String> clientIds,
//			ArrayList<Float> times) {
//		// TODO Auto-generated method stub
//		
//	}
//
//}
