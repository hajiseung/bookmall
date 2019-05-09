package bookmall.test;

import java.util.List;

import bookmall.dao.CartDao;
import bookmall.vo.CartVo;

public class CartDaoTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		insert("하지승", "1Q84", 5);
//		insert("하지원", "반지의제왕", 10);
		getList();

	}

	public static void insert(String memberName, String bookTitle, long count) {
		CartVo vo = new CartVo();
		vo.setMemberName(memberName);
		vo.setBookTitle(bookTitle);
		vo.setCount(count);

		new CartDao().insert(vo);
	}

	public static void getList() {
		List<CartVo> result = new CartDao().select();
		for (CartVo vo : result) {
			System.out.println("[책 제목 : " + vo.getBookTitle() + ", 수량:" + vo.getCount() + ", 구매 가격 : "
					+ vo.getBookPrice() + "]");
		}
	}
}
