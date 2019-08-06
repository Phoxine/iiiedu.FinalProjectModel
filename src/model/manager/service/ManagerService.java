package model.manager.service;

import java.util.List;

import model.manager.bean.ManagerBean;

public interface ManagerService {

	ManagerBean select(Integer id);

	ManagerBean select(String name);

	List<ManagerBean> select();

	int insertManager(ManagerBean bean);

	int delete(Integer id);

	long getRecordCounts();
	
	void close();

}