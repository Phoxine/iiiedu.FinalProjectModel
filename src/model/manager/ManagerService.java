package model.manager;

import java.sql.SQLException;
import java.util.List;

public class ManagerService {

	ManagerDao dao = null;

	public ManagerService() {
		dao = new ManagerDao();
	}

	public ManagerService(String dbString) {
		dao = new ManagerDao(dbString);
	}

	public ManagerService(String url, String account, String password) {
		dao = new ManagerDao(url,account, password);
	}

	public ManagerBean select(int id) {
		return dao.select(id);
	}
	public ManagerBean select(String name) {
		return dao.select(name);
	}
	public List<ManagerBean> select() {
		return dao.select();
	}

	public ManagerBean insertManager(ManagerBean bean) throws SQLException {
		return dao.insertManager(bean);
	}

	public int delete(int id) {
		return dao.delete(id);
	}
	
	public void close() {
		dao.close();
	}
}