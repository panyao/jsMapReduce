package org.jscc.common.server.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Stage;
import com.google.inject.servlet.GuiceServletContextListener;
import com.gwtplatform.dispatch.server.guice.DispatchModule;

public class ServletContextListener extends GuiceServletContextListener {
	
	@Override
	protected Injector getInjector() {
		return Guice.createInjector(
				Stage.DEVELOPMENT, 
				new ServletConfigModule(),
				new ActionHandlerConfigModule(),
				new DispatchModule());
	}
}