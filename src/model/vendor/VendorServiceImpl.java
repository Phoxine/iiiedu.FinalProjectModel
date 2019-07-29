package model.vendor;


import java.util.List;

public class VendorServiceImpl implements VendorService {
	
	VendorDao dao = null;
	
	public VendorServiceImpl() {
		dao = new VendorDaoIml();
	}	
	
	public VendorServiceImpl(String dbString) {
		dao = new VendorDaoIml(dbString);
	}	
	
	public VendorServiceImpl(String url, String account, String password) {
		dao = new VendorDaoIml(url,account,password);
	}	
	
	
	@Override
	public VendorBean select(Integer vId) {
		return dao.select(vId);
	}
	
	@Override
	public VendorBean select(String vName) {
		return dao.select(vName);
	}
	
	@Override
	public List<VendorBean> select() {
		return dao.select();
	}

	@Override
	public VendorBean insertVendor(VendorBean bean){
		return dao.insertVendor(bean);
	}

	@Override
	public Integer delete(Integer vId) {
		return dao.delete(vId);
	}
	@Override
	public void close() {
		dao.close();
	}
}