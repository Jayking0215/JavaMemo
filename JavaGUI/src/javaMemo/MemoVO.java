package javaMemo;

import java.sql.Date;

public class MemoVO {
	//캡슐화
	private int no;
	private String name;
	private String msg;
	private java.sql.Date wdate;
	
	//오버로드
	public MemoVO() {
		this(0,null,null,null);
	}

	//constructor
	public MemoVO(int no, String name, String msg, Date wdate) {
		super();
		this.no = no;
		this.name = name;
		this.msg = msg;
		this.wdate = wdate;
	}

	//setter, getter
	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public java.sql.Date getWdate() {
		return wdate;
	}

	public void setWdate(java.sql.Date wdate) {
		this.wdate = wdate;
	}
}//
