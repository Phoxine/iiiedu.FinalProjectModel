package testcase;

import java.sql.SQLException;
import java.sql.Timestamp;

import model.member.MemberBean;
import model.member.MemberDao;

public class TestMemberDao {
	public static void main(String[] args) {

		String url = "jdbc:sqlserver://localhost:1433;";
		String dbName = "databaseName=FinalProject;";
		String user = "sa";
		String password = "passw0rd";

		MemberDao dao = new MemberDao(url + dbName, user, password);

		try {
			MemberBean test = dao.insertMember(
					new MemberBean(1, "peterchao", "020488995", "¤ÑÀs°ê", new Timestamp(0), "peterchao","a12345", "peterchao@gmail.com","1996-01-30"), true);
			System.out.println(test.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dao.close();
	}
}
