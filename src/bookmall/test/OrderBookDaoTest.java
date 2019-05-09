package bookmall.test;

import java.util.List;

import bookmall.dao.OrderBookDao;
import bookmall.vo.OrderBookVo;

public class OrderBookDaoTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		insert("하지원");
		getList();
	}

	public static void insert(String memberName) {
		OrderBookVo vo = new OrderBookVo();

		vo.setMemberName(memberName);

		new OrderBookDao().insertOrderBook(vo);
	}

	public static void getList() {

		List<OrderBookVo> result = new OrderBookDao().select();
		for (OrderBookVo vo : result) {
			System.out
					.println("[책 번호:" + vo.getBookNo() + ", 책 제목:" + vo.getBookTitle() + ", 수량:" + vo.getCount() + "]");
		}
	}
}
