package javaMemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MemoDAO {
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	/**
	 * 한줄 메모장에 insert문을 수행하는 메서드(CRUD중 C)
	 * */ //MemoVO 자체가 1개의 행
	
	public void insertMemo(MemoVO memo) {
		
		
	}
}
