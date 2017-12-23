package com.sundol.VO;

import java.sql.Date;
import java.util.StringTokenizer;

/**
 * 	답변형 게시판 처리를 위한 VO 클래스이다.
 * 
 * 	@author 	김명환
 *	@date		2017.10.19
 */
public class AnBoardVO {
	public String	title;
	public	String	body;
	public	String	password;
	public	String	tags;
	public	int		no;
	public String	writer;
	public	Date	wday;
	public	int		hit;
	public	int		rno;
	//	조회수 증가를 위한 파라메터 준비
	public	int		nowPage;
	public	int		oriNo;
	//	SELECT 질의를 이용한 결과를 VO로 받기 위해서는
	//	꺼낸 데이터를 기억할 setXxx()가 준비되어야 한다.
	public	int		bgroup;
	public	int		bstep;
	public	int		border;
	//	VO가 준비할 변수들
	//		파라메터로 전달된 데이터를 기억할 변수(setXxx())
	//		SELECT 질의 명령의 결과를 기억할 변수(setXxx())
	public	String		kind;
	public	String		word;
	//	이 함수는 Vo의 데이터를 뷰에서 가지고 가기 위해서 필요한 것이다.
	public String getTitle() {
		return title;
	}
	//	이 함수는 뷰에서 Vo에게 데이터를 주기 위해서 필요한 것이다.
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		//	이 함수는 뷰에서 사용자가 입력한 데이터를 알려주는 함수이다.
		//	tags라는 변수에 사용자가 입력한 데이터를 알려주면	
		//	사용자가 입력한 데이터 즉 tags 변수가 기억한 데이터는
		//		자바 스프링, 웹		의 형식으로 입력되어 있을 것이다.
		//	우리는 #자바#스프링#웹		의 형식으로 기억해 놓아아 한다.
		
		StringTokenizer	token = new StringTokenizer(tags, " ,");
		//		자바 스프링, 웹		==>		자바 		스프링		웹
		String	realData = "";
		while(token.hasMoreTokens()) {
			String	data = token.nextToken();
			realData = realData + "#" + data;
		}
		
		//	진짜 중요한 역활은	
		//	사용자가 전달한 데이터가 실제 사용할 데이터의
		//	형태가 다르면 setXxx 함수에서 가공해서 사용할 수 있다.
		
		
		
		this.tags = realData;
		//	자신이 가지고 있는 전역 변수에 그 데이터를 그대로 입력해 놓는다.
	}
	//	SQL 파일에서 데이터를 ?에 채우기 위한 함수
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public Date getWday() {
		return wday;
	}
	public void setWday(Date wday) {
		this.wday = wday;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public int getRno() {
		return rno;
	}
	public void setRno(int rno) {
		this.rno = rno;
	}
	public int getNowPage() {
		return nowPage;
	}
	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}
	public int getOriNo() {
		return oriNo;
	}
	public void setOriNo(int oriNo) {
		this.oriNo = oriNo;
	}
	public int getBgroup() {
		return bgroup;
	}
	public void setBgroup(int bgroup) {
		this.bgroup = bgroup;
	}
	public int getBstep() {
		return bstep;
	}
	public void setBstep(int bstep) {
		this.bstep = bstep;
	}
	public int getBorder() {
		return border;
	}
	public void setBorder(int border) {
		this.border = border;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	
	
}
