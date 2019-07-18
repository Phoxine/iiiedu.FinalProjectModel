package testcase;


import java.sql.Timestamp;


import model.order.OrderBean;
import model.order.OrderDao;

public class TestOrderService {
	public static void main(String[] args) {

		String url = "jdbc:sqlserver://localhost:1433;";
		String dbName = "databaseName=FinalProject;";
		String user = "sa";
		String password = "passw0rd";

		OrderDao dao = new OrderDao(url + dbName, user, password);

		try {
			OrderBean test = dao.insertOrder(
					new OrderBean(1,1,1,new Timestamp(0),10000,"StandBy"), true);
			System.out.println(test.toString());
//			System.out.println(dao.delete(8, true));
//			System.out.println(dao.select("peterchao",true).toString());
//			System.out.println(dao.select(true).get(0).toString());
//			System.out.println(dao.select(true).toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dao.close();
	}
}
