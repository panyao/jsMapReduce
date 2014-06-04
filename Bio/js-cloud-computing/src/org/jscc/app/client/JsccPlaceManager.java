package org.jscc.app.client;

import org.jscc.app.client.presenter.MainPagePresenter;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.gwtplatform.mvp.client.EventBus;
import com.gwtplatform.mvp.client.proxy.PlaceManagerImpl;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.TokenFormatter;

@Singleton
public class JsccPlaceManager extends PlaceManagerImpl {

	@Inject
	public JsccPlaceManager(EventBus eventBus, TokenFormatter tokenFormatter) {
		super(eventBus, tokenFormatter);
	}

	@Override
	public void revealDefaultPlace() {
		revealPlace(new PlaceRequest(MainPagePresenter.PLACE_NAME));
	}

}
