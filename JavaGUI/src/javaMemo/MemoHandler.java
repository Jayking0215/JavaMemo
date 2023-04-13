package javaMemo;

import java.awt.event.*;
import java.sql.*;
import java.util.*;

public class MemoHandler implements ActionListener {
	MemoAppView app;
	MemoDAO dao=new MemoDAO();
	
	public MemoHandler(MemoAppView mav) {
		this.app=mav;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj=e.getSource();
		if(obj==app.btAdd) {//글 추가
			addMemo();
			app.clearInput();//전체 글목록 비우기
			listMemo();//전체목록 출력 메서드
		}else if(obj==app.btList) {//글 목록보기
			listMemo();
		}else if(obj==app.btDel) {//글 삭제
			deleteMemo();
			listMemo();
		}else if(obj==app.btEdit) {//글 수정
			editMemo();
		}else if(obj==app.btEditEnd) {//글 수정처리
			editMemoEnd();
			app.clearInput();
			listMemo();
		}else if(obj==app.btFind) {//글 내용 검색(키워드 검색)
			findMemo();
		}
	}//-----
	
	private void findMemo() {
		String keyword=app.showInputDialog("검색할 키워드를 입력하세요");
		if(keyword==null) return;
		if(keyword.trim().isEmpty()) {
			app.showMessage(keyword+"로 검색한 결과 해당 글은 없습니다.");
			return;
		}
	}//-----
	
	private void editMemo() {
		String noStr=app.showInputDialog("수정할 글 번호를 입력하세요.");
		if(noStr==null) return;
		try {
			MemoVO vo=dao.selectMemo(Integer.parseInt(noStr.trim()));
			if(vo==null) {
				app.showInputDialog(noStr+"번 글은 존재하지 않습니다.");
				return;
			}
		}catch(SQLException e) {
			app.showMessage(e.getMessage());
		}
	}//-----
	
	private void editMemoEnd() {
		String noStr=app.tfNo.getText();
		String name=app.tfName.getText();
		String msg=app.tfMsg.getText();
		if(noStr==null||name==null||noStr.trim().isEmpty()||name.trim().isEmpty()) {
			app.showMessage("작성자와 글번호는 반드시 입력해야 합니다.");
			return;
		}
		int no=Integer.parseInt(noStr.trim());
		MemoVO memo=new MemoVO(no,name,msg,null);
		try {
			int n=dao.deleteMemo(Integer.parseInt(noStr.trim()));
			String res=(n>0)?"글 삭제 성공!!":"글 삭제 실패!!";
			app.showMessage(res);
		}catch(SQLException e) {
			app.showInputDialog(e.getMessage());
		}
	}//-----

	private void deleteMemo() {
		String noStr=app.showInputDialog("삭제할 글 번호를 입력하세요.");
		try {
			int n=dao.deleteMemo(Integer.parseInt(noStr.trim()));
			String res=(n>0)? "글 삭제 성공!": "글 삭제 실해!";
			app.showMessage(res);
		}catch(SQLException e) {
			app.showInputDialog(e.getMessage());
		}
	}//-----
	
	private void addMemo() {
		String name=app.tfName.getText();
		String msg=app.tfName.getText();
		if(name==null||msg==null||name.trim().isEmpty()) {
			app.showMessage("작성자와 메모내용을 입력해주세요");
			app.tfName.requestFocus();
			return;
		}
		MemoVO memo=new MemoVO(0,name,msg,null);
		try {
			int result=dao.insertMemo(memo);
			if(result>0) {
				app.setTitle("글 등록 성공!!!");
			}else {
				app.showMessage("글 등록 실패!!!");
			}
		}catch(SQLException e) {
			app.showMessage(e.getMessage());
		}
	}//----
	
	private void listMemo() {
		try {
			List<MemoVO> arr=dao.listMemo();
			app.showTextArea(arr);
		}catch(SQLException e){
			app.showMessage(e.getMessage());
		}
	}//----
}
