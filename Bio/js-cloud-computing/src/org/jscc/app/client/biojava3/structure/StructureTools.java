/*
 *                  BioJava development code
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
 * Created on Jan 4, 2006
 *
 */
package org.jscc.app.client.biojava3.structure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//import org.biojava.bio.seq.ProteinTools;
//import org.biojava.bio.seq.io.SymbolTokenization;
//import org.biojava.bio.symbol.Alphabet;
//import org.biojava.bio.symbol.IllegalSymbolException;
//import org.biojava.bio.symbol.Symbol;


/**
 * A class that provides some tool methods.
 *
 * @author Andreas Prlic, Jules Jacobsen
 * @since 1.0
 * @version %I% %G%
 */
public class StructureTools {

	/** The Atom name of C-alpha atoms.
	 *
	 */
	public static final String caAtomName = " CA ";

	public static final String nAtomName = "N";

	public static final String oAtomName = "O";

	public static final String cbAtomName = "CB";


	/** The names of the Atoms that form the backbone.
	 *
	 */
	public static final String[] backboneAtomNames = {nAtomName,caAtomName,"C",oAtomName, cbAtomName};

	public static final Character UNKNOWN_GROUP_LABEL = new Character('x');;


	private static final String insertionCodeRegExp = "([0-9]+)([a-zA-Z]*)";
	private static final Pattern insertionCodePattern = Pattern.compile(insertionCodeRegExp);


	// there is a file format change in PDB 3.0 and nucleotides are being renamed
	static private Map<String, Integer> nucleotides30 ;
	static private Map<String, Integer> nucleotides23 ;

	//amino acid 3 and 1 letter code definitions
	private static final Map<String, Character> aminoAcids;

	//	// for conversion 3code 1code
	//	private static  SymbolTokenization threeLetter ;
	//	private static  SymbolTokenization oneLetter ;

	public static Logger logger =  Logger.getLogger("org.jscc.app.client.biojava3.structure");

	static {
		nucleotides30 = new HashMap<String,Integer>();
		nucleotides30.put("DA",1);
		nucleotides30.put("DC",1);
		nucleotides30.put("DG",1);
		nucleotides30.put("DT",1);
		nucleotides30.put("DI",1);
		nucleotides30.put("A",1);
		nucleotides30.put("G",1);
		nucleotides30.put("C",1);
		nucleotides30.put("U",1);
		nucleotides30.put("I",1);

		//TODO: check if they are always HETATMs, in that case this will not be necessary
		// the DNA linkers - the +C , +G, +A  +T +U and +I have been replaced with these:
		nucleotides30.put("TAF",1); // 2'-DEOXY-2'-FLUORO-ARABINO-FURANOSYL THYMINE-5'-PHOSPHATE
		nucleotides30.put("TC1",1); // 3-(5-PHOSPHO-2-DEOXY-BETA-D-RIBOFURANOSYL)-2-OXO-1,3-DIAZA-PHENOTHIAZINE
		nucleotides30.put("TFE",1); // 2'-O-[2-(TRIFLUORO)ETHYL] THYMIDINE-5'-MONOPHOSPHATE
		nucleotides30.put("TFO",1); // [2-(6-AMINO-9H-PURIN-9-YL)-1-METHYLETHOXY]METHYLPHOSPHONIC ACID"
		nucleotides30.put("TGP",1); // 5'-THIO-2'-DEOXY-GUANOSINE PHOSPHONIC ACID
		nucleotides30.put("THX",1); // PHOSPHONIC ACID 6-({6-[6-(6-CARBAMOYL-3,6,7,8-TETRAHYDRO-3,6-DIAZA-AS-INDACENE-2-CARBONYL)-3,6,7,8-TETRAHYDRO-3,6-DIAZA-AS-INDOCENE-2-CARBONYL]-3,6,7,8-TETRAHYDRO-3,6-DIAZA-AS-INDACENE-2-CARBONL}-AMINO)-HEXYL ESTER 5-(5-METHYL-2,4-DIOXO-3,4-DIHYDRO-2H-PYRIMIDIN-1-YL)-TETRAHYDRO-FURAN-2-YLMETHYL ESTER
		nucleotides30.put("TLC",1); // 2-O,3-ETHDIYL-ARABINOFURANOSYL-THYMINE-5'-MONOPHOSPHATE
		nucleotides30.put("TLN",1); //  [(1R,3R,4R,7S)-7-HYDROXY-3-(THYMIN-1-YL)-2,5-DIOXABICYCLO[2.2.1]HEPT-1-YL]METHYL DIHYDROGEN PHOSPHATE"
		nucleotides30.put("TP1",1); // 2-(METHYLAMINO)-ETHYLGLYCINE-CARBONYLMETHYLENE-THYMINE
		nucleotides30.put("TPC",1); // 5'-THIO-2'-DEOXY-CYTOSINE PHOSPHONIC ACID
		nucleotides30.put("TPN",1); // 2-AMINOETHYLGLYCINE-CARBONYLMETHYLENE-THYMINE



		// store nucleic acids (C, G, A, T, U, and I), and
		// the modified versions of nucleic acids (+C, +G, +A, +T, +U, and +I), and
		nucleotides23  = new HashMap<String,Integer>();
		String[] names = {"C","G","A","T","U","I","+C","+G","+A","+T","+U","+I"};
		for (int i = 0; i < names.length; i++) {
			String n = names[i];
			nucleotides23.put(n,1);
		}

		aminoAcids = new HashMap<String, Character>();
		aminoAcids.put("GLY", new Character('G'));
		aminoAcids.put("ALA", new Character('A'));
		aminoAcids.put("VAL", new Character('V'));
		aminoAcids.put("LEU", new Character('L'));
		aminoAcids.put("ILE", new Character('I'));
		aminoAcids.put("PHE", new Character('F'));
		aminoAcids.put("TYR", new Character('Y'));
		aminoAcids.put("TRP", new Character('W'));
		aminoAcids.put("PRO", new Character('P'));
		aminoAcids.put("HIS", new Character('H'));
		aminoAcids.put("LYS", new Character('K'));
		aminoAcids.put("ARG", new Character('R'));
		aminoAcids.put("SER", new Character('S'));
		aminoAcids.put("THR", new Character('T'));
		aminoAcids.put("GLU", new Character('E'));
		aminoAcids.put("GLN", new Character('Q'));
		aminoAcids.put("ASP", new Character('D'));
		aminoAcids.put("ASN", new Character('N'));
		aminoAcids.put("CYS", new Character('C'));
		aminoAcids.put("MET", new Character('M'));
		//MSE is only found as a molecular replacement for MET
		aminoAcids.put("MSE", new Character('M'));
		//'non-standard', genetically encoded
		//http://www.chem.qmul.ac.uk/iubmb/newsletter/1999/item3.html
		//IUBMB recommended name is 'SEC' but the wwPDB currently use 'CSE'
		//likewise 'PYL' (IUBMB) and 'PYH' (PDB)
		aminoAcids.put("CSE", new Character('U'));
		aminoAcids.put("SEC", new Character('U'));
		aminoAcids.put("PYH", new Character('O'));
		aminoAcids.put("PYL", new Character('O'));

		//		try {
		//			Alphabet alpha_prot = ProteinTools.getAlphabet();
		//			threeLetter = alpha_prot.getTokenization("name");
		//			oneLetter  = alpha_prot.getTokenization("token");
		//		} catch (Exception e) {
		//			// this should not happen.
		//			// only if BioJava has not been built correctly...
		//			logger.config(e.getMessage());
		//			e.printStackTrace() ;
		//		}

	}


	/** Count how many number of Atoms are contained within a Structure object.
	 *
	 * @param s the structure object
	 * @return the number of Atoms in this Structure
	 */
	public static final int getNrAtoms(Structure s){

		int nrAtoms = 0;

		Iterator<Group> iter = new GroupIterator(s);

		while ( iter.hasNext()){
			Group g = (Group) iter.next();
			nrAtoms += g.size();
		}

		return nrAtoms;
	}


	/** Count how many groups are contained within a structure object.
	 *
	 * @param s the structure object
	 * @return the number of groups in the structure
	 */
	public static final int getNrGroups(Structure s){
		int nrGroups = 0;

		List<Chain> chains = s.getChains(0);
		Iterator<Chain> iter = chains.iterator();
		while (iter.hasNext()){
			Chain c = (Chain) iter.next();
			nrGroups += c.getAtomLength();
		}
		return nrGroups;
	}


	/** Returns an array of the requested Atoms from the Structure object. Iterates over all groups
	 * and checks if the requested atoms are in this group, no matter if this is a AminoAcid or Hetatom group.
	 *
	 *
	 * @param s the structure to get the atoms from
	 *
	 * @param atomNames  contains the atom names to be used.
	 * @return an Atom[] array
	 */
	public static final Atom[] getAtomArray(Structure s, String[] atomNames){
		Iterator<Group> iter = new GroupIterator(s);
		List<Atom> atoms = new ArrayList<Atom>();
		while ( iter.hasNext()){
			Group g = (Group) iter.next();

			// a temp container for the atoms of this group
			List<Atom> thisGroupAtoms = new ArrayList<Atom>();
			// flag to check if this group contains all the requested atoms.
			boolean thisGroupAllAtoms = true;
			for ( int i = 0 ; i < atomNames.length; i++){
				String atomName = atomNames[i];
				try {
					Atom a = g.getAtom(atomName);
					thisGroupAtoms.add(a);
				} catch (StructureException e){
					// this group does not have a required atom, skip it...
					thisGroupAllAtoms = false;
					break;
				}
			}
			if ( thisGroupAllAtoms){
				// add the atoms of this group to the array.
				Iterator<Atom> aIter = thisGroupAtoms.iterator();
				while(aIter.hasNext()){
					Atom a = (Atom) aIter.next();
					atoms.add(a);
				}
			}

		}
		return (Atom[]) atoms.toArray(new Atom[atoms.size()]);

	}

	/** Returns an array of the requested Atoms from the Structure object. Iterates over all groups
	 * and checks if the requested atoms are in this group, no matter if this is a AminoAcid or Hetatom group.
	 *
	 *
	 * @param c the Chain to get the atoms from
	 *
	 * @param atomNames  contains the atom names to be used.
	 * @return an Atom[] array
	 */
	public static final Atom[] getAtomArray(Chain c, String[] atomNames){

		List<Group> groups = c.getAtomGroups();

		List<Atom> atoms = new ArrayList<Atom>();

		for (Group g : groups){

			// a temp container for the atoms of this group
			List<Atom> thisGroupAtoms = new ArrayList<Atom>();
			// flag to check if this group contains all the requested atoms.
			boolean thisGroupAllAtoms = true;
			for ( int i = 0 ; i < atomNames.length; i++){
				String atomName = atomNames[i];
				try {
					Atom a = g.getAtom(atomName);
					thisGroupAtoms.add(a);
				} catch (StructureException e){
					// this group does not have a required atom, skip it...
					thisGroupAllAtoms = false;
					break;
				}
			}
			if ( thisGroupAllAtoms){
				// add the atoms of this group to the array.
				Iterator<Atom> aIter = thisGroupAtoms.iterator();
				while(aIter.hasNext()){
					Atom a = (Atom) aIter.next();
					atoms.add(a);
				}
			}

		}
		return (Atom[]) atoms.toArray(new Atom[atoms.size()]);

	}

	/** Returns an Atom array of the CA atoms.
	 * @param c the structure object
	 * @return an Atom[] array
	 */
	public static final Atom[] getAtomCAArray(Chain c){
		String[] atomNames = {" CA " };
		return getAtomArray(c,atomNames);
	}

	/** Provides an equivalent copy of Atoms in a new array. Clones everything, starting with parent 
	 * groups and chains. The chain will only contain groups that are part of the CA array.
	 * 
	 * @param ca array of CA atoms
	 * @return Atom array
	 */
	public static final Atom[] cloneCAArray(Atom[] ca) throws StructureException{
		Atom[] newCA = new Atom[ca.length];

		List<Chain> model = new ArrayList<Chain>();
		int apos = -1;
		for(Atom a: ca){
			apos++;
			Group parentG = a.getParent();
			Chain parentC = parentG.getParent();

			Chain newChain = null;
			for ( Chain c : model){
				if ( c.getName().equals(parentC.getName())){
					newChain = c;
					break;
				}
			}
			if ( newChain == null){
				newChain = new ChainImpl();
				newChain.setName(parentC.getName());
				model.add(newChain);
			}

			Group parentN = (Group)parentG.clone();

			newCA[apos] = parentN.getAtom(" CA ");
			newChain.addGroup(parentN);
		}
		return newCA;
	}

	/** Clone a set of CA Atoms, but returns the parent groups
	 *  
	 * @param ca Atom array
	 * @return Group array
	 */
	public static Group[] cloneGroups(Atom[] ca) {
		Group[] newGroup = new Group[ca.length]; 

		List<Chain> model = new ArrayList<Chain>();
		int apos = -1;
		for(Atom a: ca){
			apos++;
			Group parentG = a.getParent();
			Chain parentC = parentG.getParent();

			Chain newChain = null;
			for ( Chain c : model){
				if ( c.getName().equals(parentC.getName())){
					newChain = c;
					break;
				}
			}
			if ( newChain == null){
				newChain = new ChainImpl();
				newChain.setName(parentC.getName());
				model.add(newChain);
			}

			Group ng = (Group)parentG.clone();
			newGroup[apos] = ng;
			newChain.addGroup(ng);
		}
		return newGroup;
	}

	/** Returns an Atom array of the CA atoms.
	 * @param s the structure object
	 * @return an Atom[] array
	 */
	public static Atom[] getAtomCAArray(Structure s){
		String[] atomNames = {" CA "};
		return getAtomArray(s,atomNames);
	}

	/** Returns an Atom array of the MainChain atoms.

	 * @param s the structure object
	 * @return an Atom[] array
	 */
	public static Atom[] getBackboneAtomArray(Structure s){
		String[] atomNames = backboneAtomNames;
		return getAtomArray(s,atomNames);
	}


	/** convert three character amino acid codes into single character
	 *  e.g. convert CYS to C
	 *  @return a character
	 *  @param code3 a three character amino acid representation String
	 *  @throws IllegalSymbolException
	 */

	public static final Character convert_3code_1code(String code3)
	throws UnknownPdbAminoAcidException {
		//	{
		//		Symbol sym   =  threeLetter.parseToken(code3) ;
		//		String code1 =  oneLetter.tokenizeSymbol(sym);
		//
		//		return new Character(code1.charAt(0)) ;
		Character code1 = null;
		code1 = aminoAcids.get(code3);

		if (code1 == null) {
			throw new UnknownPdbAminoAcidException(code3 + " not a standard amino acid");
		} else {
			return code1;
		}

	}

	/** convert a three letter code into single character.
	 * catches for unusual characters
	 *
	 * @param groupCode3 three letter representation
	 * @return null if group is a nucleotide code
	 */
	public static final Character get1LetterCode(String groupCode3){

		Character aminoCode1 = null;
		try {
			// is it a standard amino acid ?
			aminoCode1 = convert_3code_1code(groupCode3);
		} catch (UnknownPdbAminoAcidException e){
			// hm groupCode3 is not standard
			// perhaps it is an nucleotide?
			if ( isNucleotide(groupCode3) ) {
				//System.out.println("nucleotide, aminoCode1:"+aminoCode1);
				aminoCode1= null;
			} else {
				// does not seem to be so let's assume it is
				//  nonstandard aminoacid and label it "X"
				//logger.warning("unknown group name "+groupCode3 );
				aminoCode1 = UNKNOWN_GROUP_LABEL;
			}
		}

		return aminoCode1;

	}


	/* Test if the threelettercode of an ATOM entry corresponds to a
	 * nucleotide or to an aminoacid.
	 * @param a 3-character code for a group.
	 *
	 */
	public static final boolean isNucleotide(String groupCode3){

		String code = groupCode3.trim();
		if ( nucleotides30.containsKey(code)){
			return true;
		}

		if ( nucleotides23.containsKey(code)){
			return true;
		}

		return false ;
	}

	/** Reduce a structure to provide a smaller representation . Only takes the first model of the structure. If chainId is provided only return a structure containing that Chain ID. 
	 * Converts lower case chain IDs to upper case if structure does not contain a chain with that ID. 
	 * 
	 * @param s
	 * @param chainId
	 * @return Structure
	 * @since 3.0
	 */
	@SuppressWarnings("deprecation")
	public static final Structure getReducedStructure(Structure s, String chainId) throws StructureException{
		// since we deal here with structure alignments,
		// only use Model 1...

		Structure newS = new StructureImpl();
		newS.setHeader(s.getHeader());
		newS.setPDBCode(s.getPDBCode());
		newS.setPDBHeader(s.getPDBHeader());

		if ( chainId != null)
			chainId = chainId.trim();

		if ( chainId == null || chainId.equals("")){
			// only get model 0
			List<Chain> model0 = s.getModel(0);
			for (Chain c : model0){
				newS.addChain(c);
			}
			return newS;

		}

		Chain c =  null;
		try {
			c = s.getChainByPDB(chainId);
		} catch (StructureException e){
			System.err.println(e.getMessage() + " trying upper case Chain id...");
			c = s.getChainByPDB(chainId.toUpperCase());

		}
		if ( c != null)
			newS.addChain(c);


		return newS;
	}


	/** Reduce a structure to provide a smaller representation . Only takes the first model of the structure. If chainNr >=0 only takes the chain at that position into account.	 * 
	 * @param s
	 * @param chainNr can be -1 to request all chains of model 0, otherwise will only add chain at this position 
	 * @return Structure object
	 * @since 3.0
	 */
	@SuppressWarnings("deprecation")
	public static final Structure getReducedStructure(Structure s, int chainNr) throws StructureException{
		// since we deal here with structure alignments,
		// only use Model 1...

		Structure newS = new StructureImpl();
		newS.setHeader(s.getHeader());
		newS.setPDBCode(s.getPDBCode());
		newS.setPDBHeader(s.getPDBHeader());
		newS.setSSBonds(s.getSSBonds());
		newS.setDBRefs(s.getDBRefs());
		if ( chainNr < 0 ) {

			// only get model 0
			List<Chain> model0 = s.getModel(0);
			for (Chain c : model0){
				newS.addChain(c);
			}
			return newS;

		}


		Chain c =  null;

		c = s.getChain(chainNr);

		newS.addChain(c);


		return newS;
	}

	/** In addition to the functionality provided by getReducedStructure also provides a way to specify sub-regions of a structure with the following 
	 * specification:
	 *
	 * If range is null or "" returns the whole structure / chain.
	 * 
	 * range can be surrounded by ( and ). (but will be removed).
	 * ranges are specified as
	 * PDBresnum1 : PDBresnum2
	 * 
	 *  a list of ranges is separated by ,
	 *  
	 *  Example
	 *  4GCR(A:1-83)
	 *  1CDG(A:407-495,A:582-686)
	 *  
	 * 
	 * 
	 * @param s
	 * @param ranges
	 * @return a structure object
	 */
	@SuppressWarnings("deprecation")
	public static final Structure getSubRanges(Structure s, String ranges ) 
	throws StructureException
	{
		Structure struc = getReducedStructure(s, null);

		if ( ranges == null || ranges.equals(""))
			throw new IllegalArgumentException("ranges can't be null or empty");

		ranges = ranges.trim();

		if ( ranges.startsWith("("))
			ranges = ranges.substring(1);
		if ( ranges.endsWith(")")) {
			ranges = ranges.substring(0,ranges.length()-1);
		}

		Structure newS = new StructureImpl();
		newS.setHeader(s.getHeader());
		newS.setPDBCode(s.getPDBCode());
		newS.setPDBHeader(s.getPDBHeader());

		String[] rangS =ranges.split(",");


		String prevChainId = null;

		for ( String r: rangS){
			String[] coords = r.split(":");
			if ( coords.length > 2){
				throw new StructureException("wrong range specification, should be provided as chainID:pdbResnum1=pdbRensum2");
			}


			Chain chain = struc.getChainByPDB(coords[0]);
			Group[] groups;
			if  ( coords.length > 1){
				// if length 1, only provided a Chain id...


				String[] pdbRanges = coords[1].split("-");
				if ( pdbRanges.length!= 2)
					throw new StructureException("wrong range specification, should be provided as chainID:pdbResnum1=pdbRensum2");
				String pdbresnumStart = pdbRanges[0].trim();
				String pdbresnumEnd   = pdbRanges[1].trim();

				groups = chain.getGroupsByPDB(pdbresnumStart, pdbresnumEnd);
				
			} else {
				// only chain ID provided ... add the whole chain...
				 groups = chain.getAtomGroups().toArray(new Group[chain.getAtomGroups().size()]);
			}
			
			Chain c = null;
			if ( prevChainId == null) {
				// first chain...
				c = new ChainImpl();
				c.setName(chain.getName());
				newS.addChain(c);
			} else if ( prevChainId.equals(chain.getName())) {
				c = newS.getChainByPDB(prevChainId);

			} else {
				try {
					c = newS.getChainByPDB(chain.getName());
				} catch (StructureException e){
					// chain not in structure yet...
					c = new ChainImpl();
					c.setName(chain.getName());
					newS.addChain(c);
				}
			}

			// add the groups to the chain:
			for ( Group g: groups) {
				c.addGroup(g);
			}

			prevChainId = c.getName();
		}


		return newS;
	}

	public static final String convertAtomsToSeq(Atom[] atoms) {

		StringBuffer buf = new StringBuffer();
		Group prevGroup  = null;
		for (Atom a : atoms){
			Group g = a.getParent();
			if ( prevGroup != null) {
				if ( prevGroup.equals(g)) {
					// we add each group only once.
					continue;
				}
			}
			String code3 = g.getPDBName();
			try {
				buf.append(convert_3code_1code(code3) );
			} catch (UnknownPdbAminoAcidException e){
				buf.append('X');
			}
			prevGroup = g;

		}
		return buf.toString();
	}

	/** get a PDB residue number object for this group
	 * 
	 * @param g Group object
	 * @return a ResidueNumber object
	 */
	public static final ResidueNumber getPDBResidueNumber(Group g){

		ResidueNumber pdbResNum = new ResidueNumber();

		Chain parent = g.getParent();
		if ( parent != null)
			pdbResNum.setChainId(parent.getName());

		Matcher matcher = insertionCodePattern.matcher(g.getPDBCode());
		if (matcher.find()){

			String number = matcher.group(1);
			String insCode = matcher.group(2);
			pdbResNum.setSeqNum(Integer.parseInt(number));
			if ((insCode != null) && (! insCode.equals(""))){
				pdbResNum.setInsCode(insCode);
			}
		} else {
			try {
				pdbResNum.setSeqNum(Integer.parseInt(g.getPDBCode()));
			} catch (NumberFormatException e){

			}
		}

		return pdbResNum;

	}
	
	/** Get a group represented by a ResidueNumber.
	 * 
	 * @param struc a {@link Structure}
	 * @param pdbResNum a {@link ResidueNumber}
	 * @return a group in the structure that is represented by the pdbResNum. 
	 * @throws StructureException if the group cannot be found.
	 */
	public static final Group getGroupByPDBResidueNumber(Structure struc, 
			ResidueNumber pdbResNum) throws StructureException {
		if (struc == null || pdbResNum==null) {
			throw new IllegalArgumentException("Null argument(s).");
		}
		
		Chain chain = struc.findChain(pdbResNum.getChainId());
		
		String numIns = "" + pdbResNum.getSeqNum();
		if (pdbResNum.getInsCode() != null) {
			numIns += pdbResNum.getInsCode();
		}
		
		return chain.getGroupByPDB(numIns);
	}

}
