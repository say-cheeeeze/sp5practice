package spring;

/** 
* @packageName	:	spring 
* @fileName		:	DuplicateMemberException.java 
* @author		:	남윤재
* @date			:	2021.06.26 
* @description	:	중복 검사  	
* =========================================================== 
* 	DATE 			AUTHOR 					NOTE 
* ----------------------------------------------------------- 
* 2021.06.26 		남윤재 				최초 생성 
*/
public class DuplicateMemberException extends RuntimeException {
	
	public DuplicateMemberException( String message ) {
		super( message );
	}

}
