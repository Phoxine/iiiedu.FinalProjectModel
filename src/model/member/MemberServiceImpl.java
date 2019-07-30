package model.member;

import java.util.List;

public class MemberServiceImpl implements MemberService {

	MemberDao dao = null;

	public MemberServiceImpl() {
		dao = new MemberDaoImpl();
	}

	public MemberServiceImpl(String dbString) {
		dao = new MemberDaoImpl(dbString);
	}

	public MemberServiceImpl(String url, String account, String password) {
		dao = new MemberDaoImpl(url, account, password);
	}

	@Override
	public MemberBean select(Integer mId) {
		return dao.select(mId);
	}

	@Override
	public MemberBean select(String name) {
		return dao.select(name);
	}

	@Override
	public List<MemberBean> select() {
		return dao.select();
	}

	@Override
	public MemberBean insertMember(MemberBean bean) {
		return dao.insertMember(bean);
	}

	@Override
	public long getRecordCounts() {
		return dao.getRecordCounts();
	}
	
	@Override
	public MemberBean checkAccountPassword(String userAccount, String password) {
		return dao.checkAccountPassword(userAccount, password);
	}

	@Override
	public Integer delete(Integer mId) {
		return dao.delete(mId);
	}

	@Override
	public void close() {
		dao.close();
	}



}