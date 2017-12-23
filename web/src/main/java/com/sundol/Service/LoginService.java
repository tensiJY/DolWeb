package com.sundol.Service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;

import com.sundol.DAO.LoginDAO;

/*
 * 	이 클래스는 컨트롤로 대신에 비지니스 로직을 담당할 클래스이다.
 * 	즉, 특정 작업을 수행할 때 
 * 	컨트롤로는 파라메터 받고, 뷰 호출 부분만 처리하는 것이 원칙이고
 * 	나머지 로직 부분은 서비스 클래스를 이용해서 처리하는 것이 원칙이다.
 */
public class LoginService {

	@Autowired
	public LoginDAO	lDao;
	
	//	로그인 처리를 하기 위한 서비스 함수
	public int loginProc(String id, String pw) {
		//	할일
		//		데이터베이스를 이용해서 회원인지 확인하고
		//		그 결과를 컨트롤러에 알려준다.
		//	
		//	이 부분에서 앞에서 만들어 놓은 DAO를 이용해야 할 것이다.
		//	이때 만들어 놓은 DAO는 DI 방식을 이용해서 자동 주입하는 것이 좋다.
		
		//	자동 주입을 하기 위해서는
		//	1.	xml 파일에 사용할 클래스를 <bean> 처리한다.
		//	2.	@어노테이션을 이용해서 자동 주입해서 사용한다.
		
		HashMap	map = new HashMap();
		map.put("nick", id);
		map.put("password", pw);
		
		int	result = lDao.loginProc(map);
		return result;
	}
}








