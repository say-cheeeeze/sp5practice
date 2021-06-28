package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring.ChangePasswordService;
import spring.MemberDao;
import spring.MemberListService;
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

}
