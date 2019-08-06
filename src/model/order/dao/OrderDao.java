package model.order.dao;

import java.util.List;

import model.order.bean.OrderBean;

public interface OrderDao {

	OrderBean select(Integer oId);

	OrderBean select_by_pid(Integer pid);

	List<OrderBean> select();

	OrderBean insertOrder(OrderBean bean);

	Integer delete(Integer oId);

	long getRecordCounts();
	
	void close();

}