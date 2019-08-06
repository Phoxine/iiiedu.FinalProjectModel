package model.vendor.dao.impl;

import java.io.Serializable;
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

import model.vendor.bean.VendorBean;
import model.vendor.dao.VendorDao;

public class VendorDaoImpl implements Serializable, VendorDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	DataSource ds = null;

	public VendorDaoImpl() {
		try {
			Context context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/jdbc/MemberDB");
		} catch (NamingException e) {
			e.printStackTrace();
			throw new RuntimeException("VendorDao()#建構子發生例外: " + e.getMessage());
		}
	}

	public VendorDaoImpl(String dbString) {
		try {
			Context context = new InitialContext();
			ds = (DataSource) context.lookup(dbString);
		} catch (NamingException e) {
			e.printStackTrace();
			throw new RuntimeException("VendorDao()#建構子發生例外: " + e.getMessage());
		}
	}

	Connection connection = null;

	public VendorDaoImpl(String url, String user, String password) {
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
			throw new RuntimeException("VendorDao()#建構子發生例外: " + e1.getMessage());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("VendorDao()#建構子發生例外: " + e.getMessage());
		}

	}

	private static final String SELECT_BY_ID = "Select vId, vname, addr,tel, email, c_person, c_tel from vendor where vId = ?";
	private static final String SELECT_BY_VNAME = "Select vId, vname, addr,tel, email, c_person, c_tel from vendor where vname = ?";
	private static final String SELECT_ALL = "Select vId, vname, addr,tel, email, c_person, c_tel from vendor";
	private static final String INSERT = "Insert into vendor (vname, addr, tel, email, c_person, c_tel) values (?, ?, ?, ?, ?, ?)";
	private static final String DELETE = "Delete from vendor where vId=?";
	private static final String COUNT = "SELECT count(*) FROM vendor";

	@Override
	public VendorBean select(Integer vId) {
		VendorBean result = null;

		if (connection != null && ds == null) {
			// 使用DriverManager連接資料庫
			try (PreparedStatement stmt = connection.prepareStatement(SELECT_BY_ID);) {
				stmt.setInt(1, vId);
				try (ResultSet rset = stmt.executeQuery();) {
					if (rset.next()) {
						result = new VendorBean();
						result.setvId(rset.getInt("vId"));
						result.setVname(rset.getString("vname"));
						result.setAddr(rset.getString("addr"));
						result.setTel(rset.getString("tel"));
						result.setEmail(rset.getString("email"));
						result.setC_person(rset.getString("c_person"));
						result.setC_tel(rset.getString("c_tel"));
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("VendorDao()#selectbyid()發生例外: " + e.getMessage());

			}

		} else if (connection == null && ds != null) {
			// 使用DataSource連接資料庫
			try (Connection conn = ds.getConnection(); PreparedStatement stmt = conn.prepareStatement(SELECT_BY_ID);) {
				stmt.setInt(1, vId);
				try (ResultSet rset = stmt.executeQuery();) {
					if (rset.next()) {
						result = new VendorBean();
						result.setvId(rset.getInt("vId"));
						result.setVname(rset.getString("vname"));
						result.setAddr(rset.getString("addr"));
						result.setTel(rset.getString("tel"));
						result.setEmail(rset.getString("email"));
						result.setC_person(rset.getString("c_person"));
						result.setC_tel(rset.getString("c_tel"));
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("VendorDao()#selectbyid()發生例外: " + e.getMessage());
			}
		}

		return result;
	}

	@Override
	public VendorBean select(String vname) {
		VendorBean result = null;
		if (connection != null && ds == null) {
			// 使用DriverManager連接資料庫
			try (PreparedStatement stmt = connection.prepareStatement(SELECT_BY_VNAME);) {
				stmt.setString(1, vname);
				try (ResultSet rset = stmt.executeQuery();) {
					if (rset.next()) {
						result = new VendorBean();
						result.setvId(rset.getInt("vId"));
						result.setVname(rset.getString("vname"));
						result.setAddr(rset.getString("addr"));
						result.setTel(rset.getString("tel"));
						result.setEmail(rset.getString("email"));
						result.setC_person(rset.getString("c_person"));
						result.setC_tel(rset.getString("c_tel"));
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("VendorDao()#selectbyname()發生例外: " + e.getMessage());
			}
		} else if (connection == null && ds != null) {
			// 使用Datasource連接資料庫
			try (Connection conn = ds.getConnection();
					PreparedStatement stmt = conn.prepareStatement(SELECT_BY_VNAME);) {
				stmt.setString(1, vname);
				try (ResultSet rset = stmt.executeQuery();) {
					if (rset.next()) {
						result = new VendorBean();
						result.setvId(rset.getInt("vId"));
						result.setVname(rset.getString("vname"));
						result.setAddr(rset.getString("addr"));
						result.setTel(rset.getString("tel"));
						result.setEmail(rset.getString("email"));
						result.setC_person(rset.getString("c_person"));
						result.setC_tel(rset.getString("c_tel"));
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("VendorDao()#selectbyname()發生例外: " + e.getMessage());
			}
		}

		return result;
	}

	@Override
	public List<VendorBean> select() {
		List<VendorBean> result = null;

		if (connection != null && ds == null) {
			// 使用DriverManager連接資料庫
			try (PreparedStatement stmt = connection.prepareStatement(SELECT_ALL);
					ResultSet rset = stmt.executeQuery();) {
				result = new Vector<>();
				while (rset.next()) {
					VendorBean temp = new VendorBean();
					temp.setvId(rset.getInt("vId"));
					temp.setVname(rset.getString("vname"));
					temp.setAddr(rset.getString("addr"));
					temp.setTel(rset.getString("tel"));
					temp.setEmail(rset.getString("email"));
					temp.setC_person(rset.getString("c_person"));
					temp.setC_tel(rset.getString("c_tel"));
					result.add(temp);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("VendorDao()#selectAll()發生例外: " + e.getMessage());
			}
		} else if (connection == null && ds != null) {
			// 使用Datasource連接資料庫
			try (Connection conn = ds.getConnection();
					PreparedStatement stmt = conn.prepareStatement(SELECT_ALL);
					ResultSet rset = stmt.executeQuery();) {
				result = new Vector<>();
				while (rset.next()) {
					VendorBean temp = new VendorBean();
					temp.setvId(rset.getInt("vId"));
					temp.setVname(rset.getString("vname"));
					temp.setAddr(rset.getString("addr"));
					temp.setTel(rset.getString("tel"));
					temp.setEmail(rset.getString("email"));
					temp.setC_person(rset.getString("c_person"));
					temp.setC_tel(rset.getString("c_tel"));
					result.add(temp);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("VendorDao()#selectAll()發生例外: " + e.getMessage());
			}
		}

		return result;
	}

	@Override
	public VendorBean insertVendor(VendorBean bean) {
		VendorBean result = null;

		if (connection != null && ds == null) {
			// 使用DriverManager連接資料庫
			try (PreparedStatement stmt = connection.prepareStatement(INSERT);) {
				stmt.setString(1, bean.getVname());
				stmt.setString(2, bean.getAddr());
				stmt.setString(3, bean.getTel());
				stmt.setString(4, bean.getEmail());
				stmt.setString(5, bean.getC_person());
				stmt.setString(6, bean.getC_tel());

				int i = stmt.executeUpdate();
				if (i == 1) {
					result = this.select(bean.getVname());
				}

			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("VendorDao()#insertVendor()發生例外: " + e.getMessage());
			}
		} else if (connection == null && ds != null) {
			// 使用Datasource連接資料庫
			try (Connection conn = ds.getConnection(); PreparedStatement stmt = conn.prepareStatement(INSERT);) {
				stmt.setString(1, bean.getVname());
				stmt.setString(2, bean.getAddr());
				stmt.setString(3, bean.getTel());
				stmt.setString(4, bean.getEmail());
				stmt.setString(5, bean.getC_person());
				stmt.setString(6, bean.getC_tel());
				int i = stmt.executeUpdate();
				if (i == 1) {
					result = this.select(bean.getVname());
				}

			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("VendorDao()#delete()發生例外: " + e.getMessage());
			}
		}

		return result;
	}

	@Override
	public Integer delete(Integer vId) {
		int result = 0;
		if (connection != null && ds == null) {
			// 使用DriverManager連接資料庫
			try (PreparedStatement stmt = connection.prepareStatement(DELETE);) {
				stmt.setInt(1, vId);
				result = stmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("VendorDao()#delete()發生例外: " + e.getMessage());
			}
		} else if (connection == null && ds != null) {
			// 使用Datasource連接資料庫
			try (Connection conn = ds.getConnection(); PreparedStatement stmt = conn.prepareStatement(DELETE);) {
				stmt.setInt(1, vId);
				result = stmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("VendorDao()#delete()發生例外: " + e.getMessage());
			}
		}

		return result;
	}

	@Override
	public void close() {
		if (this.connection != null) {
			try {
				this.connection.close();
				System.out.println("DatabaseClose");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RuntimeException("VendorDao()#close()發生例外: " + e.getMessage());
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
				throw new RuntimeException("Vendor()#getRecordCounts()發生例外: " + ex.getMessage());
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
				throw new RuntimeException("Vendor()#getRecordCounts()發生例外: " + ex.getMessage());
			}
		}

		return count;
	}

}