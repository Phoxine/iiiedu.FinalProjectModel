package model.order;

import java.io.Serializable;
import java.sql.Timestamp;

public class OrderBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private int oId; // Order ID
	private int pId; // Product ID
	private int mId; // Memeber ID
	private Timestamp odate; // Order Date
	private int price; // total
	private String status; // processing status

	public OrderBean() {
	}

	public OrderBean(int oId, int pId, int mId, Timestamp date, int price, String status) {
		super();
		this.oId = oId;
		this.pId = pId;
		this.mId = mId;
		this.odate = date;
		this.price = price;
		this.status = status;
	}

	public String toString() {
		return "[" + oId + "," + pId + "," + mId + "," + odate + "," + price + "," + status + "]";
	}
	
	/**
	 * @return the oId
	 */
	public int getoId() {
		return oId;
	}

	/**
	 * @param oId the oId to set
	 */
	public void setoId(int oId) {
		this.oId = oId;
	}

	/**
	 * @return the pId
	 */
	public int getpId() {
		return pId;
	}

	/**
	 * @param pId the pId to set
	 */
	public void setpId(int pId) {
		this.pId = pId;
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
	 * @return the date
	 */
	public Timestamp getoDate() {
		return odate;
	}

	/**
	 * @param date the date to set
	 */
	public void setoDate(Timestamp date) {
		this.odate = date;
	}

	/**
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(int price) {
		this.price = price;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}



}