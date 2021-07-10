package spring;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
* 	@packageName	:	spring 
* 	@fileName		:	MemberListService.java 
* 	@author			:	cheeeeze
* 	@date			:	2021.06.27 
* 	@description	:	현재 인스턴스(Map) 의 회원 목록 전부를 보여줍니다.	 
* =========================================================== 
* 	DATE 			AUTHOR 					NOTE 
* ----------------------------------------------------------- 
* 	2021.06.27 		cheeeeze 				최초 생성
 */
public class MemberListService {

	@Autowired
	private MemberDao memberDao;
	
	public MemberListService() {
		
	}
	
	public MemberListService( MemberDao memberDao ) {
		this.memberDao = memberDao;
	}
	
	public void showList() {
		memberDao.list();
	}
	
}
