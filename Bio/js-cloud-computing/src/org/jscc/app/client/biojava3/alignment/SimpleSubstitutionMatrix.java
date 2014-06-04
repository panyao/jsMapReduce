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
 * Created on June 9, 2010
 * Author: Mark Chapman
 */

package org.jscc.app.client.biojava3.alignment;
import java.util.ArrayList;
import java.util.List;

import org.jscc.app.client.biojava3.alignment.template.SubstitutionMatrix;
import org.jscc.app.client.biojava3.core.sequence.template.Compound;
import org.jscc.app.client.biojava3.core.sequence.template.CompoundSet;

/**
 * Implements a data structure which holds the score (penalty or bonus) given during alignment for the exchange of one
 * {@link Compound} in a sequence for another.
 *
 * @author Mark Chapman
 * @param <C> each element of the matrix corresponds to a pair of {@link Compound}s of type C
 */
public class SimpleSubstitutionMatrix<C extends Compound> implements SubstitutionMatrix<C> {

    private static final String comment = "#";

    private CompoundSet<C> compoundSet;
    private String description, name;
    private short[][] matrix;
    private short max, min;
    private List<C> rows, cols;
    
    private String input = new String();


    // constructor that creates a substitution matrix by parsing input (String).
    public SimpleSubstitutionMatrix(
    		CompoundSet<C> compoundSet,
    		String input,
    		String name) {
    	
    	this.name = name;
    	this.compoundSet = compoundSet;
    	
    	rows = new ArrayList<C>();
    	cols = new ArrayList<C>();
    	 
		if (input!=null) {
			String[] lines = input.split("\n");			
			ArrayList<String> matrixLines = new ArrayList<String>();
			
			for (String line : lines) {
				if (line.startsWith(comment)) {
					description = description + line + "\n";
				} else {
					matrixLines.add(line);
				}
			}
			
			// final matrix dimensions without first lines of commentary.
			matrix = new short[matrixLines.size()-1][];
			
			for (int i=0;i<matrixLines.size();i++) {			
				
				
				// take the first input matrix line, being amino acid characters
				// and put them into cols
				if (i==0) {
					String[] splitLine = matrixLines.get(i).split("\\s+");					
					
					for (String aa : splitLine) {
						cols.add(compoundSet.getCompoundForString(aa));						
					}

				} else {
					// splitLine is the current input matrix's line with
					// amino acid characters and numbers and trash from
					// the splitting action.
					String[] splitLine = matrixLines.get(i).split(" ");
					
					// helpList temporarily stores the current input matrix
					// line, but cleaned of empty chars and trash.
					ArrayList<String> helpList = new ArrayList<String>();
					
					for (String tempLineEntry : splitLine) {
						if (!tempLineEntry.equals("")) {
							helpList.add(tempLineEntry.trim());
						}
					}
					
					// line of the pure number matrix. temporary matrix line
					// has length of helpList-1 since first entry is an
					// amino acid character.
					short[] matrixLine = new short[helpList.size()-1];
					
					for (int j=0;j<helpList.size();j++) {
						
						// adds the first matrix line entry to the rows list
						// --> amino acid character.
						if (j==0) {
							rows.add(compoundSet.getCompoundForString(
									helpList.get(0).trim()));
						} else {
							short currentValue = Short.parseShort(
									helpList.get(j));
							
							matrixLine[j-1] = currentValue;
							
							// determine min and max:
							if (currentValue < this.min) {
								this.min = currentValue;
							}
							
							if (currentValue > this.max) {
								this.max = currentValue;
							}
						}
					}
					
					// add the current collected line of pure numbers to
					// the final matrix.
					matrix[i-1] = matrixLine;
				}
			}
		}
    }

    @Override
    public short getMaxValue() {
        return this.max;
    }

    @Override
    public short getMinValue() {
        return this.min;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public short getValue(C from, C to) {
        int row = rows.indexOf(from), col = cols.indexOf(to);
        if (row == -1 || col == -1) {
            row = cols.indexOf(from);
            col = rows.indexOf(to);
            if (row == -1 || col == -1) {
                return min;
            }
        }
        return matrix[row][col];
    }

    @Override
    public SubstitutionMatrix<C> normalizeMatrix(short scale) {
        // TODO SubstitutionMatrix<C> normalizeMatrix(short)
        return null;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns in a format similar to the standard NCBI files.
     */
    @Override
    public String toString() {
        return this.input;
    }

	@Override
	public CompoundSet<C> getCompoundSet() {
		return this.compoundSet;
	}

	@Override
	public String getDescription() {
		return this.description;
	}

	@Override
	public short[][] getMatrix() {
		return this.matrix;
	}

	@Override
	public String getMatrixAsString() {
//        StringBuilder s = new StringBuilder();
//        int lengthCompound = compoundSet.getMaxSingleCompoundStringLength(), lengthRest =
//                Math.max(Math.max(Short.toString(min).length(), Short.toString(max).length()), lengthCompound) + 1;
//        String padCompound = "%" + Integer.toString(lengthCompound) + "s",
//                padRest = "%" + Integer.toString(lengthRest);
//        for (int i = 0; i < lengthCompound; i++) {
//            s.append(" ");
//        }
//        for (C col : cols) {
//            s.append(String.format(padRest + "s", compoundSet.getStringForCompound(col)));
//        }
//        s.append(String.format("%n"));
//        for (C row : rows) {
//            s.append(String.format(padCompound, compoundSet.getStringForCompound(row)));
//            for (C col : cols) {
//                s.append(String.format(padRest + "d", getValue(row, col)));
//            }
//            s.append(String.format("%n"));
//        }
//        return s.toString();
		return null;
	}


}
