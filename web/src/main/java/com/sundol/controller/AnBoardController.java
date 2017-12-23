package com.sundol.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.sundol.Service.AnBoardService;
import com.sundol.UTIL.PageUtil;
import com.sundol.VO.AnBoardVO;

@Controller
@RequestMapping("/AnBoard")
public class AnBoardController {
	//	앞에서와 같은 개념으로 컨트롤러는 주로 서비스 클래스를 이용해서
	//	작업을 진행할 예정이다.
	
	@Autowired
	public	AnBoardService		abService;
	
	//	목록 보기 요청 처리 함수
	@RequestMapping("/BoardList")
	public ModelAndView boardList
		(@RequestParam(value="nowPage", defaultValue="1") int nowPage) {
		/*
		String strPage = req.getParameter("nowPage");
		int		nowPage = 0;
		if(strPage == null || strPage.length() == 0) {
			nowPage = 1;
		}
		else {
			nowPage = Integer.parseInt(strPage);
		}
		*/
		
		//	할일
		//	파라메터 받고
		//	데이터베이스에서 목록 뽑아오고
		//		1.	총 데이터 개수를 구해서 페이지 정보를 만들곻
		int	total = abService.getTotal();
		PageUtil	pInfo = new PageUtil(nowPage, total);
		
		//		2.	페이지 정보를 이용해서 그 페이지에 필요한 목록만 구해서
		ArrayList	list = abService.getBoardList(nowPage, pInfo);
		//	뷰에 모델로 알려주고
		
		
		//	뷰를 부른다.
		ModelAndView	mv = new ModelAndView();
		//	모델 입력.......		함수		addObject("키값", 데이터);
		mv.addObject("PINFO", pInfo);
		mv.addObject("LIST", list);
		mv.setViewName("AnBoard/BoardList");
		return mv;
	}
	
	@RequestMapping("/WriteForm")
	public ModelAndView writeForm() {
		//	할일
		//		필요하다면... 세션 검사를 해서 세션이 없으면 글쓰기를 못하도록 처리하고...
		//		(이 과정은 어느정도 게시판이 완성되면... 추가할 예정)
		//		하지만 테스트 할때는 반드시 로그인 하고 테스트 하세요....
		
		//		뷰를 호출한다.
		ModelAndView	mv = new ModelAndView();
		mv.setViewName("AnBoard/WriteForm");
		return mv;
	}
	
	@RequestMapping("/WriteProc")
	public ModelAndView writeProc(AnBoardVO aVo, HttpSession session){
		//	할일
		//		파라메터 받고
		//		==>		받은 파라메터는 title, body, password, tags를 받았다.
		//				하지만 데이터베이스에는 위의 4가지 외에
		//				글쓴이도 같이 등록해야 한다.
		
		//		글쓴이는 세션에서 로그인한 사람의 id를 구해서 처리하도록 약속했다.
		//		문제	VO 클래스 안에 글쓴이 정보를 알려주어야 한다.
		//				이 작업을 어디서 해야하는가?
		//				결론	컨트롤러, 서비스 중 아무곳에서나 하면 된다.
		
		//		우리는 컨트롤러에서 하기로 하자.
		String	id = (String) session.getAttribute("UID");
		aVo.writer = id;
		
		//		디비에 입력하고
		abService.oriInsert(aVo);
		//		뷰를 부른다.
		RedirectView	rv = new RedirectView("../AnBoard/BoardList.sun");
		ModelAndView	mv = new ModelAndView();
		mv.setView(rv);
		return mv;
	}
	
	//	조회수 증가 요청을 처리할 함수
	@RequestMapping("/HitProc")
	public ModelAndView hitProc(AnBoardVO aVO, HttpSession session) {
		//	할일
		//		파라메터 받고
		//		조회수 처리하고
		//		조회수 처리 여부를 판단한다.
		//	조회수 판단을 하기 위해서는 누가 본 글인지를 세션에서 알아야 한다.
		String id = (String) session.getAttribute("UID");
		//	누가 몇번글을 볼 예정인지 알려주면 조회수 여부를 판단 해 준다.
		boolean	isHit = abService.isHitNow(id, aVO.oriNo);
		if(isHit == true) {
			//	조회수 증가 함수를 호출한다.
			abService.updateHit(aVO.oriNo);
		}
		//		뷰를 부른다.
		RedirectView	rv = new RedirectView("../AnBoard/BoardView.sun");
		rv.addStaticAttribute("nowPage", aVO.nowPage);
		rv.addStaticAttribute("oriNo", aVO.oriNo);
		ModelAndView	mv = new ModelAndView();
		mv.setView(rv);
		return mv;
	}
	
	//	상세보기 요청을 처리할 컨트롤러 함수
	@RequestMapping("/BoardView")
	public ModelAndView boardView(AnBoardVO aVO) {
		//	할일
		//		파라메터 받고
		//		상세보기 내용 구하고
		//		이 원글에 달린 댓글을 구하고
		HashMap	map = abService.boardView(aVO.oriNo);
		//		뷰를 부른다.
		ModelAndView	mv = new ModelAndView();
		//	Map으로 묶인것을 분리해서 모델로 전달해도 된다.
//		mv.addObject("VIEW", map.get("VIEW"));
//		mv.addObject("LIST", map.get("LIST"));

		mv.addObject("MAP", map);
		//	요청 설계를 할 때 nowPage는 이 컨트롤러서 필요해서 받은것이 아니고
		//	다음을 위해서 릴레이 시킨 파라메터 이므로
		//	잊지말고 다음에 반드시 전달해야 한다.
		mv.addObject("nowPage", aVO.nowPage);
		mv.setViewName("AnBoard/BoardView");
		return mv;
	}
	
	//	답글 쓰기를 위한 폼 요청 처리 함수
	@RequestMapping("/AnWriteForm")
	public ModelAndView AnWriteForm(AnBoardVO aVO) {
		//	파라메터 받고
		
		//	필요하다면 세션 점검하고(로그인을 한 사람만 쓸 수 있으니까)
		
		//	뷰를 부른다.
		ModelAndView	mv = new ModelAndView();
		mv.addObject("oriNo", aVO.oriNo);
		mv.addObject("nowPage", aVO.nowPage);
		mv.setViewName("AnBoard/AnWriteForm");
		return mv;
	}
	
	//	답글 쓰기 요청 처리 함수
	@RequestMapping("/AnWriteProc")
	public ModelAndView AnWriteProc(AnBoardVO aVO, HttpSession session) {
		//	할일
		//		파라메터 받고
		//		VO를 이용해서 파라메터를 받는 경우에는
		//		파라메터를 받을 수 있는 setXxx()가 VO 클래스에 존재하는지 확인해야 한다.
		
		//		원글 정보 얻어내고
		//		(왜냐하면 답글에 사용한 Group, Step, Order는 원글의 이용해서 만들어야 한다.)
		//			우리는 상세보기 질의 명령을 재 사용할 예정이다.
		AnBoardVO	vo = abService.getOriInfo(aVO.oriNo);
		
		//		이 정보를 이용해서 필요한 정보를 생성하고
		//		필요한 정보는 Group, Step, Order이다.
		int		newGroup = vo.bgroup;
		int		newStep = vo.bstep + 1;
		int		newOrder = vo.border + 1;

		//		데이터베이스 저장한다.
		//		파라메터로 받은 VO 안에는 클라이언트가 알려준 데이터만 존재한다.
		//		제목, 본문, 비번, 태그는 존재한다.
		//		하지만 글쓴이, 그룹, 스텝, 오더는 존재하지 않는다.
		//	부족한 데이터를 채워서 보내야 한다.
		String	id = (String) session.getAttribute("UID");
		aVO.writer = id;
		aVO.bgroup = newGroup;
		aVO.bstep = newStep;
		aVO.border = newOrder;

		//	결론
		//		특정 질의를 실행할 경우에는
		//		그 질의 명령에 필요한 데이터가 완벽하게 준비가 되었는지 
		//		분명히 확인해야 한다.
		abService.anIsert(aVO);
		
		//	이제 aVO의 no에는 지금 입력한 답글의 번호가 입력되어 있다.
		//		오더 정리를 한다.
		
		
		//	뷰를 부른다.
		RedirectView	rv = new RedirectView();
		rv.addStaticAttribute("nowPage", aVO.nowPage);
		rv.addStaticAttribute("bgroup", aVO.bgroup);
		rv.addStaticAttribute("oriNo", aVO.oriNo);
		//	no 변수는 질의 명렬을 실행할 때 <selectKey에 의해서 결과가 입력어진다.
//		rv.addStaticAttribute("oriNo", aVO.no);
		rv.setUrl("../AnBoard/BoardView.sun");
		ModelAndView	mv = new ModelAndView();
		mv.setView(rv);
		return mv;
	}
	
	//	검색 요청 처리함수
	@RequestMapping("BoardSearch")
	public ModelAndView boardSearch(AnBoardVO aVO) {
		//	파라메터 받고

		//	검색하고
		ArrayList	list = abService.boardSearch(aVO);
		//	결과본다.
		ModelAndView	mv = new ModelAndView();
		mv.addObject("LIST", list);
		mv.setViewName("AnBoard/BoardSearch");
		return mv;
	}
	
	//	수정하기 폼 요청 처리 함수
	@RequestMapping("/ModifyForm")
	public ModelAndView modifyForm(AnBoardVO aVO) {
		//	할일
		//		파라메터 받고
		
		//		수정하기를 위해서 현재 글을 알려준다.(디비에서 알아낸다.)
		//		현재글 내용은 상세보기에서 현재 글 내용을 알아내는 처리를 해놓았다.
		//		이것을 이용하면 될것이다.
		AnBoardVO vo = abService.getModifyView(aVO.oriNo);
		
		//		뷰를 부른다.
		ModelAndView mv = new ModelAndView();
		mv.addObject("VIEW", vo);
		mv.addObject("nowPage", aVO.nowPage);
		mv.setViewName("AnBoard/ModifyForm");
		return mv;
	}
	
	//	수정하기 요청 처리함수
	@RequestMapping("/ModifyProc")
	public ModelAndView modifyProc(AnBoardVO aVO) {
		//	할일
		//		파라메터 받기
		
		//		수정 처리하기
		int		count = abService.updateBoard(aVO);
//		if(count == 0) {
//			//	비번이 달라서 수정 안됨
//		}
//		else {
//			//	비번이 같아서 수정 됨
//		}
		
		//		뷰 부르기
		ModelAndView		mv = new ModelAndView();
		mv.addObject("nowPage", aVO.nowPage);
		mv.addObject("oriNo", aVO.oriNo);
		mv.addObject("count", count);
		mv.addObject("from", "update");
		mv.setViewName("AnBoard/ImsiView");
		//	임시뷰에서는 수정 여부를 알려주고
		//	곧장 상세보기로 가야하므로
		//	상세보기 필요한 oriNo, nowPage를 알려줄 필요가 있다.
		//	임시뷰에서는 수정 여부를 이용해서 alert 창을 출력해야 하므로
		//	수정 여부를 판단하는 어떤 결과값을 알려줄 필요가 있다.
		return mv;
	}
	
	//	삭제 요청 처리함수
	@RequestMapping("/DeleteProc")
	public ModelAndView deleteProc(AnBoardVO aVO) {
		
		int		count = abService.deleteBoard(aVO);
		
		ModelAndView		mv = new ModelAndView();
		mv.addObject("nowPage", aVO.nowPage);
		mv.addObject("oriNo", aVO.oriNo);
		mv.addObject("count", count);
		mv.addObject("from", "delete");
		mv.setViewName("AnBoard/ImsiView");
		return mv;
	}
}
















