///*
// *                    PDB web development code
// *
// * This code may be freely distributed and modified under the
// * terms of the GNU Lesser General Public Licence.  This should
// * be distributed with the code.  If you do not have a copy,
// * see:
// *
// *      http://www.gnu.org/copyleft/lesser.html
// *
// * Copyright for this code is held jointly by the individual
// * authors.  These should be listed in @author doc comments.
// *
// *
// * Created on Jul 8, 2009
// * Created by ap3
// *
// */
//
//package org.jscc.app.client.biojava3.structure.align.util;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//
//import org.jscc.app.client.biojava3.core.util.PrettyXMLWriter;
//import org.jscc.app.client.biojava3.core.util.XMLWriter;
//import org.jscc.app.client.biojava3.structure.align.ce.StartupParameters;
//import org.jscc.app.client.biojava3.structure.io.PDBFileReader;
//
//
///** A container to persist config to the file system
// * 
// * @author Andreas Prlic
// *
// */
//public class UserConfiguration
//{
//
//   String pdbFilePath;
//
//   boolean isSplit;
//
//   private boolean autoFetch;
//
//   String fileFormat;
//
//   public static final String PDB_FORMAT   = "PDB";
//   public static final String MMCIF_FORMAT = "mmCif";
//
//   public static final String TMP_DIR = "java.io.tmpdir";
//   public static final String PDB_DIR = "PDB_DIR";
//   
//   public UserConfiguration(){
//      isSplit = true;
//      autoFetch = true;
//      // accessing temp. OS directory:         
//      
//
//      String userProvidedDir = System.getProperty(PDB_DIR);
//
//      if ( userProvidedDir != null ) {
//      
//         pdbFilePath = userProvidedDir;
//         
//      } else {
//
//         String tempdir = System.getProperty(TMP_DIR);
//         
//         pdbFilePath = tempdir;
//      }
//      if ( ! pdbFilePath.endsWith(PDBFileReader.lineSplit) )
//         pdbFilePath = pdbFilePath + PDBFileReader.lineSplit;
//             
//      fileFormat = PDB_FORMAT;
//   }
//
//   public String getPdbFilePath()
//   {
//      return pdbFilePath;
//   }
//
//   public void setPdbFilePath(String pdbFilePath)
//   {
//      this.pdbFilePath = pdbFilePath;
//   }
//
//
//   public boolean isSplit() {
//      return isSplit;
//   }
//
//   public void setSplit(boolean isSplit) {
//      this.isSplit = isSplit;
//   }
//
//   public boolean getAutoFetch() {
//      return autoFetch;
//   }
//
//   public void setAutoFetch(boolean autoFetch) {
//      this.autoFetch = autoFetch;
//   }
//
//   /** convert Configuration to an XML file so it can be serialized
//    * 
//    * @param pw
//    * @return XMLWriter
//    * @throws IOException
//    */
//   public XMLWriter toXML(PrintWriter pw) 
//   throws IOException
//   {
//
//      XMLWriter     xw = new PrettyXMLWriter( pw);
//
//      toXML(xw);
//      return xw ;
//   }
//
//
//   /** convert Configuration to an XML file so it can be serialized
//    * add to an already existing xml file.
//    * 
//    * @param xw the XML writer to use
//    * @return the writer again
//    * @throws IOException
//    */
//
//   public XMLWriter toXML(XMLWriter xw) 
//   throws IOException
//   {
//      xw.printRaw("<?xml version='1.0' standalone='no' ?>");
//      //xw.printRaw("<!DOCTYPE " + XML_CONTENT_TYPE + " SYSTEM '" + XML_DTD + "' >");
//      xw.openTag("JFatCatConfig");
//
//      xw.openTag("PDBFILEPATH");
//      // we don;t serialize the tempdir...
//      String tempdir = System.getProperty(TMP_DIR);
//      if (! pdbFilePath.equals(tempdir))
//         xw.attribute("path", pdbFilePath);
//      
//      xw.attribute("split", isSplit +"" );
//      xw.attribute("autofetch", autoFetch+"");
//      xw.attribute("fileFormat", fileFormat);
//      xw.closeTag("PDBFILEPATH");
//
//      xw.closeTag("JFatCatConfig");
//      return xw ;
//
//   }
//
//   public static UserConfiguration fromStartupParams(StartupParameters params) {
//      UserConfiguration config = new UserConfiguration();
//      config.setPdbFilePath(params.getPdbFilePath());
//      config.setAutoFetch(params.isAutoFetch());
//      config.setSplit(params.isPdbDirSplit());
//      // TODO support MMCif Files
//      config.setFileFormat(UserConfiguration.PDB_FORMAT);
//      return config;
//   }
//
//   public void setFileFormat (String fileFormat){
//      this.fileFormat = fileFormat;
//   }
//
//   public String getFileFormat()
//   {
//      return fileFormat;
//   }
//
//
//
//
//
//
//}
