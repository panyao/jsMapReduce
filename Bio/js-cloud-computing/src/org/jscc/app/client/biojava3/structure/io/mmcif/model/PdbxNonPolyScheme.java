package org.jscc.app.client.biojava3.structure.io.mmcif.model;

/** A bean for the PDBX_NONPOLY_SCHEME category, which provides residue level nomenclature
 * mapping for non-polymer entities.
 * @author Andreas Prlic
 * @since 1.7
 */
public class PdbxNonPolyScheme {
	String asym_id;
	String entity_id;
	String seq_id;
	String mon_id;
	String ndb_seq_num;
	String pdb_seq_num ;
	String auth_seq_num ;
	String 	pdb_mon_id;
	String auth_mon_id;
	String pdb_strand_id;
	String pdb_ins_code;
	public String getAsym_id() {
		return asym_id;
	}
	public void setAsym_id(String asym_id) {
		this.asym_id = asym_id;
	}
	public String getEntity_id() {
		return entity_id;
	}
	public void setEntity_id(String entity_id) {
		this.entity_id = entity_id;
	}
	public String getSeq_id() {
		return seq_id;
	}
	public void setSeq_id(String seq_id) {
		this.seq_id = seq_id;
	}
	public String getMon_id() {
		return mon_id;
	}
	public void setMon_id(String mon_id) {
		this.mon_id = mon_id;
	}
	public String getNdb_seq_num() {
		return ndb_seq_num;
	}
	public void setNdb_seq_num(String ndb_seq_num) {
		this.ndb_seq_num = ndb_seq_num;
	}
	public String getPdb_seq_num() {
		return pdb_seq_num;
	}
	public void setPdb_seq_num(String pdb_seq_num) {
		this.pdb_seq_num = pdb_seq_num;
	}
	public String getAuth_seq_num() {
		return auth_seq_num;
	}
	public void setAuth_seq_num(String auth_seq_num) {
		this.auth_seq_num = auth_seq_num;
	}
	public String getPdb_mon_id() {
		return pdb_mon_id;
	}
	public void setPdb_mon_id(String pdb_mon_id) {
		this.pdb_mon_id = pdb_mon_id;
	}
	public String getAuth_mon_id() {
		return auth_mon_id;
	}
	public void setAuth_mon_id(String auth_mon_id) {
		this.auth_mon_id = auth_mon_id;
	}
	public String getPdb_strand_id() {
		return pdb_strand_id;
	}
	public void setPdb_strand_id(String pdb_strand_id) {
		this.pdb_strand_id = pdb_strand_id;
	}
	public String getPdb_ins_code() {
		return pdb_ins_code;
	}
	public void setPdb_ins_code(String pdb_ins_code) {
		this.pdb_ins_code = pdb_ins_code;
	}

}
