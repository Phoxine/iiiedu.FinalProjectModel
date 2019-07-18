package model.member;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class MemberBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private int mId; // member id
	private String name; // member name
	private String tel; // phone
	private String addr; // address
	private java.sql.Timestamp rdate; // register date
	private String account; // account
	private String password; // password
	private String email; // email
	private java.sql.Timestamp birthday;// birthday
	private boolean sex; // true = male , false = female

	/**
	 * @return the sex
	 */
	public String getSex() {
		if(this.sex) {
			return "male";
		}else {
			return "female";
		}
		
	}

	/**
	 * @param sex the sex to set
	 * @throws Exception 
	 */
	public void setSex(String sex) throws Exception {
		if (sex.equalsIgnoreCase("MALE")) {
			this.sex = true;
		} else if (sex.equalsIgnoreCase("FEMALE")) {
			this.sex = false;
		}else {
			throw new Exception("Sex Data Error");
		}
	}

	public String toString() {

		return "[" + mId + "," + name + "," + tel + "," + addr + "," + rdate + "," + account + "," + password + ","
				+ email + "," + birthday +","+ this.getSex() +"]";
	}

	/**
	 * @return the birthday
	 */
	public java.sql.Timestamp getBirthday() {
		return birthday;
	}

	/**
	 * @param birthday the birthday to set
	 */
	public void setBirthday(java.sql.Timestamp birthday) {
		this.birthday = birthday;
	}

	/**
	 * @return the mId
	 */
	public int getmId() {
		return mId;
	}

	/**
	 * @param mId the mId to set
	 */
	public void setmId(int mId) {
		this.mId = mId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the tel
	 */
	public String getTel() {
		return tel;
	}

	/**
	 * @param tel the tel to set
	 */
	public void setTel(String tel) {
		this.tel = tel;
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
	 * @return the rdate
	 */
	public java.sql.Timestamp getRdate() {
		return rdate;
	}

	/**
	 * @param rdate the rdate to set
	 */
	public void setRdate(java.sql.Timestamp rdate) {
		this.rdate = rdate;
	}

	/**
	 * @return the account
	 */
	public String getAccount() {
		return account;
	}

	/**
	 * @param account the account to set
	 */
	public void setAccount(String account) {
		this.account = account;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
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

	public MemberBean() {
	}

	public MemberBean(int mId, String name, String tel, String addr, Timestamp rdate, String account, String password,
			String email, String birthday, boolean sex) {

		this.mId = mId;
		this.name = name;
		this.tel = (tel);
		this.addr = addr;
		this.rdate = rdate;
		this.account = account;
		this.password = password;
		this.email = email;
		this.birthday = new Timestamp(java.sql.Date.valueOf(birthday).getTime());
		this.sex = sex;
	}

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	public static java.util.Date convertDate(String temp) {
		java.util.Date result = new java.util.Date();
		try {
			result = sdf.parse(temp);
		} catch (ParseException e) {
			result = null;
			e.printStackTrace();
		}
		return result;
	}

}