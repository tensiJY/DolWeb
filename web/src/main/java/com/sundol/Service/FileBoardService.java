package com.sundol.Service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;

import com.sundol.DAO.FileBoardDAO;
import com.sundol.UTIL.PageUtil;
import com.sundol.VO.FileBoardVO;

public class FileBoardService {
	//	보나마나 이 서비스는 주로 DAO를 이용해서 DB 처릴 할 예정이다.
	@Autowired
	private	FileBoardDAO		fDAO;
	
	/*
	 * 	게시물 등록 처리 함수
	 */
	public void insertBoard(FileBoardVO fVO, ArrayList list) {
		//	일반 게시물 등록
		fDAO.insertBoard(fVO);
		
		//	첨부파일 등록을 하자.
		//	list가 등록될 첨부 파일의 정보를 다 가지고 있다.
		int	size = list.size();		//	첨부 파일의 개수
		for(int i = 0; i < size; i++) {
			HashMap map = (HashMap) list.get(i);
			//	문제는 이 안에 정보는 등록할 모든 정보를 가지고 있어야 한다.
			//	근데 우리는 이 안에 원글의 번호는 없다.
			map.put("oriNo", fVO.getNo());
			fDAO.insertFileInfo(map);
		}
	}
	
	/*
	 * 	총 데이터 개수 처리 함수
	 * 	==>		총 데이터 개수를 구하는 목적은 페이지 정보를 알기 위함이다.
	 * 			아예 여기서 페이지 정보까지 만들어서 제공하자.
	 * 
	 * 			페이지 정보를 구하기 위해서는 현재 페이지, 총 데이터 개수가 필요하므로
	 * 			그중에서 현재 알고있는 nowPage는 알려주도록 한다.
	 */
	public PageUtil getPageInfo(int nowPage) {
		int	total = fDAO.getTotal();
		
		PageUtil pInfo = new PageUtil(nowPage, total);
		return pInfo;
	}
	
	/*
	 * 	목록 구하기 처리 함수
	 * ==>		우리는 현재 페이지에서 필요한 목록만 꺼내기로 했으므로
	 * 			목록 구하기를 위해서는 페이지 정보가 필요하다.
	 */
	public ArrayList	getBoardList(PageUtil pInfo) {
		//	구하고자하는 목록의 시작 위치와 마지막 위치를 알아내자.
		int		start = (pInfo.nowPage - 1) * pInfo.listCount + 1;
		int		end = pInfo.nowPage * pInfo.listCount;
		
		HashMap	map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		
		ArrayList	list = fDAO.getBoardList(map);
		return list;
	}
	
	/*
	 * 	상세보기 관련 데이터 검색을 위한 서비스 함수
	 */
	public HashMap getBoardView(int oriNo, String kind, HashMap map1) {
		FileBoardVO	vo = fDAO.getBoardView(oriNo);
		
		ArrayList		list = fDAO.getUploadFile(oriNo);
		
		HashMap		map = fDAO.getPreNext(oriNo, kind, map1);
		
		HashMap		rMap = new HashMap();
		rMap.put("VIEW", vo);
		rMap.put("FILE", list);
		rMap.put("PRENEXT", map);
		
		return rMap;
	}
	
	/*
	 * 	다운로드 파일 정보 처리를 위한 함수
	 */
	public FileBoardVO getDownloadInfo(int oriNo) {
		return fDAO.getDownloadInfo(oriNo);
	}
	/*
	 * 	원래 서비스 클래스는 상위 인터페이스를 만들고
	 * 	그 인터페이스를 상속 받아서 만들도록 권장하고 있다.
	 * 	(모든 서비스 클래스는 다형성 구현을 하도록 하고 있다.)
	 * 
	 * 	스프링 3이상에 오면 굳이 다형성 구현을 하지 않아도 내부적으로 처리를 해주게 된다.
	 */
	
	/*
	 * 	검색 개수 구하기 처리 함수
	 */
	public PageUtil getSearchTotal(FileBoardVO fVO) {
		//	검색 개수 구하기는 target, word를 알아야 한다.
		
		//	검색 개수는 HashMap으로 파라메터를 알려주기로 했으므로....
		HashMap	map = new HashMap();
		map.put("target", fVO.getTarget());
		map.put("word", fVO.getWord());
		
		int		total = fDAO.getSearchTotal(map);
		
		PageUtil	pInfo = new PageUtil(fVO.getNowPage(), total);
		
		return pInfo;
	}
	/*
	 * 	검색 처리 함수
	 */
	public ArrayList	getSearch(FileBoardVO fVO, PageUtil pInfo) {
		//	검색을 하기 위해서는 target, word, start, end가 필요하다.
		//	start, end는 현재 페이지를 이용해서 만들어 주어야 한다.
		
		int		start = (pInfo.nowPage - 1) * pInfo.listCount + 1;
		int		end = pInfo.nowPage * pInfo.listCount;
		
		//	이 정보를 VO를 이용해서 질의를 하기로 약속했다.
		fVO.setStart(start);
		fVO.setEnd(end);

		ArrayList	list = fDAO.getSearch(fVO);
		return list;
	}
}
