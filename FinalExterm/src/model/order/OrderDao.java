package model.order;

import java.sql.Connection;
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

	private static final String SELECT_BY_ID = "Select oId, pId, mId, date, price, status from ordertable where oId = ?";

	public OrderBean select(int oId) {
		OrderBean result = null;
		try (Connection conn = ds.getConnection(); PreparedStatement stmt = conn.prepareStatement(SELECT_BY_ID);) {
			stmt.setInt(1, oId);
			try (ResultSet rset = stmt.executeQuery();) {
				if (rset.next()) {
					result = new OrderBean();
					result.setoId(rset.getInt("oId"));
					result.setpId(rset.getInt("password"));
					result.setmId(rset.getInt("mId"));
					result.setDate(rset.getTimestamp("date"));
					result.setPrice(rset.getInt("price"));
					result.setStatus(rset.getString("status"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	private static final String SELECT_ALL = "Select oId, pId, mId, date, price, status from ordertable";

	public List<OrderBean> select() {
		List<OrderBean> result = null;
		try (Connection conn = ds.getConnection();
				PreparedStatement stmt = conn.prepareStatement(SELECT_ALL);
				ResultSet rset = stmt.executeQuery();) {
			result = new Vector<>();
			while (rset.next()) {
				OrderBean temp = new OrderBean();
				temp.setoId(rset.getInt("oId"));
				temp.setpId(rset.getInt("password"));
				temp.setmId(rset.getInt("mId"));
				temp.setDate(rset.getTimestamp("date"));
				temp.setPrice(rset.getInt("price"));
				temp.setStatus(rset.getString("status"));
				result.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	private static final String INSERT = "Insert into ordertable (oId, pId, mId, date, price, status) values (?, ?, ?, ?, ?, ?)";

	public OrderBean insertMember(OrderBean bean) throws SQLException {
		OrderBean result = null;
		try (Connection conn = ds.getConnection(); PreparedStatement stmt = conn.prepareStatement(INSERT);) {
			stmt.setInt(1, bean.getoId());
			stmt.setInt(2, bean.getpId());
			stmt.setInt(3, bean.getmId());
			Timestamp ts = new Timestamp(System.currentTimeMillis());
			stmt.setTimestamp(4, ts);
			stmt.setInt(5, bean.getPrice());
			stmt.setString(5, bean.getStatus());

			int i = stmt.executeUpdate();
			if (i == 1) {
				result = this.select(bean.getoId());
			}

		}
		return result;
	}

	private static final String DELETE = "Delete from ordertable where oId=?";

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
}