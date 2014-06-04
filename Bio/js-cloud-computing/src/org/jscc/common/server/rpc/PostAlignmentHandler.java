package org.jscc.common.server.rpc;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.jscc.app.client.objectify.PlayerEntity;
import org.jscc.common.client.rpc.PostAlignmentAction;
import org.jscc.common.client.rpc.PostAlignmentResult;
import org.jscc.common.server.dao.TopPlayerDao;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

@SuppressWarnings("unchecked")
public class PostAlignmentHandler implements 
ActionHandler<PostAlignmentAction,PostAlignmentResult> {
	
	private final Log logger;
	private final Provider<ServletContext> servletContext;
	private final Provider<HttpServletRequest> servletRequest;
	
	
	@Inject
	public PostAlignmentHandler(
			final Log logger,
			final Provider<ServletContext> servletContext,
			final Provider<HttpServletRequest> servletRequest) {
		
		this.logger = logger;
		this.servletContext = servletContext;
		this.servletRequest = servletRequest;
	}
	
	
	@Override
	public PostAlignmentResult execute(
			final PostAlignmentAction postAlignmentAction,
			final ExecutionContext context)
	
	throws ActionException {
		
		
		//Add the new result to the top scorer list
		TopPlayerDao topPlayerDao = new TopPlayerDao();
		topPlayerDao.addClient(postAlignmentAction.getClientID(), postAlignmentAction.getTimeClient());	
		
		
		PlayerEntity player = topPlayerDao.getPlayer(
				postAlignmentAction.getClientID());
				
		// Print out results on the server
		System.out.println("[Server says] " + postAlignmentAction.getListId()+
				" | Client | score: " + postAlignmentAction.getScore() +
				" * time: " + postAlignmentAction.getTimeClient() + " | " +
				postAlignmentAction.getClientID());
		
		
		// Send the results back to the client
		PostAlignmentResult postAlignmentResult = new PostAlignmentResult(
				player.getClientId(),
				player.getTime(),
				player.getTrial(),
				postAlignmentAction.getListId(),
				0
				);		
		
		
		return postAlignmentResult;
	}
	
	@Override
	public Class<PostAlignmentAction> getActionType() {
		return PostAlignmentAction.class;
	}	

	@Override
	public void undo(PostAlignmentAction action, PostAlignmentResult result,
			ExecutionContext context) throws ActionException {
		// TODO Auto-generated method stub
		
	}
	
}
