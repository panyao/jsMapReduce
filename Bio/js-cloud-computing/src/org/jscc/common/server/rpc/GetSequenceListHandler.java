package org.jscc.common.server.rpc;

import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.jscc.common.client.rpc.GetSequenceListAction;
import org.jscc.common.client.rpc.GetSequenceListResult;
import org.jscc.common.server.util.BenchmarkServerUtil;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

@SuppressWarnings("unchecked")
public class GetSequenceListHandler
implements ActionHandler<GetSequenceListAction, GetSequenceListResult> {
	
	private final Log logger;
	private final Provider<ServletContext> servletContext;
	private final Provider<HttpServletRequest> servletRequest;
	
	
	@Inject
	public GetSequenceListHandler(
			final Log logger,
			final Provider<ServletContext> servletContext,
		    final Provider<HttpServletRequest> servletRequest) {
		
		this.logger = logger;
		this.servletContext = servletContext;
		this.servletRequest = servletRequest;
	}

	@Override
	public GetSequenceListResult execute(
			final GetSequenceListAction action,
			final ExecutionContext context)
	throws ActionException {

		GetSequenceListResult getResult = new GetSequenceListResult();
		String filename = "TIR-like";
		
		// Doing an alignment benchmark on the server
		BenchmarkServerUtil benchmarkServerUtil = new BenchmarkServerUtil();
		benchmarkServerUtil.setSequencesFromFastaFile(filename);
		benchmarkServerUtil.performAlignment();
		
		System.out.println("[Server says] List size: "+
				benchmarkServerUtil.getSequences().size());
		
		System.out.println("[Server says] Avg. length: " +
				benchmarkServerUtil.getAverageSequenceLength());
		
		System.out.println("[Server says] | Server  | score:" +
				benchmarkServerUtil.getTotalScore() + " * time: " + 
				benchmarkServerUtil.getComputationTime() + "s");

		ArrayList<String> sequenceList = benchmarkServerUtil.getSequences();
		getResult.setSequenceList(sequenceList);
		getResult.setListID(filename);
		return getResult;
	}
	
	@Override
	public Class<GetSequenceListAction> getActionType() {
		return GetSequenceListAction.class;
	}

	@Override
	public void undo(GetSequenceListAction action,
			GetSequenceListResult result, ExecutionContext context)
			throws ActionException {
		// TODO Auto-generated method stub
		
	}
	
	
}