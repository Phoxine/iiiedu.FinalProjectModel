package model.manager.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import model.Global.HibernateUtils;
import model.manager.bean.ManagerBean;
import model.manager.dao.ManagerDao;
import model.manager.dao.impl.ManagerDaoImpl_Hibernate;
import model.manager.service.ManagerService;

public class ManagerServiceImpl_Hibernate implements ManagerService {

	ManagerDao dao = null;
	SessionFactory factory = null;

	public ManagerServiceImpl_Hibernate() {
		this.factory = HibernateUtils.getSessionFactory();
		this.dao = new ManagerDaoImpl_Hibernate();
	}

	@Override
	public ManagerBean select(Integer id) {
		ManagerBean mb = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			mb = dao.select(id);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return mb;
	}

	@Override
	public ManagerBean select(String name) {
		ManagerBean mb = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			mb = dao.select(name);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return mb;
	}

	@Override
	public List<ManagerBean> select() {
		List<ManagerBean> list = null;

		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			list = dao.select();
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return list;
	}

	@Override
	public int insertManager(ManagerBean bean) {
		int n = 0;

		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			dao.insertManager(bean);
			n++;
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return n;
	}

	@Override
	public long getRecordCounts() {
		long count = 0;

		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			count = dao.getRecordCounts();
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return count;
	}

	@Override
	public int delete(Integer id) {
		int n = 0;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			dao.delete(id);
			n++;
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return n;
	}

	@Override
	public void close() {
		dao.close();
	}

}