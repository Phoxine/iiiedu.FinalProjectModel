package model.order;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class OrderDao {

	DataSource ds = null;

	public OrderDao() {
		try {
			Context context = new InitialContext();
			// TODO: change lookup
			ds = (DataSource) context.lookup("java:comp/env/jdbc/MemberDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public OrderDao(String dbString) {
		try {
			Context context = new InitialContext();
			ds = (DataSource) context.lookup(dbString);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String SELECT_ALL = "Select oId, pId, mId, odate, price, status from ordertable";
	private static final String INSERT = "Insert into ordertable (pId, mId, odate, price, status) values (?, ?, ?, ?, ?)";
	private static final String DELETE = "Delete from ordertable where oId=?";
	private static final String SELECT_BY_ID = "Select oId, pId, mId, odate, price, status from ordertable where oId = ?";
	private static final String SELECT_BY_PID = "Select oId, pId, mId, odate, price, status from ordertable where pId = ?";

	public OrderBean select(int oId) {
		OrderBean result = null;
		try (Connection conn = ds.getConnection(); PreparedStatement stmt = conn.prepareStatement(SELECT_BY_ID);) {
			stmt.setInt(1, oId);
			try (ResultSet rset = stmt.executeQuery();) {
				if (rset.next()) {
					result = new OrderBean();
					result.setoId(rset.getInt("oId"));
					result.setpId(rset.getInt("pId"));
					result.setmId(rset.getInt("mId"));
					result.setoDate(rset.getTimestamp("odate"));
					result.setPrice(rset.getInt("price"));
					result.setStatus(rset.getString("status"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<OrderBean> select() {
		List<OrderBean> result = null;
		try (Connection conn = ds.getConnection();
				PreparedStatement stmt = conn.prepareStatement(SELECT_ALL);
				ResultSet rset = stmt.executeQuery();) {
			result = new Vector<>();
			while (rset.next()) {
				OrderBean temp = new OrderBean();
				temp.setoId(rset.getInt("oId"));
				temp.setpId(rset.getInt("pId"));
				temp.setmId(rset.getInt("mId"));
				temp.setoDate(rset.getTimestamp("odate"));
				temp.setPrice(rset.getInt("price"));
				temp.setStatus(rset.getString("status"));
				result.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public OrderBean insertOrder(OrderBean bean) throws SQLException {
		OrderBean result = null;
		try (Connection conn = ds.getConnection(); PreparedStatement stmt = conn.prepareStatement(INSERT);) {
//			stmt.setInt(1, bean.getoId());
			stmt.setInt(1, bean.getpId());
			stmt.setInt(2, bean.getmId());
			Timestamp ts = new Timestamp(System.currentTimeMillis());
			stmt.setTimestamp(3, ts);
			stmt.setInt(4, bean.getPrice());
			stmt.setString(5, bean.getStatus());

			int i = stmt.executeUpdate();
			if (i == 1) {
				result = this.select(bean.getpId());
			}

		}
		return result;
	}

	public int delete(int oId) {
		int result = 0;
		try (Connection conn = ds.getConnection(); PreparedStatement stmt = conn.prepareStatement(DELETE);) {
			stmt.setInt(1, oId);
			result = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/***********************************************************************************************
	 * JDBC test code
	 */

	Connection connection = null;

	public OrderDao(String url, String user, String password) {
		String sqlserver_jdbcdriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		try {
			Class.forName(sqlserver_jdbcdriver);
			this.connection = DriverManager.getConnection(url, user, password);
			if (!connection.isClosed()) {
				System.out.println("DataBase Connected");
			}
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public OrderBean select(int pId, boolean test) {
		OrderBean result = null;
		try (PreparedStatement stmt = connection.prepareStatement(SELECT_BY_PID);) {
			stmt.setInt(1, pId);
			try (ResultSet rset = stmt.executeQuery();) {
				if (rset.next()) {
					result = new OrderBean();
					result.setoId(rset.getInt("oId"));
					result.setpId(rset.getInt("pId"));
					result.setmId(rset.getInt("mId"));
					result.setoDate(rset.getTimestamp("odate"));
					result.setPrice(rset.getInt("price"));
					result.setStatus(rset.getString("status"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<OrderBean> select(boolean test) {
		List<OrderBean> result = null;
		try (PreparedStatement stmt = connection.prepareStatement(SELECT_ALL); ResultSet rset = stmt.executeQuery();) {
			result = new Vector<>();
			while (rset.next()) {
				OrderBean temp = new OrderBean();
				temp.setoId(rset.getInt("oId"));
				temp.setpId(rset.getInt("password"));
				temp.setmId(rset.getInt("mId"));
				temp.setoDate(rset.getTimestamp("odate"));
				temp.setPrice(rset.getInt("price"));
				temp.setStatus(rset.getString("status"));
				result.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public OrderBean insertOrder(OrderBean bean, boolean test) throws SQLException {
		OrderBean result = null;
		try (PreparedStatement stmt = connection.prepareStatement(INSERT);) {
//			stmt.setInt(1, bean.getoId());
			stmt.setInt(1, bean.getpId());
			stmt.setInt(2, bean.getmId());
			Timestamp ts = new Timestamp(System.currentTimeMillis());
			stmt.setTimestamp(3, ts);
			stmt.setInt(4, bean.getPrice());
			stmt.setString(5, bean.getStatus());

			int i = stmt.executeUpdate();
			if (i == 1) {
				result = this.select(bean.getpId(),true);
			}

		}
		return result;
	}

	public int delete(int oId, boolean test) {
		int result = 0;
		try (PreparedStatement stmt = connection.prepareStatement(DELETE);) {
			stmt.setInt(1, oId);
			result = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public void close() {
		try {
			connection.close();
			System.out.println("database close");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
