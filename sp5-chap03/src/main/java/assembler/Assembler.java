package assembler;

import spring.ChangePasswordService;
import spring.MemberDao;
import spring.MemberRegisterService;

/** 
* 	@packageName	:	assembler 
* 	@fileName		:	Assembler.java 
* 	@author			:	cheeeeze
* 	@date			:	2021.06.27 
* 	@description	:	의존 대상 객체 조립 역할 
*  
* =========================================================== 
* 	DATE 			AUTHOR 					NOTE 
* ----------------------------------------------------------- 
* 	2021.06.27 		cheeeeze 				최초 생성 
*/
public class Assembler {

	private MemberDao memberDao;
	private MemberRegisterService regService;
	private ChangePasswordService pwdService;
	
	/**
	 * 의존 주입을 합니다.
	 */
	public Assembler() {
		memberDao = new MemberDao();
		regService = new MemberRegisterService( memberDao );
		pwdService = new ChangePasswordService();
		pwdService.setMemberDao( memberDao );
	}
	
	public MemberDao getMemberDao() {
		return memberDao;
	}
	
	public MemberRegisterService getMemberRegisterService() {
		return regService;
	}
	
	public ChangePasswordService getChangePasswordService() {
		return pwdService;
	}
}
