package bookmall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookmall.jdbc.JDBC;
import bookmall.vo.CartVo;
import bookmall.vo.OrderBookVo;

public class OrderBookDao {
	public Boolean insertOrderBook(OrderBookVo vo) {
		Boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = JDBC.getConnection();
			String sql = "insert into order_book values((select a.no from purchase a,member b where a.member_no=b.no and b.name=?),(select a.no from purchase a, member b where a.member_no=b.no and b.name=?), (select a.count from cart a, member b where a.member_no = b.no and b.name=?))";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getMemberName());
			pstmt.setString(2, vo.getMemberName());
			pstmt.setString(3, vo.getMemberName());

			int count = pstmt.executeUpdate();
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

	public List<OrderBookVo> select() {
		List<OrderBookVo> result = new ArrayList<OrderBookVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = JDBC.getConnection();

			String sql = "select a.book_no,b.title,a.count from order_book a, book b where a.book_no=b.no";

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				OrderBookVo vo = new OrderBookVo();
				vo.setBookNo(rs.getLong(1));
				vo.setBookTitle(rs.getString(2));
				vo.setCount(rs.getLong(3));
				result.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}
}
