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
	
	public VendorBean select(String id) {
		return dao.select(id);
	}
	
	public List<VendorBean> select() {
		return dao.select();
	}

	public VendorBean insertMember(VendorBean bean) throws SQLException {
		return dao.insertMember(bean);
	}

	public int delete(String memberId) {
		return dao.delete(memberId);
	}
}