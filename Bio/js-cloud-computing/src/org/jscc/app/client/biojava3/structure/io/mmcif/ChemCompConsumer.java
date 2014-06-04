package org.jscc.app.client.biojava3.structure.io.mmcif;

import java.util.List;

import org.jscc.app.client.biojava3.structure.io.FileParsingParameters;
import org.jscc.app.client.biojava3.structure.io.mmcif.chem.ResidueType;
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

public class ChemCompConsumer implements MMcifConsumer {

	ChemicalComponentDictionary dictionary;

	public ChemCompConsumer(){
		dictionary = new ChemicalComponentDictionary();
	}

	public void documentStart() {


	}

	public ChemicalComponentDictionary getDictionary(){
		return dictionary;
	}

	public void newChemComp(ChemComp c) {
		dictionary.addChemComp(c);
		if ( c.getResidueType() == ResidueType.nonPolymer)
			return;

		if ( c.getResidueType() == ResidueType.saccharide)
			return;

		if ( c.getResidueType() == ResidueType.dSaccharide)
			return;

		//if ( c.isStandard())
		//	System.out.println(c);
	}

	public void documentEnd() {


	}

	public void newAtomSite(AtomSite atom) {
		// TODO Auto-generated method stub

	}

	public void newDatabasePDBremark(DatabasePDBremark remark) {
		// TODO Auto-generated method stub

	}

	public void newDatabasePDBrev(DatabasePDBrev dbrev) {
		// TODO Auto-generated method stub

	}

	public void newEntity(Entity entity) {
		// TODO Auto-generated method stub

	}

	public void newEntityPolySeq(EntityPolySeq epolseq) {
		// TODO Auto-generated method stub

	}

	public void newExptl(Exptl exptl) {
		// TODO Auto-generated method stub

	}

	public void newPdbxEntityNonPoly(PdbxEntityNonPoly pen) {
		// TODO Auto-generated method stub

	}

	public void newPdbxNonPolyScheme(PdbxNonPolyScheme ppss) {
		// TODO Auto-generated method stub

	}

	public void newPdbxPolySeqScheme(PdbxPolySeqScheme ppss) {
		// TODO Auto-generated method stub

	}

	public void newRefine(Refine r) {
		// TODO Auto-generated method stub

	}

	public void newStructAsym(StructAsym sasym) {
		// TODO Auto-generated method stub

	}

	public void newStructKeywords(StructKeywords kw) {
		// TODO Auto-generated method stub

	}

	public void newStructRef(StructRef sref) {
		// TODO Auto-generated method stub

	}

	public void newStructRefSeq(StructRefSeq sref) {
		// TODO Auto-generated method stub

	}

	public void setStruct(Struct struct) {
		// TODO Auto-generated method stub

	}

	public void newGenericData(String category, List<String> loopFields,
			List<String> lineData) {
		//System.out.println("unhandled category: " + category);

	}


   public void newAuditAuthor(AuditAuthor aa)
   {
      // TODO Auto-generated method stub

   }

   public FileParsingParameters getFileParsingParameters()
   {
     // can be ingored in this case...
      return null;
   }

   public void setFileParsingParameters(FileParsingParameters params)
   {
      // TODO Auto-generated method stub
      
   }

}
