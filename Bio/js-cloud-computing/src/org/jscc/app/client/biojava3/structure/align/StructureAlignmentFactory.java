package org.jscc.app.client.biojava3.structure.align;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.jscc.app.client.biojava3.structure.StructureException;
import org.jscc.app.client.biojava3.structure.align.ce.CeCPMain;
import org.jscc.app.client.biojava3.structure.align.ce.CeMain;
import org.jscc.app.client.biojava3.structure.align.fatcat.FatCatFlexible;
import org.jscc.app.client.biojava3.structure.align.fatcat.FatCatRigid;
import org.jscc.app.client.biojava3.structure.align.seq.SmithWaterman3Daligner;


public class StructureAlignmentFactory {

   public static StructureAlignment[] getAllAlgorithms(){


      List<StructureAlignment> algorithms = new LinkedList<StructureAlignment>();

      algorithms.add( new CeMain() );
      algorithms.add( new CeCPMain() );
      //algorithms.add(new CeSideChainMain());

      StructureAlignment fatcatRigid    = new FatCatRigid();
      StructureAlignment fatcatFlexible = new FatCatFlexible();

      if ( fatcatRigid != null) {
         algorithms.add( fatcatRigid) ;

      }
      if ( fatcatFlexible != null){
         algorithms.add( fatcatFlexible );

      }
      algorithms.add( new SmithWaterman3Daligner()) ;


      //algorithms.add( new BioJavaStructureAlignment());
      return algorithms.toArray(new StructureAlignment[algorithms.size()]);

   }

   public static StructureAlignment getAlgorithm(String name) throws StructureException{
      StructureAlignment[] algorithms = getAllAlgorithms();
      for ( StructureAlignment algo : algorithms){
         if (algo.getAlgorithmName().equalsIgnoreCase(name))
            return algo;
      }


      throw new StructureException("Unknown alignment algorithm: " + name);
   }



   public static String[] getAllAlgorithmNames(){
      StructureAlignment[] algos = getAllAlgorithms();
      List<String> names = new ArrayList<String>();

      for (StructureAlignment alg : algos){
         names.add(alg.getAlgorithmName());
      }

      return (String[])names.toArray(new String[names.size()]);
   }
}
