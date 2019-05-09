package bookmall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookmall.jdbc.JDBC;
import bookmall.vo.CategroyVo;

public class CategoryDao {
	public Boolean insert(CategroyVo vo) {
		Boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = JDBC.getConnection();
			String sql = "insert into category values(null,?)";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getGenre());

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

	public List<CategroyVo> select() {
		List<CategroyVo> result = new ArrayList<CategroyVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = JDBC.getConnection();

			String sql = "select genre from category";

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				CategroyVo vo = new CategroyVo();
				String genre = rs.getString(1);

				vo.setGenre(genre);

				result.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}
}
