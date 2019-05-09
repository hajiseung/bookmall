package bookmall.main;

import bookmall.test.BookDaoTest;
import bookmall.test.CartDaoTest;
import bookmall.test.CategoryDaoTest;
import bookmall.test.MemberDaoTest;
import bookmall.test.OrderBookDaoTest;
import bookmall.test.PurchaseDaoTest;

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
		PurchaseDaoTest.getList();
		System.out.println("--------------------------------------------------");
		OrderBookDaoTest.getList();
		System.out.println("--------------------------------------------------");
	}

}
