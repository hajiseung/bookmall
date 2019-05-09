package bookmall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import bookmall.vo.BookVo;
import bookmall.jdbc.JDBC;

public class BookDao {
	public Boolean insert(BookVo vo) {
		Boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = JDBC.getConnection();
			String sql = "insert into book values(null,?,?,(select no from category where genre=?))";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getTitle());
			pstmt.setLong(2, vo.getPrice());
			pstmt.setString(3, vo.getCategory());

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

	public List<BookVo> select() {
		List<BookVo> result = new ArrayList<BookVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = JDBC.getConnection();

			String sql = "select a.title,a.price, b.genre from book a, category b where a.category_no=b.no";

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				BookVo vo = new BookVo();
				vo.setTitle(rs.getString(1));
				vo.setPrice(rs.getLong(2));
				vo.setCategory(rs.getString(3));

				result.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}
}
