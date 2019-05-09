package bookmall.test;

import java.util.List;

import bookmall.dao.CategoryDao;
import bookmall.vo.CategroyVo;

public class CategoryDaoTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		insert("소설");
//		insert("it");
//		insert("수필");
		getList();
	}

	public static void insert(String genre) {
		CategroyVo vo = new CategroyVo();
		vo.setGenre(genre);
		new CategoryDao().insert(vo);
	}

	public static void getList() {
		List<CategroyVo> result = new CategoryDao().select();
		for (CategroyVo vo : result) {
			System.out.println("[장르 : " + vo.getGenre() + "]");
		}
	}
}
