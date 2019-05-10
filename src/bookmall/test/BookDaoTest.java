package bookmall.test;

import java.util.List;

import bookmall.dao.BookDao;
import bookmall.vo.BookVo;

public class BookDaoTest {

	public static void main(String[] args) {
		insert("Servlet/Jsp", 20000L, 1L);
		insert("1Q84", 16000L, 2L);
		insert("반지의제왕", 18000L, 3L);

	}

	public static void insert(String title, long price, Long categoryNo) {
		BookVo vo = new BookVo();
		vo.setTitle(title);
		vo.setPrice(price);
		vo.setCategoryNo(categoryNo);
		new BookDao().insert(vo);
	}

	public static void getList() {
		List<BookVo> result = new BookDao().select();
		for (BookVo vo : result) {
			System.out.println("[책 제목 : " + vo.getTitle() + ", 책 가격 : " + vo.getPrice() + "원]");
		}
	}
}
