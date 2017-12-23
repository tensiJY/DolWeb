package com.sundol.UTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/*
 * 	이 클래스는 인터셉터 되었을때 컨트롤러보다 먼저 실행할 클래스이다.
 * 	이 클래스에서 반환값에 따라서 실제 컨트롤러의 실행 여부를 판단하게 된다.
 * 
 * 	클래스 만드는 규칙
 * 	1.	반드시 HandlerInterceptorAdapter 클래스를 상속 받아서 만든다.
 * 
 * 	2.	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
 * 		와
 * 		public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
 * 
 * 		를 오버라이드 한다.
 */
public class InterceptorUtil extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		//	이 함수는 컨트롤러가 실행되기 전에 실행할 함수
		//	이 함수의 반환값이 true이면 컨트롤러가 실행되고
		//	이 함수의 반환값이 false이면 컨트롤러가 실행되지 않게 된다.
		
		//	우리는 로그인이 되지 않은 경우에는 컨트롤러를 실행하지 못하게 하고 싶다.
		//	그리고 대신 로그인 폼으로 리다이렉트 시키고 싶다.
		
		//	이처럼 컨트롤러가 실행되기 전에 공통으로 점검할 내용이 있으면
		//	인터셉터를 이용해서 처리하면 된다.
		
		//	버전이 낮은 경우 인터셉터 제외 처리가 되지 않는다.
		//	이 경우에는 인터셉트를 타도록 해놓고
		//	대신 특정 요청인 경우에는 무조건 컨트롤러를 타도록 하면 된다.
		
		String	url = request.getRequestURI();
		if(url.contains("/AnBoard/BoardList.sun")) {
			return true;
		}
		//	위와 같은 개념으로 필요한 요청은 무조건 컨트롤러를 타도록 처리하면
		//	버전 낮은 스프링에서도 처리가 가능하다.
		
		
		HttpSession session = request.getSession();
		boolean	isLogin = SessionUtil.isLogin(session);
		
		if(isLogin == true) {
			//	로그인한 사람이므로 컨트롤러를 정상적으로 처리하면 된다.
			return true;
		}
		else {
			//	이사람은 로그인한 사람이 아니므로 컨트롤러를 실행하면 곤란하다.
			
			response.sendRedirect("../Login/LoginForm.sun");
			return false;
			//	대신 로그인 폼으로 리다이렉트 시키고자 한다.
		}
		
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		//	이 함수는 컨트롤러 실행이 종료된 후 실행할 함수
		//	즉, 모든 컨트롤러에서 공동으로 작업할 내용이 있으면 이 함수 안에서 작업해 주면 된다.
	}
}
