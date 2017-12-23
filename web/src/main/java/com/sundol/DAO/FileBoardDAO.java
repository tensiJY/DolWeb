package com.sundol.DAO;

import java.util.ArrayList;
import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import 	org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import com.sundol.VO.FileBoardVO;

public class FileBoardDAO extends SqlSessionDaoSupport {

	@Autowired
	private SqlSessionTemplate		sSession;
	//	이 DAO는 보나마나 서비스에서 사용할 예정이다.
	//	당근 서비스에서 이 클래스를 new 시켜서 사용하면 되는데....
	
	//	서비스에서도 이 DAO를 미리 만들어 놓고 사용하도록 조치해 놓자.
	//	xml 파일이 이 클래스를 <bean> 처리 해놓으면 된다.
	
	/*
	 * 	게시물 등록 질의 실행 함수
	 */
	public void insertBoard(FileBoardVO fVO) {
		sSession.insert("fileBoard.boardInsert", fVO);
	}
	
	/*
	 * 파일 정보 등록 질의 실행 함수
	 */
	public void insertFileInfo(HashMap map) {
		sSession.insert("fileBoard.fileInfoInsert", map);
	}
	
	/*
	 * 	총 데이터 개수 구하기 질의 실행 함수
	 */
	public int getTotal() {
		return (Integer) sSession.selectOne("fileBoard.getTotal");
	}
	
	/*
	 * 	게시물 목록 구하기 질의 실행 함수
	 */
	public ArrayList getBoardList(HashMap map) {
		return (ArrayList) sSession.selectList("fileBoard.getBoardList", map);
	}
	/*
	 * 	게시물 상세보기 질의 실행 함수
	 */
	public FileBoardVO	getBoardView(int oriNo) {
		return (FileBoardVO) sSession.selectOne("fileBoard.boardView", oriNo);
	}
	/*
	 * 게시물 첨부파일 검색 질의 실행 함수
	 */
	public ArrayList	getUploadFile(int oriNo) {
		return (ArrayList) sSession.selectList("fileBoard.boardFile", oriNo);
	}
	/*
	 * 이전글 다음글 질의 실행함수
	 */
	public HashMap getPreNext(int oriNo, String kind, HashMap map) {
		//	이 함수 하나가지고 2가지 질의 명령을 수행할 예정이다.
		//		kind	에 list, search가 오도록 약속해서
		//	이것을 이용해서 둘 중 한 질의만 수행하도록 한다.
		if(kind.equals("list")) {
			return (HashMap) sSession.selectOne("fileBoard.prenext", oriNo);
		}
		else {
			return (HashMap) sSession.selectOne("fileBoard.searchprenext", map);
		}
	}
	
	/*
	 * 	다운로드 파일의 정보 검색 질의 실행 함수
	 */
	public FileBoardVO	getDownloadInfo(int oriNo) {
		return (FileBoardVO) sSession.selectOne("fileBoard.downloadInfo", oriNo);
	}
	
	/*
	 * 	검색 결과 개수 구하기 질의 실행 함수
	 */
	public int getSearchTotal(HashMap map) {
		return (Integer) sSession.selectOne("fileBoard.searchTotal", map);
	}
	/*
	 * 	검색 질의 실행 함수
	 */
	public ArrayList	getSearch(FileBoardVO fVO) {
		return (ArrayList) sSession.selectList("fileBoard.searchList", fVO);
	}
	
	
}




