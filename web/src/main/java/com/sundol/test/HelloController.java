package com.sundol.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {
	//	이 안에는 각각의 요청을 처리할 함수를 몇개든지 만들 수 있다.
	//	스프링은 하나의 요청에 하나의 컨트롤러를 사용하는 것이 아니고
	//	하나의 요청에 하나의 함수 단위로 처리된다.
	//	대신 각각의 함수마다 요청 URL을 지정하면 된다.
	
	@RequestMapping("Hello")
	public String hello() {
		//	이 안에서는 JSP 시간에 처리한 것과 같이 요청을 처리할
		//	비즈니스 로직을 구현하면 된다.
		System.out.println("Hello 요청");
		//	비즈니스 로직이 끝나면 모델을 생산해서 뷰에게 제공하고
		
		//	뷰를 호출한다.
		return "Test/Hello";
		//	이렇게만 하면 서블릿 환경설정 내용에 의해서
		//	실제 뷰는 /WEB-INF/views/Test/Hello.jsp		가 된다.
		
	}
	@RequestMapping("Test")
	public String hello1() {
		System.out.println("Test 요청");
		return "Test/Hello";
	}
	@RequestMapping("Sample")
	public String hello2() {
		System.out.println("Sample 요청");
		return "Test/Hello";
	}
}



