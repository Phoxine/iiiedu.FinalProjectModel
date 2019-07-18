package testcase;



import model.product.ProductBean;
import model.product.ProductDao;

public class TestProductService {
	public static void main(String[] args) {

		String url = "jdbc:sqlserver://localhost:1433;";
		String dbName = "databaseName=FinalProject;";
		String user = "sa";
		String password = "passw0rd";

		ProductDao dao = new ProductDao(url + dbName, user, password);

		try {
//			ProductBean test = dao.insertProduct(
//					new ProductBean(1,"福利熊",1,1,4,"冰川食者,手淫家","1996-01-05","2001-11-18"), true);
//			System.out.println(test.toString());
//			System.out.println(dao.delete(8, true));
//			System.out.println(dao.select("peterchao",true).toString());
			System.out.println(dao.select(true).get(0).toString());
			System.out.println(dao.select(true).toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dao.close();
	}
}
