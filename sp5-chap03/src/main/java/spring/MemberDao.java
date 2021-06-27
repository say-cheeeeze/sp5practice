package spring;

import java.util.HashMap;
import java.util.Map;

/** 
* @packageName	:	spring 
* @fileName		:	MemberDao.java 
* @author		:	남윤재
* @date			:	2021.06.26 
* @description	:	회원가입 처리 관련 클래스
* 					email 을 통한 회원검색 기능
* 					회원 등록, 수정 기능 
* 	 
* =========================================================== 
* 	DATE 			AUTHOR 					NOTE 
* ----------------------------------------------------------- 
* 2021.06.26 		남윤재 				최초 생성
*  
*/
public class MemberDao {
	
	private static long nextId = 0;
	
	private Map<String, Member> map = new HashMap<>();
	
	public Member selectByEmail( String email ) {
		return map.get( email );
	}
	
	public void insert( Member member ) {
		member.setId( ++nextId );
		map.put( member.getEmail(), member);
	}
	
	public void update( Member member ) {
		map.put( member.getEmail(), member);
	}
	
}
