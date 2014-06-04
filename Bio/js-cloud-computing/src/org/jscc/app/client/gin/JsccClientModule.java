package org.jscc.app.client.gin;

import org.jscc.app.client.DefaultPlace;
import org.jscc.app.client.JsccPlaceManager;
import org.jscc.app.client.presenter.MainPagePresenter;
import org.jscc.app.client.view.MainView;

import com.google.inject.Singleton;
import com.gwtplatform.dispatch.shared.SecurityCookie;
import com.gwtplatform.mvp.client.DefaultEventBus;
import com.gwtplatform.mvp.client.DefaultProxyFailureHandler;
import com.gwtplatform.mvp.client.EventBus;
import com.gwtplatform.mvp.client.RootPresenter;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.proxy.ParameterTokenFormatter;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyFailureHandler;
import com.gwtplatform.mvp.client.proxy.ProxyRaw;
import com.gwtplatform.mvp.client.proxy.TokenFormatter;

/**
 * First part of connecting everything. That is PlaceManager, EventBus
 * etc. and presenter-view-proxy triplets. Part two would be the GInjector.
 * 
 * @author nico
 *
 */
public class JsccClientModule extends AbstractPresenterModule{

	@Override
	protected void configure() {
		bindConstant().annotatedWith( SecurityCookie.class ).to("MYCOOKIE");

		bind(EventBus.class).to(DefaultEventBus.class).in(Singleton.class);
		bind(PlaceManager.class).to(JsccPlaceManager.class).in(Singleton.class);
		
		bind(TokenFormatter.class).to(ParameterTokenFormatter.class).in(
				Singleton.class);
		
		bind(RootPresenter.class).asEagerSingleton();
		
		bind(ProxyFailureHandler.class).to(DefaultProxyFailureHandler.class).in(
				Singleton.class);
		
		bind(ProxyRaw.class).annotatedWith(DefaultPlace.class).to(
				MainPagePresenter.MyProxy.class);
		

		//binding the triplet presenter-proxy-view together
		bindPresenter(
				MainPagePresenter.class,
				MainPagePresenter.MyView.class,
				MainView.class,
				MainPagePresenter.MyProxy.class);
	}

}
