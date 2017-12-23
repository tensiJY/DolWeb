package com.sundol.DAO;

import java.util.ArrayList;
import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import com.sundol.VO.AnBoardVO;

public class AnBoardDAO extends SqlSessionDaoSupport {
	//	이 클래스는 질의 명령을 실행하기 위한 클래스이다.
	//	이 클래스에서 가장 중요한 것은 컨넥션 풀에 있는 컨넥션을 이용하는 것이다.
	//	참고
	//		myBatis에선 컨넥션을 "세션"이라고 부른다.
	//	세션을 관리하는 변수를 만들어야 하는데... 우리는 이미 myBatis 등록을 할때
	//	세션 관리를 위한 SqlSessionTemplate를 <bean> 처리해 놓았으므로
	//	DI 기법으로 불러오면 될 것이다.
	@Autowired
	public SqlSessionTemplate		sSession;
	
	//	필요한 질의 명령을 수행할 함수를 만들자.
	
	//		원글 게시물 등록을 위한 질의 명령 수행 함수
	public void oriInsert(AnBoardVO abVO) {
		sSession.insert("anBoard.oriInsert", abVO);
		//	모든 질의 실행함수의 첫번째 파라메터는 실행 질의 명령을 찾아올
		//	질의 코드값을 입력한다.
		//		질의 코드값		SQL파일의 namespace.질의의 id
		
		//	두번째 파라메터 부터는 질의 실행에 필요한 데이터를 입력하는 부분이다.
		
		
		//	진짜 재미있는것은
		//	myBatis를 사용하면 스테이트먼트도 자동 생성되고
		//							사용후 close도 자동 처리된다.
	}
	
	//	게시물 목록 꺼내기 질의 실행 명령
	public ArrayList getBoardList(HashMap map) {
		//	myBatis의 SQL에서 resultType은 한줄만 고려해서 설정을 하지만...
		//	DAO는 실제로 실행한 결과를 받기 때문에 여려줄을 고려해서 받아야 한다.
		//	만약 여려줄이 나올 염려가 있다면 ArrayList로 받아야 한다.
		
		//	()안에는 이 질의 명령을 수행할 때 필요한 데이터를 지정하는 것이므로
		//	우리는 데이터를 Map으로 알려주기로 했으므로....
		
		ArrayList	list = (ArrayList) sSession.selectList("anBoard.boardList", map);
		return list;
	}
	
	//	총 데이터 개수 구하기 질의 명령 실행 함수
	public int getTotal() {
		return sSession.selectOne("anBoard.getTotal");
		
		//	참고	DAO는 반드시 특정 질의를 위한 함수가 따로 존재해야 하는 것은 아니다.
		//			한개의 함수를 이용해서 다른 여러개의 질의를 실행할 수도 있다.
		
		/*	예>		public void ????(String code) {
		 * 				sSesseion.?????(code);
		 * 			}
		 * 
		 * 			실행시
		 * 			sDao.?????("anBoard.getTotal")		<==		총 데이터 개수 구하기
		 * 			sDao.?????("anBoard.getTotal1")	<==		다른 질의 명령이 실행 될 수 있다.
		 * 		의 방식을 이용해서 실행할 질의 코드를 다르게 알려주면
		 * 		그때 그때마다 다른 질의 명령을 실행하도록 할 수도 있다.
		*/	
	}
	
	//	이미 본 글 번호를 조회할 질의 명령을 실행할 함수
	public HashMap getHitNO(String user) {
		return sSession.selectOne("anBoard.getHitNO", user);
		//	주의
		//		1.	파라메터가 Map이면 #{내용}이 Map의 키값이 되어야 한다.
		//		2.	파라메터가 VO이면 #{내용}이 setXxx 함수의 이름이 되어야 한다.
		//		3.	파라메터가 일반 데이터이면 #{내용}이 데이터의 변수이름이 되어야 한다.
	}
	
	//	나는 지금부터 하나의 함수를 이용해서 두개의 질의 명령을 실행하도록 
	//	만들어볼 예정이다.
	
	public void updateHitNo(String code, HashMap map) {
		if(code.equals("anBoard.insertHitNO")) {
			sSession.insert(code, map);
		}
		else {
			sSession.update(code, map);
		}
		
		//	서비스 쪽에서 이 함수를 실행할 때
		//	sService.updateHitNo("anBoard.isertHitNO", map)	을 하면
		//		insert 질의 명령이 실행될 것이고
		//	sService.updateHitNo("anBoard.updateHitNO", map)	을 하면
		//		update 질의 명령이 실행될 것이다.
	}
	//	실제 조회수를 증가할 함수
	public void updateHit(int oriNo) {
		sSession.update("anBoard.updateHit", oriNo);
	}
	
	//	상세보기 질의를 실행할 함수
	public AnBoardVO getBoardView(int oriNo) {
		//	파라메터가 일반 데이터이면 #{키값}의 내용과 변수의 이름이 동일해야한다.
		//	★★★
		//	질의 명령의 resultType은 질의 실행 결과의 한줄만 가지고 생각해라
		//	DAO의 반환값은 실제 나올 수 있는 경우를 대비해서 처리해야 한다.
		return sSession.selectOne("anBoard.boardView", oriNo);
	}
	//	그 글에 대한 그룹을 알아내기 위한 질의 명령 실행 함수
	public int getGroup(int oriNo) {
		return (Integer) sSession.selectOne("anBoard.getGroup", oriNo);
	}
	
	//	답글을 꺼낼 질의를 실행할 함수
	public ArrayList getAnList(int bgroup) {
		return (ArrayList) sSession.selectList("anBoard.anList", bgroup);
	}
	//	댓글 입력 질의 실행 함수
	public void insertAn(AnBoardVO vo) {
		sSession.insert("anBoard.anInsert", vo);
	}
	
	//	댓글 입력시 오더 조절 실행 함수
	public void updateOrder(HashMap map) {
		sSession.update("anBoard.orderSet", map);
	}
	
	//	검색 질의 실행 함수
	public ArrayList boardSearch(HashMap map) {
		return (ArrayList) sSession.selectList("anBoard.search", map);
	}
	
	//	수정 질의 실행 함수
	public int updateBoard(AnBoardVO abVO) {
		//	update, delete를 수행하면 그 명령에 의해서 변경된 데이터의 개수를
		//	알 수 있다.
		//	이 개수가 0이면 변경되지 않은것이고
		//	이 개수가 1이면 변경된 것이다.
		return sSession.update("anBoard.updateBoard", abVO);
	}
	
	//	삭제 질의 실행함수
	public int deleteBoard(AnBoardVO abVO) {
		return sSession.update("anBoard.delete", abVO);
	}
}









