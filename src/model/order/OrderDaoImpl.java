package model.order;

import java.io.Serializable;
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

public class OrderDaoImpl implements Serializable, OrderDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	DataSource ds = null;

	public OrderDaoImpl() {
		try {
			Context context = new InitialContext();
			// TODO: change lookup
			ds = (DataSource) context.lookup("java:comp/env/jdbc/MemberDB");
		} catch (NamingException e) {
			e.printStackTrace();
			throw new RuntimeException("OrderDao()#建構子發生例外: " + e.getMessage());
		}
	}

	public OrderDaoImpl(String dbString) {
		try {
			Context context = new InitialContext();
			ds = (DataSource) context.lookup(dbString);
		} catch (NamingException e) {
			e.printStackTrace();
			throw new RuntimeException("OrderDao()#建構子發生例外: " + e.getMessage());
		}
	}

	Connection connection = null;

	public OrderDaoImpl(String url, String user, String password) {
		String sqlserver_jdbcdriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		try {
			Class.forName(sqlserver_jdbcdriver);
			this.connection = DriverManager.getConnection(url, user, password);
			if (!connection.isClosed()) {
				System.out.println("DataBase Connected");
			}
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
			throw new RuntimeException("OrderDao()#建構子發生例外: " + e1.getMessage());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("OrderDao()#建構子發生例外: " + e.getMessage());
		}

	}

	private static final String SELECT_ALL = "Select oId, pId, mId, odate, price, status from ordertable";
	private static final String INSERT = "Insert into ordertable (pId, mId, odate, price, status) values (?, ?, ?, ?, ?)";
	private static final String DELETE = "Delete from ordertable where oId=?";
	private static final String SELECT_BY_ID = "Select oId, pId, mId, odate, price, status from ordertable where oId = ?";
	private static final String SELECT_BY_PID = "Select oId, pId, mId, odate, price, status from ordertable where pId = ?";
	private static final String COUNT = "SELECT count(*) FROM ordertable";

	@Override
	public OrderBean select(Integer oId) {
		OrderBean result = null;
		if (connection != null && ds == null) {
			// 使用DriverManager連接資料庫
			try (PreparedStatement stmt = connection.prepareStatement(SELECT_BY_ID);) {
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
				throw new RuntimeException("OrderDao()#selectbyoid()發生例外: " + e.getMessage());
			}
		} else if (connection == null && ds != null) {
			// 使用Datasource連接資料庫
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
				throw new RuntimeException("OrderDao()#selectbyoid()發生例外: " + e.getMessage());
			}
		}

		return result;
	}

	@Override
	public OrderBean select_by_pid(Integer pid) {
		OrderBean result = null;
		if (connection != null && ds == null) {
			// 使用DriverManager連接資料庫
			try (PreparedStatement stmt = connection.prepareStatement(SELECT_BY_PID);) {
				stmt.setInt(1, pid);
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
				throw new RuntimeException("OrderDao()#selectbypid()發生例外: " + e.getMessage());
			}
		} else if (connection == null && ds != null) {
			// 使用Datasource連接資料庫
			try (Connection conn = ds.getConnection(); PreparedStatement stmt = conn.prepareStatement(SELECT_BY_ID);) {
				stmt.setInt(1, pid);
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
				throw new RuntimeException("OrderDao()#selectbypid()發生例外: " + e.getMessage());
			}
		}

		return result;
	}

	@Override
	public List<OrderBean> select() {
		List<OrderBean> result = null;
		if (connection != null && ds == null) {
			// 使用DriverManager連接資料庫
			try (PreparedStatement stmt = connection.prepareStatement(SELECT_ALL);
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
				throw new RuntimeException("OrderDao()#selectAll()發生例外: " + e.getMessage());
			}
		} else if (connection == null && ds != null) {
			// 使用Datasource連接資料庫
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
				throw new RuntimeException("OrderDao()#selectAll()發生例外: " + e.getMessage());
			}
		}

		return result;
	}

	@Override
	public OrderBean insertOrder(OrderBean bean) {
		OrderBean result = null;
		if (connection != null && ds == null) {
			// 使用DriverManager連接資料庫
			try (PreparedStatement stmt = connection.prepareStatement(INSERT);) {
//				stmt.setInt(1, bean.getoId());
				stmt.setInt(1, bean.getpId());
				stmt.setInt(2, bean.getmId());
				Timestamp ts = new Timestamp(System.currentTimeMillis());
				stmt.setTimestamp(3, ts);
				stmt.setInt(4, bean.getPrice());
				stmt.setString(5, bean.getStatus());

				int i = stmt.executeUpdate();
				if (i == 1) {
					result = this.select_by_pid(bean.getpId());
				}

			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("OrderDao()#insertOrder()發生例外: " + e.getMessage());
			}
		} else if (connection == null && ds != null) {
			// 使用Datasource連接資料庫
			try (Connection conn = ds.getConnection(); PreparedStatement stmt = conn.prepareStatement(INSERT);) {
//				stmt.setInt(1, bean.getoId());
				stmt.setInt(1, bean.getpId());
				stmt.setInt(2, bean.getmId());
				Timestamp ts = new Timestamp(System.currentTimeMillis());
				stmt.setTimestamp(3, ts);
				stmt.setInt(4, bean.getPrice());
				stmt.setString(5, bean.getStatus());

				int i = stmt.executeUpdate();
				if (i == 1) {
					result = this.select_by_pid(bean.getpId());
				}

			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("OrderDao()#insertOrder()發生例外: " + e.getMessage());
			}
		}

		return result;
	}

	@Override
	public Integer delete(Integer oId) {
		int result = 0;
		if (connection != null && ds == null) {
			// 使用DriverManager連接資料庫
			try (PreparedStatement stmt = connection.prepareStatement(DELETE);) {
				stmt.setInt(1, oId);
				result = stmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("OrderDao()#delete()發生例外: " + e.getMessage());
			}
		} else if (connection == null && ds != null) {
			// 使用Datasource連接資料庫
			try (Connection conn = ds.getConnection(); PreparedStatement stmt = conn.prepareStatement(DELETE);) {
				stmt.setInt(1, oId);
				result = stmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("OrderDao()#delete()發生例外: " + e.getMessage());
			}
		}

		return result;
	}

	@Override
	public void close() {
		if (this.connection != null) {
			try {
				connection.close();
				System.out.println("database close");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RuntimeException("OrderDao()#close()發生例外: " + e.getMessage());
			}
		}
	}

	@Override
	public long getRecordCounts() {
		long count = 0; // 必須使用 long 型態

		if (connection != null && ds == null) {
			// 使用DriverManager連接資料庫
			try (PreparedStatement stmt = connection.prepareStatement(COUNT); ResultSet rs = stmt.executeQuery();) {
				if (rs.next()) {
					count = rs.getLong(1);
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
				throw new RuntimeException("Order()#getRecordCounts()發生例外: " + ex.getMessage());
			}
		} else if (connection == null && ds != null) {
			// 使用Datasource連接資料庫
			try (Connection connection = ds.getConnection();
					PreparedStatement ps = connection.prepareStatement(COUNT);
					ResultSet rs = ps.executeQuery();) {
				if (rs.next()) {
					count = rs.getLong(1);
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
				throw new RuntimeException("Order()#getRecordCounts()發生例外: " + ex.getMessage());
			}
		}
		return count;
	}

}
