package org.jscc.common.server.dao;

import java.util.ArrayList;
import java.util.Random;

import org.jscc.app.client.objectify.PlayerEntity;

import com.google.appengine.api.datastore.QueryResultIterator;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Query;
import com.googlecode.objectify.helper.DAOBase;


/**
 * Handles the top scorer objects
 * @author Workspace
 */
public class TopPlayerDao extends DAOBase{
	
	
	private int TOP_SCORER_LIST_SIZE = 5;
	
	private ArrayList<String> bestAlignmentClientIdList;
	private ArrayList<Float> bestAlignmentTimesList;
		
	
	
	// static field serves to register all entity class used.
	// Mandatory for Objectify use!!!
	static {
		ObjectifyService.register(PlayerEntity.class);
	}
	
	
	
	
	public void addClient(String clientId, float time){
		
		PlayerEntity topScorer = new PlayerEntity(clientId, time);
		
		try {	
			
			//Check if user already exists!			
			if(ofy().query(PlayerEntity.class).filter("clientId", clientId).get() != null){
				
				//user exists! now then get the entity
				topScorer = ofy().query(PlayerEntity.class).filter("clientId", clientId).get();
				topScorer.increaseTrialCounter();
				// compare new time with fastest of the
				// current user and update it if necessary
				if(time < topScorer.getTime()){
					topScorer.setTime(time);
				}
			}
			// Add new/Update existing User
			ofy().put(topScorer);	
						
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ERROR: Adding/Updating the client in "+this.getClass().getName()+": "+e);
		}
	}
	
	

	public ArrayList<ArrayList<?>> getTopScorer(int size){
		
		this.bestAlignmentTimesList = new ArrayList<Float>();
		this.bestAlignmentClientIdList = new ArrayList<String>();
		
		ArrayList<ArrayList<?>> scores = new ArrayList<ArrayList<?>>();
		
		Query<PlayerEntity> query = ofy().query(PlayerEntity.class).order("userAlignmentTime");
	    QueryResultIterator<PlayerEntity> iterator = query.iterator();	    
	    
	    
	    int counter = 0;
	    while (iterator.hasNext() && counter <= size) {	    	
	    
	    	PlayerEntity topScorerEntity = iterator.next();
	        this.bestAlignmentClientIdList.add(topScorerEntity.getClientId());
	        this.bestAlignmentTimesList.add(topScorerEntity.getTime());
	        counter++;
	    }	    
	    
	    
	   
	    scores.add(this.bestAlignmentClientIdList);
	    scores.add(this.bestAlignmentTimesList);
	    
	    
	    return scores;
	    
	}
	
	public void setClientAndTimes(){
		
		Query<PlayerEntity> query = ofy().query(PlayerEntity.class);
	    QueryResultIterator<PlayerEntity> iterator = query.iterator();
	    
	    
	    while (iterator.hasNext()) {
	        PlayerEntity topScorerEntity = iterator.next();
	        this.bestAlignmentClientIdList.add(topScorerEntity.getClientId());
	        this.bestAlignmentTimesList.add(topScorerEntity.getTime());	        
	    }		
	}
	
	
	
	
	public ArrayList<String> getBestAlignmentClientIdList(){		
		return this.bestAlignmentClientIdList;
	}
	
	
		
	public ArrayList<Float> getBestAlignmentTimesList(){		
		return this.bestAlignmentTimesList;
	}
	
	
	public void clearObjectify(){
		
		Query<PlayerEntity> query = ofy().query(PlayerEntity.class);
	    QueryResultIterator<PlayerEntity> iterator = query.iterator();	
	    
	    while (iterator.hasNext()) {	    	
		    
	    	ofy().delete(iterator.next());
	    	
	    }	 
	}
	
	
	public void fillTimeListWithRandomValue(){		
		
		for(int i = 0; i < TOP_SCORER_LIST_SIZE; i++){
			
			
			Random rn = new Random();
			float n = rn.nextFloat();			
			addClient(""+i, n);
			
		}
		
	}


	public PlayerEntity getPlayer(String clientId) {		
		return ofy().query(PlayerEntity.class).filter("clientId", clientId).get();
		
	}
	
//
//	
//	public Key<TopScorerEntity> createTopScorerEntity() {
//		System.out.println("putting");
//		Key<TopScorerEntity> key = ofy().put(new TopScorerEntity("null",0));		
//		System.out.println("after put: " + key);		
//		return(key);
//	}
//	
//		
//	public Key<TopScorerEntity> getTopScorerEntityKey() {
//		
//		List<Key<TopScorerEntity>> keyList = new ArrayList<Key<TopScorerEntity>>();
//		
//		Iterable<Key<TopScorerEntity>> allKeys =
//			ofy().query(TopScorerEntity.class).fetchKeys();
//		 
//		Key<TopScorerEntity> returnKey = null;
//		
//		for (Key<TopScorerEntity> key : allKeys) {
//			keyList.add(key);
//		}
//		if (keyList.isEmpty()) {
//			Key<TopScorerEntity> entityKey = ofy().put(new TopScorerEntity("null",0));
//			return entityKey;
//		} else {
//			return keyList.get(0);
//		}
//		
		
		// if there are no keys yet == no entity => create one
//		if (allKeys == null) {
//			Key<TopScorerEntity> key = ofy().put(
//					new TopScorerEntity(Integer.MAX_VALUE));
//			return key;
//		
//		// if there is an entity => return its key
//		} else {
//			List<Key<TopScorerEntity>> keyList =
//				new ArrayList<Key<TopScorerEntity>>();
//			
//			for (Key<TopScorerEntity> key : allKeys) {
//				keyList.add(key);
//			}
//			return keyList.get(0);
//		}

//	}
	
	/**
	 * Determines if user is in the Top Scorer List.
	 * @param userAlignmentTime
	 * @return Boolean
	 */
//	public Boolean isUserInTopScorerList(Integer userAlignmentTime) {
//		Boolean returnVal = false;
//		Key<TopScorerEntity> entityKey = getTopScorerEntityKey();
//		TopScorerEntity topScorerEntity = ofy().get(entityKey);
//		returnVal = topScorerEntity.isUserInTopScorerList(userAlignmentTime);
//		return returnVal;
//	}
}
