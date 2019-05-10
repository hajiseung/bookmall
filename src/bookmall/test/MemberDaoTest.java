package bookmall.test;

import java.util.List;

import bookmall.dao.MemberDao;
import bookmall.vo.MemberVo;

public class MemberDaoTest {

	public static void main(String[] args) {
		insert("하지승", "010-1111-1111", "gkwltmd@naver.com", "1234");
		insert("하지원", "010-2222-2222", "gkwldnjs@naver.com", "5678");
	}

	public static void insert(String name, String phonenum, String email, String passwd) {
		MemberVo vo = new MemberVo();
		vo.setName(name);
		vo.setPhonenum(phonenum);
		vo.setEmail(email);
		vo.setPasswd(passwd);

		new MemberDao().insert(vo);
	}

	public static void getList() {
		List<MemberVo> result = new MemberDao().select();
		for (MemberVo vo : result) {
			System.out.println("[고객 이름:" + vo.getName() + ", 고객 전화번호:" + vo.getPhonenum() + ", 고객 이메일:" + vo.getEmail()
					+ ", 비밀번호:" + vo.getPasswd() + "]");
		}
	}
}
