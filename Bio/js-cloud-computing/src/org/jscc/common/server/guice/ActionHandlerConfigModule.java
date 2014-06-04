package org.jscc.common.server.guice;


import org.apache.commons.logging.Log;
import org.jscc.common.client.rpc.GetSequenceListAction;
import org.jscc.common.client.rpc.GetTopPlayerAction;
import org.jscc.common.client.rpc.PostAlignmentAction;
import org.jscc.common.client.rpc.TestAction;
import org.jscc.common.server.rpc.GetSequenceListHandler;
import org.jscc.common.server.rpc.GetTopPlayerHandler;
import org.jscc.common.server.rpc.PostAlignmentHandler;
import org.jscc.common.server.rpc.TestHandler;

import com.google.inject.Singleton;
import com.gwtplatform.dispatch.server.guice.HandlerModule;

/**
 * Module binding handlers and configurations
 */
public class ActionHandlerConfigModule extends HandlerModule {

	@Override
	protected void configureHandlers() {
		
		bindHandler(GetSequenceListAction.class, GetSequenceListHandler.class);

		bindHandler(PostAlignmentAction.class, PostAlignmentHandler.class);
		
		bindHandler(GetTopPlayerAction.class, GetTopPlayerHandler.class);
		
		bindHandler(TestAction.class, TestHandler.class);
		
		bind(Log.class).toProvider(LogProvider.class).in(Singleton.class);
	}
	
}