package org.jscc.app.client;

import org.jscc.app.client.gin.JsccGInjector;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.gwtplatform.mvp.client.DelayedBindRegistry;

public class JsccApp implements EntryPoint {
	
	private final JsccGInjector ginjector = GWT.create(JsccGInjector.class);
	
	public void onModuleLoad() {
		DelayedBindRegistry.bind(ginjector);
		ginjector.getPlaceManager().revealCurrentPlace();
	}
	
}
