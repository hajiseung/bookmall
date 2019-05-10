package bookmall.test;

import java.util.ArrayList;
import java.util.List;

import bookmall.dao.OrderDao;
import bookmall.vo.OrderVo;

public class OrderDaoTest {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		insert("동탄순환대로20길31", 1L);

	}

	public static void insert(String place, long memberNo) {
		OrderVo vo = new OrderVo();

		vo.setPlace(place);
		vo.setMemberNo(memberNo);

		new OrderDao().insertOrder(vo);
	}

	public static void getList() {
		List<OrderVo> result = new ArrayList<OrderVo>();
		result = new OrderDao().getOrderList();
		for (OrderVo vo : result) {
			System.out.println("[주문번호:" + vo.getOrderNo() + ", 주문자:" + vo.getMemberName() + "/" + vo.getEmail()
					+ ", 결제금액:" + vo.getPrice() + ", 배송지:" + vo.getPlace() + "]");
		}
	}

	public static void getOrderBookList() {
		List<OrderVo> result = new ArrayList<OrderVo>();
		result = new OrderDao().getOrderBookList();
		for (OrderVo vo : result) {
			System.out
					.println("[도서번호:" + vo.getBookNo() + ", 도서제목:" + vo.getBookName() + ", 수량:" + vo.getCount() + "]");
		}
	}

}
