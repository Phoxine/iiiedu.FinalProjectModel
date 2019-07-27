package model.member;

import java.sql.SQLException;
import java.util.List;

public class MemberService {

	MemberDao dao = null;

	public MemberService() {
		dao = new MemberDao();
	}

	public MemberService(String dbString) {
		dao = new MemberDao(dbString);
	}

	public MemberService(String url, String account, String password) {
		dao = new MemberDao(url,account, password);
	}

	public MemberBean select(int mId) {
		return dao.select(mId);
	}
	public MemberBean select(String name) {
		return dao.select(name);
	}
	public List<MemberBean> select() {
		return dao.select();
	}

	public MemberBean insertMember(MemberBean bean) throws SQLException {
		return dao.insertMember(bean);
	}

	public int delete(int mId) {
		return dao.delete(mId);
	}
	
	public void close() {
		dao.close();
	}
}