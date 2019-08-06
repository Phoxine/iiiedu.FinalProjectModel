package model.manager.dao;

import java.util.List;

import model.manager.bean.ManagerBean;

public interface ManagerDao {

	ManagerBean select(Integer id);

	ManagerBean select(String name);

	List<ManagerBean> select();

	int insertManager(ManagerBean bean);

	Integer delete(Integer id);

	long getRecordCounts();

	void close();

}