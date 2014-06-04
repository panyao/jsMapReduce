package org.jscc.common.client.rpc;


import com.google.gwt.user.client.rpc.IsSerializable;
import com.gwtplatform.dispatch.shared.Result;

public class PostAlignmentResult implements Result, IsSerializable {

	private static final long serialVersionUID = -1391600029562217803L;
	
	private String clientId;
	private float fastestTime;
	private int trials;
	private String listId;
	private int numberOfSequences;
	
	@SuppressWarnings("unused")
	private PostAlignmentResult(){}
	
	public PostAlignmentResult(
			String clientId,
			float fastestTime,
			int trials,
			String listId,
			int numberOfSequence) {
		
		this.clientId = clientId;
		this.fastestTime = fastestTime;
		this.trials = trials;
		this.listId = listId;
		this.numberOfSequences = numberOfSequence;
	}

	/**
	 * @return the clientId
	 */
	public String getClientId() {
		return clientId;
	}

	/**
	 * @return the fastestTime
	 */
	public float getFastestTime() {
		return fastestTime;
	}

	/**
	 * @return the trials
	 */
	public int getTrials() {
		return trials;
	}

	/**
	 * @return the listId
	 */
	public String getListId() {
		return listId;
	}

	/**
	 * @return the numberOfSequences
	 */
	public int getNumberOfSequences() {
		return numberOfSequences;
	}	
		
	

}