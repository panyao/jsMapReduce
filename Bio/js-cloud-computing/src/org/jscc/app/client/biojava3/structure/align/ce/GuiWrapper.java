package org.jscc.app.client.biojava3.structure.align.ce;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.jscc.app.client.biojava3.structure.Atom;
import org.jscc.app.client.biojava3.structure.Group;
import org.jscc.app.client.biojava3.structure.Structure;
import org.jscc.app.client.biojava3.structure.align.model.AFPChain;

/** A class to wrap some of the strucutre.gui classes using Reflection
 *  
 * @author Andreas Prlic
 *
 */
public class GuiWrapper {

	static final String guiPackage = "org.jscc.app.client.biojava3.structure.gui";

	static final String strucAlignmentDisplay = "org.jscc.app.client.biojava3.structure.align.gui.StructureAlignmentDisplay";
	static final String displayAFP   = "org.jscc.app.client.biojava3.structure.align.gui.DisplayAFP" ;
	static final String alignmentGUI = "org.jscc.app.client.biojava3.structure.align.gui.AlignmentGui";
	static final String strucAligJmol = "org.jscc.app.client.biojava3.structure.align.gui.jmol.StructureAlignmentJmol";

	public static boolean isGuiModuleInstalled(){
		String className = displayAFP;
		try {
			Class c = Class.forName(className);
		} catch (ClassNotFoundException ex){
			return false;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	public static Object display(AFPChain afpChain, Atom[] ca1, Atom[] ca2) 
	throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException{

		Class c = Class.forName(strucAlignmentDisplay);

		Method display = c.getMethod("display", new Class[]{AFPChain.class, Atom[].class, 
				Atom[].class});

		Object structureAlignmentJmol = display.invoke(null, afpChain,ca1,ca2);

		return structureAlignmentJmol;
	}

	@SuppressWarnings("unchecked")
	public static void showAlignmentImage(AFPChain afpChain, Atom[] ca1,
			Atom[] ca2, Object jmol)
	throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException{

		Class structureAlignmentJmol = Class.forName(strucAligJmol);

		Class c = Class.forName(displayAFP);		
		Method show = c.getMethod("showAlignmentImage", new Class[] {AFPChain.class, Atom[].class, Atom[].class, structureAlignmentJmol});

		show.invoke(null,afpChain, ca1, ca2, jmol);
	}

	@SuppressWarnings("unchecked")
	public static void showAlignmentGUI()
	throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		// proxy for AlignmentGui.getInstance();


		Class c = Class.forName(alignmentGUI);
		Method m = c.getMethod("getInstance", null);
		m.invoke(c,null);
	}

	public static Structure getAlignedStructure(Atom[] ca1, Atom[] ca2)
	throws ClassNotFoundException, NoSuchMethodException,
	InvocationTargetException, IllegalAccessException{

		Class structureAlignmentJmol = Class.forName(strucAligJmol);

		Class c = Class.forName(displayAFP);		
		Method show = c.getMethod("getAlignedStructure", new Class[] { Atom[].class, Atom[].class});

		Structure s = (Structure) show.invoke(null, ca1, ca2);

		return s;

	}
	
	public static Group[] prepareGroupsForDisplay(AFPChain afpChain, Atom[] ca1,
			Atom[] ca2)
		throws ClassNotFoundException, NoSuchMethodException,
		InvocationTargetException, IllegalAccessException{
			Class c = Class.forName(strucAlignmentDisplay);

			Method display = c.getMethod("prepareGroupsForDisplay", new Class[]{AFPChain.class, Atom[].class, 
					Atom[].class});

			Object groups = display.invoke(null, afpChain,ca1,ca2);

			return (Group[]) groups;
	}
	
	public static Atom[] getAtomArray(Atom[] ca, List<Group> hetatoms, List<Group> nucs)
	throws ClassNotFoundException, NoSuchMethodException,
	InvocationTargetException, IllegalAccessException{

		Class structureAlignmentJmol = Class.forName(strucAligJmol);

		Class c = Class.forName(displayAFP);		
		Method show = c.getMethod("getAtomArray", new Class[] { Atom[].class, List.class, List.class});

		Atom[] atoms = (Atom[]) show.invoke(null, ca, hetatoms, nucs);

		return atoms;

	}

}
