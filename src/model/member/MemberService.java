package model.member;

import java.util.List;

public interface MemberService {

	MemberBean select(Integer mId);

	MemberBean select(String name);

	List<MemberBean> select();

	MemberBean insertMember(MemberBean bean);

	Integer delete(Integer mId);

	long getRecordCounts();
	
	void close();

}