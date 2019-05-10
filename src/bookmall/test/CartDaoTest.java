package bookmall.test;

import java.util.List;

import bookmall.dao.CartDao;
import bookmall.vo.CartVo;

public class CartDaoTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		insert(1L, 1L, 5);
		insert(1L, 2L, 10);

	}

	public static void insert(long memberNo, long bookNo, long count) {
		CartVo vo = new CartVo();
		vo.setMemberNo(memberNo);
		vo.setBookNo(bookNo);
		vo.setCount(count);

		new CartDao().insert(vo);
	}

	public static void getList() {
		List<CartVo> result = new CartDao().select();
		for (CartVo vo : result) {
			System.out.println(
					"[책 제목 : " + vo.getBookTitle() + ", 수량:" + vo.getCount() + ", 구매 가격 : " + vo.getBookPrice() + "]");
		}
	}
}
