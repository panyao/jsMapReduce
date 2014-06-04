//package org.jscc.app.client.biojava3.structure.align.client;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.io.StringWriter;
//import java.net.InetAddress;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.SortedSet;
//import java.util.TreeSet;
//import java.util.UUID;
//
//import org.jscc.app.client.biojava3.core.util.FlatFileCache;
//import org.jscc.app.client.biojava3.core.util.PrettyXMLWriter;
//import org.jscc.app.client.biojava3.structure.Atom;
//import org.jscc.app.client.biojava3.structure.StructureException;
//import org.jscc.app.client.biojava3.structure.align.StructureAlignment;
//import org.jscc.app.client.biojava3.structure.align.StructureAlignmentFactory;
//import org.jscc.app.client.biojava3.structure.align.events.AlignmentProgressListener;
//import org.jscc.app.client.biojava3.structure.align.model.AFPChain;
//import org.jscc.app.client.biojava3.structure.align.util.AtomCache;
//import org.jscc.app.client.biojava3.structure.align.util.ResourceManager;
//import org.jscc.app.client.biojava3.structure.align.xml.AFPChainXMLConverter;
//
//
//
//
///** Contains the single thread for a job that can run multiple alignments.
// * 
// * @author Andreas Prlic
// *
// */
//public class FarmJobRunnable implements Runnable {
//
//
//	//private static final int DEFAULT_PAIR_FETCH_DELAY   = 30000;
//	//private static final String CONNECTION_PAIR_DELAY   = "connection.pair.delay";
//	private static final String JFATCAT_NAME            = "jfatcat.name";
//	private static final String JFATCAT_VERSION         = "jfatcat.version";
//
//	private static ResourceManager resourceManager = ResourceManager.getResourceManager("jfatcat");
//
//
//	private static DateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy h:mm a");
//
//	FarmJobParameters params;
//
//	String prevName1;
//	Atom[] ca1 ;
//
//
//	long startTime;
//	long maxTime;
//	int maxNrAlignments;
//	int alignmentsCalculated;
//
//	boolean waitForAlignments;
//
//	private static final String randomUsername = getRandomUsername();
//
//	boolean terminated ;
//
//	List<AlignmentProgressListener> progressListeners;
//	CountProgressListener counter ;
//
//	String userName = null;
//	AtomCache cache;
//
//	public FarmJobRunnable(FarmJobParameters params){
//		terminated = false;
//		this.params = params;
//
//		// multiple farm jobs share the same SoftHashMap for caching coordinates
//		cache = new AtomCache( params.getPdbFilePath(), params.isPdbDirSplit());
//
//		maxNrAlignments = params.getNrAlignments();
//		progressListeners = null;
//		if (params.getUsername() == null) {
//			userName = randomUsername;						
//		} else {
//			userName = params.getUsername();
//		}
//		counter  = new CountProgressListener();
//		addAlignmentProgressListener(counter);
//		waitForAlignments = true;
//	}
//
//	public void addAlignmentProgressListener(AlignmentProgressListener listener){
//
//		if (progressListeners == null)
//			progressListeners = new ArrayList<AlignmentProgressListener>();
//
//		progressListeners.add(listener);
//	}
//
//	public void clearListeners(){
//		if ( progressListeners == null) 
//			return;
//		progressListeners.clear();
//		progressListeners = null;
//	}
//
//	protected static String getRandomUsername(){
//		String name = "";
//		try {
//			InetAddress i = InetAddress.getLocalHost();
//			name += i.getHostAddress();
//			name += "_";
//		} catch (Exception e){
//			//e.printStackTrace();
//		}
//		name +=  UUID.randomUUID();
//
//		return name;
//
//	}
//
//	public static void log(String message){
//		StringBuffer buf = new StringBuffer();
//
//		buf.append("[");
//		Date date = new Date();
//		buf.append(dateFormat.format(date));
//		buf.append("] ");
//		buf.append(message);
//		System.out.println(buf.toString());
//	}
//
//	public void run() {
//
//		// Retrieve resource
//		String appVersion = resourceManager.getString(JFATCAT_VERSION);
//		String appName    = resourceManager.getString(JFATCAT_NAME);
//		log(appName + " version:" + appVersion);
//
//
//		startTime = System.currentTimeMillis();
//		// -t ime is in seconds.
//		long maxSec = params.getTime();
//
//		if ( maxSec < 5 ) 
//			maxTime = Long.MAX_VALUE;
//		else 
//			maxTime = startTime + params.getTime() * 1000;
//
//		terminated = false;
//
//		alignmentsCalculated = 0;
//		int totalAligs = 0;
//		maxNrAlignments = params.getNrAlignments();
//
//		if ( maxNrAlignments < 0 ){
//			maxNrAlignments = Integer.MAX_VALUE;
//		}
//
//		log("running job for max: " + maxNrAlignments + " alignments");
//
//
//		while (! terminated){
//
//			// talk to server
//			// get list of alignments to run
//			// if maxNrAlignments > 100 we split up the calculations in junks of 100.
//			// otherwise we request all of them at once.
//			// we request
//			SortedSet<PdbPair> alignmentPairs = getAlignmentPairsFromServer(); 
//			log(userName+": Server responded with " + alignmentPairs.size() + " pairs.");
//			List<String> results = new ArrayList<String>();
//
//			for(PdbPair pair : alignmentPairs){
//
//				if ( terminated)
//					break;
//
//				long now = System.currentTimeMillis();
//				if ( now >= maxTime)  {
//					terminated = true;
//					break;
//				}
//
//				if ( alignmentsCalculated >= maxNrAlignments) {
//					terminated = true;
//					break;
//				}
//
//
//				String name1 = pair.getName1();
//				String name2 = pair.getName2();
//
//				if ( progressListeners != null)
//					notifyStartAlignment(name1,name2);
//
//				try {
//					//System.out.println("calculating alignent: " + name1 + "  " + name2);
//					String resultXML = alignPair(name1, name2);
//
//					if ( progressListeners != null)
//						notifyEndAlignment();
//
//					//System.out.println("got XML: " + resultXML);
//					results.add(resultXML);
//
//				} catch (Exception e){
//					if (e.getMessage() == null)
//						e.printStackTrace();
//					// log that an exception has occurred and send it back to server!1
//					log("Error: " + e.getMessage() + " while aligning " + name1 + " vs. " + name2);
//					System.err.println(e.getMessage());
//					//e.printStackTrace();
//
//					StringWriter sw = new StringWriter();
//					PrintWriter writer = new PrintWriter(sw);
//
//					PrettyXMLWriter xml = new PrettyXMLWriter(writer);
//					try {
//						xml.openTag("AFPChain");
//
//						xml.attribute("name1", name1);
//						xml.attribute("name2", name2);
//						xml.attribute("error", e.getMessage());
//						xml.closeTag("AFPChain");
//					} catch(IOException ex){
//						ex.printStackTrace();
//					}
//					results.add(sw.toString());
//				}
//
//				alignmentsCalculated++;
//				totalAligs++;
//			}
//
//			// send results back to server
//			sendResultsToServer(results);
//			//log("sent results to server: " + counter.toString());
//
//			long end = System.currentTimeMillis();
//			if ( end >= maxTime)  {
//				System.out.println("OK end of job: reached maxTime.");
//				terminated = true;
//
//			}
//
//			if ( alignmentsCalculated >= maxNrAlignments) {
//				System.out.println("OK end of job: reached maxNrAlignments");
//				terminated = true;
//
//			}		
//
//			long tdiff = (end - startTime);
//			if ( tdiff != 0) {
//
//				log(userName + String.format(": job has run for :  %.2f", ( tdiff)/1000.0/60) + " min.");
//				log(userName + ": total nr of alignments calculated: " +alignmentsCalculated );
//				if ( alignmentsCalculated > 0)
//					log(userName + String.format(": average time / alignment: %.2f", ( tdiff / alignmentsCalculated / 1000.0 )) + " sec.");
//			}
//		}	
//
//		log(userName+": jFATCAT job result: " + counter.toString());
//
//		// clean up in the end...
//		clearListeners();
//	}
//
//
//	private void notifyStartAlignment(String name1, String name2) {
//		if ( progressListeners != null){
//			for (AlignmentProgressListener li : progressListeners){
//				li.alignmentStarted(name1, name2);
//			}
//		}		
//	}
//
//	private void notifyEndAlignment(){
//		if ( progressListeners != null){
//			for (AlignmentProgressListener li : progressListeners){
//				li.alignmentEnded();
//
//			}
//		}
//	}
//
//	private void notifyRequestingAlignments(int nrAlignments){
//		if ( progressListeners != null){
//			for (AlignmentProgressListener li : progressListeners){
//				li.requestingAlignmentsFromServer(nrAlignments);
//
//			}
//		}
//	}
//
//	private void notifySubmittingAlignments(int nrAlignments, String message){
//		if ( progressListeners != null){
//			for (AlignmentProgressListener li : progressListeners){
//				li.sentResultsToServer(nrAlignments,message);
//
//			}
//		}
//	}
//
//	public String alignPair(String name1, String name2) throws StructureException, IOException{
//
//		StructureAlignment fatCatRigid = StructureAlignmentFactory.getAlgorithm("jFatCat_rigid");
//
//		// we are running with default parameters
//
//		//long startTime = System.currentTimeMillis();
//
//		if ( prevName1 == null)
//			initMaster(name1);
//
//		if ( ! prevName1.equals(name1)){
//			// we need to reload the master
//			initMaster(name1);
//		}
//
//
//
//		// get a copy of the atoms, but clone them, since they will be rotated...
//		Atom[] ca2 =  cache.getAtoms(name2,true);
//
//		//		if ( FatCatAligner.printTimeStamps){
//		//			long endTime = System.currentTimeMillis();
//		//			System.out.println("time to get " + name1 + " " + name2 + " : " + (endTime-startTime) / 1000.0 + " sec.");
//		//		}
//		AFPChain afpChain = fatCatRigid.align(ca1, ca2);
//
//		afpChain.setName1(name1);
//		afpChain.setName2(name2);
//
//		String xml = AFPChainXMLConverter.toXML(afpChain, ca1, ca2);
//		return xml;
//	}
//
//
//
//
//	private void initMaster(String name1) throws IOException, StructureException{
//		//AtomCache cache = AtomCache.getInstance();
//
//		ca1 = cache.getAtoms(name1,true);
//
//		prevName1 = name1;
//
//
//	}
//
//
//	/** talk to centralized server and fetch all alignments to run.
//	 * 
//	 * @return a list of pairs to align.
//	 */
//	protected SortedSet<PdbPair> getAlignmentPairsFromServer() {
//
//
//		String url = params.getServer();
//
//		int nrPairs = params.getStepSize();
//
//		if ( maxNrAlignments < nrPairs )
//			nrPairs = maxNrAlignments;
//
//		SortedSet<PdbPair> allPairs = new TreeSet<PdbPair>();
//
//		//		int pairDelay = DEFAULT_PAIR_FETCH_DELAY;
//		//
//		//		String pairDelayS=resourceManager.getString(CONNECTION_PAIR_DELAY);
//		//		if ( pairDelayS !=null) {
//		//
//		//			try {
//		//				pairDelay = Integer.parseInt(pairDelayS);				
//		//			} catch (NumberFormatException ex){
//		//				ex.printStackTrace();
//		//			}
//		//		}
//
//		try {
//
//			if ( progressListeners != null)
//				notifyRequestingAlignments(nrPairs);
//
//			if ( ! waitForAlignments) {
//				
//				allPairs = JFatCatClient.getPdbPairs(url, nrPairs, userName);
//				
//			} else {
//
//				while (allPairs.size() == 0) {
//
//					allPairs = JFatCatClient.getPdbPairs(url, nrPairs, userName);
//
//					if ( allPairs.size() == 0 ) {
//						try {
//
//							int delay = JFatCatClient.getRandomSleepTime();
//							System.err.println("sleeping "+ delay/1000 + " sec.");
//							Thread.sleep(delay);
//						} catch (InterruptedException ex){
//							ex.printStackTrace();
//						}
//					}
//				}
//			}
//		} catch ( JobKillException k ){
//
//			terminate();
//
//		} catch (Exception e){
//			if ( e.getMessage() == null)
//				e.printStackTrace();
//			System.err.println("Error while requesting alignment pairs: " + e.getMessage());
//			// an error has occured sleep 30 sec.
//
//			try {
//				int delay = JFatCatClient.getRandomSleepTime();
//				System.err.println("sleeping "+ delay/1000 + " sec.");
//				Thread.sleep(delay);
//			} catch (InterruptedException ex){
//				ex.printStackTrace();
//			}
//
//
//		}
//
//		return allPairs;
//	}
//
//	protected void sendResultsToServer(List<String> results) {
//
//		String serverLocation = params.getServer();
//
//		if ( results.size() < 1)
//			return;
//
//		//System.out.println("sending " + results.size() + " results back to server");
//
//		String fullXml = "<alignments>";
//
//		for (String xml: results){
//			fullXml +=xml;
//		}
//		fullXml += "</alignments>";
//		String msg = "";
//		try {
//			msg = JFatCatClient.sendMultiAFPChainToServer(serverLocation,fullXml, userName);
//		} catch (JobKillException e){
//			e.printStackTrace();
//			terminate();
//		}
//
//		if ( progressListeners != null)
//			notifySubmittingAlignments(results.size(), msg);
//		log (userName + ": Sent " + results.size() +" results to server. job status:" + counter.toString());
//		log (userName + ": fileCache size:" + FlatFileCache.getInstance().size());
//	}
//
//
//	/** Send signal to terminate calculations
//	 * 
//	 */
//	public synchronized void terminate(){
//		terminated = true;
//	}
//
//	public boolean isWaitForAlignments() {
//		return waitForAlignments;
//	}
//
//	public void setWaitForAlignments(boolean waitForAlignments) {
//		this.waitForAlignments = waitForAlignments;
//	}
//
//
//
//}
