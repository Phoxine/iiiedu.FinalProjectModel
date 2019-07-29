package model.manager;

import java.util.List;

public interface ManagerDao {

	ManagerBean select(Integer id);

	ManagerBean select(String name);

	List<ManagerBean> select();

	ManagerBean insertManager(ManagerBean bean);

	Integer delete(Integer id);

	long getRecordCounts();
	
	void close();

}