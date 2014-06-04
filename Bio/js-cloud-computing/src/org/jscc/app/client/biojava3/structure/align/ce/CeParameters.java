/*
 *                    BioJava development code
 *
 * This code may be freely distributed and modified under the
 * terms of the GNU Lesser General Public Licence.  This should
 * be distributed with the code.  If you do not have a copy,
 * see:
 *
 *      http://www.gnu.org/copyleft/lesser.html
 *
 * Copyright for this code is held jointly by the individual
 * authors.  These should be listed in @author doc comments.
 *
 * For more information on the BioJava project and its aims,
 * or to join the biojava-l mailing list, visit the home page
 * at:
 *
 *      http://www.biojava.org/
 *
 * Created on Sep 15, 2009
 * Author: Andreas Prlic 
 *
 */

package org.jscc.app.client.biojava3.structure.align.ce;

import java.util.ArrayList;
import java.util.List;


/** Contains the parameters that can be sent to CE
 * 
 * @author Andreas Prlic
 *
 */
public class CeParameters implements ConfigStrucAligParams  {

   int winSize;
   double rmsdThr;
   double rmsdThrJoin;
   private double maxOptRMSD;

   public static final int DEFAULT_SCORING_STRATEGY = 0;
   public static final int SIDE_CHAIN_SCORING = 1;
   public static final int SIDE_CHAIN_ANGLE_SCORING = 2;
   public static final int CA_AND_SIDE_CHAIN_ANGLE_SCORING = 3;

   public static final String SCORING_STRATEGY = "ScoringStrategy";
   int scoringStrategy;
   //String[] alignmentAtoms;
   private int maxGapSize;

   boolean showAFPRanges;
   boolean checkCircular;
   int  sideChainScoringType;

   private static final double DEFAULT_GAP_OPEN = 5.0;
   private static final double DEFAULT_GAP_EXTENSION  = 0.5;
   private static final double DISTANCE_INCREMENT=0.5;
   private static final double DEFAULT_oRmsdThr = 2.0; 

   double gapOpen;
   double gapExtension;
   double distanceIncrement;
   double oRmsdThr;

   public CeParameters(){
      reset();
   }



   @Override
   public String toString() {
      return "CeParameters [scoringStrategy=" + scoringStrategy 
      + ", maxGapSize=" + maxGapSize 
      + ", rmsdThr=" + rmsdThr 
      + ", rmsdThrJoin="+ rmsdThrJoin 
      + ", winSize=" + winSize 
      + ", showAFPRanges=" + showAFPRanges 
      + ", checkCircular=" + checkCircular 
      + ", maxOptRMSD=" + maxOptRMSD      
      + "]";
   }



   public void reset(){
      winSize = 8;
      rmsdThr = 3.0;
      rmsdThrJoin = 4.0;
      scoringStrategy = DEFAULT_SCORING_STRATEGY;
      maxGapSize = 30;
      showAFPRanges = false;
      checkCircular = false;
      maxOptRMSD = 99;

      gapOpen = DEFAULT_GAP_OPEN;
      gapExtension = DEFAULT_GAP_EXTENSION;
      distanceIncrement = DISTANCE_INCREMENT;
      oRmsdThr = DEFAULT_oRmsdThr;
   }

   /** The window size to look at
    * 
    * @return window size
    */
   public Integer getWinSize() {
      return winSize;
   }
   public void setWinSize(Integer winSize) {
      this.winSize = winSize;
   }

   /** RMSD Threshold
    * 
    * @return RMSD threshold
    */
   public Double getRmsdThr() {
      return rmsdThr;
   }
   public void setRmsdThr(Double rmsdThr) {
      this.rmsdThr = rmsdThr;
   }

   /** RMSD threshold for joining of AFPs
    * 
    * @return rmsd threshold
    */
   public Double getRmsdThrJoin() {
      return rmsdThrJoin;
   }
   public void setRmsdThrJoin(Double rmsdThrJoin) {
      this.rmsdThrJoin = rmsdThrJoin;
   }

   public Integer getScoringStrategy()
   {
      return scoringStrategy;
   }


   /** Set the scoring strategy to use. 0 is default CE scoring scheme. 1 uses
    * Side chain orientation.
    * 
    * @param scoringStrategy
    */
   public void setScoringStrategy(Integer scoringStrategy)
   {
      this.scoringStrategy = scoringStrategy;
   }



   /** Set the Max gap size parameter. Default 30. For unlimited gaps set to -1
    * 
    * @param maxGapSize
    */
   public void setMaxGapSize(Integer maxGapSize){
      this.maxGapSize = maxGapSize;
   }

   /** the Max gap size parameter G . default is 30, which was
    * described to obtained empirically in the CE paper.
    * the larger the max gap size, the longer the compute time,
    * but in same cases drastically improved results. Set to -1 for unlimited gap size. 
    * 
    * @return max gap size parameter
    */
   public Integer getMaxGapSize() {
      return maxGapSize;
   }


   public List<String> getUserConfigHelp() {
      List<String> params =new ArrayList<String>();
      String helpMaxGap = "This parameter configures the maximum gap size G, that is applied during the AFP extension. The larger the value, the longer the calculation time can become, Default value is 30. Set to 0 for no limit. " ;
      //String helpRmsdThr = "This configures the RMSD threshold applied during the trace of the fragment matrix.";
      String helpWinSize = "This configures the fragment size m of Aligned Fragment Pairs (AFPs).";
      String helpCircular = "Should the algoritm check for circular permutations? Increases calculation time.";
      String helpScoring = "Which scoring function to use.";
      params.add(helpMaxGap);
      //params.add(helpRmsdThr);
      params.add(helpWinSize);
      params.add(helpCircular);
      params.add(helpScoring);
      params.add("The maximum RMSD at which to stop alignment optimization. (default: unlimited=99)");
      params.add("Gap opening penalty during alignment optimization.");
      params.add("Gap extension penalty during alignment optimization.");
      return params;
   }

   public List<String> getUserConfigParameters() {
      List<String> params = new ArrayList<String>();
      params.add("MaxGapSize");
      //params.add("RmsdThr");
      params.add("WinSize");
      params.add("CheckCircular");
      params.add(SCORING_STRATEGY);
      params.add("MaxOptRMSD");
      params.add("GapOpen");
      params.add("GapExtension");

      return params;
   }

   public List<String> getUserConfigParameterNames(){
      List<String> params = new ArrayList<String>();
      params.add("max. gap size G (during AFP extension).");
      //params.add("RMSD threshold during trace of the fragment matrix.");
      params.add("fragment size m");
      params.add("check for circular permutations");
      params.add("Which scoring function to use");
      params.add("RMSD threshold for alignment.");
      params.add("Gap open");
      params.add("Gap extension");
      return params;
   }

   public List<Class> getUserConfigTypes() {
      List<Class> params = new ArrayList<Class>();
      params.add(Integer.class);
      //params.add(Double.class);
      params.add(Integer.class);
      params.add(Boolean.class);
      params.add(Integer.class);
      params.add(Double.class);
      params.add(Double.class);
      params.add(Double.class);
      return params;
   }



   /**
    * @return whether information about AFPs should be printed
    */
   public boolean isShowAFPRanges()
   {
      return showAFPRanges;
   }
   public void setShowAFPRanges(boolean showAFPRanges)
   {
      this.showAFPRanges = showAFPRanges;
   }


   /**
    * @return whether the protein should be checked for circular permutations.
    */

   public Boolean getCheckCircular() {
      return checkCircular;
   }
   /**
    * @param checkCircular whether the protein should be checked for circular permutations
    */
   public void setCheckCircular(Boolean checkCircular) {
      this.checkCircular = checkCircular;
   }


   /** set the maximum RMSD cutoff to be applied during alignment optimization. (default: 99 = unlimited)
    * 
    * @param param maxOptRMSD
    */
   public void setMaxOptRMSD(Double param){
      if ( param == null)
         param = 99d;
      maxOptRMSD = param;
   }

   /** Returns the maximum RMSD cutoff to be applied during alignment optimization (default: 99 = unlimited)
    * 
    * @return maxOptRMSD
    */
   public Double getMaxOptRMSD()
   {
      return maxOptRMSD;
   }



   public Double getGapOpen()
   {
      return gapOpen;
   }



   public void setGapOpen(Double gapOpen)
   {
      this.gapOpen = gapOpen;
   }



   public Double getGapExtension()
   {
      return gapExtension;
   }



   public void setGapExtension(Double gapExtension)
   {
      this.gapExtension = gapExtension;
   }



   public Double getDistanceIncrement()
   {
      return distanceIncrement;
   }



   public void setDistanceIncrement(Double distanceIncrement)
   {
      this.distanceIncrement = distanceIncrement;
   }



   /** Get the Original RMSD threshold from which the alignment optimization is started
    * 
    * @return oRMSDThreshold
    */
   public Double getORmsdThr()
   {
      return oRmsdThr;
   }



   /** Set the Original RMSD threshold from which the alignment optimization is started
    * 
    * @param oRmsdThr the threshold
    */
   public void setORmsdThr(Double oRmsdThr)
   {
      this.oRmsdThr = oRmsdThr;
   }


}
