package testcase;



import model.product.bean.ProductBean;
import model.product.dao.ProductDaoImpl;
import model.product.service.ProductService;
import model.product.service.impl.ProductServiceImpl;

public class TestProductService {
	public static void main(String[] args) {

		String url = "jdbc:sqlserver://localhost:1433;";
		String dbName = "databaseName=FinalProject;";
		String user = "sa";
		String password = "passw0rd";

		ProductService service = new ProductServiceImpl(url + dbName, user, password);

		try {
//			ProductBean test = service.insertProduct(
//					new ProductBean(1,"�֧Q��",1,1,4,"�B�t����,��]�a","1996-01-05","2001-11-18"));
//			System.out.println(test.toString());
//			System.out.println(service.delete(1));
//			System.out.println(service.select("�֧Q��").toString());
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
