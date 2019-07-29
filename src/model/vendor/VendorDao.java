package model.vendor;

import java.util.List;

public interface VendorDao {

	VendorBean select(Integer vId);

	VendorBean select(String vname);

	List<VendorBean> select();

	VendorBean insertVendor(VendorBean bean);

	Integer delete(Integer vId);

	void close();

}