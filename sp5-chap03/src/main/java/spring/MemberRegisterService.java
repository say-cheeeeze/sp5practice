package spring;

import java.time.LocalDateTime;

/** 
* @packageName	:	spring 
* @fileName		:	MemberRegisterService.java 
* @author		:	남윤재
* @date			:	2021.06.26 
* @description	:	회원 가입 서비스 객체	 
* =========================================================== 
* 	DATE 			AUTHOR 					NOTE 
* ----------------------------------------------------------- 
* 2021.06.26 		남윤재 				최초 생성 
*/
public class MemberRegisterService {
	
	private MemberDao memberDao;
	
	public MemberRegisterService( MemberDao memberDao ) {
		this.memberDao = memberDao;
	}
	
	/**
	 * 회원등록 로직
	 * @param request
	 * @return	등록한 회원의 ID
	 */
	public Long regist( RegisterRequest request ) {
		
		Member member = memberDao.selectByEmail( request.getEmail() );
		
		if ( member != null ) {
			throw new DuplicateMemberException(" duplicated mail : " + request.getEmail() );
		}
		Member newMember = new Member(
									request.getEmail(), 
									request.getPassword(), 
									request.getName(), 
									LocalDateTime.now()
									);
		memberDao.insert( newMember );
		return newMember.getId();
	}
	
}


