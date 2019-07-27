package model.product;

import java.sql.SQLException;
import java.util.List;

public class ProductService {
	
	ProductDao dao = null;
	
	public ProductService() {
		dao = new ProductDao();
	}	
	
	public ProductService(String dbString) {
		dao = new ProductDao(dbString);
	}	
	
	public ProductService(String url, String account, String password) {
		dao = new ProductDao(url, account, password);
	}
	
	public ProductBean select(int pId) {
		return dao.select(pId);
	}
	
	public ProductBean select(String pname) {
		return dao.select(pname);
	}
	
	public List<ProductBean> select() {
		return dao.select();
	}

	public ProductBean insertProduct(ProductBean bean) throws SQLException {
		return dao.insertProduct(bean);
	}

	public int delete(int pId) {
		return dao.delete(pId);
	}
	
	public void close() {
		dao.close();
	}
}