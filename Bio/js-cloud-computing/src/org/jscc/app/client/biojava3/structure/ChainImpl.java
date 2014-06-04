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
 * Created on 12.03.2004
 * @author Andreas Prlic
 *
 */
package org.jscc.app.client.biojava3.structure;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


import org.biojava3.core.sequence.compound.AminoAcidCompound;
import org.biojava3.core.sequence.template.Sequence;
import org.biojava3.core.sequence.ProteinSequence;
import org.jscc.app.client.biojava3.structure.io.PDBFileReader;
import org.jscc.app.client.biojava3.structure.io.mmcif.ChemCompGroupFactory;
import org.jscc.app.client.biojava3.structure.io.mmcif.chem.PolymerType;
import org.jscc.app.client.biojava3.structure.io.mmcif.chem.ResidueType;
import org.jscc.app.client.biojava3.structure.io.mmcif.model.ChemComp;


/**
 * A Chain in a PDB file. It contains several groups which can be of
 * one of the types defined in the {@link GroupType} constants.
 *
 * @author Andreas Prlic
 * @author Jules Jacobsen
 * @since 1.4
 */
public class ChainImpl implements Chain, Serializable {

   /**
    *
    */
   private static final long serialVersionUID = 1990171805277911840L;

   /** The default chain identifier is an empty space.
    *
    */
   public static String DEFAULT_CHAIN_ID = "A";

   String swissprot_id ;
   String name ; // like in PDBfile
   List <Group> groups;


   List<Group> seqResGroups;
   private Long id;
   Compound mol;
   Structure parent;

   Map<String, Integer> pdbResnumMap;

   /**
    *  Constructs a ChainImpl object.
    */
   public ChainImpl() {
      super();

      name = DEFAULT_CHAIN_ID;
      groups = new ArrayList<Group>() ;
      
      seqResGroups = new ArrayList<Group>();
      pdbResnumMap = new HashMap<String,Integer>();

   }

   /** {@inheritDoc}
    *
    */
   public Long getId() {
      return id;
   }

   /** {@inheritDoc}
    *
    */
   public void setId(Long id) {
      this.id = id;
   }

   /** {@inheritDoc}
    *
    */
   public void setParent(Structure parent) {
      this.parent = parent;
   }

   /** Returns the parent Structure of this chain.
    *
    * @return the parent Structure object
    */

   public Structure getParent() {


      return parent;
   }


   /** Returns an identical copy of this Chain .
    * @return an identical copy of this Chain
    */
   public Object clone() {
      // go through all groups and add to new Chain.
      Chain n = new ChainImpl();
      // copy chain data:

      n.setName( getName());
      n.setSwissprotId ( getSwissprotId());
      for (int i=0;i<groups.size();i++){
         Group g = (Group)groups.get(i);
         n.addGroup((Group)g.clone());
      }
      n.setHeader(this.getHeader());

      return n ;
   }

   

   /** {@inheritDoc}
    *
    */
   public void setHeader(Compound mol) {
      this.mol = mol;
   }

   /** {@inheritDoc}
    *
    */
   public Compound getHeader() {
      return this.mol;
   }

   /** set the Swissprot id of this chains .
    * @param sp_id  a String specifying the swissprot id value
    * @see #getSwissprotId
    */

   public void setSwissprotId(String sp_id){
      swissprot_id = sp_id ;
   }

   /** get the Swissprot id of this chains .
    * @return a String representing the swissprot id value
    * @see #setSwissprotId
    */
   public String getSwissprotId() {
      return swissprot_id ;
   }

   /** {@inheritDoc}
    *
    */
   public void addGroup(Group group) {

      group.setChain(this);

      groups.add(group);

      // store the position internally for quick access of this group
      String pdbResnum = group.getPDBCode();
      if ( pdbResnum != null) {
         Integer pos = new Integer(groups.size()-1);
         // ARGH sometimes numbering in PDB files is confusing.
         // e.g. PDB: 1sfe
         /*
          * ATOM    620  N   GLY    93     -24.320  -6.591   4.210  1.00 46.82           N
          * ATOM    621  CA  GLY    93     -24.960  -6.849   5.497  1.00 47.35           C
          * ATOM    622  C   GLY    93     -26.076  -5.873   5.804  1.00 47.24           C
          * ATOM    623  O   GLY    93     -26.382  -4.986   5.006  1.00 47.56           O
             and ...
          * HETATM 1348  O   HOH    92     -21.853 -16.886  19.138  1.00 66.92           O
          * HETATM 1349  O   HOH    93     -26.126   1.226  29.069  1.00 71.69           O
          * HETATM 1350  O   HOH    94     -22.250 -18.060  -6.401  1.00 61.97           O
          */

         // this check is to give in this case the entry priority that is an AminoAcid / comes first...
         if (  pdbResnumMap.containsKey(pdbResnum)) {
            if ( group instanceof AminoAcid)
               pdbResnumMap.put(pdbResnum,pos);
         } else
            pdbResnumMap.put(pdbResnum,pos);
      }

   }

   /** return the group at position .
    *
    *
    * @param position  an int
    * @return a Group object
    * @deprecated use getAtomGroup or getSeqResGroup instead
    */
   public Group getGroup(int position) {

      return (Group)groups.get(position);
   }



   /** return the group at position .
    *
    *
    * @param position  an int
    * @return a Group object
    */
   public Group getAtomGroup(int position) {

      return (Group)groups.get(position);
   }

   /** Return a list of all groups of one of the types defined in hte {@link GroupType} constants.
    *
    *
    * @param type  a String
    * @return an List object containing the groups of type...
    * @deprecated use getAtomGroups instead
    */
   public List<Group> getGroups( String type) {
      return getAtomGroups(type);
   }

   /**  Get the Groups of a certain type, that are listed int the ATOM records of the PDB file.
    *
    *  @param type the type of the groups to return. Can be one of the 3 types defined in the {@link GroupType} constants
    *  @return a list of group objects
    */
   public List<Group> getAtomGroups(String type){
      List<Group> tmp = new ArrayList<Group>() ;
      for (int i=0;i<groups.size();i++){
         Group g = (Group)groups.get(i);
         if (g.getType().equals(type)){
            tmp.add(g);
         }
      }

      return tmp ;
   }

   /** return all groups of this chain .
    * @return a List object representing the Groups of this Chain.
    * @deprecated use getAtomGroups instead
    */
   public List<Group> getGroups(){
      return groups ;
   }


   /** {@inheritDoc}
    *
    */
   public List<Group> getAtomGroups(){
      return groups ;
   }

   /** {@inheritDoc}
    *
    */
   public void setAtomGroups(List<Group> groups){
      for (Group g:groups){
         g.setChain(this);
      }
      this.groups = groups;
   }

   /** {@inheritDoc}
    *
    */
   public Group[] getGroupsByPDB(String pdbresnumStart, String pdbresnumEnd, boolean ignoreMissing)
   throws StructureException {

      if (! ignoreMissing )
         return getGroupsByPDB(pdbresnumStart, pdbresnumEnd);

      List<Group> retlst = new ArrayList<Group>();

      int startPos = Integer.MIN_VALUE;
      int endPos   = Integer.MAX_VALUE;
      boolean doIntCheck = true;
      try {
         startPos = Integer.parseInt(pdbresnumStart);
         endPos   = Integer.parseInt(pdbresnumEnd);

      } catch (Exception e){
         // has insertion code, in this case this will not work...
         doIntCheck = false;
      }


      boolean adding = false;
      boolean foundStart = false;

      for (Group g: groups){

         if ( g.getPDBCode().equals(pdbresnumStart)) {
            adding = true;
            foundStart = true;
         }

         if ( ! (foundStart && adding) ) {
            if ( doIntCheck){
               try {
                  int pos = Integer.parseInt(g.getPDBCode());

                  if ( pos >= startPos) {
                     foundStart = true;
                     adding = true;
                  }
               } catch (Exception e){
                  // insertion code catch. will not work with them...
               }
            }
         }

         if ( adding)
            retlst.add(g);

         if ( g.getPDBCode().equals(pdbresnumEnd)) {
            if ( ! adding)
               throw new StructureException("did not find start PDB residue number " + pdbresnumStart + " in chain " + name);
            adding = false;
            break;
         }
         if (doIntCheck && adding){

            try {
               int pos = Integer.parseInt(g.getPDBCode());
               if (pos >= endPos) {
                  adding = false;
                  break;
               }
            } catch (Exception e){
               // insertion code problem
            }
         }
      }

      if ( ! foundStart){
         throw new StructureException("did not find start PDB residue number " + pdbresnumStart + " in chain " + name);
      }


      //not checking if the end has been found in this case...

      return (Group[]) retlst.toArray(new Group[retlst.size()] );
   }


   public Group getGroupByPDB(String pdbresnum) throws StructureException {
      if ( pdbResnumMap.containsKey(pdbresnum)) {
         Integer pos = (Integer) pdbResnumMap.get(pdbresnum);
         return (Group) groups.get(pos.intValue());
      } else {
         throw new StructureException("unknown PDB residue number " + pdbresnum + " in chain " + name);
      }

   }

   /**
    * {@inheritDoc}
    *
    */
   public Group[] getGroupsByPDB(String pdbresnumStart, String pdbresnumEnd)
   throws StructureException {

      List<Group> retlst = new ArrayList<Group>();

      Iterator<Group> iter = groups.iterator();
      boolean adding = false;
      boolean foundStart = false;

      while ( iter.hasNext()){
         Group g = (Group) iter.next();
         if ( g.getPDBCode().equals(pdbresnumStart)) {
            adding = true;
            foundStart = true;
         }

         if ( adding)
            retlst.add(g);

         if ( g.getPDBCode().equals(pdbresnumEnd)) {
            if ( ! adding)
               throw new StructureException("did not find start PDB residue number " + pdbresnumStart + " in chain " + name);
            adding = false;
            break;
         }
      }

      if ( ! foundStart){
         throw new StructureException("did not find start PDB residue number " + pdbresnumStart + " in chain " + name);
      }
      if ( adding) {
         throw new StructureException("did not find end PDB residue number " + pdbresnumEnd + " in chain " + name);
      }

      return (Group[]) retlst.toArray(new Group[retlst.size()] );
   }



   /**
    * @deprecated use getAtomLength instead
    */
   public int getLength() {
      return getAtomLength();
   }

   /** {@inheritDoc}
    *
    */
   public int getLengthAminos() {

      List<Group> g = getAtomGroups(GroupType.AMINOACID);
      return g.size() ;
   }

   public int getSeqResLength() {
      //new method returns the length of the sequence defined in the SEQRES records
      return seqResGroups.size();
   }

   /** get and set the name of this chain (Chain id in PDB file ).
    * @param nam a String specifying the name value
    * @see #getName
    *
    */

   public void   setName(String nam) { name = nam;   }

   /** get and set the name of this chain (Chain id in PDB file ).
    * @return a String representing the name value
    * @see #setName
    */
   public String getName()           {	return name;  }


   /** String representation.
    * @return String representation of the Chain
    *  */
   public String toString(){
      String newline = System.getProperty("line.separator");
      StringBuffer str = new StringBuffer();
      str.append("Chain >"+getName()+"<"+newline) ;
      if ( mol != null ){
         if ( mol.getMolName() != null){
            str.append(mol.getMolName()+newline);
         }
      }
      str.append("total SEQRES length: " + getSeqResGroups().size() +
            " total ATOM length:" + getAtomLength() + " residues " + newline);

      // loop over the residues

      for ( int i = 0 ; i < seqResGroups.size();i++){
         Group gr = (Group) seqResGroups.get(i);
         str.append(gr.toString() + newline) ;
      }
      return str.toString() ;

   }

   /** Convert the SEQRES groups of a Chain to a Biojava Sequence object.
    *
    * @return the SEQRES groups of the Chain as a Sequence object.
    * @throws IllegalSymbolException
    */
   public Sequence<?> getBJSequence()  {

      //List<Group> groups = c.getSeqResGroups();
      String seq = getSeqResSequence();

      String name = "";
      if ( this.getParent() != null )
         name = getParent().getPDBCode();
      name += "." + getName();

      Sequence<AminoAcidCompound> s = null;
      
      s = new ProteinSequence(seq);
     
      //TODO: return a DNA sequence if the content is DNA...
      return s;

   }



   /** get amino acid sequence of the chain. for backwards compatibility this returns
    * the Atom sequence of the chain.
    * @return a String representing the sequence.
    * @deprecated use getAtomSequence instead
    * @see #getAtomSequence()
    * @see #getSeqResSequence()
    */
   public String getSequence(){
      return getAtomSequence();
   }


   /** {@inheritDoc}
    *
    */
   public String getAtomSequence(){

      String prop = System.getProperty(PDBFileReader.LOAD_CHEM_COMP_PROPERTY);

      if ( prop != null && prop.equalsIgnoreCase("true")){


         List<Group> groups = getAtomGroups();
         StringBuffer sequence = new StringBuffer() ;

         for ( Group g: groups){
            ChemComp cc = g.getChemComp();

            if ( PolymerType.PROTEIN_ONLY.contains(cc.getPolymerType()) ||
                  PolymerType.POLYNUCLEOTIDE_ONLY.contains(cc.getPolymerType())){
               // an amino acid residue.. use for alignment
               String oneLetter= ChemCompGroupFactory.getOneLetterCode(cc);
               sequence.append(oneLetter);
            }

         }
         return sequence.toString();
      }

      // not using ChemCOmp records...		
      List<Group> aminos = getAtomGroups("amino");
      StringBuffer sequence = new StringBuffer() ;
      for ( int i=0 ; i< aminos.size(); i++){
         AminoAcid a = (AminoAcid)aminos.get(i);
         sequence.append( a.getAminoType());
      }

      return sequence.toString();

   }

   /** {@inheritDoc}
    *
    */
   public String getSeqResSequence(){

      String prop = System.getProperty(PDBFileReader.LOAD_CHEM_COMP_PROPERTY);

      if ( prop != null && prop.equalsIgnoreCase("true")){
         StringBuffer str = new StringBuffer();
         for (Group g : seqResGroups) {
            ChemComp cc = g.getChemComp();

            if ( PolymerType.PROTEIN_ONLY.contains(cc.getPolymerType()) ||
                  PolymerType.POLYNUCLEOTIDE_ONLY.contains(cc.getPolymerType())){
               // an amino acid residue.. use for alignment
               String oneLetter= ChemCompGroupFactory.getOneLetterCode(cc);
               str.append(oneLetter);
            }
         }
         return str.toString();
      }

      StringBuffer str = new StringBuffer();
      for (Group group : seqResGroups) {
         if (group instanceof AminoAcid) {
            AminoAcid aa = (AminoAcid)group;
            str.append(aa.getAminoType()) ;
         }

      }
      return str.toString();

   }


   /** {@inheritDoc}
    *
    */
   public Group getSeqResGroup(int position) {

      return seqResGroups.get(position);
   }

   /** {@inheritDoc}
    *
    */
   public List<Group> getSeqResGroups(String type) {
      return seqResGroups;
   }

   /** {@inheritDoc}
    *
    */
   public List<Group> getSeqResGroups() {
      return seqResGroups;
   }

   /** {@inheritDoc}
    *
    */
   public void setSeqResGroups(List<Group> groups){
      for (Group g: groups){
         g.setChain(this);
      }
      this.seqResGroups = groups;
   }


   /** {@inheritDoc}
    *
    */
   public int getAtomLength() {

      return groups.size();
   }

   /** {@inheritDoc}
    *
    */
   public List<Group> getAtomLigands(){

      return getLigands(getAtomGroups());

   }

   private List<Group> getLigands(List<Group> allGroups){
      String prop = System.getProperty(PDBFileReader.LOAD_CHEM_COMP_PROPERTY);

      if ( prop == null || ( ! prop.equalsIgnoreCase("true"))){
         System.err.println("You did not specify PDBFileReader.setLoadChemCompInfo, need to fetch Chemical Components anyways.");
      }


      List<String> waternames = Arrays.asList(new String[]{"HOH", "DOD",  "WAT"});
      List<Group> groups = new ArrayList<Group>();
      for ( Group g: allGroups) {

         ChemComp cc = g.getChemComp();



         if ( ResidueType.lPeptideLinking.equals(cc.getResidueType()) ||
               PolymerType.PROTEIN_ONLY.contains(cc.getPolymerType()) ||
               PolymerType.POLYNUCLEOTIDE_ONLY.contains(cc.getPolymerType())
         ){
            continue;
         }
         if ( ! waternames.contains(g.getPDBName())) {
            //System.out.println("not a prot, nuc or solvent : " + g.getChemComp());
            groups.add(g);
         }
      }
      return groups;
   }
}

