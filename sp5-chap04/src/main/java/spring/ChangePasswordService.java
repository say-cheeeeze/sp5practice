package spring;

import org.springframework.beans.factory.annotation.Autowired;

/** 
* @packageName	:	spring 
* @fileName		:	ChangePasswordService.java 
* @author		:	남윤재
* @date			:	2021.06.26 
* @description	:	 
* =========================================================== 
* 	DATE 			AUTHOR 					NOTE 
* ----------------------------------------------------------- 
* 2021.06.26 		남윤재 				최초 생성 
*/
public class ChangePasswordService {
	
	/**
	 * 	필드에 Autowired 애노테이션이 붙어있으면
	 * 	스프링이 해당 타입의 Bean 객체를 찾아서 필드에 할당한다.
	 */
	@Autowired
	private MemberDao memberDao;
	
	/**
	 * 비밀번호를 변경합니다.
	 * @param email, oldPwd, newPwd
	 */
	public void changePassword( String email, String oldPwd, String newPwd ) {
		
		Member member = memberDao.selectByEmail( email );
		
		if ( member == null ) {
			throw new MemberNotFoundException();
		}
		member.changePassword(oldPwd, newPwd);
		
		memberDao.update( member );
	}
	
	public void setMemberDao( MemberDao memberDao ) {
		this.memberDao = memberDao;
	}
}
