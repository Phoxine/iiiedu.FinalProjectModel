package model.vendor.service;

import java.util.List;

import model.vendor.bean.VendorBean;

public interface VendorService {

	VendorBean select(Integer vId);

	VendorBean select(String vName);

	List<VendorBean> select();

	VendorBean insertVendor(VendorBean bean);

	Integer delete(Integer vId);

	long getRecordCounts();
	
	void close();

}