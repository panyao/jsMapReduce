package org.jscc.app.client.biojava3.structure.align.ce;


/** a simple bean that contains the parameters that can get set at startup
 * 
 * @author Andreas Prlic
 *
 */
public class StartupParameters {

	
	
	String pdbFilePath;
	String outFile;
	String pdb1;
	String pdb2;
	String file1;
	String file2;
	String showDBresult;
	boolean printXML;
	boolean printFatCat;
	boolean show3d;
	boolean autoFetch;
	boolean flexible;
	boolean pdbDirSplit;
	boolean printCE;
	boolean showMenu;
	boolean showAFPRanges;
	boolean checkCircular;
	boolean printPDB;
	Double maxOptRMSD;
	
	
	// for DB searches
	String alignPairs;
	String saveOutputDir;
	
	int maxGapSize;
	
	public StartupParameters(){
		show3d = false;
		printXML = false;
		printPDB = false;
		printFatCat = false;
		autoFetch = false;
		flexible = false;
		pdbDirSplit = true;
		maxGapSize = 30;
		showAFPRanges = false;
		checkCircular = false;
		showMenu = false;
		maxOptRMSD = 99d;
		
	}
	
	

	
	
	@Override
   public String toString()
   {
      return "StartupParameters [alignPairs=" + alignPairs + ", autoFetch=" + autoFetch + ", file1=" + file1 + ", file2=" + file2
            + ", flexible=" + flexible + ", maxGapSize=" + maxGapSize + ", outFile=" + outFile + ", pdb1=" + pdb1 + ", pdb2=" + pdb2
            + ", pdbDirSplit=" + pdbDirSplit + ", pdbFilePath=" + pdbFilePath + ", printCE=" + printCE + ", printFatCat=" + printFatCat
            + ", printXML=" + printXML + ", saveOutputDir=" + saveOutputDir + ", show3d=" + show3d + ", showDBresult=" + showDBresult
            + ", showMenu=" + showMenu + ", showAFPRanges=" + showAFPRanges + ", checkCircular=" + checkCircular 
            + ", printPDB=" + printPDB 
            + "]";
   }





   public String getAlignPairs() {
		return alignPairs;
	}

	public void setAlignPairs(String alignPairs) {
		this.alignPairs = alignPairs;
	}

	public String getSaveOutputDir() {
		return saveOutputDir;
	}

	public void setSaveOutputDir(String saveOutputDir) {
		this.saveOutputDir = saveOutputDir;
	}

	public boolean isShowMenu() {
		return showMenu;
	}

	public void setShowMenu(boolean showMenu) {
		this.showMenu = showMenu;
	}

	/** Display the output string in CE style
	 * 
	 * @return flag
	 */
	public boolean isPrintCE() {
		return printCE;
	}

	/** Display the output string in CE style
	 * 
	 * @param printCE a flag
	 */
	public void setPrintCE(boolean printCE) {
		this.printCE = printCE;
	}

	

	public boolean isFlexible() {
		return flexible;
	}
	public void setFlexible(boolean flexible) {
		this.flexible = flexible;
	}
	

	public String getPdb1() {
		return pdb1;
	}
	/** mandatory argument to set the first PDB (and optionally chain ID) to be aligned.
	 * 
	 * @param pdb1
	 */
	public void setPdb1(String pdb1) {
		this.pdb1 = pdb1;
	}
	public String getPdb2() {
		return pdb2;
	}

	/** mandatory argument to set the second PDB (and optionally chain ID) to be aligned.
	 *  @param pdb2
	 */
	public void setPdb2(String pdb2) {
		this.pdb2 = pdb2;
	}

	public boolean isPdbDirSplit() {
		return pdbDirSplit;
	}
	public void setPdbDirSplit(boolean pdbDirSplit) {
		this.pdbDirSplit = pdbDirSplit;
	}
	public boolean isPrintXML() {
		return printXML;
	}
	public void setPrintXML(boolean printXML) {
		this.printXML = printXML;
	}
	public boolean isPrintFatCat() {
		return printFatCat;
	}
	public void setPrintFatCat(boolean printFatCat) {
		this.printFatCat = printFatCat;
	}

	public String getPdbFilePath() {
		return pdbFilePath;
	}

	/** mandatory argument to set the location of PDB files.
	 * 
	 * @param pdbFilePath
	 */
	public void setPdbFilePath(String pdbFilePath) {
		this.pdbFilePath = pdbFilePath;
	}
	public boolean isShow3d() {
		return show3d;
	}
	public void setShow3d(boolean show3d) {
		this.show3d = show3d;
	}
	public String getOutFile() {
		return outFile;
	}
	public void setOutFile(String outFile) {
		this.outFile = outFile;
	}
	public boolean isAutoFetch() {
		return autoFetch;
	}
	public void setAutoFetch(boolean autoFetch) {
		this.autoFetch = autoFetch;
	}
	public String getShowDBresult() {
		return showDBresult;
	}
	public void setShowDBresult(String showDBresult) {
		this.showDBresult = showDBresult;
	}

	/** CE specific parameter: set the Max gap size parameter G (during AFP extension). Default: 30
	 * 
	 * @return the maximum gap size G parameter.
	 */
	public int getMaxGapSize() {
		return maxGapSize;
	}

	/** CE specific parameter: set the Max gap size parameter G (during AFP extension). Default: 30
	 * 
	 * @param maxGapSize
	 */
	public void setMaxGapSize(int maxGapSize) {
		this.maxGapSize = maxGapSize;
	}

   public String getFile1()
   {
      return file1;
   }

   public void setFile1(String file1)
   {
      this.file1 = file1;
   }

   public String getFile2()
   {
      return file2;
   }

   public void setFile2(String file2)
   {
      this.file2 = file2;
   }


   public boolean isShowAFPRanges()
   {
      return showAFPRanges;
   }

   public void setShowAFPRanges(boolean showAFP)
   {
      this.showAFPRanges = showAFP;
   }
	
   /**
	 * @return Returns whether the protein should be checked for circular permutations. 
	 */
	public boolean isCheckCircular() {
		return checkCircular;
	}

	/**
	 * @param checkCircular Indicates whether the protein should be checked for circular permutations
	 */
	public void setCheckCircular(boolean checkCircular) {
		this.checkCircular = checkCircular;
	}


	/** When writing the results to a file, don;t write as XML but write aligned PDB file
	 * 
	 * @return flag
	 */
	public boolean isOutputPDB() {
		return printPDB;
	}
	/** When writing the results to a file, don;t write as XML but write aligned PDB file
	 * 
	 * @param printPDB flag to print aligned PDB 
	 */
	public void setOutputPDB(boolean printPDB) {
		this.printPDB = printPDB;
	}



	
	/**(jCE specific): maximum RMSD that shall be calculated for the alignment.
	 * 
	 * @return maxOptRMSD parameter
	 */
	public Double getMaxOptRMSD() {
		return maxOptRMSD;
	}

	/** (jCE specific): maximum RMSD that shall be calculated for the alignment.
	 * 
	 * @param maxOptRMSD max RMSD to calculate
	 */
	public void setMaxOptRMSD(Double maxOptRMSD) {
		this.maxOptRMSD = maxOptRMSD;
	}




	
	

	
}
