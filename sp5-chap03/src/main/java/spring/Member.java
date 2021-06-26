package spring;

import java.time.LocalDateTime;

/** 
* @packageName	:	spring 
* @fileName		:	Member.java 
* @author		:	남윤재
* @date			:	2021.06.26 
* @description	:	회원 데이터 관련 클래스
* =========================================================== 
* 	DATE 			AUTHOR 					NOTE 
* ----------------------------------------------------------- 
* 	2021.06.26 		남윤재 				최초 생성
* 
*/
public class Member {
	
	private Long id;
	private String email;
	private String password;
	private String name;
	private LocalDateTime regDate;
	
	public Member( String email, String password, String name, LocalDateTime regDate ) {
		this.email = email;
		this.password = password;
		this.name = name;
		this.regDate = regDate;
	}
	
	public Long getId() {
		return id;
	}	
	
	void setId( Long id ) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getPassword() {
		return password;
	}
	public String getName() {
		return name;
	}
	public LocalDateTime getRegDate() {
		return regDate;
	}
	public void changePassword( String oldPassword, String newPassword ) {
		if ( !password.equals( oldPassword ) ) {
			throw new WrongIdPasswordException();
		}
		this.password = newPassword;
	}
	
	
}
