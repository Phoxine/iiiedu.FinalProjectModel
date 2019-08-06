package model.vendor.dao;

import java.util.List;

import model.vendor.bean.VendorBean;

public interface VendorDao {

	VendorBean select(Integer vId);

	VendorBean select(String vname);

	List<VendorBean> select();

	VendorBean insertVendor(VendorBean bean);

	Integer delete(Integer vId);
	
	long getRecordCounts();
	
	void close();

}