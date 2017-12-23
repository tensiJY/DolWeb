package com.sundol.Service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;

import com.sundol.DAO.AnBoardDAO;
import com.sundol.UTIL.PageUtil;
import com.sundol.VO.AnBoardVO;

public class AnBoardService {
	//	서비스 클래스는 비지니스 로직을 담당하고
	//	혹시 데이터베이스 처리가 필요하면 DAO를 이용할 것이다.
	//	그러므로 DAO 클래스가 필요할 예정인데 이것 역시 <bean> 처리 해 놓았다.
	@Autowired
	public 	AnBoardDAO		abDAO;
	
	//	원글 게시물 등록 처리를 위한 서비스 함수
	public void oriInsert(AnBoardVO		abVO) {
		//	받은 VO 안에는 데이터베이스 처리를 위한 모든 데이터가 준비되어 있다.
		
		//	할일
		//		데이터베이스에 기록하자.
		abDAO.oriInsert(abVO);
	}
	
	//	게시물 목록 꺼내기 처리를 위한 서비스 함수
	public ArrayList getBoardList(int nowPage, PageUtil pInfo) {
		//	질의 명령에서 그 페이지에 필요한 내용만 꺼내는 조건식을 위한
		//	데이터를 준비해야 한다.
		
		//	우리는 그 페이지에 필요한 데이터만 꺼내도록 질의 명령을 만들었으므로
		//	꺼낼 데이터의 시작 번호와 종료 번호를 알려주어야 한다.
		//	이론적인 내용(한 페이지에 10개씩 보이도록 약속했다면)
		//		시작 위치는		1페이지이면		1~
		//							2페이지이면		11~
		//							3페이지이면		21~
		int	start = (nowPage - 1) * (pInfo.listCount) + 1;
		//		종료 위치			시작 페이지 + 9
		//							1페이지이면		~10
		//							2페이지이면		~20
		int	end = start + (pInfo.listCount - 1);
		
		HashMap		map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		
		ArrayList	list = abDAO.getBoardList(map);
		
		return list;
		//	근데 오늘은 아직 실행하면 안된다.
		//	왜냐하면 PageInfo를 new 시키지 않았기 때문이다.
	}
	
	//	총 데이터 개수 구하기 질의 명령 실행 함수
	public int getTotal() {
		//	할일
		//		디비를 이용해서 데이터 개수 구해서 알려준다.
		
		int		total = abDAO.getTotal();
		return total;
	}
	
	//	조회수 증가 여부를 판단할 함수
	public boolean isHitNow(String id, int no) {
		//	반환값
		//		조회수 증가를 해야하면 true를 반환하고
		//		조회수 증가를 하지 말아야 하면 false를 반환할 예정이다.
		//	매개변수
		//		조회수 증가 여부를 판단하기 위해서는 누가 몇번글을 볼예정임을
		//		알아야 한다.
		//	1.	이 사람이 이미 본 글 번호를 알아낸다.
		HashMap	map = abDAO.getHitNO(id);
		if(map == null || map.size() == 0) {
			//	이 사람은 아직도 한번도 글을 본적이 없는 사람이다.
			//	대신에
			//		지금 본 글번호를 인써트 해서 다음에는 이 글을 볼때 조회수 증가를
			//		못하도록 한다.
			HashMap temp = new HashMap();
			temp.put("user", id);
			temp.put("no", "#"+ no + "#");
			abDAO.updateHitNo("anBoard.insertHitNO", temp);
			
			//	증가를 할 수 있도록 해준다.
			return true;
		}
		else {
			//	이 사람은 먼가 본적이 있는 사람이다.
			//	이 사람이 이미 본 글 번호를 알아낸다.
			String	ano = (String) map.get("no");
			//	#10##25##101##7##52#
			//	이중에서 #5#이 있는지를 확이해야 하므로...
			int	test = ano.indexOf("#" + no + "#");
			if(test == -1) {
				//	이 결과는 이번글은 본적이 없다는 말이다.
				//	대신에 이번글도 본것으로 수정해 놓아야 한다.
				HashMap	temp = new HashMap();
				temp.put("user", id);
				temp.put("no", ano + "#" + no + "#");
				abDAO.updateHitNo("anBoard.updateHitNO", temp);
				//	조회수 증가를 할 수 있도록 한다.
				return true;
			}
			else {
				//	이 결과는 이번글도 본적이 있다는 말이다.
				//	조회수 증가를 하지 못하도록 한다.
				return false;
			}
		}
	}
	//	실제 조회수 증가를 처리할 함수
	//	(이 함수는 앞의 함수의 결과에 따라 호출 여부가 판단될 예정이다.)
	public void updateHit(int oriNo) {
		abDAO.updateHit(oriNo);
	}
	
	//	상세보기 처리를 보조할 함수
	public HashMap boardView(int oriNo) {
		//	상세보기를 처리할 경우에는 우리는
		//	상세보기 내용을 꺼내야 하고
		AnBoardVO 	vo = abDAO.getBoardView(oriNo);
		//	답글 목록을 꺼내야 한다.
		//	==>		먼저 그룹을 알아내고
		int		group = abDAO.getGroup(oriNo);
		//	==>		답글을 꺼낸다.
		ArrayList		list = abDAO.getAnList(group);
		
		HashMap	map = new HashMap();
		map.put("VIEW", vo);
		map.put("LIST", list);
		
		return map;
	}
	
	//	원글의 정보를 알아내는 서비스 함수
	public AnBoardVO getOriInfo(int oriNo) {
		AnBoardVO 	vo = abDAO.getBoardView(oriNo);
		return vo;
	}
	
	//	답글 입력 처리를 위한 함수
	public void anIsert(AnBoardVO vo) {
		
		//	오더 조절까지 마무리(1588)
		HashMap	map = new HashMap();
		map.put("bgroup", vo.bgroup);
		map.put("border", vo.border);
		
		abDAO.updateOrder(map);
		
		//	댓글을 입력하고
		abDAO.insertAn(vo);

	}
	
	//	검색 처리 서비스 함수
	public ArrayList	boardSearch(AnBoardVO aVO) {
		//	검색할라면 vo 중에서 kind, word만 Map으로 만들어 주기로 했으므로
		HashMap	map = new HashMap();
		map.put("kind", aVO.kind);
		map.put("word", aVO.word);
		
		ArrayList	list = abDAO.boardSearch(map);
		return list;
	}
	
	//	수정을 위한 원글 내용 꺼내기 처리 함수
	public AnBoardVO getModifyView(int oriNo) {
		AnBoardVO 	vo = abDAO.getBoardView(oriNo);
		return vo;
	}

	//	수정 처리를 위한 함수
	public int updateBoard(AnBoardVO aVO) {
		return abDAO.updateBoard(aVO);
	}
	
	//	삭제 처리를 위함 함수
	public int deleteBoard(AnBoardVO aVO) {
		return abDAO.deleteBoard(aVO);
	}
}










