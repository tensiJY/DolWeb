package com.sundol.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.sundol.Service.ShopManagerService;
import com.sundol.UTIL.FileUtil;
import com.sundol.UTIL.SessionUtil;
import com.sundol.VO.ShopManagerVO;

@Controller
public class ShopManagerController {
	@Autowired
	private	ShopManagerService		smS;
	
	/*
	 * 	카테고리 등록 폼 보기
	 */
	@RequestMapping("/Shop/CategoryReg")
	public ModelAndView categoryReg(HttpSession session, @CookieValue(value="AUTO", required=false) Cookie c) {
		//	쿠키가 존재하는지 확인하자.
		boolean	isCookie = false;
		if(c == null) {
			isCookie = false;
		}
		else {
			//	이사람은 자동 로그인을 원하는 사람이다.
			isCookie = true;
		}
		System.out.println("111" + isCookie);
		//	근데 아직 로그인이 안되었으면 자동 로그인을 해주어야겠다.
		if(!SessionUtil.isLogin(session)) {
			//	근데 이사람이 자동 로그인을 요청했으면.... 로그인 처리를 해주어야 겠다.
			if(isCookie == true) {
				String	auto = c.getValue();
				//	이 데이터는 isundol:1234로 되어 있으므로
				int		index = auto.indexOf(":");
				String	id = auto.substring(0, index);
				String	pw = auto.substring(index + 1);
				//	이제 로그인 처리를 하도록 해보자.
				//	로그인 처리를 하도록 리다이렉트 시키자.
				ModelAndView	mv1 = new ModelAndView();
				RedirectView rv = new RedirectView("../Login/LoginProc.sun");
				rv.addStaticAttribute("id", id);
				rv.addStaticAttribute("pw", pw);
				mv1.setView(rv);
				return mv1;
			}
		}
		
		ArrayList	list = smS.getLCategory();
		ModelAndView		mv = new ModelAndView();
		mv.addObject("LLIST", list);
		mv.setViewName("Shop/Manager/CategoryReg");
		return mv;
	}
	
	@RequestMapping("/Shop/LCategoryReg")
	public ModelAndView	lCategoryReg(@RequestParam("lname") String lname) {
		smS.insertLCategory(lname);
		ModelAndView	mv = new ModelAndView();
		RedirectView	rv = new RedirectView("../Shop/CategoryReg.sun");
		mv.setView(rv);
		return mv;
	}
	
	@RequestMapping("/Shop/MCategoryReg")
	public ModelAndView	mCategoryReg(ShopManagerVO vo) {
		smS.insertMCategory(vo);
		ModelAndView	mv = new ModelAndView();
		RedirectView	rv = new RedirectView("../Shop/CategoryReg.sun");
		mv.setView(rv);
		return mv;
	}
	/*
	 * 	중 카테고리를 Ajax 기법으로 요청한 경우 처리할 함수
	 */
	@RequestMapping("/Shop/MCategorySearch")
	@ResponseBody
	public HashMap mCategorySearch(@RequestParam("lcode") String lcode) {
		ArrayList	list = smS.selectMCate(lcode);
		HashMap	map = new HashMap();
		map.put("mcate", list);
		return map;
	}
	/*
	 * 	소카테고리를 Ajax 기법으로 요청한 경우 처리할 함수
	 */
	@RequestMapping("/Shop/SCategorySearch")
	@ResponseBody	/*	뷰없이 Body에 JSON 문서로 응답하도록하는 어노테이션	*/
	public HashMap sCategorySearch(@RequestParam("mcode") String mcode) {
		ArrayList	list = smS.selectSCate(mcode);
		/*
		 * 	알아낸 결과를 JSON 문서로 꾸며서 응답한다.
		 */
		HashMap	map = new HashMap();
		map.put("scate", list);
		return map;
	}

	/*
	 * 	소카테고리 등록 처리함수
	 */
	@RequestMapping("/Shop/SCategoryReg")
	public ModelAndView sCategoryReg(ShopManagerVO vo) {
		smS.insertSCategory(vo);
		ModelAndView	mv = new ModelAndView();
		RedirectView	rv = new RedirectView("../Shop/CategoryReg.sun");
		mv.setView(rv);
		return mv;
	}
	
	/*
	 * 	상품 등록 폼 요청 처리 함수
	 */
	@RequestMapping("/Shop/GoodsRegForm")
	public ModelAndView goodsRegForm() {
		//	할일
		//		상품을 등록하기 위해서는 그 상품이 소속될 카테고리를 지정해야 한다.
		//		근데 카테고리를 대 카테고리를 중심으로해서 찾아가야 하므로
		//		일단 대 카테고리를 불러서 알려주자.
		
		ArrayList	list = smS.getLCategory();
		
		//	뷰를 부르자.
		ModelAndView	mv = new ModelAndView();
		mv.addObject("LCATE", list);
		mv.setViewName("Shop/Manager/GoodsRegForm");
		return mv;
	}
	
	/*
	 * 	상품 등록 요청 처리 함수(임시로 주석을 달아놓고...)
	 * 	완성된 후에는 다시 주석을 변경해야 한다.
	 * 	이런 주석은 Document주석이라 부른다.
	 */
	@RequestMapping("/Shop/GoodsRegProc")
	public ModelAndView goodsRegProc(ShopManagerVO vo, HttpSession session) {
		//	파라메터 받기
		//	이 중에는 업로드를 위한 파일이 존재한다.
		//	그러므로 강제 업로드를 시켜주어야한다.
		
		//	어디에 업로드할지를 지정해 주어야 한다.
		//	프로젝트 안에 만든 폴더는 보이는 위치와 실제 서버가 관리하는 위치가
		//	다르기때문에 서버가 관리하는 실제 위치를 알아내야한다.
		String	path = session.getServletContext().getRealPath("goodsImg");
		//	업로드할 파일의 이름을 알아내야 한다.
		String	fileName = vo.getImg().getOriginalFilename();
		
		//	이 두개가 있으면 언제든지 복사가 가능하고 우리를 이것을
		//	사용하기 위해서 유틸리티 클래스를 만들어 놓았다.
		
		String	saveName = FileUtil.upload(vo.getImg(), fileName, path);
		
		//	이제 마무리로 이 정보를 데이터베이스에 기록하자.
		//	vo 안에는 받는 모든 데이터가 이미 입력되었으므로 이것을 알려주면 되는데....
		//	다만 saveName은 업로드할 때 발생했으므로 아직 vo안에 없다.
		vo.setSaveName(saveName);
		
		smS.insertGoods(vo);

		ModelAndView	mv = new ModelAndView();
		RedirectView	rv = new RedirectView("../Shop/GoodsRegForm.sun");
		mv.setView(rv);
		return mv;
	}
	

}













