package org.jscc.common.server.guice;

import java.util.logging.Logger;

import org.jscc.app.server.JsccServlet;

import com.google.inject.servlet.ServletModule;
import com.gwtplatform.dispatch.server.DispatchServiceImpl;
import com.gwtplatform.dispatch.server.HttpSessionSecurityCookieFilter;
import com.gwtplatform.dispatch.shared.Action;
import com.gwtplatform.dispatch.shared.SecurityCookie;

class ServletConfigModule extends ServletModule {
	
	private static final Logger logger =
		Logger.getLogger(ServletConfigModule.class.getName());
	
	@Override 
	protected void configureServlets() {
		logger.severe("config servlets");
		logger.severe("identity hashcode: " + System.identityHashCode(this));
		logger.severe("thread id: " + Thread.currentThread().getId());
		bindConstant().annotatedWith( SecurityCookie.class ).to("MYCOOKIE");
		

		// EXTREMELY IMPORTANT!!!!!
		// Always put normal "serve" before something like "/*" since this
		// catch every request!!! => Result: No RPC and no error message.
		filter("/*").through(
				HttpSessionSecurityCookieFilter.class);
		
		serve( "/gwtapp/" + Action.DEFAULT_SERVICE_NAME ).with(
				DispatchServiceImpl.class );
		
	
		serve("/*").with(JsccServlet.class);
	}

}
