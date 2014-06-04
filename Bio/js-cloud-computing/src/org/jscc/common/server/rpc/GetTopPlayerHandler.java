package org.jscc.common.server.rpc;

import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.jscc.app.client.objectify.PlayerEntity;
import org.jscc.common.client.rpc.GetTopPlayerAction;
import org.jscc.common.client.rpc.GetTopPlayerResult;
import org.jscc.common.client.rpc.PostAlignmentAction;
import org.jscc.common.client.rpc.PostAlignmentResult;
import org.jscc.common.server.dao.TopPlayerDao;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

@SuppressWarnings("unchecked")
public class GetTopPlayerHandler implements 
	ActionHandler<GetTopPlayerAction,GetTopPlayerResult> {
	
	private final Log logger;
	private final Provider<ServletContext> servletContext;
	private final Provider<HttpServletRequest> servletRequest;
	
	
	@Inject
	public GetTopPlayerHandler(
			final Log logger,
			final Provider<ServletContext> servletContext,
			final Provider<HttpServletRequest> servletRequest) {
		
		this.logger = logger;
		this.servletContext = servletContext;
		this.servletRequest = servletRequest;
	}
	
	
	
	
	@Override
	public GetTopPlayerResult execute(
			final GetTopPlayerAction action,
			final ExecutionContext context)
	
		
	throws ActionException {	
		
			
						
			
			// Get the top players from objectify thing
			TopPlayerDao topScorerDao = new TopPlayerDao();
			
			// #0 : clientId
			// #1 : times
			ArrayList<ArrayList<?>> scores = topScorerDao.getTopScorer(5);
			
			// Send the top players list back to the client
			GetTopPlayerResult getTopPlayerResult = new GetTopPlayerResult();
		
			getTopPlayerResult.setBestAlignmentClientIdList((ArrayList<String>) scores.get(0));
			getTopPlayerResult.setBestAlignmentTimesList((ArrayList<Float>) scores.get(1));
			
			return getTopPlayerResult;
			
	}
	
	@Override
	public Class<GetTopPlayerAction> getActionType() {
		return GetTopPlayerAction.class;
	}

	@Override
	public void undo(GetTopPlayerAction action, GetTopPlayerResult result,
			ExecutionContext context) throws ActionException {
		// TODO Auto-generated method stub
		
	}
	
}
