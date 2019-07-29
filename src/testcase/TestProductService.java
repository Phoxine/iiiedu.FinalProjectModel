package testcase;



import model.product.ProductBean;
import model.product.ProductDaoImpl;
import model.product.ProductService;
import model.product.ProductServiceImpl;

public class TestProductService {
	public static void main(String[] args) {

		String url = "jdbc:sqlserver://localhost:1433;";
		String dbName = "databaseName=FinalProject;";
		String user = "sa";
		String password = "passw0rd";

		ProductService service = new ProductServiceImpl(url + dbName, user, password);

		try {
//			ProductBean test = service.insertProduct(
//					new ProductBean(1,"福利熊",1,1,4,"冰川食者,手淫家","1996-01-05","2001-11-18"));
//			System.out.println(test.toString());
//			System.out.println(service.delete(1));
//			System.out.println(service.select("福利熊").toString());
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
