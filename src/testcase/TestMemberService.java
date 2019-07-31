package testcase;

import java.sql.Blob;
import java.sql.Timestamp;
import model.member.MemberBean;
import model.member.MemberDao;
import model.member.MemberService;
import model.member.MemberServiceImpl;

public class TestMemberService {
	public static void main(String[] args) {

		String url = "jdbc:sqlserver://localhost:1433;";
		String dbName = "databaseName=FinalProject;";
		String user = "sa";
		String password = "passw0rd";
		MemberService service = new MemberServiceImpl(url + dbName, user, password);

		try {
//			MemberBean test = service.insertMember(
//					new MemberBean(1, "peterchao", "020488995", "¤ÑÀs°ê", new Timestamp(0), "peterchao","a12345", "peterchao@gmail.com","1996-01-30",true));
//			System.out.println(test.toString());
//			System.out.println(service.delete(3));
//			System.out.println(service.select("peterchao").toString());
//			System.out.println(service.select().get(0).toString());
			System.out.println(service.select().toString());
//			System.out.println(service.getRecordCounts());
//			System.out.println(service.checkAccountPassword("peterchao", "aaaaa"));
//			System.out.println(service.checkAccountPassword("peterchao", "a12345").toString());
			System.out.println(service.checkPhonePassword("020488995", "a123452"));
			System.out.println(service.checkPhonePassword("020488995", "a12345"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		service.close();
	}
}
