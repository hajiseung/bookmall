package bookmall.vo;

public class MemberVo {
	private long no;
	private String name;
	private String phonenum;
	private String email;
	private String passwd;

	public long getNo() {
		return no;
	}

	public void setNo(long no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhonenum() {
		return phonenum;
	}

	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	@Override
	public String toString() {
		return "MemberVo [no=" + no + ", name=" + name + ", phonenum=" + phonenum + ", email=" + email + ", passwd="
				+ passwd + "]";
	}

}
