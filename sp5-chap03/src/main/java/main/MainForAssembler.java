package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import assembler.Assembler;
import spring.ChangePasswordService;
import spring.DuplicateMemberException;
import spring.MemberNotFoundException;
import spring.MemberRegisterService;
import spring.RegisterRequest;

/** 
* 	@packageName	:	main 
* 	@fileName		:	MainForAssembler.java 
* 	@author			:	cheeeeze
* 	@date			:	2021.06.27 
* 	@description	:	콘솔에서 명령어를 입력받고 각 명령어에 알맞는 기능을 수행하도록 구현함
* 						new : 새로운 회원 데이터를 추가한다.
* 						change : 회원 데이터의 암호를 변경한다.
* =========================================================== 
* 	DATE 			AUTHOR 					NOTE 
* ----------------------------------------------------------- 
* 	2021.06.27 		cheeeeze 				최초 생성 
*/
public class MainForAssembler {
	
	public static void main( String[] args ) throws IOException {
		
		BufferedReader reader = new BufferedReader( new InputStreamReader( System.in ) );
		
		while ( true ) {
			
			System.out.println( "명령어를 입력하세요.");
			String command = reader.readLine();
			
			if( command.equalsIgnoreCase("exit") ) {
				System.out.println("종료합니다...");
				break;
			}
			if( command.equals("new ") ) {
				processNewCommand( command.split(" ") );
				continue;
			}
			else if( command.startsWith("change ") ) {
				processNewCommand( command.split(" ") );
				continue;
			}
			printHelp();
		}
	}
	
	private static Assembler assembler = new Assembler();
	
	private static void processNewCommand( String[] args ) {
		if ( args.length != 5 ) {
			printHelp();
			return;
		}
		MemberRegisterService regService = assembler.getMemberRegisterService();
		RegisterRequest req = new RegisterRequest();
		req.setEmail(args[1]);
		req.setName(args[2]);
		req.setPassword(args[3]);
		req.setConfirmPassword(args[4]);
		
		if( !req.isPasswordEqualToConfirmPassword() ) {
			System.out.println( "암호와 확인이 일치하지 않습니다." );
			return;
		}
		try {
			regService.regist(req);
			System.out.println("등록 완료");
		} catch( DuplicateMemberException e) {
			System.out.println("이미 존재하는 메일입니다.");
		}
	}
	
	private static void processChangeCommand( String[] args ) {
		if ( args.length != 4 ) {
			printHelp();
			return;
		}
		
		ChangePasswordService changePwdService = assembler.getChangePasswordService();
		try {
			changePwdService.changePassword(args[1], args[2], args[3]);
			System.out.println("암호를 변경했습니다.");
		} catch( MemberNotFoundException e ) {
			System.out.println("존재하지 않는 이메일입니다.");
		} catch( WrongIdPasswordException e ) {
			System.out.println("이메일과 암호가 일치하지 않습니다.");
		}
	}
	
}
