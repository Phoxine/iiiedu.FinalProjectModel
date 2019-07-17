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
	
	public ProductBean select(String id) {
		return dao.select(id);
	}
	
	public List<ProductBean> select() {
		return dao.select();
	}

	public ProductBean insertMember(ProductBean bean) throws SQLException {
		return dao.insertMember(bean);
	}

	public int delete(String memberId) {
		return dao.delete(memberId);
	}
}