package org.jscc.common.server.util;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

import org.biojava3.core.sequence.ProteinSequence;
import org.biojava3.core.sequence.io.FastaReaderHelper;
import org.jscc.app.client.util.BenchmarkUtil;

public class BenchmarkServerUtil extends BenchmarkUtil{
	
	
	

	
	
	
	//Important for creating random sequences
	private int numberOfSequences = 30; // #Sequences
	private int lengthOfSequences = 50; // length of sequences
	private String chars = "A  R  N  D  C  Q  E  G  H  I  L  K  M  F  P  S  T  W  Y  V  B  Z  X";
	
	
	
	public void setSequencesFromFastaFile(String filename){
		
		this.sequenceNames = new ArrayList<String>();
		this.sequences = new ArrayList<String>();
		
		setSequenceSetName(filename);		
		String temp_seq = ""; 
		
		try{
		    // Open the file that is the first 
		    // command line parameter
		    FileInputStream fstream = new FileInputStream(filename);
		    // Get the object of DataInputStream
		    DataInputStream in = new DataInputStream(fstream);
		    BufferedReader br = new BufferedReader(new InputStreamReader(in));

		    
		    String strLine = "";
		    
		    //Read File Line By Line
		    while ((strLine = br.readLine()) != null)   {
		      // Print the content on the console
		    	if(strLine.startsWith(">")){
		    		if(!temp_seq.equals("")){		    			
		    			this.sequences.add(temp_seq);
		    		}
		    		this.sequenceNames.add(""+strLine);
		    		temp_seq = "";
		    	}else{
		    		temp_seq = temp_seq+strLine;
		    	}
		    }
		    sequences.add(temp_seq);
		    //Close the input stream
		    in.close();
		}catch (Exception e){//Catch exception if any
		      System.err.println("Error: " + e.getMessage());
		}
	}
	
	
	public static String randSequence(String chars,int l){
		String seq = "";
		
		String[] alph = chars.split("\\s+");
		
		float n = alph.length;
		float r = 1/n;		
		
		Random rn = new Random();
				
		for(int i = 0; i<l; i++){
			
			double d = rn.nextFloat();
			for(int j = 0; j < n; j++){
				if(j*r <= d && d < (j+1)*r){
					seq += alph[j];
					break;
				}
			}						
		}
				
		
		return seq;	
	}
	public void CreateRandomSequences(){
		this.sequences = new ArrayList<String>();
		for(int i = 0; i < this.numberOfSequences; i++){
			String rSeq = randSequence(this.chars, this.lengthOfSequences);
			this.sequences.add(rSeq);
		}
	}
	
	
	
	
	
	/**
	 *	Grabs the pdb file for a given ID from the pdb database
	 *
	 * @param uniProtId
	 * @returns sequence
	 * @throws Exception
	 */
	
	private static ProteinSequence getSequenceForId(String uniProtId) throws Exception {
	        URL uniprotFasta = new URL(String.format("http://www.uniprot.org/uniprot/%s.fasta", uniProtId));
	        ProteinSequence seq = FastaReaderHelper.readFastaProteinSequence(uniprotFasta.openStream()).get(uniProtId);
	        System.out.printf("id : %s %s%n%s%n", uniProtId, seq, seq.getOriginalHeader());
	        return seq;
	}
	
}
