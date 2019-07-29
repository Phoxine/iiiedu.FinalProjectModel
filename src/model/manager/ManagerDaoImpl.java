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
import javax.sql.DataSource;

public class ManagerDaoImpl implements ManagerDao {

	DataSource ds = null;

	public ManagerDaoImpl() {
		try {
			Context context = new InitialContext();
			// TODO change lookup parameter
			ds = (DataSource) context.lookup("java:comp/env/jdbc/MemberDB");
		} catch (NamingException e) {
			e.printStackTrace();
			throw new RuntimeException("Manager()#建構子發生例外: " + e.getMessage());
		}
	}

	public ManagerDaoImpl(String dbString) {
		try {
			Context context = new InitialContext();
			ds = (DataSource) context.lookup(dbString);
		} catch (NamingException e) {
			e.printStackTrace();
			throw new RuntimeException("Manager()#建構子發生例外: " + e.getMessage());
		}
	}

	Connection connection = null;

	public ManagerDaoImpl(String url, String user, String password) {
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
			throw new RuntimeException("Manager()#建構子發生例外: " + e1.getMessage());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("Manager()#建構子發生例外: " + e.getMessage());
		}

	}

	private static final String SELECT_BY_ID = "Select id, name, account, password from manager where id = ?";
	private static final String SELECT_BY_NAME = "Select id, name, account, password from manager where name = ?";
	private static final String SELECT_ALL = "Select id, name, account, password from manager";
	private static final String INSERT = "Insert into manager ( name,account, password) values (?, ?, ?)";
	private static final String DELETE = "Delete from manager where id=?";

	@Override
	public ManagerBean select(Integer id) {
		ManagerBean result = null;
		if (connection != null && ds == null) {
			// 使用DriverManager連接資料庫
			try (PreparedStatement stmt = connection.prepareStatement(SELECT_BY_ID);) {
				stmt.setInt(1,id);
				try (ResultSet rset = stmt.executeQuery();) {
					if (rset.next()) {
						result = new ManagerBean();
						result.setId(rset.getInt("id"));
						result.setName(rset.getString("name"));
						result.setAccount(rset.getString("account"));
						result.setPassword(rset.getString("password"));
					}
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException("Manager()#selectbyid()發生例外: " + e.getMessage());
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("Manager()#selectbyid()發生例外: " + e.getMessage());
			}
		} else if (connection == null && ds != null) {
			// 使用Datasource連接資料庫
			try (Connection conn = ds.getConnection(); PreparedStatement stmt = conn.prepareStatement(SELECT_BY_ID);) {
				stmt.setInt(1,id);
				try (ResultSet rset = stmt.executeQuery();) {
					if (rset.next()) {
						result = new ManagerBean();
						result.setId(rset.getInt("id"));
						result.setName(rset.getString("name"));
						result.setAccount(rset.getString("account"));
						result.setPassword(rset.getString("password"));
					}
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException("Manager()#selectbyid()發生例外: " + e.getMessage());
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("Manager()#selectbyid()發生例外: " + e.getMessage());
			}
		}

		return result;
	}

	@Override
	public ManagerBean select(String name) {
		ManagerBean result = null;
		if (connection != null && ds == null) {
			// 使用DriverManager連接資料庫
			try (PreparedStatement stmt = connection.prepareStatement(SELECT_BY_NAME);) {
				stmt.setString(1, name);
				try (ResultSet rset = stmt.executeQuery();) {
					if (rset.next()) {
						result = new ManagerBean();
						result.setId(rset.getInt("id"));
						result.setName(rset.getString("name"));
						result.setAccount(rset.getString("account"));
						result.setPassword(rset.getString("password"));
					}
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException("Manager()#selectbyname()發生例外: " + e.getMessage());
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("Manager()#selectbyname()發生例外: " + e.getMessage());
			}
		} else if (connection == null && ds != null) {
			// 使用Datasource連接資料庫
			try (Connection conn = ds.getConnection();
					PreparedStatement stmt = conn.prepareStatement(SELECT_BY_NAME);) {
				stmt.setString(1, name);
				try (ResultSet rset = stmt.executeQuery();) {
					if (rset.next()) {
						result = new ManagerBean();
						result.setId(rset.getInt("id"));
						result.setName(rset.getString("name"));
						result.setAccount(rset.getString("account"));
						result.setPassword(rset.getString("password"));
					}
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException("Manager()#selectbyname()發生例外: " + e.getMessage());
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("Manager()#selectbyname()發生例外: " + e.getMessage());
			}
		}

		return result;
	}

	@Override
	public List<ManagerBean> select() {
		List<ManagerBean> result = null;
		if (connection != null && ds == null) {
			// 使用DriverManager連接資料庫
			try (PreparedStatement stmt = connection.prepareStatement(SELECT_ALL);
					ResultSet rset = stmt.executeQuery();) {
				result = new Vector<>();
				while (rset.next()) {
					ManagerBean temp = new ManagerBean();
					temp.setId(rset.getInt("id"));
					temp.setName(rset.getString("name"));
					temp.setAccount(rset.getString("account"));
					temp.setPassword(rset.getString("password"));
					result.add(temp);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("Manager()#selectAll()發生例外: " + e.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("Manager()#selectAll()發生例外: " + e.getMessage());
			}
		} else if (connection == null && ds != null) {
			// 使用Datasource連接資料庫
			try (Connection conn = ds.getConnection();
					PreparedStatement stmt = conn.prepareStatement(SELECT_ALL);
					ResultSet rset = stmt.executeQuery();) {
				result = new Vector<>();
				while (rset.next()) {
					ManagerBean temp = new ManagerBean();
					temp.setId(rset.getInt("id"));
					temp.setName(rset.getString("name"));
					temp.setAccount(rset.getString("account"));
					temp.setPassword(rset.getString("password"));
					result.add(temp);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("Manager()#selectAll()發生例外: " + e.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("Manager()#selectAll()發生例外: " + e.getMessage());
			}
		}

		return result;
	}

	@Override
	public ManagerBean insertManager(ManagerBean bean){
		ManagerBean result = null;
		if (connection != null && ds == null) {
			// 使用DriverManager連接資料庫
			try (PreparedStatement stmt = connection.prepareStatement(INSERT);) {
				stmt.setString(1, bean.getName());
				stmt.setString(2, bean.getAccount());
				stmt.setString(3, bean.getPassword());
				int i = stmt.executeUpdate();
				if (i == 1) {
					result = this.select(bean.getName());
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("Manager()#insertManager()發生例外: " + e.getMessage());
			}
		} else if (connection == null && ds != null) {
			// 使用Datasource連接資料庫
			try (Connection conn = ds.getConnection(); PreparedStatement stmt = conn.prepareStatement(INSERT);) {
				stmt.setString(1, bean.getName());
				stmt.setString(2, bean.getAccount());
				stmt.setString(3, bean.getPassword());
				int i = stmt.executeUpdate();
				if (i == 1) {
					result = this.select(bean.getName());
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("Manager()#insertManager()發生例外: " + e.getMessage());
			}
		}
		return result;
	}

	@Override
	public Integer delete(Integer id) {
		int result = 0;
		if (connection != null && ds == null) {
			// 使用DriverManager連接資料庫
			try (PreparedStatement stmt = connection.prepareStatement(DELETE);) {
				stmt.setInt(1, id);
				result = stmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("Manager()#delete()發生例外: " + e.getMessage());
			}
		} else if (connection == null && ds != null) {
			// 使用Datasource連接資料庫
			try (Connection conn = ds.getConnection(); PreparedStatement stmt = conn.prepareStatement(DELETE);) {
				stmt.setInt(1, id);
				result = stmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("Manager()#delete()發生例外: " + e.getMessage());
			}
		}

		return result;
	}

	@Override
	public void close() {
		if (this.connection != null) {
			try {
				this.connection.close();
				System.out.println("database close");
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("Manager()#close()發生例外: " + e.getMessage());
			}
		}
	}

}