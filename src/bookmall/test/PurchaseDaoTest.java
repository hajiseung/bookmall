package bookmall.test;

import java.util.ArrayList;
import java.util.List;

import bookmall.dao.OrderDao;
import bookmall.vo.BookVo;
import bookmall.vo.PurchaseVo;

public class PurchaseDaoTest {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		insert("동탄순환대로20길31", "하지승");
//		insert("야탑동", "하지원");
		getList();

	}

	public static void insert(String place, String memberName) {
		PurchaseVo vo = new PurchaseVo();

		vo.setPlace(place);
		vo.setMemberName(memberName);

		new OrderDao().insertOrder(vo);
	}

	public static void getList() {
		List<PurchaseVo> result = new ArrayList<PurchaseVo>();
		result = new OrderDao().getOrderList();
		for (PurchaseVo vo : result) {
			System.out.println("[주문번호:" + vo.getOrderNo() + ", 주문자:" + vo.getMemberName() + "/" + vo.getEmail()
					+ ", 가격:" + vo.getPrice() + ", 장소:" + vo.getPlace() + "]");
		}
	}
}
