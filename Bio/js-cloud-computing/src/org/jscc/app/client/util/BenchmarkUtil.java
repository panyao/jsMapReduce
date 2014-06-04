package org.jscc.app.client.util;

import java.util.ArrayList;

import org.jscc.app.client.biojava3.alignment.NeedlemanWunsch;
import org.jscc.app.client.biojava3.alignment.SimpleGapPenalty;
import org.jscc.app.client.biojava3.alignment.SimpleSubstitutionMatrix;
import org.jscc.app.client.biojava3.alignment.template.GapPenalty;
import org.jscc.app.client.biojava3.alignment.template.SubstitutionMatrix;
import org.jscc.app.client.biojava3.core.sequence.ProteinSequence;
import org.jscc.app.client.biojava3.core.sequence.compound.AminoAcidCompound;
import org.jscc.app.client.biojava3.core.sequence.compound.AminoAcidCompoundSet;

public class BenchmarkUtil {
	
	protected ArrayList<String> sequenceNames;
	protected ArrayList<String> sequences;
	protected int time = 0;
	protected int average_length = 0;
	protected int score = 0;
	protected String sequenceSetName;
	
	public String blosum62=
	     "A  R  N  D  C  Q  E  G  H  I  L  K  M  F  P  S  T  W  Y  V  B  Z  X  *\n"+
	  "A  5 -2 -2 -2 -1 -1 -1  0 -2 -2 -2 -1 -2 -3 -1  1  0 -3 -3 -1 -2 -1 -1 -6\n"+ 
	  "R -2  6 -1 -2 -4  1 -1 -3  0 -4 -3  2 -2 -4 -2 -1 -2 -4 -3 -3 -2  0 -2 -6\n"+ 
	  "N -2 -1  7  1 -4  0 -1 -1  0 -4 -4  0 -3 -4 -3  0  0 -5 -3 -4  4 -1 -2 -6\n"+ 
	  "D -2 -2  1  7 -5 -1  1 -2 -2 -5 -5 -1 -4 -4 -2 -1 -2 -6 -4 -4  4  1 -2 -6\n"+ 
	  "C -1 -4 -4 -5  9 -4 -5 -4 -5 -2 -2 -4 -2 -3 -4 -2 -2 -4 -3 -1 -4 -5 -3 -6\n"+ 
	  "Q -1  1  0 -1 -4  6  2 -3  1 -4 -3  1  0 -4 -2 -1 -1 -3 -2 -3 -1  4 -1 -6\n"+ 
	  "E -1 -1 -1  1 -5  2  6 -3 -1 -4 -4  0 -3 -4 -2 -1 -1 -4 -4 -3  0  4 -1 -6\n"+ 
	  "G  0 -3 -1 -2 -4 -3 -3  6 -3 -5 -5 -2 -4 -4 -3 -1 -2 -4 -5 -4 -1 -3 -2 -6\n"+ 
	  "H -2  0  0 -2 -5  1 -1 -3  8 -4 -3 -1 -3 -2 -3 -1 -2 -3  2 -4 -1  0 -2 -6\n"+ 
	  "I -2 -4 -4 -5 -2 -4 -4 -5 -4  5  1 -3  1 -1 -4 -3 -1 -3 -2  3 -5 -4 -2 -6\n"+ 
	  "L -2 -3 -4 -5 -2 -3 -4 -5 -3  1  4 -3  2  0 -4 -3 -2 -3 -2  0 -5 -4 -2 -6\n"+
	  "K -1  2  0 -1 -4  1  0 -2 -1 -3 -3  6 -2 -4 -2 -1 -1 -5 -3 -3 -1  1 -1 -6\n"+ 
	  "M -2 -2 -3 -4 -2  0 -3 -4 -3  1  2 -2  7 -1 -3 -2 -1 -2 -2  0 -4 -2 -1 -6\n"+ 
	  "F -3 -4 -4 -4 -3 -4 -4 -4 -2 -1  0 -4 -1  7 -4 -3 -3  0  3 -1 -4 -4 -2 -6\n"+ 
	  "P -1 -2 -3 -2 -4 -2 -2 -3 -3 -4 -4 -2 -3 -4  8 -1 -2 -5 -4 -3 -3 -2 -2 -6\n"+ 
	  "S  1 -1  0 -1 -2 -1 -1 -1 -1 -3 -3 -1 -2 -3 -1  5  1 -4 -2 -2  0 -1 -1 -6\n"+ 
	  "T  0 -2  0 -2 -2 -1 -1 -2 -2 -1 -2 -1 -1 -3 -2  1  5 -4 -2  0 -1 -1 -1 -6\n"+ 
	  "W -3 -4 -5 -6 -4 -3 -4 -4 -3 -3 -3 -5 -2  0 -5 -4 -4 11  2 -3 -5 -4 -3 -6\n"+ 
	  "Y -3 -3 -3 -4 -3 -2 -4 -5  2 -2 -2 -3 -2  3 -4 -2 -2  2  7 -2 -4 -3 -2 -6\n"+ 
	  "V -1 -3 -4 -4 -1 -3 -3 -4 -4  3  0 -3  0 -1 -3 -2  0 -3 -2  5 -4 -3 -1 -6\n"+ 
	  "B -2 -2  4  4 -4 -1  0 -1 -1 -5 -5 -1 -4 -4 -3  0 -1 -5 -4 -4  4  0 -2 -6\n"+ 
	  "Z -1  0 -1  1 -5  4  4 -3  0 -4 -4  1 -2 -4 -2 -1 -1 -4 -3 -3  0  4 -1 -6\n"+ 
	  "X -1 -2 -2 -2 -3 -1 -1 -2 -2 -2 -2 -1 -1 -2 -2 -1 -1 -3 -2 -1 -2 -1 -2 -6\n"+ 
	  "* -6 -6 -6 -6 -6 -6 -6 -6 -6 -6 -6 -6 -6 -6 -6 -6 -6 -6 -6 -6 -6 -6 -6  1"; 
	
	public void align(){
		AminoAcidCompoundSet aminoAcidCompoundSet = new AminoAcidCompoundSet();
		
		SubstitutionMatrix<AminoAcidCompound> substitutionMatrix =
			new SimpleSubstitutionMatrix<AminoAcidCompound>(
					aminoAcidCompoundSet,
					blosum62,
					"blosum62");
				
		Short extendGap = -1;
		Short openGap = -1;
		GapPenalty gapPenalty = new SimpleGapPenalty(openGap, extendGap);
		this.time = 0;
		this.score = 0;
		this.average_length = 0;

		for(int i = 0; i < this.sequences.size(); i++){
			String query = this.sequences.get(i);
			this.average_length += query.length();

			for(int j = i + 1; j < this.sequences.size(); j++){
				String target = this.sequences.get(j);
				ProteinSequence s1 = new ProteinSequence(query);
				ProteinSequence s2 = new ProteinSequence(target);

				NeedlemanWunsch<ProteinSequence, AminoAcidCompound> needlemanWunsch =
					new NeedlemanWunsch<ProteinSequence, AminoAcidCompound>(
							s1,
							s2,
							gapPenalty,
							substitutionMatrix);

				this.time += needlemanWunsch.getComputationTime();
				this.score += needlemanWunsch.getScore();
			}
			
		}

		// Convert time from milliseconds to seconds
		//this.time = this.time / (1000);
		
		// Average sequence 
		this.average_length = this.average_length/sequences.size();
		
	}

	/**
	 * Check if sequences sizes is valid and than perform alignment.
	 */
	public void performAlignment(){
		if(this.sequences.size() > 1)
			align();
		else
			System.out.println("Error: List of sequences should be larger than one!");
	}
	
	
	public ArrayList<String> getSequences(){
		return this.sequences;
	}
	
	public ArrayList<String> getSequenceNames(){
		return this.sequenceNames;
	}
	
	public int getComputationTime(){
		return this.time;
	}
	
	public int getAverageSequenceLength(){
		return this.average_length;
	}
	
	public int getTotalScore(){
		return this.score;
	}
	
	public String getSequenceSetName(){
		return this.sequenceSetName;
	}
	
	public void setSequences(ArrayList<String> sequences){
		this.sequences = sequences;
	}
	
	public void setSequenceSetName(String name){
		this.sequenceSetName = name;
	}
	
}
