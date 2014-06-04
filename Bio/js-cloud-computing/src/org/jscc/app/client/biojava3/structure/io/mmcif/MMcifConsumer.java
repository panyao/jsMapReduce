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
 * created at Mar 4, 2008
 */
package org.jscc.app.client.biojava3.structure.io.mmcif;

import java.util.List;

import org.jscc.app.client.biojava3.structure.io.FileParsingParameters;
import org.jscc.app.client.biojava3.structure.io.mmcif.model.AtomSite;
import org.jscc.app.client.biojava3.structure.io.mmcif.model.AuditAuthor;
import org.jscc.app.client.biojava3.structure.io.mmcif.model.ChemComp;
import org.jscc.app.client.biojava3.structure.io.mmcif.model.DatabasePDBremark;
import org.jscc.app.client.biojava3.structure.io.mmcif.model.DatabasePDBrev;
import org.jscc.app.client.biojava3.structure.io.mmcif.model.Entity;
import org.jscc.app.client.biojava3.structure.io.mmcif.model.EntityPolySeq;
import org.jscc.app.client.biojava3.structure.io.mmcif.model.Exptl;
import org.jscc.app.client.biojava3.structure.io.mmcif.model.PdbxEntityNonPoly;
import org.jscc.app.client.biojava3.structure.io.mmcif.model.PdbxNonPolyScheme;
import org.jscc.app.client.biojava3.structure.io.mmcif.model.PdbxPolySeqScheme;
import org.jscc.app.client.biojava3.structure.io.mmcif.model.Refine;
import org.jscc.app.client.biojava3.structure.io.mmcif.model.Struct;
import org.jscc.app.client.biojava3.structure.io.mmcif.model.StructAsym;
import org.jscc.app.client.biojava3.structure.io.mmcif.model.StructKeywords;
import org.jscc.app.client.biojava3.structure.io.mmcif.model.StructRef;
import org.jscc.app.client.biojava3.structure.io.mmcif.model.StructRefSeq;

/** An interface for the events triggered by a MMcifParser.
 * The Consumer listens to the events and builds up the protein structure.
 *
 * @author Andreas Prlic
 *  @since 1.7
 *
 */
public interface MMcifConsumer {
	/** called at start of document
	 *
	 */
	public void documentStart();

	/** called at end of document
	 *
	 */
	public void documentEnd();


	/** A new AtomSite record has been read. Contains the Atom data
	 *
	 * @param atom
	 */
	public void newAtomSite(AtomSite atom);
	public void newEntity(Entity entity);
	public void newEntityPolySeq(EntityPolySeq epolseq);
	public void newStructAsym(StructAsym sasym);
	public void setStruct(Struct struct);
	public void newDatabasePDBrev(DatabasePDBrev dbrev);
	public void newDatabasePDBremark(DatabasePDBremark remark);
	public void newExptl(Exptl exptl);
	public void newStructRef(StructRef sref);
	public void newStructRefSeq(StructRefSeq sref);
	public void newPdbxPolySeqScheme(PdbxPolySeqScheme ppss);
	public void newPdbxNonPolyScheme(PdbxNonPolyScheme ppss);
	public void newPdbxEntityNonPoly(PdbxEntityNonPoly pen);
	public void newStructKeywords(StructKeywords kw);
	public void newRefine(Refine r);
	public void newChemComp(ChemComp c);

	/** AuditAuthor contains the info from the PDB-AUTHOR records.
	 *
	 * @param aa
	 */
	public void newAuditAuthor(AuditAuthor aa);

	/** This method is called if no particular handler for the provided cif category
	 * has been implemented so far.
	 * @param category The category that is being processed.
	 * @param loopFields the fields of this category.
	 * @param lineData the data that is being provided.
	 */
	public void newGenericData(String category, List<String> loopFields, List<String> lineData);

	public void setFileParsingParameters(FileParsingParameters params);
	public FileParsingParameters getFileParsingParameters();


}
