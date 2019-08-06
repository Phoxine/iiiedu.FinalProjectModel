package testcase;

import java.sql.Timestamp;

import model.manager.bean.ManagerBean;
import model.manager.service.ManagerService;
import model.manager.service.impl.ManagerServiceImpl;

public class TestManagerService {
	public static void main(String[] args) {

		String url = "jdbc:sqlserver://localhost:1433;";
		String dbName = "databaseName=FinalProject;";
		String user = "sa";
		String password = "passw0rd";
		ManagerService service = new ManagerServiceImpl(url + dbName, user, password);

		try {
//			ManagerBean test = service.insertManager(
//					new ManagerBean(1, "peterchao", "peterchao","a12345"));
//			System.out.println(test.toString());
//			System.out.println(service.delete(1));
			System.out.println(service.select("peterchao").toString());
			System.out.println(service.select().get(0).toString());
			System.out.println(service.select().toString());
			System.out.println(service.getRecordCounts());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		service.close();
	}
}
