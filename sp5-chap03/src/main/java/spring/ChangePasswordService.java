package spring;


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
	
	private MemberDao memberDao;
	
	public void changePassword( String email, String oldPwd, String newPwd ) {
		Member member = memberDao.selectByEmail( email );
		if ( member == null ) {
			throw new MemberNotFoundException();
		}
		member.changePassword(oldPwd, newPwd);
		
	}
}
