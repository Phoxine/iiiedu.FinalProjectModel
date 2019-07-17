package model.order;

import java.sql.SQLException;
import java.util.List;

public class OrderService {
	
	OrderDao dao = null;
	
	public OrderService() {
		dao = new OrderDao();
	}	
	
	public OrderService(String dbString) {
		dao = new OrderDao(dbString);
	}	
	
	public OrderBean select(int oId) {
		return dao.select(oId);
	}
	
	public List<OrderBean> select() {
		return dao.select();
	}

	public OrderBean insertMember(OrderBean bean) throws SQLException {
		return dao.insertOrder(bean);
	}

	public int delete(int oId) {
		return dao.delete(oId);
	}
}