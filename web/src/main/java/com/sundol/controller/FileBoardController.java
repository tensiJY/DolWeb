package com.sundol.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import 	org.springframework.stereotype.Controller;
import 	org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.sundol.Service.FileBoardService;
import com.sundol.UTIL.FileUtil;
import com.sundol.UTIL.PageUtil;
import com.sundol.UTIL.SessionUtil;
import com.sundol.UTIL.StringUtil;
import com.sundol.VO.FileBoardVO;

@Controller
//	이 컨트롤러는 파일 업로드용 컨트롤러 역활만 하도록 약속했므로..
//	파일 업로드 요청의 공통 사항을 미리 밝혀 주어서 다음 부터는 변경된 내용만
//	처리하도록 하자.
@RequestMapping("/FileBoard")
public class FileBoardController {
	//	컨트롤러에서는 서비스 클래스를 이용해서 처리하기로 했으므로....
	@Autowired
	private 	FileBoardService		fService;
	
	
	
	/*
	 * 게시물 목록 보기 요청 처리 함수
	 */
	@RequestMapping("/BoardList") 
	public ModelAndView	boardList(@RequestParam(value="nowPage", defaultValue="1") int nowPage) {
		//	할일	
		//		파라메터 받고
		//		게시물 꺼내고
		PageUtil	pInfo = fService.getPageInfo(nowPage);
		ArrayList	list = fService.getBoardList(pInfo);
		
		//		뷰를 부른다.
		ModelAndView	mv = new ModelAndView();
		//		페이지 이동기능을 할때 역시 같은 파라메터를 전달해야 한다.
		//		nowPage를 릴레이 시킨것이다.
		mv.addObject("PINFO", pInfo);
		mv.addObject("LIST", list);
		mv.setViewName("FileBoard/BoardList");
		return mv;
	}
	
	/*
	 * 	글쓰기 폼 요청 처리 함수
	 */
	@RequestMapping("/WriteForm")
	public ModelAndView	writeForm(HttpSession session) {
		//	글쓰기는 로그인을 한 사람만 쓰도록 해보자.
		//	그러기 위해서는 권한 검사를 해주어야 한다.
		
		boolean	isLogin = SessionUtil.isLogin(session);
		if(!isLogin) {
			//	로그인하도록 로그인 화면으로 보내도록 하자.
			ModelAndView	mv = new ModelAndView();
			RedirectView	rv = new RedirectView("../Login/LoginForm.sun");

			//	이처럼 무슨일을 하다가 로그인 화면으로 강제로 나가면....
			//	로그인을 했을 경우 자동으로 원래하던일로 복귀할 수 있나요?
			//	이 경우에는 현재 어느 상태에서 왔는지 자신의 실행될 URL을 보낸다.
//			rv.addStaticAttribute("URL", "../FileBoard/WriteForm.sun");
			//	로그인 처리에서는 이것을 파라메터로 받아 놓았다가
			//	로그인 처리가 성공하면 받아놓은 URL로 Redirect 시키면
			//	원래하던 일로 다시 자동 복귀할 것이다.
			
			//	로그인 컨트롤러에서
			/*	public ModelAndView login(URL을 파라메터로 받아놓았다가) {
			 * 
			 * 	로그인에 성공하면
			 * 
			 * 	RedirectView	rv = new RedirectView(URL);
			*/
			
			mv.setView(rv);
			return mv;
		}
		//	로그인에 성공한 경우이므로....
		//	뷰를 보여준다.
		
		ModelAndView	mv = new ModelAndView();
		mv.setViewName("FileBoard/WriteForm");
		return mv;
	}
	
	/*
	 * 	글쓰기 처리 요청 처리 함수
	 */
	@RequestMapping("/WriteProc")
	public ModelAndView writeProc(FileBoardVO fVO, HttpSession session) {
		//	혹시라도 권한이 없는 사람이라면 글쓰기 자체를 못하도록 방지해야 한다.
		boolean	isLogin = SessionUtil.isLogin(session);
		if(!isLogin) {
			//	로그인 하도록 조치한다.
			ModelAndView	mv = new ModelAndView();
			RedirectView	rv = new RedirectView("../Login/LoginForm.sun");
			mv.setView(rv);
			return mv;
		}
		//	로그인은 한 사람이라면 그사람의 아이디를 알아야 게시물에 등록을 할것이다.
		String	id = SessionUtil.getLoginId(session);
		
		
		//	할일
		//		파라메터 받기
		//		파일 첨부가 있는 경우
		//			1.	VO
		//			2.	@RequestParam		로 받을 수 있다.
		//		참고
		//			fileupload.jar 파일을 이용할 때 첨부된 파일을 받는 방법
		//			첨부된 파일은 MultipartFile 이라는 클래스로 받아야 한다.
		
		//	참고	이처럼 첨부 파일의 파라메터를 받으면....
		//			첨부 파일이 임시 저장장소에 저장되어진다.
		//			(물론 임시 저장 장소는 개발자가 선택할 수 있다. 환경 설정 부분에서...)
		//			하지만 임시 저장 장소를 사용자가 지정했다고 해서
		//			파일 업로드가 되는 것은 아니다.
		//			왜냐하면 임시 저장된 파일은 이 컨트롤러의 실행이 종료되면
		//			자동으로 삭제된다.
		
		//			이 컨트롤러가 마치기 전에 반드시 개발자가
		//			임시 저장 장소에 저장된 파일을 원하는 위치에 강제로 복사해 놓아야 한다.
		//			그래야 실제로 업로드가 완료된다.
		
		String	path = "D:\\SpringUpload";
		//	파일을 저장하는 위치는 그 파일의 목적에 따라서 달라진다.
		//	즉, 오직 다운로드 목적으로 사용할 경우라면 개발자가 특정폴더를 정하면 된다.
		//	첨부된 파일을 WEB에서 사용할 목적이라면 이것은 프로젝트 안에 만들어야 한다.
		//	방법
		//		1.	웹 프로젝트 안에 폴더를 만든다.
		//		2.	프로젝트에서 보이는 폴더와 실제 웹 서버가 관리하는 위치는 다르므로
//		String	path1 = session.getServletContext().getRealPath("image");
		//		를 이용해서 실제 서버가 사용하는 위치를 알아내서 사용해야 한다.
		
		
		
		//	복사할 파일의 이름은 임시 저장된 파일의 이름과 동일하게 한다.
		
		//	한개만 업로드된 경우 처리 방법
//		String	name = fVO.getFiles()[0].getOriginalFilename();

		//	여러개가 한번에 업로드 된 경우 처리하는 방법
		int		len = fVO.getFiles().length;
		//	==>		첨부된 파일의 개수만큼 반복하면서 업로드 시킬 부분
		//			파일의 개수가 몇개인지 모르므로 첨부 파일의 정보를 기억할 변수를
		//			미리 만들어 놓자.
		ArrayList	fileInfoList = new ArrayList();
		for(int i = 0; i < len; i++) {
			String	name = fVO.getFiles()[i].getOriginalFilename();
			//	만약 사용자가 첨부 파일 양식을 만들어 놓고 파일을 첨부하지 않으면
			//	파일의 이름이 없으므로 에러가 발생한다.
			//	이 경우에는 파일의 원래 이름이 없기 때문에
			//	이것을 이용해서 처리하면 된다.
			
			//	이 기법은 파일 첨부가 선택인 경우에도 사용할 수 있다.
			if(StringUtil.isNull(name)) {
				continue;
			}
			String saveName = FileUtil.upload(fVO.getFiles()[i], name, path);
			//	이제 한 파일이 업로드가 끝났으므로 업로드된 파일의 정보를 알아내자
			//	그래야 나중에 이 정보를 이용해서 디비에 기록할 것이므로...
			
			//	한 파일의 정보를 기억할 변수
			HashMap	map = new HashMap();
			map.put("oriName", fVO.getFiles()[i].getOriginalFilename());
			map.put("saveName", saveName);
			map.put("len", fVO.getFiles()[i].getSize());
			map.put("path", path);
			
			fileInfoList.add(map);
		}
		//	===>		위의 과정을 거치면서 클라이언트가 준 파일은 업로드가 끝났다.
		//	클라이언트가 보내준 게시물 정보와 파일의 정보를 디비에 기록해야 한다.

		//	1.	원글의 정보를 기록한다.
		
		//	2.	파일의 정보를 기록한다.
		
		//	여기서도 주의
		//		fVO안에 insert 할 데이터가 준비되어 있는지 확인하자.
		//	항상 권한이 필요한 경우에는 권한 점검을 하도록 하자.
		//	여기서는 로그인 권한 검사를 해서 처리하도록 한다.
		//	로그인한 아이디가 글쓴이로 등록되어야 한다.
		fVO.setWriter(id);
		
		fService.insertBoard(fVO, fileInfoList);
		//	뷰를 부른다.
		ModelAndView	mv = new ModelAndView();
		RedirectView	rv = new RedirectView("../FileBoard/BoardList.sun");
		mv.setView(rv);
		return mv;
	}
	
	/*
	 * 	상세보기 요청 처리 함수
	 */
	@RequestMapping("/BoardView")
	public ModelAndView boardView(FileBoardVO fVO, HttpSession session) {
		//	원래는 조회수 증가를 해주어야 하는데...	이것은 다음날 하고
		
		//	숙제
		//	조회수 증가를 데이터베이스를 이용해서 처리하는 방법을
		//	완료한다.
		
		//	상세보기를 위해서 디비에서 데이터를 꺼낸다.
		//	이때 상세보기 데이터
		//	이전글, 다음글 데이터
		
		//	검색대상의 이전, 다음은 검색 단어를 알려주어야 한다.
		//	검색 단어는 세션이 가지고 있도록 약속했었다.
		
		HashMap map1 = new HashMap(); 
		map1.put("oriNo", fVO.getOriNo());
		map1.put("target", (String) session.getAttribute("target"));
		map1.put("word", (String) session.getAttribute("word"));
		
		HashMap	map = fService.getBoardView(fVO.getOriNo(), fVO.getKind(), map1);
		
		//	뷰를 호출한다.
		ModelAndView		mv = new ModelAndView();
		mv.addObject("nowPage", fVO.getNowPage());
		mv.addObject("kind", fVO.getKind());
		//	나는 받은 데이터를 분리해서 줌으로써 뷰에서 좀더 편하게 작업을 할 수 있도록 
		//	조치하자.
		mv.addObject("VIEW", map.get("VIEW"));
		mv.addObject("FILE", map.get("FILE"));
		mv.addObject("PRENEXT", map.get("PRENEXT"));
		mv.setViewName("FileBoard/BoardView");
		return mv;
	}
		
	/*
	 *	파일 다운로드 요청 처리함수 
	 */
	@RequestMapping("/FileDownload")
	public ModelAndView fileDownload(@RequestParam(value="oriNo") int oriNo) {
		//	할일
		//		다운로드해줄 파일의 정보를 디비에서 꺼내서
		FileBoardVO	fVO = fService.getDownloadInfo(oriNo);
		
		//		이제 이 정보를 이용해서 다운로드를 실시할 예정이다.
		//		이 작업이 바로 사용자 뷰가 내부적으로 처리할 내용이다.
		//		뷰에게 Model 형식을 취해서 다운로드 받을 파일의 정보를 알려주면 된다.
		
		//		그 정보를 어떻게 알려줄것 인가는 개발자 몫인데...
		//		우리는 당연히 파일 다운로드가 목적이므로
		//		파일의 정보를 File 클래스로 알려주면 좋을것 같다.
		File		downFile = new File(fVO.getPath() + "\\" + fVO.getSaveName());
		
		//		뷰에게 알려준다.
		return new ModelAndView("download", "downloadFile", downFile);
		//		파라메터?
		//		1		사용할 뷰 선택(bean 처리한 사용자 뷰를 이용한다.)
		//				==>		bean 처리할 때 사용한 id값을 입력한다.
		//		2		전달할 파라메터(정보)에 사용할 키값
		//		3		전달할 파라메터(정보)
	}

	/*
	 * 	검색 요청 처리 함수
	 */
	@RequestMapping("/BoardSearch")
	public ModelAndView boardSearch(FileBoardVO fVO, HttpSession session) {
		//	검색 결과 페이지 이동 기능을 필요하면
		//	같은 /FileBoard/BoardSearch.sun 요청을 해야할 것이고
		//	그러면 같은 파라메터를 전달해야 한다.
		//	필요한 파라메터는 릴레이 시켜야 한다.
	
		//	릴레이될 데이터가 많은 경우는 릴레이 작업이 조금 복잡해진다.
		//	이런 경우 처리하는 다른 방식이 있다.
		//		세션을 이용하는 방법
		//		원리	처음 검색을 시도할 때 릴레이할 데이터를 세션에 입력해 놓는다.
		//				다음 검색(페이지 이동)을 시도할 때는 세션에서
		//				필요한 데이터를 받아서 사용하면 된다.

		//	혹시 받은 데이터중에 검색 대상이 존재하니를 알아낸다.
		//	만약 검색 대상이 존재하면 보나마나 처음 검색을 시도하는 것이다.
		//	이 데이터가 없으면 이것은 페이지 이동 기능을 시도한 경우일 것이다.
		boolean	isTarget = StringUtil.isNull(fVO.getTarget());
		if(isTarget == true) {
			//	비어있으면....
			fVO.setTarget((String) session.getAttribute("target"));
			fVO.setWord((String) session.getAttribute("word"));
		}
		//	★
		//	다음을 위해서 세션에 필요한 데이터를 입력해 놓아야 한다.
		session.setAttribute("target", fVO.getTarget());
		session.setAttribute("word", fVO.getWord());
		
		//	데이터베이스에서 검색하자.
		PageUtil	pInfo = fService.getSearchTotal(fVO);
		ArrayList	list = fService.getSearch(fVO, pInfo);
		
		//	뷰를 주세요
		ModelAndView	mv = new ModelAndView();
		mv.addObject("PINFO", pInfo);
		mv.addObject("LIST", list);
		mv.setViewName("FileBoard/BoardSearch");
	
		return mv;
	}
}
