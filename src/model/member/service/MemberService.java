package model.member.service;

import java.util.List;

import model.member.bean.MemberBean;

public interface MemberService {

	MemberBean select(Integer mId);

	MemberBean select(String name);

	List<MemberBean> select();

	MemberBean insertMember(MemberBean bean);

	Integer delete(Integer mId);

	long getRecordCounts();
	

	void close();

	MemberBean checkAccountPassword(String userId, String password);
	
	MemberBean checkPhonePassword(String phone, String password);

}