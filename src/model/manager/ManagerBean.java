package model.manager;

import java.io.Serializable;


public class ManagerBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id; // manager id
	private String name; // manager name
	private String account; // manager account
	private String password; // manager password

	public ManagerBean() {

	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
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
	 * @return the manager
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param manager the manager to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	public ManagerBean(Integer id, String name, String account, String password) {
		super();
		this.id = id;
		this.name = name;
		this.account = account;
		this.password = password;
	}

	public String toString() {

		return "[" + id + "," + name + "," + account + "," + password + "]";
	}

}