package assembler;

import spring.ChangePasswordService;
import spring.MemberDao;
import spring.MemberListService;
import spring.MemberRegisterService;

/** 
* 	@packageName	:	assembler 
* 	@fileName		:	Assembler.java 
* 	@author			:	cheeeeze
* 	@date			:	2021.06.27 
* 	@description	:	의존 대상 객체 조립 역할 
* 
* 			Assembler 는 자신이 생성하고 조립한 객체를 리턴하는 메서드를 제공한다.
* 
*  
* =========================================================== 
* 	DATE 			AUTHOR 					NOTE 
* ----------------------------------------------------------- 
* 	2021.06.27 		cheeeeze 				최초 생성 
* 	2021.06.28		cheeeeze				목록 기능 추가
*/
public class Assembler {

	private MemberDao memberDao;
	private MemberRegisterService regService;
	private ChangePasswordService pwdService;
	private MemberListService listService;
	
	/**
	 * 의존 주입을 합니다.
	 */
	public Assembler() {
		memberDao = new MemberDao();
		regService = new MemberRegisterService( memberDao );
		listService = new MemberListService( memberDao );
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
	public MemberListService getMemberListService() {
		return listService;
	}
}
