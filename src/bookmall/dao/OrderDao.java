package bookmall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookmall.jdbc.JDBC;
import bookmall.vo.OrderVo;

public class OrderDao {
	public Boolean insertOrder(OrderVo vo) {
		Boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		List<OrderVo> list = new ArrayList<OrderVo>();
		try {
			conn = JDBC.getConnection();
			String sql = "insert into purchase values(null,(SELECT concat((select date_format(now(),'%Y%m%d') as dateformat),'-',(SELECT LPAD((select (count(*)+1) from purchase p), 4, '0') as count))),(select sum(a.count*b.price) price from cart a, book b where a.book_no=b.no and a.member_no=?),?,?)";

			pstmt = conn.prepareStatement(sql);

			pstmt.setLong(1, vo.getMemberNo());
			pstmt.setString(2, vo.getPlace());
			pstmt.setLong(3, vo.getMemberNo());

			int count = pstmt.executeUpdate();

			list = getCartList(vo);

			insertOrderBook(list);

			result = count == 1;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public Boolean insertOrderBook(List<OrderVo> vo) {
		Boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = JDBC.getConnection();
			for (OrderVo tmp : vo) {
				OrderVo pcv = new OrderVo();
				pcv.setNo(tmp.getNo());
				pcv.setBookNo(tmp.getBookNo());
				pcv.setCount(tmp.getCount());

				String sql = "insert into order_book values(?,?,?)";

				pstmt = conn.prepareStatement(sql);
				pstmt.setLong(1, pcv.getNo());
				pstmt.setLong(2, tmp.getBookNo());
				pstmt.setLong(3, tmp.getCount());

				int count = pstmt.executeUpdate();
				result = count == 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public List<OrderVo> getCartList(OrderVo vo) {
		List<OrderVo> result = new ArrayList<OrderVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = JDBC.getConnection();

			String sql = "select a.no,b.book_no,b.count from purchase a, cart b where a.member_no=b.member_no and a.member_no=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, vo.getMemberNo());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				OrderVo tmp = new OrderVo();
				tmp.setNo(rs.getLong(1));
				tmp.setBookNo(rs.getLong(2));
				tmp.setCount(rs.getLong(3));

				result.add(tmp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;

	}

	public List<OrderVo> getOrderList() {
		List<OrderVo> result = new ArrayList<OrderVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = JDBC.getConnection();

			String sql = "select a.order_no,b.name,a.price,a.place,b.email from purchase a, member b where a.member_no=b.no";

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				OrderVo vo = new OrderVo();
				vo.setOrderNo(rs.getString(1));
				vo.setMemberName(rs.getString(2));
				vo.setPrice(rs.getLong(3));
				vo.setPlace(rs.getString(4));
				vo.setEmail(rs.getString(5));

				result.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public List<OrderVo> getOrderBookList() {
		List<OrderVo> result = new ArrayList<OrderVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = JDBC.getConnection();

			String sql = "select a.book_no,b.title,a.count from order_book a, book b where a.book_no=b.no";

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				OrderVo vo = new OrderVo();
				vo.setBookNo(rs.getLong(1));
				vo.setBookName(rs.getString(2));
				vo.setCount(rs.getLong(3));
				result.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}
}
