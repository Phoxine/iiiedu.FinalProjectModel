package model.order.service.impl;

import java.sql.SQLException;
import java.util.List;

import model.order.bean.OrderBean;
import model.order.dao.OrderDao;
import model.order.dao.impl.OrderDaoImpl;
import model.order.service.OrderService;

public class OrderServiceImpl implements OrderService {
	
	OrderDao dao = null;
	
	public OrderServiceImpl() {
		dao = new OrderDaoImpl();
	}	
	
	public OrderServiceImpl(String dbString) {
		dao = new OrderDaoImpl(dbString);
	}	
	
	public OrderServiceImpl(String url, String account, String password) {
		dao = new OrderDaoImpl(url,account,password);
	}
	
	@Override
	public OrderBean select(Integer oId) {
		return dao.select(oId);
	}
	
	@Override
	public List<OrderBean> select() {
		return dao.select();
	}

	@Override
	public OrderBean insertOrder(OrderBean bean) throws SQLException {
		return dao.insertOrder(bean);
	}

	@Override
	public long getRecordCounts() {
		return dao.getRecordCounts();
	}
	
	@Override
	public Integer delete(Integer oId) {
		return dao.delete(oId);
	}
	
	@Override
	public void close() {
		dao.close();
	}
}