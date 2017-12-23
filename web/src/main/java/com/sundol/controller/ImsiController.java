package com.sundol.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ImsiController {
	/*
	 * 글쓰기 폼 요청 처리 함수
	 */
	@RequestMapping("/Imsi/ImsiWriteForm")
	public ModelAndView imsiWriteForm() {
		//	오늘은 할일 없고 뷰만 부르자
		ModelAndView	mv = new ModelAndView();
		mv.setViewName("Imsi/ImsiWriteForm");
		return mv;
	}
}
