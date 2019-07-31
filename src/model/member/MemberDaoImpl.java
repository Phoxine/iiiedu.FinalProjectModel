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

public class MemberDaoImpl implements MemberDao {

	DataSource ds = null;

	public MemberDaoImpl() {
		try {
			Context context = new InitialContext();
			// TODO change lookup parameter
			ds = (DataSource) context.lookup("java:comp/env/jdbc/MemberDB");
		} catch (NamingException e) {
			e.printStackTrace();
			throw new RuntimeException("MemberDao()#建構子發生例外: " + e.getMessage());
		}
	}

	public MemberDaoImpl(String dbString) {
		try {
			Context context = new InitialContext();
			ds = (DataSource) context.lookup(dbString);
		} catch (NamingException e) {
			e.printStackTrace();
			throw new RuntimeException("MemberDao()#建構子發生例外: " + e.getMessage());
		}
	}

	Connection connection = null;

	public MemberDaoImpl(String url, String user, String password) {
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
			throw new RuntimeException("MemberDao()#建構子發生例外: " + e1.getMessage());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("MemberDao()#建構子發生例外: " + e.getMessage());
		}

	}

	private static final String SELECT_BY_ID = "Select mId, name, tel, addr, rdate, account, password, email,birthday,gender,memberImage from member where mId = ?";
	private static final String SELECT_BY_NAME = "Select mId, name, tel, addr, rdate, account, password, email, birthday,gender,memberImage from member where name = ?";
	private static final String SELECT_ALL = "Select mId, name, tel, addr, rdate, account, password, email , birthday,gender,memberImage from member";
	private static final String INSERT = "Insert into member ( name, tel, addr, rdate, account, password, email, birthday,gender) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String INSERT_WITH_MEMBERIMAGE = "Insert into member ( name, tel, addr, rdate, account, password, email, birthday, gender, memberImage) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String DELETE = "Delete from member where mId=?";
	private static final String COUNT = "SELECT count(*) FROM member";
	private static final String CHECK_ACCOUNT_PASSWORD = "SELECT * FROM Member m WHERE m.account = ? and m.password = ?";
	private static final String CHECK_PHONE_PASSWORD = "SELECT * FROM Member m WHERE m.tel = ? and m.password = ?";

	@Override
	public MemberBean select(Integer mId) {
		MemberBean result = null;
		if (connection != null && ds == null) {
			// 使用DriverManager連接資料庫
			try (PreparedStatement stmt = connection.prepareStatement(SELECT_BY_ID);) {
				stmt.setInt(1, mId);
				try (ResultSet rset = stmt.executeQuery();) {
					if (rset.next()) {
						result = new MemberBean();
						result.setmId(rset.getInt("mId"));
						result.setName(rset.getString("name"));
						result.setTel(rset.getString("tel"));
						result.setAddr(rset.getString("addr"));
						// TODO 型態確認
						result.setRdate(rset.getTimestamp("rdate"));
						result.setAccount(rset.getString("account"));
						result.setPassword(rset.getString("password"));
						result.setEmail(rset.getString("email"));
						result.setBirthday(rset.getTimestamp("birthday"));
						result.setGender(rset.getString("gender"));
					}
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException("MemberDao()#selectbymid()發生例外: " + e.getMessage());
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("MemberDao()#selectbymid()發生例外: " + e.getMessage());
			}
		} else if (connection == null && ds != null) {
			// 使用Datasource連接資料庫
			try (Connection conn = ds.getConnection(); PreparedStatement stmt = conn.prepareStatement(SELECT_BY_ID);) {
				stmt.setInt(1, mId);
				try (ResultSet rset = stmt.executeQuery();) {
					if (rset.next()) {
						result = new MemberBean();
						result.setmId(rset.getInt("mId"));
						result.setName(rset.getString("name"));
						result.setTel(rset.getString("tel"));
						result.setAddr(rset.getString("addr"));
						// TODO 型態確認
						result.setRdate(rset.getTimestamp("rdate"));
						result.setAccount(rset.getString("account"));
						result.setPassword(rset.getString("password"));
						result.setEmail(rset.getString("email"));
						result.setBirthday(rset.getTimestamp("birthday"));
						result.setGender(rset.getString("gender"));
					}
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException("MemberDao()#selectbymid()發生例外: " + e.getMessage());
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("MemberDao()#selectbymid()發生例外: " + e.getMessage());
			}
		}

		return result;
	}

	@Override
	public MemberBean select(String name) {
		MemberBean result = null;
		if (connection != null && ds == null) {
			// 使用DriverManager連接資料庫
			try (PreparedStatement stmt = connection.prepareStatement(SELECT_BY_NAME);) {
				stmt.setString(1, name);
				try (ResultSet rset = stmt.executeQuery();) {
					if (rset.next()) {
						result = new MemberBean();
						result.setmId(rset.getInt("mId"));
						result.setName(rset.getString("name"));
						result.setTel(rset.getString("tel"));
						result.setAddr(rset.getString("addr"));
						// TODO 型態確認
						result.setRdate(rset.getTimestamp("rdate"));
						result.setAccount(rset.getString("account"));
						result.setPassword(rset.getString("password"));
						result.setEmail(rset.getString("email"));
						result.setBirthday(rset.getTimestamp("birthday"));
						result.setGender(rset.getString("gender"));
					}
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException("MemberDao()#selectbyname()發生例外: " + e.getMessage());
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("MemberDao()#selectbyname()發生例外: " + e.getMessage());
			}
		} else if (connection == null && ds != null) {
			// 使用Datasource連接資料庫
			try (Connection conn = ds.getConnection();
					PreparedStatement stmt = conn.prepareStatement(SELECT_BY_NAME);) {
				stmt.setString(1, name);
				try (ResultSet rset = stmt.executeQuery();) {
					if (rset.next()) {
						result = new MemberBean();
						result.setmId(rset.getInt("mId"));
						result.setName(rset.getString("name"));
						result.setTel(rset.getString("tel"));
						result.setAddr(rset.getString("addr"));
						// TODO 型態確認
						result.setRdate(rset.getTimestamp("rdate"));
						result.setAccount(rset.getString("account"));
						result.setPassword(rset.getString("password"));
						result.setEmail(rset.getString("email"));
						result.setBirthday(rset.getTimestamp("birthday"));
						result.setGender(rset.getString("gender"));
					}
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException("MemberDao()#selectbyname()發生例外: " + e.getMessage());
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("MemberDao()#selectbyname()發生例外: " + e.getMessage());
			}
		}

		return result;
	}

	@Override
	public List<MemberBean> select() {
		List<MemberBean> result = null;
		if (connection != null && ds == null) {
			// 使用DriverManager連接資料庫
			try (PreparedStatement stmt = connection.prepareStatement(SELECT_ALL);
					ResultSet rset = stmt.executeQuery();) {
				result = new Vector<>();
				while (rset.next()) {
					MemberBean temp = new MemberBean();
					temp.setmId(rset.getInt("mId"));
					temp.setName(rset.getString("name"));
					temp.setTel(rset.getString("tel"));
					temp.setAddr(rset.getString("addr"));
					// TODO 型態確認
					temp.setRdate(rset.getTimestamp("rdate"));
					temp.setAccount(rset.getString("account"));
					temp.setPassword(rset.getString("password"));
					temp.setEmail(rset.getString("email"));
					temp.setBirthday(rset.getTimestamp("birthday"));
					temp.setGender(rset.getString("gender"));
					temp.setMemberImage(rset.getBlob("memberImage"));
					result.add(temp);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("MemberDao()#selectAll()發生例外: " + e.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("MemberDao()#selectAll()發生例外: " + e.getMessage());
			}
		} else if (connection == null && ds != null) {
			// 使用Datasource連接資料庫
			try (Connection conn = ds.getConnection();
					PreparedStatement stmt = conn.prepareStatement(SELECT_ALL);
					ResultSet rset = stmt.executeQuery();) {
				result = new Vector<>();
				while (rset.next()) {
					MemberBean temp = new MemberBean();
					temp.setmId(rset.getInt("mId"));
					temp.setName(rset.getString("name"));
					temp.setTel(rset.getString("tel"));
					temp.setAddr(rset.getString("addr"));
					// TODO 型態確認
					temp.setRdate(rset.getTimestamp("rdate"));
					temp.setAccount(rset.getString("account"));
					temp.setPassword(rset.getString("password"));
					temp.setEmail(rset.getString("email"));
					temp.setBirthday(rset.getTimestamp("birthday"));
					temp.setGender(rset.getString("gender"));
					result.add(temp);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("MemberDao()#selectAll()發生例外: " + e.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("MemberDao()#selectAll()發生例外: " + e.getMessage());
			}
		}

		return result;
	}

	@Override
	public MemberBean insertMember(MemberBean bean) {
		MemberBean result = null;
		if (connection != null && ds == null) {
			// 使用DriverManager連接資料庫
			try (PreparedStatement stmt = connection.prepareStatement(INSERT);) {
				stmt.setString(1, bean.getName());
				stmt.setString(2, bean.getTel());
				stmt.setString(3, bean.getAddr());
				Timestamp ts = new Timestamp(System.currentTimeMillis());
				stmt.setTimestamp(4, ts);
				stmt.setString(5, bean.getAccount());
				stmt.setString(6, bean.getPassword());
				stmt.setString(7, bean.getEmail());
				stmt.setTimestamp(8, bean.getBirthday());
				stmt.setString(9, bean.getGender());
				int i = stmt.executeUpdate();
				if (i == 1) {
					result = this.select(bean.getName());
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("MemberDao()#insertMember()發生例外: " + e.getMessage());
			}
		} else if (connection == null && ds != null) {
			// 使用Datasource連接資料庫
			try (Connection conn = ds.getConnection(); PreparedStatement stmt = conn.prepareStatement(INSERT);) {
				stmt.setString(1, bean.getName());
				stmt.setString(2, bean.getTel());
				stmt.setString(3, bean.getAddr());
				Timestamp ts = new Timestamp(System.currentTimeMillis());
				stmt.setTimestamp(4, ts);
				stmt.setString(5, bean.getAccount());
				stmt.setString(6, bean.getPassword());
				stmt.setString(7, bean.getEmail());
				stmt.setTimestamp(8, bean.getBirthday());
				stmt.setString(9, bean.getGender());
				int i = stmt.executeUpdate();
				if (i == 1) {
					result = this.select(bean.getName());
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("MemberDao()#insertMember()發生例外: " + e.getMessage());
			}
		}
		return result;
	}

	@Override
	public MemberBean insertMember_With_memberImage(MemberBean bean) {
		MemberBean result = null;
		if (connection != null && ds == null) {
			// 使用DriverManager連接資料庫
			try (PreparedStatement stmt = connection.prepareStatement(INSERT_WITH_MEMBERIMAGE);) {
				stmt.setString(1, bean.getName());
				stmt.setString(2, bean.getTel());
				stmt.setString(3, bean.getAddr());
				Timestamp ts = new Timestamp(System.currentTimeMillis());
				stmt.setTimestamp(4, ts);
				stmt.setString(5, bean.getAccount());
				stmt.setString(6, bean.getPassword());
				stmt.setString(7, bean.getEmail());
				stmt.setTimestamp(8, bean.getBirthday());
				stmt.setString(9, bean.getGender());
				stmt.setBinaryStream(10, bean.getMemberImage().getBinaryStream());
				int i = stmt.executeUpdate();
				if (i == 1) {
					result = this.select(bean.getName());
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("MemberDao()#insertMember()發生例外: " + e.getMessage());
			}
		} else if (connection == null && ds != null) {
			// 使用Datasource連接資料庫
			try (Connection conn = ds.getConnection(); PreparedStatement stmt = conn.prepareStatement(INSERT);) {
				stmt.setString(1, bean.getName());
				stmt.setString(2, bean.getTel());
				stmt.setString(3, bean.getAddr());
				Timestamp ts = new Timestamp(System.currentTimeMillis());
				stmt.setTimestamp(4, ts);
				stmt.setString(5, bean.getAccount());
				stmt.setString(6, bean.getPassword());
				stmt.setString(7, bean.getEmail());
				stmt.setTimestamp(8, bean.getBirthday());
				stmt.setString(9, bean.getGender());
				int i = stmt.executeUpdate();
				if (i == 1) {
					result = this.select(bean.getName());
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("MemberDao()#insertMember()發生例外: " + e.getMessage());
			}
		}
		return result;
	}

	@Override
	public Integer delete(Integer mId) {
		int result = 0;
		if (connection != null && ds == null) {
			// 使用DriverManager連接資料庫
			try (PreparedStatement stmt = connection.prepareStatement(DELETE);) {
				stmt.setInt(1, mId);
				result = stmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("MemberDao()#delete()發生例外: " + e.getMessage());
			}
		} else if (connection == null && ds != null) {
			// 使用Datasource連接資料庫
			try (Connection conn = ds.getConnection(); PreparedStatement stmt = conn.prepareStatement(DELETE);) {
				stmt.setInt(1, mId);
				result = stmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("MemberDao()#delete()發生例外: " + e.getMessage());
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
				throw new RuntimeException("MemberDao()#close()發生例外: " + e.getMessage());
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
				throw new RuntimeException("Member()#getRecordCounts()發生例外: " + ex.getMessage());
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
				throw new RuntimeException("Member()#getRecordCounts()發生例外: " + ex.getMessage());
			}
		}
		return count;
	}

	// 檢查使用者在登入時輸入的帳號與密碼是否正確。如果正確，傳回該帳號所對應的MemberBean物件，
	// 否則傳回 null。
	@Override
	public MemberBean checkAccountPassword(String userAccount, String password) {

		if (connection != null && ds == null) {
			// 使用DriverManager連接資料庫
			try (PreparedStatement stmt = connection.prepareStatement(CHECK_ACCOUNT_PASSWORD);) {
				stmt.setString(1, userAccount);
				stmt.setString(2, password);
				try (ResultSet rset = stmt.executeQuery();) {
					if (rset.next()) {
						MemberBean temp = new MemberBean();
						temp.setmId(rset.getInt("mId"));
						temp.setName(rset.getString("name"));
						temp.setTel(rset.getString("tel"));
						temp.setAddr(rset.getString("addr"));
						temp.setRdate(rset.getTimestamp("rdate"));
						temp.setAccount(rset.getString("account"));
						temp.setPassword(rset.getString("password"));
						temp.setEmail(rset.getString("email"));
						temp.setBirthday(rset.getTimestamp("birthday"));
						temp.setGender(rset.getString("gender"));
						temp.setMemberImage(rset.getBlob("memberImage"));
						return temp;
					}
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
				throw new RuntimeException("MemberDaoImpl類別#checkIDPassword()發生SQL例外: " + ex.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("MemberDaoImpl類別#checkIDPassword()發生SQL例外: " + e.getMessage());
			}
		} else if (connection == null && ds != null) {
			// 使用Datasource連接資料庫
			try (Connection connection = ds.getConnection();
					PreparedStatement stmt = connection.prepareStatement(CHECK_ACCOUNT_PASSWORD);) {
				stmt.setString(1, userAccount);
				stmt.setString(2, password);
				try (ResultSet rset = stmt.executeQuery();) {
					if (rset.next()) {
						MemberBean temp = new MemberBean();
						temp.setmId(rset.getInt("mId"));
						temp.setName(rset.getString("name"));
						temp.setTel(rset.getString("tel"));
						temp.setAddr(rset.getString("addr"));
						temp.setRdate(rset.getTimestamp("rdate"));
						temp.setAccount(rset.getString("account"));
						temp.setPassword(rset.getString("password"));
						temp.setEmail(rset.getString("email"));
						temp.setBirthday(rset.getTimestamp("birthday"));
						temp.setGender(rset.getString("gender"));
						temp.setMemberImage(rset.getBlob("memberImage"));
						return temp;
					}
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
				throw new RuntimeException("MemberDaoImpl類別#checkIDPassword()發生SQL例外: " + ex.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("MemberDaoImpl類別#checkIDPassword()發生SQL例外: " + e.getMessage());
			}

		}
		return null;
	}

	@Override
	public MemberBean checkPhonePassword(String phone, String password) {
		
		
		if (connection != null && ds == null) {
			// 使用DriverManager連接資料庫
			try (PreparedStatement stmt = connection.prepareStatement(CHECK_PHONE_PASSWORD);) {
				stmt.setString(1, phone);
				stmt.setString(2, password);
				try (ResultSet rset = stmt.executeQuery();) {
					if (rset.next()) {
						MemberBean temp = new MemberBean();
						temp.setmId(rset.getInt("mId"));
						temp.setName(rset.getString("name"));
						temp.setTel(rset.getString("tel"));
						temp.setAddr(rset.getString("addr"));
						temp.setRdate(rset.getTimestamp("rdate"));
						temp.setAccount(rset.getString("account"));
						temp.setPassword(rset.getString("password"));
						temp.setEmail(rset.getString("email"));
						temp.setBirthday(rset.getTimestamp("birthday"));
						temp.setGender(rset.getString("gender"));
						temp.setMemberImage(rset.getBlob("memberImage"));
						return temp;
					}
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
				throw new RuntimeException("MemberDaoImpl類別#checkPhonePassword()發生SQL例外: " + ex.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("MemberDaoImpl類別#checkPhonePassword()發生SQL例外: " + e.getMessage());
			}
		} else if (connection == null && ds != null) {
			// 使用Datasource連接資料庫
			try (Connection connection = ds.getConnection();
					PreparedStatement stmt = connection.prepareStatement(CHECK_PHONE_PASSWORD);) {
				stmt.setString(1, phone);
				stmt.setString(2, password);
				try (ResultSet rset = stmt.executeQuery();) {
					if (rset.next()) {
						MemberBean temp = new MemberBean();
						temp.setmId(rset.getInt("mId"));
						temp.setName(rset.getString("name"));
						temp.setTel(rset.getString("tel"));
						temp.setAddr(rset.getString("addr"));
						temp.setRdate(rset.getTimestamp("rdate"));
						temp.setAccount(rset.getString("account"));
						temp.setPassword(rset.getString("password"));
						temp.setEmail(rset.getString("email"));
						temp.setBirthday(rset.getTimestamp("birthday"));
						temp.setGender(rset.getString("gender"));
						temp.setMemberImage(rset.getBlob("memberImage"));
						return temp;
					}
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
				throw new RuntimeException("MemberDaoImpl類別#checkPhonePassword()發生SQL例外: " + ex.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("MemberDaoImpl類別#checkPhonePassword()發生SQL例外: " + e.getMessage());
			}

		}
		
		return null;
	}

}