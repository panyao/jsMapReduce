/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jscc.app.client.biojava3.structure;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Locale;

/**
 * Holds the data of sites presented in PDB files
 * Example from the PDB flatfile:
 * SITE     1 AC1  3 GLY A  65  CYS A  67  HOH A 180
 * SITE     1 AC2 10 HIS C  37  ALA C  39  THR C 152  LEU C 153
 * SITE     2 AC2 10 HIS D  37  ALA D  39  THR D 152  LEU D 153
 * SITE     3 AC2 10 SER D 154  GOL D 172
 * @author Amr AL-Hossary
 * @author Jules Jacobsen
 */
public class Site implements PDBRecord, Serializable {

    private static final long serialVersionUID = -4577047072916341237L;
    private static final String lineEnd = System.getProperty("line.separator");

    private String siteID = "";
    private List<Group> groups = new ArrayList<Group>();
    //variables for REMARK 800
    private String evCode = "";
    private String description = "";

    public Site() {
    }

    public Site(String siteID, List<Group> groups) {
        this.siteID = siteID;
        this.groups = groups;
    }


    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("SITE ");
        stringBuilder.append(siteID + " " + groups.size() + " ");
        for (Group group : groups) {
            // 012345678910   
            //'ARG H 221A '
            String groupString = String.format("%s %s",
                        group.getPDBName(),
                        group.getResidueNumber().toPDB());
            stringBuilder.append(groupString);
        }
        stringBuilder.append(lineEnd);
        return stringBuilder.toString();
    }

   
    public String toPDB() {
        StringBuffer buffer = new StringBuffer();
        toPDB(buffer);
        return buffer.toString();
    }


    public void toPDB(StringBuffer buf) {
        if (groups == null || groups.size() < 1) {
            return;
        }

        //SITE     1 CAT  3 HIS H  57  ASP H 102  SER H 195
        //SITE     1 AC1  6 ARG H 221A LYS H 224  HOH H 403  HOH H 460
        //SITE     2 AC1  6 HOH H 464  HOH H 497
        //         ^  ^   ^
        //     cont# id  group size
        //max 4 groups per line

        //counters for tracking where we are
        int seqNum = 0;
        int groupsWritten = 0;
        int groupNum = 0;
        //new StringBuilder for addingthe groups to
        StringBuffer stringBuilder = new StringBuffer();
        Formatter form = new Formatter(stringBuilder, Locale.UK);
        
        while (groupsWritten < groups.size()) {
//            stringBuilder.append("SITE  ");
//            stringBuilder.append(String.format("SITE   %3d %3s %2d ", seqNum + 1, siteID, groups.size()));
            StringBuilder groupsString = new StringBuilder();
            for (int i = 0; i < 4 && groupsWritten < groups.size(); i++) {
                Group group = groups.get(groupNum);
                String groupString = String.format("%s %s",
//                String groupString = String.format("%s %s%4d%-2s",
                        group.getPDBName(),
                        group.getResidueNumber().toPDB());
                groupsWritten++;
                groupNum++;
                //remove the trailing whitespace of the last element of a line
                if (i == 3 || groupsWritten == groups.size()) {
//                    System.out.println("i = " + i +" last element '" + groupString + "'");
                    groupString = groupString.trim();
                }
//                System.out.println("i = " + i + " group = '" + groupString + "'");
//                stringBuilder.append(groupString);
                groupsString.append(groupString);
            }
            stringBuilder.append(String.format("SITE   %3d %3s %2d %-62s", seqNum + 1, siteID, groups.size(), groupsString.toString()));
            //iterate the line counter, add the end of line character
            seqNum++;
            stringBuilder.append(lineEnd);
        }

        buf.append(form.toString());
    }

    /**
     * Appends the REMARK 800 section pertaining to the site onto the end of the
     * StringBuffer provided.
     *
     * For example in pdb 1a4w:
     * REMARK 800 SITE_IDENTIFIER: CAT
     * REMARK 800 EVIDENCE_CODE: UNKNOWN
     * REMARK 800 SITE_DESCRIPTION: ACTIVE SITE
     *
     * @param stringBuffer
     */
    public void remark800toPDB(StringBuffer stringBuffer) {
        //REMARK 800 SITE_IDENTIFIER: CAT
        //REMARK 800 EVIDENCE_CODE: UNKNOWN
        //REMARK 800 SITE_DESCRIPTION: ACTIVE SITE

        stringBuffer.append(String.format(Locale.UK, "REMARK 800 SITE_IDENTIFIER: %-52s%s", siteID, lineEnd));
        stringBuffer.append(String.format(Locale.UK, "REMARK 800 EVIDENCE_CODE: %-54s%s", evCode, lineEnd));
        stringBuffer.append(String.format(Locale.UK, "REMARK 800 SITE_DESCRIPTION: %-51s%s", description, lineEnd));

    }

    /**
     * Provides REMARK 800 section pertaining to the site as a string.
     *
     * For example in pdb 1a4w:
     * REMARK 800 SITE_IDENTIFIER: CAT
     * REMARK 800 EVIDENCE_CODE: UNKNOWN
     * REMARK 800 SITE_DESCRIPTION: ACTIVE SITE
     *
     * 
     */
    public String remark800toPDB() {
        StringBuffer stringBuffer = new StringBuffer();
        remark800toPDB(stringBuffer);
        return stringBuffer.toString();
    }

    /**
     * @param siteID the siteID to set
     * e.g. CAT, AC1, AC2...
     */
    public void setSiteID(String siteID) {
        this.siteID = siteID;
    }

    /**
     * @return the siteID
     * e.g. CAT, AC1, AC2...
     */
    public String getSiteID() {
        return siteID;
    }

    /**
     * @return the groups
     */
    public List<Group> getGroups() {
        return groups;
    }

    /**
     * @param residues the groups to set
     */
    public void setGroups(List<Group> residues) {
        this.groups = residues;
    }


    /**
     * gets the REMARK 800 description of the site
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * sets the REMARK 800 description of the site
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * gets the REMARK 800 EVIDENCE CODE for the site.
     * @return evidence code
     */
    public String getEvCode() {
        return evCode;
    }

    /**
     * sets the REMARK 800 EVIDENCE CODE for the site.
     */
    public void setEvCode(String evCode) {
        this.evCode = evCode;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Site other = (Site) obj;
        if ((this.siteID == null) ? (other.siteID != null) : !this.siteID.equals(other.siteID)) {
            return false;
        }
        if (this.groups != other.groups && (this.groups == null || !this.groups.equals(other.groups))) {
            return false;
        }
        if ((this.evCode == null) ? (other.evCode != null) : !this.evCode.equals(other.evCode)) {
            return false;
        }
        if ((this.description == null) ? (other.description != null) : !this.description.equals(other.description)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + (this.siteID != null ? this.siteID.hashCode() : 0);
        hash = 37 * hash + (this.groups != null ? this.groups.hashCode() : 0);
        hash = 37 * hash + (this.evCode != null ? this.evCode.hashCode() : 0);
        hash = 37 * hash + (this.description != null ? this.description.hashCode() : 0);
        return hash;
    }


}
