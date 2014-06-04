package org.jscc.common.server.rpc;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.jscc.common.client.rpc.TestAction;
import org.jscc.common.client.rpc.TestResult;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class TestHandler implements ActionHandler<TestAction, TestResult>{
	
	 private Provider<HttpServletRequest> requestProvider;
	  private ServletContext servletContext;


	
	@Inject
	public TestHandler(ServletContext servletContext,
		      Provider<HttpServletRequest> requestProvider) {
		this.servletContext = servletContext;
	    this.requestProvider = requestProvider;

	}

	@Override
	public TestResult execute(TestAction action, ExecutionContext context)
			throws ActionException {
		TestResult result = new TestResult();
		result.setReturnString("Hello");
		return result;
	}

	@Override
	public Class<TestAction> getActionType() {
		return TestAction.class;
	}

	@Override
	public void undo(TestAction action, TestResult result,
			ExecutionContext context) throws ActionException {
		// TODO Auto-generated method stub
		
	}

}
