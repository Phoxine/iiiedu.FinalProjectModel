package testcase;

import java.sql.Timestamp;
import model.member.MemberBean;
import model.member.MemberService;
import model.vendor.VendorBean;
import model.vendor.VendorService;

public class TestVendorDao {
	public static void main(String[] args) {

		String url = "jdbc:sqlserver://localhost:1433;";
		String dbName = "databaseName=FinalProject;";
		String user = "sa";
		String password = "passw0rd";
		VendorService service = new VendorService(url + dbName, user, password);

		try {
//			VendorBean test = service.insertMember(
//					new VendorBean(1, "佛心公司-Garena","天龍國", "020488995", "foshigonsu@foshi.com","aaa","0204"));
//			System.out.println(test.toString());
//			System.out.println(service.delete(3));
//			System.out.println(service.select("佛心公司-Garena").toString());
//			System.out.println(service.select().get(0).toString());
//			System.out.println(service.select().toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		service.close();
	}
}
