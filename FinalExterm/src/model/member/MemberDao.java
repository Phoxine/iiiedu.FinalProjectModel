package model.member;

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

public class MemberDao {

	DataSource ds = null;

	public MemberDao() {
		try {
			Context context = new InitialContext();
			// TODO change lookup parameter
			ds = (DataSource) context.lookup("java:comp/env/jdbc/MemberDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public MemberDao(String dbString) {
		try {
			Context context = new InitialContext();
			ds = (DataSource) context.lookup(dbString);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String SELECT_BY_ID = "Select mId, name, tel, addr, rdate, account, password, email,birthday,sex from member where mId = ?";
	private static final String SELECT_BY_NAME = "Select mId, name, tel, addr, rdate, account, password, email, birthday,sex from member where name = ?";
	private static final String SELECT_ALL = "Select mId, name, tel, addr, rdate, account, password, email , birthday,sex from member";
	private static final String INSERT = "Insert into member ( name, tel, addr, rdate, account, password, email, birthday,sex) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String DELETE = "Delete from member where mId=?";

	public MemberBean select(int mid) {
		MemberBean result = null;
		try (Connection conn = ds.getConnection(); PreparedStatement stmt = conn.prepareStatement(SELECT_BY_ID);) {
			stmt.setInt(1, mid);
			try (ResultSet rset = stmt.executeQuery();) {
				if (rset.next()) {
					result = new MemberBean();
					result.setmId(rset.getInt("mId"));
					result.setName(rset.getString("name"));
					result.setTel(rset.getBigDecimal("tel"));
					result.setAddr(rset.getString("addr"));
					// TODO 型態確認
					result.setRdate(rset.getTimestamp("rdate"));
					result.setAccount(rset.getString("account"));
					result.setPassword(rset.getString("password"));
					result.setEmail(rset.getString("email"));
					result.setBirthday(rset.getTimestamp("birthday"));
					result.setSex(rset.getString("sex"));
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public MemberBean select(String name) {
		MemberBean result = null;
		try (Connection conn = ds.getConnection(); PreparedStatement stmt = conn.prepareStatement(SELECT_BY_NAME);) {

			stmt.setString(1, name);
			try (ResultSet rset = stmt.executeQuery();) {
				if (rset.next()) {
					result = new MemberBean();
					result.setmId(rset.getInt("mId"));
					result.setName(rset.getString("name"));
					result.setTel(rset.getBigDecimal("tel"));
					result.setAddr(rset.getString("addr"));
					// TODO 型態確認
					result.setRdate(rset.getTimestamp("rdate"));
					result.setAccount(rset.getString("account"));
					result.setPassword(rset.getString("password"));
					result.setEmail(rset.getString("email"));
					result.setBirthday(rset.getTimestamp("birthday"));
					result.setSex(rset.getString("sex"));
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<MemberBean> select() {
		List<MemberBean> result = null;
		try (Connection conn = ds.getConnection();
				PreparedStatement stmt = conn.prepareStatement(SELECT_ALL);
				ResultSet rset = stmt.executeQuery();) {
			result = new Vector<>();
			while (rset.next()) {
				MemberBean temp = new MemberBean();
				temp.setmId(rset.getInt("mId"));
				temp.setName(rset.getString("name"));
				temp.setTel(rset.getBigDecimal("tel"));
				temp.setAddr(rset.getString("addr"));
				// TODO 型態確認
				temp.setRdate(rset.getTimestamp("rdate"));
				temp.setAccount(rset.getString("account"));
				temp.setPassword(rset.getString("password"));
				temp.setEmail(rset.getString("email"));
				temp.setBirthday(rset.getTimestamp("birthday"));
				temp.setSex(rset.getString("sex"));
				result.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public MemberBean insertMember(MemberBean bean) throws SQLException {
		MemberBean result = null;
		try (Connection conn = ds.getConnection(); PreparedStatement stmt = conn.prepareStatement(INSERT);) {
//			stmt.setInt(1, bean.getmId());
			stmt.setString(1, bean.getName());
			stmt.setBigDecimal(2, bean.getTel());
			stmt.setString(3, bean.getAddr());
			Timestamp ts = new Timestamp(System.currentTimeMillis());
			stmt.setTimestamp(4, ts);
			stmt.setString(5, bean.getAccount());
			stmt.setString(6, bean.getPassword());
			stmt.setString(7, bean.getEmail());
			stmt.setTimestamp(8, bean.getBirthday());
			stmt.setString(9,bean.getSex());
			int i = stmt.executeUpdate();
			if (i == 1) {
				result = this.select(bean.getmId());
			}
		}
		return result;
	}

	public int delete(int mId) {
		int result = 0;
		try (Connection conn = ds.getConnection(); PreparedStatement stmt = conn.prepareStatement(DELETE);) {
			stmt.setInt(1, mId);
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

	public MemberDao(String url, String user, String password) {
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

	public MemberBean select(String name, boolean test) {
		MemberBean result = null;
		try (PreparedStatement stmt = connection.prepareStatement(SELECT_BY_NAME);) {
			stmt.setString(1, name);
			try (ResultSet rset = stmt.executeQuery();) {
				if (rset.next()) {
					result = new MemberBean();
					result.setmId(rset.getInt("mId"));
					result.setName(rset.getString("name"));
					result.setTel(rset.getBigDecimal("tel"));
					result.setAddr(rset.getString("addr"));
					// TODO 型態確認
					result.setRdate(rset.getTimestamp("rdate"));
					result.setAccount(rset.getString("account"));
					result.setPassword(rset.getString("password"));
					result.setEmail(rset.getString("email"));
					result.setBirthday(rset.getTimestamp("birthday"));
					result.setSex(rset.getString("sex"));
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<MemberBean> select(boolean test) {
		List<MemberBean> result = null;
		try (PreparedStatement stmt = connection.prepareStatement(SELECT_ALL); ResultSet rset = stmt.executeQuery();) {
			result = new Vector<>();
			while (rset.next()) {
				MemberBean temp = new MemberBean();
				temp.setmId(rset.getInt("mId"));
				temp.setName(rset.getString("name"));
				temp.setTel(rset.getBigDecimal("tel"));
				temp.setAddr(rset.getString("addr"));
				// TODO 型態確認
				temp.setRdate(rset.getTimestamp("rdate"));
				temp.setAccount(rset.getString("account"));
				temp.setPassword(rset.getString("password"));
				temp.setEmail(rset.getString("email"));
				temp.setBirthday(rset.getTimestamp("birthday"));
				temp.setSex(rset.getString("sex"));
				result.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public MemberBean insertMember(MemberBean bean, boolean test) throws SQLException {
		MemberBean result = null;
		try (PreparedStatement stmt = connection.prepareStatement(
				INSERT);) {
			stmt.setString(1, bean.getName());
			stmt.setBigDecimal(2, bean.getTel());
			stmt.setString(3, bean.getAddr());
			Timestamp ts = new Timestamp(System.currentTimeMillis());
			stmt.setTimestamp(4, ts);
			stmt.setString(5, bean.getAccount());
			stmt.setString(6, bean.getPassword());
			stmt.setString(7, bean.getEmail());
			stmt.setTimestamp(8, bean.getBirthday());
			stmt.setString(9, bean.getSex());
			int i = stmt.executeUpdate();
			if (i == 1) {
				result = this.select(bean.getName(), true);
			}
		}
		return result;
	}

	public int delete(int mId, boolean test) {
		int result = 0;
		try (PreparedStatement stmt = connection.prepareStatement(DELETE);) {
			stmt.setInt(1, mId);
			result = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public void close() {
		try {
			this.connection.close();
			System.out.println("database close");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}