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
 * Created on Jun 17, 2010
 * Author: ap3 
 *
 */

package org.jscc.app.client.biojava3.structure;

import java.io.Serializable;

/** Everything that is needed to uniquely describe a residue position
 * 
 * @author Andreas Prlic
 *
 */
public class ResidueNumber implements Serializable
{
   
   private static final long serialVersionUID = 1773011704758536083L;
   private String chainId;
   private String insCode;
   private Integer seqNum;

   public ResidueNumber() {
   }

    public ResidueNumber(String chainId, Integer residueNumber, String insCode) {
        this.chainId = chainId;
        this.seqNum = residueNumber;
        this.insCode = insCode;
    }
   
   public String getChainId()
   {
      return chainId;
   }
   public void setChainId(String chainId)
   {
      this.chainId = chainId;
   }
   public String getInsCode()
   {
      return insCode;
   }
   public void setInsCode(String insCode)
   {
      this.insCode = insCode;
   }
   public Integer getSeqNum()
   {
      return seqNum;
   }
   public void setSeqNum(Integer seqNum)
   {
      this.seqNum = seqNum;
   }

    @Override
   public boolean equals(Object obj) {
	   if (!(obj instanceof ResidueNumber))
		   return false;
	   
	   if (obj==this)
		   return true;
	   
	   ResidueNumber anNumber = (ResidueNumber) obj;
	   
	   if (!chainId.equals(anNumber.getChainId()))
		   return false;
	   
	   if (insCode!=null) {
		   if (!insCode.equals(anNumber.getInsCode()))
			   return false;
	   } else {
		   if (anNumber.getInsCode()!=null)
			   return false;
	   }
	   
	   if (!seqNum.equals(anNumber.getSeqNum()))
		   return false;
	   
	   return true;
   }
   
    @Override
   public int hashCode() {
	   int result = 17;
	   result = 31 * result + chainId.hashCode();
	   result = 31 * result + seqNum.hashCode();
	   result = 31 * result + (insCode==null ? 0 : insCode.hashCode());
	   return result;
   }
   
   @Override
   public String toString() {
	   return "Chain:" + chainId + " resNum:" + seqNum + " insCode:" + insCode ;
   }

   public String toPDB() {
       return String.format("%s%4d%-2s", chainId, seqNum, insCode);
   }
}
