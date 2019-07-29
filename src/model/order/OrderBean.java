package model.order;

import java.io.Serializable;
import java.sql.Timestamp;

public class OrderBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer oId; // Order ID
	private Integer pId; // Product ID
	private Integer mId; // Memeber ID
	private Timestamp odate; // Order Date
	private Integer price; // total
	private String status; // processing status

	public OrderBean() {
	}

	public OrderBean(Integer oId, Integer pId, Integer mId, Timestamp date, Integer price, String status) {
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
	public void setoId(Integer oId) {
		this.oId = oId;
	}

	/**
	 * @return the pId
	 */
	public Integer getpId() {
		return pId;
	}

	/**
	 * @param pId the pId to set
	 */
	public void setpId(Integer pId) {
		this.pId = pId;
	}

	/**
	 * @return the mId
	 */
	public Integer getmId() {
		return mId;
	}

	/**
	 * @param mId the mId to set
	 */
	public void setmId(Integer mId) {
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
	public Integer getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(Integer price) {
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