package com.sundol.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.sundol.Service.LoginService;
import com.sundol.UTIL.StringUtil;

@Controller
public class LoginController {
	
	@Autowired
	public LoginService 	lService;
	
	@RequestMapping("/Login/LoginForm")
	public String loginForm() {
		return "Login/LoginForm";
	}
	
	@RequestMapping("/Login/LoginProc")
	public ModelAndView loginProc(HttpServletRequest req, HttpSession session, HttpServletResponse resp) {
		String		id = req.getParameter("id");
		String		pw = req.getParameter("pw");
		//	자동 로그인을 요구했는지 알아보자.
		String		auto = req.getParameter("auto");
		//	체크상자는 선택한 경우에만 데이터가 넘어오게 된다.
		//	선택하지 않으면 아예 null로 남게 된다.
		int result = lService.loginProc(id, pw);
		
		if(result == 1) {
			session.setAttribute("UID", id);
			//	이 사람이 자동 로그인을 요청했는지 알아보자.
			if(!StringUtil.isNull(auto)) {
				//	이 사람의 아이디와 비번을 쿠키에 담아서 저장해 놓자..
				//	그래야 다음에 다시 접속할 때 이 사람의 쿠키를 이용해서 처리해 보자.
				Cookie 	c = new Cookie("AUTO", id + ":" + pw);
				c.setMaxAge(60 * 60 * 24 * 1000);
				resp.addCookie(c);
			}
		}
		else {
		}
		RedirectView	rv = new RedirectView("../Login/LoginForm.sun");
		
		ModelAndView	mv = new ModelAndView();
		mv.setView(rv);
		
		return mv;
	}
	
	@RequestMapping("/Login/Logout")
	public ModelAndView logout(HttpSession session, RedirectView rv, ModelAndView mv) {
		//	����
		//		������ �����.
		session.removeAttribute("UID");
		//		�並 �θ���.
		rv.setUrl("../Login/LoginForm.sun");
		mv.setView(rv);
		return mv;
	}
}









