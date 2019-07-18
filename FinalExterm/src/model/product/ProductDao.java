package model.product;

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

public class ProductDao {

	DataSource ds = null;

	public ProductDao() {
		try {
			Context context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/jdbc/MemberDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public ProductDao(String dbString) {
		try {
			Context context = new InitialContext();
			ds = (DataSource) context.lookup(dbString);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String SELECT_BY_ID = "Select pId, pname, price, vId, amount, category, sdate, expdate from product where pId = ?";
	private static final String SELECT_ALL = "Select pId, pname, price, vId, amount, category, sdate, expdate from product";
	private static final String INSERT = "Insert into product (pname, price, vId, amount, category, sdate, expdate) values (?, ?, ?, ?, ?, ?, ?)";
	private static final String DELETE = "Delete from member42 where oId=?";

	public ProductBean select(int pId) {
		ProductBean result = null;
		if (connection != null && ds == null) {
			// 使用DriverManager連接資料庫
		} else if (connection == null && ds != null) {
			// 使用Datasource連接資料庫
		}
		try (Connection conn = ds.getConnection(); PreparedStatement stmt = conn.prepareStatement(SELECT_BY_ID);) {
			stmt.setInt(1, pId);
			try (ResultSet rset = stmt.executeQuery();) {
				if (rset.next()) {
					result = new ProductBean();
					result.setpId(rset.getInt("pId"));
					result.setPname(rset.getString("pname"));
					result.setPrice(rset.getInt("price"));
					result.setvId(rset.getInt("vId"));
					result.setAmount(rset.getInt("amount"));
					result.setCategory(rset.getString("category"));
					result.setSdate(rset.getTimestamp("sdate"));
					result.setExpdate(rset.getTimestamp("expdate"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<ProductBean> select() {
		List<ProductBean> result = null;
		try (Connection conn = ds.getConnection();
				PreparedStatement stmt = conn.prepareStatement(SELECT_ALL);
				ResultSet rset = stmt.executeQuery();) {
			result = new Vector<>();
			while (rset.next()) {
				ProductBean temp = new ProductBean();
				temp.setpId(rset.getInt("pId"));
				temp.setPname(rset.getString("pname"));
				temp.setPrice(rset.getInt("price"));
				temp.setvId(rset.getInt("vId"));
				temp.setAmount(rset.getInt("amount"));
				temp.setCategory(rset.getString("category"));
				temp.setSdate(rset.getTimestamp("sdate"));
				temp.setExpdate(rset.getTimestamp("expdate"));
				result.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public ProductBean insertProduct(ProductBean bean) throws SQLException {
		ProductBean result = null;
		try (Connection conn = ds.getConnection(); PreparedStatement stmt = conn.prepareStatement(INSERT);) {
			stmt.setString(1, bean.getPname());
			stmt.setInt(2, bean.getPrice());
			stmt.setInt(3, bean.getvId());
			stmt.setInt(4, bean.getAmount());
			stmt.setString(5, bean.getCategory());
			stmt.setTimestamp(6, bean.getSdate());
			stmt.setTimestamp(7, bean.getExpdate());

			int i = stmt.executeUpdate();
			if (i == 1) {
				result = this.select(bean.getvId());
			}

		}
		return result;
	}

	public int delete(int pId) {
		int result = 0;
		try (Connection conn = ds.getConnection(); PreparedStatement stmt = conn.prepareStatement(DELETE);) {
			stmt.setInt(1, pId);
			result = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	/*
	 * jdbc test code
	 */

	Connection connection = null;

	public ProductDao(String url, String user, String password) {
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

	public ProductBean select(int pId, boolean test) {
		ProductBean result = null;
		try (PreparedStatement stmt = connection.prepareStatement(SELECT_BY_ID);) {
			stmt.setInt(1, pId);
			try (ResultSet rset = stmt.executeQuery();) {
				if (rset.next()) {
					result = new ProductBean();
					result.setpId(rset.getInt("pId"));
					result.setPname(rset.getString("pname"));
					result.setPrice(rset.getInt("price"));
					result.setvId(rset.getInt("vId"));
					result.setAmount(rset.getInt("amount"));
					result.setCategory(rset.getString("category"));
					result.setSdate(rset.getTimestamp("sdate"));
					result.setExpdate(rset.getTimestamp("expdate"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<ProductBean> select(boolean test) {
		List<ProductBean> result = null;
		try (PreparedStatement stmt = connection.prepareStatement(SELECT_ALL); ResultSet rset = stmt.executeQuery();) {
			result = new Vector<>();
			while (rset.next()) {
				ProductBean temp = new ProductBean();
				temp.setpId(rset.getInt("pId"));
				temp.setPname(rset.getString("pname"));
				temp.setPrice(rset.getInt("price"));
				temp.setvId(rset.getInt("vId"));
				temp.setAmount(rset.getInt("amount"));
				temp.setCategory(rset.getString("category"));
				temp.setSdate(rset.getTimestamp("sdate"));
				temp.setExpdate(rset.getTimestamp("expdate"));
				result.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public ProductBean insertProduct(ProductBean bean, boolean test) throws SQLException {
		ProductBean result = null;
		try (PreparedStatement stmt = connection.prepareStatement(INSERT);) {
			stmt.setString(1, bean.getPname());
			stmt.setInt(2, bean.getPrice());
			stmt.setInt(3, bean.getvId());
			stmt.setInt(4, bean.getAmount());
			stmt.setString(5, bean.getCategory());
			stmt.setTimestamp(6, bean.getSdate());
			stmt.setTimestamp(7, bean.getExpdate());

			int i = stmt.executeUpdate();
			if (i == 1) {
				result = this.select(bean.getvId(), true);
			}

		}
		return result;
	}

	public int delete(int pId, boolean test) {
		int result = 0;
		try (PreparedStatement stmt = connection.prepareStatement(DELETE);) {
			stmt.setInt(1, pId);
			result = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public void close() {
		if (this.connection != null) {
			try {
				connection.close();
				System.out.println("database close");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}