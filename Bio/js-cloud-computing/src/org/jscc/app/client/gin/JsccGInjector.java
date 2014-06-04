package org.jscc.app.client.gin;

import org.jscc.app.client.presenter.MainPagePresenter;

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.client.DispatchAsync;
import com.gwtplatform.dispatch.client.gin.DispatchAsyncModule;
import com.gwtplatform.mvp.client.EventBus;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyFailureHandler;


@GinModules({DispatchAsyncModule.class, JsccClientModule.class})
public interface JsccGInjector extends Ginjector {
	ProxyFailureHandler getProxyFailureHandler();
	PlaceManager getPlaceManager();
	EventBus getEventBus();
	Provider<MainPagePresenter> getMainPagePresenter();
	DispatchAsync getDispatchAsync();
}