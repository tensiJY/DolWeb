package com.sundol.VO;

import java.sql.Date;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

public class FileBoardVO {
	//	데이터를 기억할 변수를 만든다.
	//	엄밀히 말해서 실무에서는 변수를 만들때 public으로 하지 않는 것을 원칙으로 한다.
	//	이 이유는?
	//	==>		데이터는 은닉화 시킨다.
	//		예를 들어		private String title;
	//		사용할 때		setTitle("재목");			을 이용해서 데이터를 기억하고
	//						getTitle();					을 이용해서 데이터를 꺼내야 한다.
	private	String	title;
	private	String	body;
	private	String	pw;
//	private		MultipartFile		files;		//	name 속성이 각각 다른 경우에는 한개만 받도록 한다.
	private	MultipartFile[] 	files;		//	같은 name으로 여러개 오는 경우에는
												//	배열로 받으면 된다.
	private	int		no;
	private	String	writer;
	
	private	Date	wday;
	private	int		hit;
	private	int		cnt;
	private	int		rno;
	
	private	int		oriNo;
	private	int		nowPage;
	private	String	kind;
	
	private	String	oriName;
	private	String	saveName;
	private	long	len;
	private	String	path;
	private	int		download;
	private	Date	uploadDay;
	
	private	String	target;
	private	String	word;
	
	private	int		start;
	private	int		end;
	
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public MultipartFile[] getFiles() {
		return files;
	}
	public void setFiles(MultipartFile[] files) {
		this.files = files;
	}
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
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public int getRno() {
		return rno;
	}
	public void setRno(int rno) {
		this.rno = rno;
	}
	public int getOriNo() {
		return oriNo;
	}
	public void setOriNo(int oriNo) {
		this.oriNo = oriNo;
	}
	public int getNowPage() {
		return nowPage;
	}
	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public String getOriName() {
		return oriName;
	}
	public void setOriName(String oriName) {
		this.oriName = oriName;
	}
	public String getSaveName() {
		return saveName;
	}
	public void setSaveName(String saveName) {
		this.saveName = saveName;
	}
	public long getLen() {
		return len;
	}
	public void setLen(long len) {
		this.len = len;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public int getDownload() {
		return download;
	}
	public void setDownload(int download) {
		this.download = download;
	}
	public Date getUploadDay() {
		return uploadDay;
	}
	public void setUploadDay(Date uploadDay) {
		this.uploadDay = uploadDay;
	}
	
	//	set, get 함수를 만든다.
	
//	public void setLoginId(HttpSession session) {
//		this.writer = (Stirng) session.getAttribute("UID");
//	}
}











