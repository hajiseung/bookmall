package bookmall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookmall.jdbc.JDBC;
import bookmall.vo.MemberVo;
import bookmall.vo.PurchaseVo;

public class OrderDao {
	public Boolean insertOrder(PurchaseVo vo) {
		Boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = JDBC.getConnection();
			String sql = "insert into purchase values(null,(SELECT concat ((select date_format(now(),'%Y%m%d') as dateformat), '-' ,(SELECT LPAD((select (count(*)+1) from purchase p), 4, '0') as count))),(select a.count*b.price price from cart a,book b,member c where a.book_no=b.no and a.member_no=c.no and c.name=?),?,(select a.no from member a where a.name=?));";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getMemberName());
			pstmt.setString(2, vo.getPlace());
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

	public List<PurchaseVo> getOrderList() {
		List<PurchaseVo> result = new ArrayList<PurchaseVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = JDBC.getConnection();

			String sql = "select a.order_no,b.name,a.price,a.place,b.email from purchase a, member b where a.member_no=b.no";

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				PurchaseVo vo = new PurchaseVo();
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
}
