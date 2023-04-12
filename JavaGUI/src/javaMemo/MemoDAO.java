package javaMemo;

import java.sql.*;
import java.util.*;
import common.util.*;

import common.util.*;

public class MemoDAO {
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	/**
	 * 한줄 메모장에 insert문을 수행하는 메서드
	 * */ //MemoVO 자체가 1개의 행
	
	public int insertMemo(MemoVO memo) throws SQLException {
		try {
			con=DBUtil.getCon();
			StringBuilder buf=new StringBuilder("INSERT INTO MEMO(no,name,msg,wdate)")
							.append(" VALUES(MEMO_SEQ.NEXTVAL,?,?,SYSDATE");
			String sql=buf.toString();
			
			ps=con.prepareStatement(sql);
			ps.setString(1, memo.getName());
			ps.setString(2, memo.getMsg());
			
			int n=ps.executeUpdate();
			return n;
		}finally {
			close();
		}
	}//----
	
	/**전체 메모글을 가져오는 메서드
	 * */ //memoVO자체가 행 1줄
	public List<MemoVO> listMemo() throws SQLException{
		try {
			con=DBUtil.getCon();
			StringBuilder buf=new StringBuilder("SELECT RPAD(no,10,' ') no, RPAD(name,16,' ') name,")
							.append(" RPAD(msg,100,' '), wdate FROM MEMO ORDER BY wdate DESC");
			String sql=buf.toString();
			
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			
			List<MemoVO> arr=makeList(rs);
			return arr;
		}finally {
			close();
		}
	}//----
	
	private List<MemoVO> makeList(ResultSet rs) throws SQLException {
		List<MemoVO> arr=new ArrayList<>();
		while(rs.next()) {
			int no=rs.getInt("no");
			String name=rs.getString("name");
			String msg=rs.getString("msg");
			java.sql.Date wdate=rs.getDate("wdate");
			//오버로드
			MemoVO memo=new MemoVO(no,name,msg,wdate);
			arr.add(memo);
		}
		return arr;
	}//----
	
	/** 글번호로(PK)로 메모글을 가져오는 메서드 
	 * */
	public MemoVO selectMemo(int no) throws SQLException{
		try {
			con=DBUtil.getCon();
			String sql="SELECT no,name,msg,wdate FROM MEMO WHERE no=?";
			ps=con.prepareStatement(sql);
			ps.setInt(1, no);
			rs=ps.executeQuery();
			List<MemoVO> arr=makeList(rs);
			if(arr!=null && arr.size()==1) {
				return arr.get(0);
			}
			return null;//해당글이 없을 경우
		}finally {
			close();
		}
	}//----
	
	/** keyword로 메모 글 내용을 검색하는 메서드
	 * */
	public List<MemoVO> findMemo(String keyword) throws SQLException{
		try {
			con=DBUtil.getCon();
			StringBuilder buf=new StringBuilder("SELECT RPAD(no,10,' ')no,")
						.append(" RPAD(name,16,' ')name, RPAD(msg,100,' ')msg, wdate FROM MEMO")
						.append(" WHERE msg LIKE ?");
			String sql=buf.toString();
			ps=con.prepareStatement(sql);
			ps.setString(1,  "%"+keyword+"%");
			rs=ps.executeQuery();
			List<MemoVO> arr=makeList(rs);
			return arr;
		}finally {
			close();
		}
	}//----
	
	/**메모 글 내용,작성자를 수정하는 메서드
	 * */
	public int updateMemo(MemoVO vo) throws SQLException{
		try {
			con=DBUtil.getCon();
			String sql="UPDATE MEMO SET name=?, msg=? WHERE no=?";
			ps=con.prepareStatement(sql);
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getMsg());
			ps.setInt(3, vo.getNo());
			
			return ps.executeUpdate();
		}finally {
			close();
		}
	}//----
	
	/**글번호로 메모글을 삭제하는 메서드
	 * */
	public int deleteMemo(int no) throws SQLException{
		try {
			con=DBUtil.getCon();
			String sql="DELETE FROM MEMO WHERE no=?";
			ps=con.prepareStatement(sql);
			ps.setInt(1, no);
			return ps.executeUpdate();
		}finally {
			close();
		}
	}//----

	private void close() {
		try {
			if(rs!=null)rs.close();
			if(ps!=null)ps.close();
			if(con!=null)con.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		
	}
}
