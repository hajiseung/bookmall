package bookmall.main;

import bookmall.test.BookDaoTest;
import bookmall.test.CartDaoTest;
import bookmall.test.CategoryDaoTest;
import bookmall.test.MemberDaoTest;
import bookmall.test.OrderDaoTest;

public class MainApp {

	public static void main(String[] args) {
		MemberDaoTest.getList();
		System.out.println("--------------------------------------------------");
		CategoryDaoTest.getList();
		System.out.println("--------------------------------------------------");
		BookDaoTest.getList();
		System.out.println("--------------------------------------------------");
		CartDaoTest.getList();
		System.out.println("--------------------------------------------------");
		OrderDaoTest.getList();
		System.out.println("--------------------------------------------------");
		OrderDaoTest.getOrderBookList();
		System.out.println("--------------------------------------------------");
	}

}
