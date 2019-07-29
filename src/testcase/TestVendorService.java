package testcase;


import model.vendor.VendorBean;
import model.vendor.VendorService;
import model.vendor.VendorServiceImpl;

public class TestVendorService {
	public static void main(String[] args) {

		String url = "jdbc:sqlserver://localhost:1433;";
		String dbName = "databaseName=FinalProject;";
		String user = "sa";
		String password = "passw0rd";
		VendorService service = new VendorServiceImpl(url + dbName, user, password);

		try {
//			VendorBean test = service.insertVendor(
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
