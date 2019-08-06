package testcase;


import java.sql.Timestamp;

import model.order.bean.OrderBean;
import model.order.dao.impl.OrderDaoImpl;
import model.order.service.OrderService;
import model.order.service.impl.OrderServiceImpl;

public class TestOrderService {
	public static void main(String[] args) {

		String url = "jdbc:sqlserver://localhost:1433;";
		String dbName = "databaseName=FinalProject;";
		String user = "sa";
		String password = "passw0rd";

		OrderService service = new OrderServiceImpl(url + dbName, user, password);

		try {
//			OrderBean test = service.insertOrder(
//					new OrderBean(1,1,1,new Timestamp(0),10000,"StandBy"));
//			System.out.println(test.toString());
//			System.out.println(service.delete(1));
//			System.out.println(service.select(2).toString());
//			System.out.println(service.select().get(0).toString());
//			System.out.println(service.select().toString());
			System.out.println(service.getRecordCounts());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		service.close();
	}
}
