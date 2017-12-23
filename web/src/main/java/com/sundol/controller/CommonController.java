package com.sundol.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.sundol.UTIL.FileUtil;

@Controller
public class CommonController {

	/*
	 * 	이미지 업로드 폼 요청 처리 함수
	 */
	@RequestMapping("/Common/ImageUploadForm")
	public ModelAndView imageUploadForm() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("Common/ImageUploadForm");
		return mv;
	}
	
	/*
	 * 	이미지 업로드 처리 함수
	 */
	@RequestMapping("/Common/ImageUploadProc")
	public ModelAndView imageUploadProc(@RequestParam("files") MultipartFile files, HttpSession session) {
		//	할일
		//		첨부된 파일을 받아서
		//		서버에 저장하고
		String path = session.getServletContext().getRealPath("seupload");
		System.out.println(path);
		String	name = files.getOriginalFilename();
		
		String saveName = FileUtil.upload(files, name, path);
		
		//		뷰를 부른다.
		//		==>		뷰를 부르면 이 뷰에서는 업로드된 결과를 스마트에디터에
		//				알려줄 예정이다.
		
		ModelAndView		mv = new ModelAndView();
		mv.addObject("PATH", path);
		mv.addObject("NAME", saveName);
		mv.setViewName("Common/ImageUploadProc");
		return mv;
		
	}
}











