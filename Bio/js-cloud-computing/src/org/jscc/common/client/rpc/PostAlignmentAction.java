package org.jscc.common.client.rpc;


import com.gwtplatform.dispatch.shared.ActionImpl;

/**
 * Providing client data like client calculation time and client score for
 * the corresponding Handler to print to the console.
 * This RPC serves, more or less, only to print data.
 * @author nico
 *
 */
public class PostAlignmentAction extends
ActionImpl<PostAlignmentResult>{

	private static final long serialVersionUID = -1291600029562217803L;
	private String clientID;	
	private float time_client;	
	private String listId;
	private int score;
	
	

	@SuppressWarnings("unused")
	private PostAlignmentAction() {}
	
	public PostAlignmentAction(			
			String clientID,
			float time_client,			
			String listId,
			int score){
		
		this.clientID = clientID;
		this.setTime(time_client);
		this.setScore(score);
		this.setListId(listId);
		
				
	}
	
	
	
	public float getTimeClient() {
		return this.time_client;
	}
	
	public void setTime(float time) {
		this.time_client = time;
	}
	
	
	public String getClientID(){
		return this.clientID;
	}
	
	public String getListId() {
		return listId;
	}

	public void setListId(String listId) {
		this.listId = listId;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
}
