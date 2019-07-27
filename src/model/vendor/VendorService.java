package model.vendor;

import java.sql.SQLException;
import java.util.List;

public class VendorService {
	
	VendorDao dao = null;
	
	public VendorService() {
		dao = new VendorDao();
	}	
	
	public VendorService(String dbString) {
		dao = new VendorDao(dbString);
	}	
	
	public VendorService(String url, String account, String password) {
		dao = new VendorDao(url,account,password);
	}	
	
	
	public VendorBean select(int vId) {
		return dao.select(vId);
	}
	
	public VendorBean select(String vName) {
		return dao.select(vName);
	}
	
	public List<VendorBean> select() {
		return dao.select();
	}

	public VendorBean insertVendor(VendorBean bean) throws SQLException {
		return dao.insertVendor(bean);
	}

	public int delete(int vId) {
		return dao.delete(vId);
	}
	public void close() {
		dao.close();
	}
}