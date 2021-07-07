package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring.ChangePasswordService;
import spring.MemberDao;
import spring.MemberInfoPrinter;
import spring.MemberListPrinter;
import spring.MemberListService;
import spring.MemberPrinter;
import spring.MemberRegisterService;

@Configuration
public class AppCtx {
	
	@Bean
	public MemberDao memberDao() {
		return new MemberDao();
	}
	
	@Bean
	public MemberRegisterService memberRegService() {
		return new MemberRegisterService( memberDao() );
	}
	
	@Bean
	public MemberListService memberListService() {
		return new MemberListService( memberDao() );
	}
	
	@Bean
	public ChangePasswordService changePwdService() {
		ChangePasswordService passwordService = new ChangePasswordService();
		passwordService.setMemberDao(memberDao());
		return passwordService;
	}
	
	@Bean
	public MemberPrinter memberPrinter() {
		return new MemberPrinter();
	}
	
	@Bean
	public MemberListPrinter listPrinter() {
		// 두 개 이상의 생성자도 의존객체에 할당할 수 있다 
		return new MemberListPrinter( memberDao(), memberPrinter());
	}
	
	@Bean
	public MemberInfoPrinter infoPrinter() {
		MemberInfoPrinter infoPrinter = new MemberInfoPrinter();
		infoPrinter.setMemberDao( memberDao() );
		infoPrinter.setPrinter( memberPrinter());
		return infoPrinter;
	}

}
