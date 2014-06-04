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
 * Created on Nov 2, 2009
 * Author: Andreas Prlic 
 *
 */

package org.jscc.app.client.biojava3.structure.align.fatcat;


import org.jscc.app.client.biojava3.structure.align.StructureAlignment;
import org.jscc.app.client.biojava3.structure.align.ce.AbstractUserArgumentProcessor;
import org.jscc.app.client.biojava3.structure.align.fatcat.calc.FatCatParameters;


public class FatCatUserArgumentProcessor extends AbstractUserArgumentProcessor {


	public StructureAlignment getAlgorithm() {
		StructureAlignment algorithm = null;
		if ( params.isFlexible()) {
			System.out.println("running flexible alignment");
			algorithm = new FatCatFlexible();
		}
		else { 
			algorithm = new FatCatRigid();			
		}
		return algorithm;
		
	}
	
	public Object getParameters() {
		
		FatCatParameters jparams = new FatCatParameters();
		return jparams;
	}
		
	public String getDbSearchLegend(){
		String legend = "# name1\tname2\tscore\tprobability\trmsd\tlen1\tlen2\tsim1\tsim2\t " ;
		return legend;
	}

}
