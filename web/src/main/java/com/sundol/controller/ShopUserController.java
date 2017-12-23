package com.sundol.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sundol.Service.ShopManagerService;
import com.sundol.UTIL.StringUtil;

@Controller
public class ShopUserController {
	
	@Autowired
	private	ShopManagerService smS;
	
	/*
	 * 상품 목록보기 요청 처리 함수
	 */
	@RequestMapping("/Shop/GoodsList")
	public ModelAndView goodsList(@RequestParam(value="code", required=false) String code) {
		//	문제
		//		이 코드값은 사용자가 선택한 카테고리 코드값이다.
		//		이 코드값은 안올수도 있고(전체보기)
		//		이 코드값은 대, 중, 소카테고리가 올 수 있다.
		System.out.println("나실행되니");
		//		이 경우를 모두 판단하여 다음 카테고리와 검색할 상품을 정해야 한다.
		String	searchGoods = "";
		//		상품을 검색할 때 4가지 경우에 맞게 상품 검색에 대한 조건식을 
		//		기억할 변수
		//		예>			전체검색		WHERE		gi_Category LIKE '____________'
		//					대카테고리	WHERE		gi_Category LIKE '0001________'
		//					중카테고리	WHERE		gi_Category LIKE '00010001____'
		
		//	다음 검색을 위해서 카테고리를 보여줄 필요가 있겠다.
		//	대카테고리는 4글자로 이루어진것을 검색하면 된다.
		//	중카테고리는 선택한 대카테고리를 알아야 검색이 가능하다.
		//	소카테고리는 선택한 중카테고리를 알아야 검색이 가능하다.
		
		String	lcate = "";
		String	mcate = "";		//	선택한 대카테고리와 중카테고리를 기억할 변수
		
		//	우리는 
		if(StringUtil.isNull(code)) {
			searchGoods = "____________";
			//	아무것도 선택하지 않았을 경우에는 어떤 기준으로 중, 소카테고리를
			//	보여줄 것인가?		여러분이 선택하도록 한다.

			//	우리는 강제로 가장 처음 중, 소카테고리를 보여주도록 하고자 한다.
			lcate = "0001";
			mcate = "00010001";
		}
		else if(code.length() == 4) {
			searchGoods = code + "________";
			lcate = code;
			
			//	중카테고리는 대카테고리를 이용해서 검색할 수 있는데...
			//	소카테고리는 무엇을 보여줄지 결정해야 한다.
			
			//	우리는 강제로 가장 처음 소카테고리를 보여주도록 하고자 한다.
			mcate = lcate + "0001";
			
		}
		else if(code.length() == 8) {
			searchGoods = code + "____";
			//	소카테고리는 선택한 중 카테고리의 소카테고리를 검색할 수 있다.
			mcate = code;
			
			//	중카테고리를 무엇을 보여줄 것인가?
			
			//	우리는 선택한 중카테고리가 소속된 대카테고리에 대한 모든
			//	중 카테고리를 보여주고 싶다.
			lcate = code.substring(0, 4);
			
		}
		else {
			searchGoods = code;
			
			//	우리는 중, 소카테고리는 선택한 소카테고리가 속한
			//	내용을 이용하고자 한다.
			lcate = code.substring(0, 4);
			mcate = code.substring(0, 8);
		}
		
		//	위의 가공한 내용을 이용해서 메뉴 구성에 필요한 카테고리를 
		//	데이터베이스에서 꺼내보자.
		ArrayList	lcode = smS.getLCategory();
		ArrayList	mcode = smS.selectMCate(lcate);
		ArrayList	scode = smS.selectSCate(mcate);
		
		//	이것을 이용해서 사용자가 원하는 상품 목록을 데이터베이스에서 꺼내온다.
		ArrayList	list = smS.getGoodsList(searchGoods);
		
		//	뷰를 부른다.
		ModelAndView mv = new ModelAndView();
		mv.addObject("GLIST", list);
		mv.addObject("LCODE", lcode);
		mv.addObject("MCODE", mcode);
		mv.addObject("SCODE", scode);
		mv.setViewName("Shop/User/GoodsList");
		return mv;
	}
}













