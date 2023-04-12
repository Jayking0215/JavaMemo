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
	public void actionPerFormed(ActionEvent e) {
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

	private void listMemo() {
		
	}

	private void addMemo() {
		
	}

	private void deleteMemo() {
		
	}

	private void editMemoEnd() {
		
	}

	private void editMemo() {
		
	}

	private void findMemo() {
		
	}
	
	
}
