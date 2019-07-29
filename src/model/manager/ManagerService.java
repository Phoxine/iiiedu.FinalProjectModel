package model.manager;

import java.util.List;

public interface ManagerService {

	ManagerBean select(Integer id);

	ManagerBean select(String name);

	List<ManagerBean> select();

	ManagerBean insertManager(ManagerBean bean);

	int delete(Integer id);

	void close();

}