package model.manager;

import java.util.List;

public class ManagerServiceImpl implements ManagerService {

	ManagerDao dao = null;

	public ManagerServiceImpl() {
		dao = new ManagerDaoImpl();
	}

	public ManagerServiceImpl(String dbString) {
		dao = new ManagerDaoImpl(dbString);
	}

	public ManagerServiceImpl(String url, String account, String password) {
		dao = new ManagerDaoImpl(url,account, password);
	}

	@Override
	public ManagerBean select(Integer id) {
		return dao.select(id);
	}
	@Override
	public ManagerBean select(String name) {
		return dao.select(name);
	}
	@Override
	public List<ManagerBean> select() {
		return dao.select();
	}

	@Override
	public ManagerBean insertManager(ManagerBean bean){
		return dao.insertManager(bean);
	}

	@Override
	public int delete(Integer id) {
		return dao.delete(id);
	}
	
	@Override
	public void close() {
		dao.close();
	}
}