package nico_no_real_tests;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;


public class nico_matrix_test {
	private static String NAME = "matrix";
	private static String description = new String();
	private static short min, max;
	private static short[][] matrix;
	private static ArrayList<String> cols = new ArrayList<String>();
	private static ArrayList<String> rows = new ArrayList<String>();

	
	public static void main(String[] args) {
		
		constructor();
		System.out.println(getDescription());
		System.out.println(getName());
		System.out.println(getMin());
		System.out.println(getMax());
		
		short[][] tempMatrix = getMatrix();
		for (int counter=0;counter<tempMatrix.length;counter++) {
			String out = new String();
			for (int counter2=0; counter2<tempMatrix.length; counter2++) {
				out = out+tempMatrix[counter][counter2]+", ";
			}
			System.out.println(out);
		}
		
		System.out.println(getInput());
		
	}
	
	
	private static void constructor() {
		String input = getInput();
		if (input!=null) {
			String[] lines = input.split("\n");
			ArrayList<String> matrixLines = new ArrayList<String>();
			
			for (String line : lines) {
				if (line.startsWith("#")) {
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
					String[] splitLine = matrixLines.get(i).split(" ");
					for (String aa : splitLine) {
						if (!aa.trim().equals("")) {
							cols.add(aa);
						}
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
							rows.add(helpList.get(0).trim());
						} else {
							short currentValue = Short.parseShort(
									helpList.get(j));
							
							matrixLine[j-1] = currentValue;
							
							// determine min and max:
							if (currentValue < min) {
								min = currentValue;
							}
							
							if (currentValue > max) {
								max = currentValue;
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
	
	private static String getInput() {
		String returnVal = null;
		File f = new File("/blosum62.txt");
		if (f.exists()) {
			try {
				byte[] buffer = new byte[(int) f.length()];
				FileInputStream input = new FileInputStream(f);
				BufferedInputStream bi = new BufferedInputStream(input);
				bi.read(buffer);
				returnVal = new String(buffer);
				input.close();
				bi.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Blosum matrix not found. Check Path.");
		}
		return returnVal;
	}
	
	private static String getDescription() {
		return ("Stored Description: \n"+description);

	}
	
	private static short getMax() {
		return max;
	}
	
	private static short getMin() {
		return min;
	}
	
	private static String getName() {
		return NAME;
	}
	
	private static short[][] getMatrix() {
		return matrix;
	}
	
	/**
	 * Simply gets the input as String.
	 * @return String
	 */
	private static String asString() {
		return getInput();
	}
}
