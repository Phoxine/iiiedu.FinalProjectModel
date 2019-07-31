package model.member;

import java.util.List;

public interface MemberDao {

	MemberBean select(Integer mId);

	MemberBean select(String name);

	List<MemberBean> select();

	MemberBean insertMember(MemberBean bean);
	
	MemberBean insertMember_With_memberImage(MemberBean bean);

	MemberBean checkAccountPassword(String userAccount, String password);
	
	MemberBean checkPhonePassword(String phone, String password);
	
	Integer delete(Integer mId);

	long getRecordCounts();
	
	void close();





}