package model.vendor;

import java.io.Serializable;
import java.math.BigDecimal;

public class VendorBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer vId; // vendor id
	private String vname; // vendor name
	private String addr; // vendor address
	private String tel; // vendor telephone
	private String email; // vendor email
	private String c_person;// contact person
	private String c_tel; // contact phone
	
	public VendorBean() {
		
	}
	
	public VendorBean(Integer vId, String vname, String addr, String tel, String email, String c_person, String c_tel) {
		this.vId = vId;
		this.vname = vname;
		this.addr = addr;
		this.tel = tel;
		this.email = email;
		this.c_person = c_person;
		this.c_tel = c_tel;
	}

	/**
	 * @return the vId
	 */
	public Integer getvId() {
		return vId;
	}

	/**
	 * @param vId the vId to set
	 */
	public void setvId(Integer vId) {
		this.vId = vId;
	}

	/**
	 * @return the vname
	 */
	public String getVname() {
		return vname;
	}

	/**
	 * @param vname the vname to set
	 */
	public void setVname(String vname) {
		this.vname = vname;
	}

	/**
	 * @return the addr
	 */
	public String getAddr() {
		return addr;
	}

	/**
	 * @param addr the addr to set
	 */
	public void setAddr(String addr) {
		this.addr = addr;
	}

	/**
	 * @return the tel
	 */
	public String getTel() {
		return tel.toString();
	}

	/**
	 * @param tel the tel to set
	 */
	public void setTel(String tel) {
		this.tel =tel;
	}


	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the c_person
	 */
	public String getC_person() {
		return c_person;
	}

	/**
	 * @param c_person the c_person to set
	 */
	public void setC_person(String c_person) {
		this.c_person = c_person;
	}

	/**
	 * @return the c_tel
	 */
	public String getC_tel() {
		return c_tel.toString();
	}

	/**
	 * @param c_tel the c_tel to set
	 */
	

	public void setC_tel(String c_tel) {
		this.c_tel = c_tel;
	}

	public String toString() {
		return "[" + vId + "," + vname + "," + addr + "," + this.getTel() + "," + email + "," + c_person +","+this.getC_tel()+ "]";
	}

}