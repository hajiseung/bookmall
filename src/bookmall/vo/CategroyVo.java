package bookmall.vo;

public class CategroyVo {
	private long no;
	private String genre;
	
	public long getNo() {
		return no;
	}

	public void setNo(long no) {
		this.no = no;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	@Override
	public String toString() {
		return "CategroyVo [no=" + no + ", genre=" + genre + "]";
	}

}
