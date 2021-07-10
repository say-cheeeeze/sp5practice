package spring;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;

/** 
* @packageName	:	spring 
* @fileName		:	MemberRegisterService.java 
* @author		:	cheeeze
* @date			:	2021.06.26 
* @description	:	회원 가입 서비스 객체	 
* =========================================================== 
* 	DATE 			AUTHOR 					NOTE 
* ----------------------------------------------------------- 
* 2021.06.26 		cheeeze 				최초 생성 
*/
public class MemberRegisterService {
	
	@Autowired
	private MemberDao memberDao;
	
	public MemberRegisterService() {
	}
	
	//생성자를 통해 의존 객체를 주입받음 
	public MemberRegisterService( MemberDao memberDao ) {
		
		// 주입받은 객체를 필드에 할당함
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


