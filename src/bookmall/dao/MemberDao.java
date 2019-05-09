package bookmall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookmall.jdbc.JDBC;
import bookmall.vo.MemberVo;

public class MemberDao {
	public Boolean insert(MemberVo vo) {
		Boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = JDBC.getConnection();
			String sql = "insert into member values(null,?,?,?,password(?))";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPhonenum());
			pstmt.setString(3, vo.getEmail());
			pstmt.setString(4, vo.getPasswd());

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

	public List<MemberVo> select() {
		List<MemberVo> result = new ArrayList<MemberVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = JDBC.getConnection();

			String sql = "select name,phonenum,email,passwd from member";

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				MemberVo vo = new MemberVo();
				String name = rs.getString(1);
				String phonenum = rs.getString(2);
				String email = rs.getString(3);
				String passwd = rs.getString(4);

				vo.setName(name);
				vo.setPhonenum(phonenum);
				vo.setEmail(email);
				vo.setPasswd(passwd);

				result.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}
}
