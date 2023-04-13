package javaMemo;

import javax.swing.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.util.List;
import java.awt.*;
import java.awt.event.*;//[1]

public class MemoAppView extends JFrame{
	
	Container cp;
	JPanel pN = new JPanel(new GridLayout(2,1));
	JPanel pS = new JPanel();
	
	JPanel pN_sub=new JPanel(new GridLayout(2,1));
	JPanel pN_sub_1=new JPanel();
	JPanel pN_sub_2=new JPanel();
	
	JTextArea ta;
	JLabel lbName, lbTitle, lbDate, lbNo, lbMsg;
	JButton btAdd, btList, btDel, btEdit, btEditEnd, btFind;
	JTextField tfName, tfDate, tfNo, tfMsg;
	
	//외부에서 핸들러 가져오기 위한 controller
	MemoHandler handler;
	
	public MemoAppView() {
		super("::MemoView::");
		cp=this.getContentPane();
		
		ta=new JTextArea("::한줄 메모장::");
		JScrollPane sp=new JScrollPane(ta);
		
		cp.add(pN, "North");
		cp.add(sp, "Center");
		cp.add(pS, "South");
		ta.setEditable(false);//편집불가 option
		
		lbTitle=new JLabel("🦾한줄 메모장👍",JLabel.CENTER);
		pN.add(lbTitle);
		lbTitle.setFont(new Font("Serif",Font.BOLD,28));//font option
		
		pN.add(pN_sub);
		pN_sub.add(pN_sub_1);
		pN_sub.add(pN_sub_2);
		
		pN_sub_1.setLayout(new FlowLayout(FlowLayout.LEFT));
		pN_sub_1.add(lbName=new JLabel("작성자: "));
		pN_sub_1.add(tfName=new JTextField(15));
		pN_sub_1.add(lbDate=new JLabel("작성일: "));
		pN_sub_1.add(tfDate=new JTextField(15));
		pN_sub_1.add(lbNo=new JLabel("글번호: "));
		pN_sub_1.add(tfNo=new JTextField(15));
		
		pN_sub_2.setLayout(new FlowLayout(FlowLayout.LEFT));
		pN_sub_2.add(lbMsg=new JLabel("메모내용: "));
		pN_sub_2.add(tfMsg=new JTextField(40));
		pN_sub_2.add(btAdd=new JButton("글등록"));
		pN_sub_2.add(btList=new JButton("글목록"));
		
		tfDate.setEditable(false);
		tfDate.setForeground(Color.red);
		String date=this.getSysdate();
		tfDate.setText(date);
		tfDate.setFont(new Font("Serif",Font.BOLD,12));
		tfDate.setHorizontalAlignment(JTextField.CENTER);
		
		tfNo.setEditable(false);
		
		pS.add(btDel=new JButton("글삭제"));
		pS.add(btEdit=new JButton("글수정"));
		pS.add(btEditEnd=new JButton("글수정처리"));
		pS.add(btFind=new JButton("글검색"));
		
		//리스너 부착
		handler=new MemoHandler(this);
		btAdd.addActionListener(handler);
		btList.addActionListener(handler);
		btDel.addActionListener(handler);
		btEdit.addActionListener(handler);
		btEditEnd.addActionListener(handler);
		btFind.addActionListener(handler);
		
		//창닫기
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}//생성자
	
	/**핸자 os날짜를 yyyy-MM-dd 포맷의 문자열로 변환하여 반환하는 메서드*/
	public String getSysdate() {
		Date today=new Date();//java.util.Date
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//java.text.SimpleDateFormat
		String str=sdf.format(today);
		return str;
	}//-----
	
	/** 메시지를 대화창에 보여주는 메서드
	 * */
	public void showMessage(String str) {
		JOptionPane.showMessageDialog(this, str);
	}//-----

	/** 입력 필드를 모두 빈문자열로 초기화하는 메서드
	 * */
	public void clearInput() {
		tfNo.setText("");
		tfName.setText("");
		tfMsg.setText("");
		tfName.requestFocus();
	}//-----

	/**전체 메모 글을 TextArea에 출력해주는 메서드
	 * */
	public void showTextArea(List<MemoVO> arr) {
		if(arr==null||arr.size()==0) {
			ta.setText("데이터가 없습니다");
		}else {
			ta.setText("");
			ta.append("======================================================================================================================\n");
			ta.append("\t글번호\t 작성자\t 메모내용\t\t\t\t작성일\n");
			ta.append("======================================================================================================================\n");
			for(MemoVO vo:arr) {
				ta.append("    "+vo.getNo()+"\t"+vo.getName()+"\t"+vo.getMsg()+"\t"+vo.getWdate()+"\n");
			}//for---
			ta.append("======================================================================================================================\n");
		}
	}//----

	public String showInputDialog(String str) {
		String res=JOptionPane.showInputDialog(str);
		return null;
	}//---

	public static void main(String[] args) {//프로그램 main 시작점
		MemoAppView mv=new MemoAppView();
		mv.setSize(900, 600);
		mv.setResizable(false);
		mv.setVisible(true);
	}



	

}
