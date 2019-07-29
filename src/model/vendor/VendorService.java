package model.vendor;

import java.util.List;

public interface VendorService {

	VendorBean select(Integer vId);

	VendorBean select(String vName);

	List<VendorBean> select();

	VendorBean insertVendor(VendorBean bean);

	Integer delete(Integer vId);

	void close();

}