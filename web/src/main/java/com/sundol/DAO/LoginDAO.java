package com.sundol.DAO;

import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import 	org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

/*
 * 	이 클래스는 myBatis를 이용해서 질의 명령을 실제로 수행할 DAO 클래스가 되겠다.
 * 
 * 	DAO 클래스가 되기 위한 필수 조건
 * 
 * 		반드시 SqlSessionDaoSupport	클래스를 상속 받아서 제작해야 한다.
 * 
 * 		SqlSessionDaoSupport는 myBatis가 가지고 있는 컨넥션을 관리하는 클래스이다.
 * 
 */
public class LoginDAO extends SqlSessionDaoSupport {

	//	질의 명령을 수행할 때 가장 중요한 것은 당근 컨넥션이다.
	//	우리는 myBatis를 환경 설정에 등록할 때 이미 이 컨넥션을 <bean> 처리 해 놓았다.
	//	그로므로 자동 주입 방식에 의해서 컨넥션을 new 시켜서 사용한다.
	
	//	자동주입 중 Autowired는 <bean>처리된 내용 중에서 클래스이름을 가지고 찾아오는 방식이다.
	//				  Resouecr는 id를 이용해서 찾아오는 방식이다.
	@Autowired
	public SqlSessionTemplate	sqlSession;
	
	//	질의를 실행할 함수를 제작하자.
	public int loginProc(HashMap map) {
		//	질의 명령을 실행하는 방법
		//	이미 컨넥션과 필요한 조치는 myBatis가 해 놓았기 때문에 실행만 하면 된다.
		//	실행 명령은	질의 종류에 따라서 조금씩 달라진다.
		//		즉	질의 명령이 SELECT이면 selectXXX 함수를 이용하면 되고
		//			질의 명령이 UPDATE이면 updateXXX 함수를 이용하면 된다.
		
		//	참고
		//		모든 함수의 1번 파라메터는 String이다.
		//		이것이 하는 역활이 바로 실행할 질의 명령을 지정하는 기능이다.
		//		질의 명령 지정방법
		//			질의 명령이 포함된 SQL 파일의 namespace.질의명령에 부여한 id
		//		예>
		//			우리가 실행할 질의 명령은		login	이라는 namespace에 있는 질의
		//							질의 명령 id		login	이다.
		//			결론적으로 질의 명령 코드		login.login	정해진다.
		
		//	우리는 질의 명령 실행시 ?를 채울 데이터를 Map으로 알려주기로 약속했기 때문에...
//		HashMap map = new HashMap();
//		map.put("password", pw);
//		map.put("nick", id);
		
		//	★★★
		//	매우 중요
		//		?를 채울 데이터를 map으로 제공하는 경우에는
		//		? 대신 사용한 #{적당한말}의 적당한말이 Map의 키값이 되어야 한다.
		
		//	대신 순서는 따지지 않는다.
		
		int	result = sqlSession.selectOne("login.login", map);
		return result;
		//	참고
		//		select 질의를 실행하는 함수
		//		1.	select
		//			==>	보편적인 질의 명령 실행 함수
		//		2.	selectList
		//			==>	일반적으로 결과가 여러줄인 질의 명령을 실행할 때 주로 사용한다.
		//				예를 들어 게시판 목록 구하기......
		//		3.	selectMap
		//			==>	결과에 데이터가 여러개인 경우
		//				예를 들어	게시판 상세보기 구하기...
		//		4.	selectOne
		//			==>	결과가 한개인 경우
		//			==>	일반적으로 결과가 한줄인 질의 명령을 실행할 때 주로 사용한다.

		//		위의 경우를 참조해서 실행 함수를 선택하면 된다.(권장사항일 뿐이다.)
		
		//	참고
		//		2번 파라메터 부터는 ?를 채울 데이터를 알려주는 기능이다.
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	
	
	
}






