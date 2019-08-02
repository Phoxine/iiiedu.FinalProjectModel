package model.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.NoResultException;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import model.Global.HibernateUtils;

public class ManagerDaoImpl_Hibernate implements ManagerDao {

	SessionFactory factory;

	public ManagerDaoImpl_Hibernate() {
		factory = HibernateUtils.getSessionFactory();
	}

	@Override
	public ManagerBean select(Integer id) {
		ManagerBean mb = null;
		Session session = factory.getCurrentSession();
		String hql = "FROM Manager WHERE id = :mid";
		try {
			mb = (ManagerBean) session.createQuery(hql).setParameter("mid", id).uniqueResult();
		} catch (NoResultException e) {
			mb = null;
		}
		return mb;

	}

	@Override
	public ManagerBean select(String name) {
		ManagerBean mb = null;
		Session session = factory.getCurrentSession();
		String hql = "FROM Manager WHERE name = :mname";
		try {
			mb = (ManagerBean) session.createQuery(hql).setParameter("mname", name).uniqueResult();
		} catch (NoResultException e) {
			mb = null;
		}
		return mb;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ManagerBean> select() {
		List<ManagerBean> list = null;
		String hql = "FROM Manager";
		Session session = factory.getCurrentSession();

		list = session.createQuery(hql).list();
		return list;
	}

	@Override
	public int insertManager(ManagerBean bean) {
		int n = 0;
		Session session = factory.getCurrentSession();
		session.save(bean);
		n++;
		return n;
	}

	@Override
	public Integer delete(Integer id) {
		int n = 0;
		Session session = factory.getCurrentSession();
		ManagerBean mb = new ManagerBean();
		mb.setId(id);
		session.delete(mb);
		n++;
		return n;
	}

	@Override
	public void close() {
//		if (this.connection != null) {
//			try {
//				this.connection.close();
//				System.out.println("database close");
//			} catch (SQLException e) {
//				e.printStackTrace();
//				throw new RuntimeException("Manager()#close()Error: " + e.getMessage());
//			}
//		}
	}

	@Override
	public long getRecordCounts() {
		long count = 0;
		Session session = factory.getCurrentSession();
		String hql = "SELECT COUNT(*) FROM Manager";
				try {
					count = (long) session.createQuery(hql).uniqueResult();
				} catch (NoResultException e) {
					count = 0;
				}
				
		return count;
	}

}