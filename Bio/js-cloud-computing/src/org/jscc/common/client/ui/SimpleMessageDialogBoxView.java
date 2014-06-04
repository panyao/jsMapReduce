//package com.seethebig.common.client.ui;
//
//import com.google.gwt.event.dom.client.HasClickHandlers;
//import com.google.gwt.user.client.ui.Button;
//import com.google.gwt.user.client.ui.DialogBox;
//import com.google.gwt.user.client.ui.FlowPanel;
//import com.google.gwt.user.client.ui.HTML;
//import com.google.gwt.user.client.ui.HasHorizontalAlignment;
//import com.google.gwt.user.client.ui.VerticalPanel;
//import com.google.gwt.user.client.ui.Widget;
//
//
//public class SimpleMessageDialogBoxView extends DialogBox
//		implements SimpleMessageDialogBoxPresenter.Display {
//
//	
//	
//	private Button okButton = new Button("OK");
//	
//	private HTML messageAsHtml = new HTML("");
//	
//	
//	public SimpleMessageDialogBoxView() {
//		
//		super(true, true);
//		
//
//		VerticalPanel mainPanel = new VerticalPanel();
//		
//		this.setWidget(mainPanel);	
//		
//		this.setAnimationEnabled(true);
//		this.setGlassEnabled(true);
//		
//		
//
//		drawDialogBox(mainPanel);
//	}
//	
//	
//	
//	void drawDialogBox(VerticalPanel mainPanel) {
//		
//		mainPanel.setSpacing(5);
//		mainPanel.add(messageAsHtml);
//	
//		FlowPanel buttonPanel = new FlowPanel();
//
//		buttonPanel.add(okButton);
//		
//		mainPanel.add(buttonPanel);
//
//		mainPanel.setCellHorizontalAlignment(
//				buttonPanel, 
//				HasHorizontalAlignment.ALIGN_RIGHT);
//
//	}
//
//
//
//	@Override
//	public HasClickHandlers getOkClick() {
//		return okButton;
//	}
//
//	@Override
//	public void hideDialog() {
//		this.hide();		
//	}
//	
//	@Override
//	public void showDialog() {
//		this.center();		
//	}
//
//
//
//	@Override
//	public void setMessageAsHtml(String messageAsHtml) {
//		this.messageAsHtml.setHTML(messageAsHtml);
//		
//	}
//
//
//
//	@Override
//	public Widget asWidget() {
//		return this;
//	}
//
//
//
//	@Override
//	public void startProcessing() {
//		// TODO Auto-generated method stub
//		
//	}
//
//
//
//	@Override
//	public void stopProcessing() {
//		// TODO Auto-generated method stub
//		
//	}
//
//
//
//	@Override
//	public void setTitleOfDialog(String title) {
//		this.setTitle(title);
//		
//	}
//	
//
//}
