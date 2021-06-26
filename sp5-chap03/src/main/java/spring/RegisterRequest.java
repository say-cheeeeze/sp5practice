package spring;


/** 
* @packageName	:	spring 
* @fileName		:	RegisterRequest.java 
* @author		:	남윤재
* @date			:	2021.06.26 
* @description	:	회원 등록 처리 
* =========================================================== 
* 	DATE 			AUTHOR 					NOTE 
* ----------------------------------------------------------- 
* 2021.06.26 		남윤재 				최초 생성 
*/
public class RegisterRequest {

	private String email;
	private String password;
	private String confirmPassword;
	private String name;
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail( String email ) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword( String password ) {
		this.password = password;
	}
	
	public String getConfirmPassword() {
		return confirmPassword;
	}
	
	public void setConfirmPassword( String confirmPassword ) {
		this.confirmPassword = confirmPassword;
	}
	
	public String getName() {
		return name;
	}
	public void setName( String name ) {
		this.name = name;
	}
	
	public boolean isPasswordEqualToConfirmPassword() {
		return password.equals( confirmPassword );
	}
	
	
}
