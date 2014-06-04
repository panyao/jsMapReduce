//package com.seethebig.common.client.ui;
//
//import net.customware.gwt.presenter.client.EventBus;
//import net.customware.gwt.presenter.client.place.Place;
//import net.customware.gwt.presenter.client.place.PlaceRequest;
//import net.customware.gwt.presenter.client.widget.WidgetDisplay;
//import net.customware.gwt.presenter.client.widget.WidgetPresenter;
//
//import com.google.gwt.event.dom.client.ClickEvent;
//import com.google.gwt.event.dom.client.ClickHandler;
//import com.google.gwt.event.dom.client.HasClickHandlers;
//import com.google.gwt.user.client.ui.HasValue;
//import com.google.inject.Inject;
//
//
//public class SimpleUserInputDialogPresenter 
//extends WidgetPresenter<SimpleUserInputDialogPresenter.Display> {
//
//	
//	public interface Display extends WidgetDisplay {
//		
//		void setTitleOfDialog(String title);
//		
//		void setMessageAsHtml(String messageAsHtml);
//		
//		
//		HasValue<String> getUserInput();
//		
//		HasClickHandlers getOkClick();
//		
//		
//		HasClickHandlers getCancelClick();
//		
//		
//		void hideDialog();
//		
//		void showDialog();
//		
//	}
//	
//
//	
//	@Inject
//	SimpleUserInputDialogPresenter(
//			Display display,
//			EventBus eventBus) {
//	
//			super(display, eventBus);
//
//	}
//
//	@Override
//	public Place getPlace() {
//		return null;
//	}
//
//	@Override
//	protected void onBind() {
//		
//		display.getCancelClick().addClickHandler(new ClickHandler() {
//			
//			@Override
//			public void onClick(ClickEvent event) {
//
//				display.hideDialog();				
//			}
//		});	
//		
//	}
//
//	@Override
//	protected void onPlaceRequest(PlaceRequest request) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	protected void onUnbind() {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void refreshDisplay() {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void revealDisplay() {
//		// TODO Auto-generated method stub
//		
//	}
//	
//	
//
//}
