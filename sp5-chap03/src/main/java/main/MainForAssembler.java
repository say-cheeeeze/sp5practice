package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import assembler.Assembler;
import spring.ChangePasswordService;
import spring.DuplicateMemberException;
import spring.MemberListService;
import spring.MemberNotFoundException;
import spring.MemberRegisterService;
import spring.RegisterRequest;
import spring.WrongIdPasswordException;

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
	
	/**
	 *
	 * 	회원 등록 및 수정 , 리스트 프로그램을 실행합니다.
	 */
	public static void main( String[] args ) throws IOException {
		
		BufferedReader reader = new BufferedReader( new InputStreamReader( System.in ) );
		
		printHelp();

		while ( true ) {
			
			System.out.println( "명령어를 입력하세요.");
			String command = reader.readLine();
			
			if( command.equalsIgnoreCase("exit") ) {
				System.out.println("종료합니다...");
				break;
			}
			
			// readLine() 으로 입력받은 문자열은 " " 공백 한칸을 기준으로 배열을 return 한다.
			// 이 배열을 가지고 각기 다른 command 을 요청하게 된다.
			
			if( command.startsWith( "new ") ) {
				processNewCommand( command.split(" ") );
				continue;
			}
			else if( command.startsWith( "change " ) ) {
				processChangeCommand( command.split(" ") );
				continue;
			}
			else if ( command.equals( "list" ) ) {
				processShowList();
				continue;
			}
			printWrong();
		}
	}

	/**
	 * 객체 생성과 조립을 담당할 assembler 를 생성합니다.
	 */
	private static Assembler assembler = new Assembler();
	
	/**
	 * 회원 등록 처리합니다.
	 */
	private static void processNewCommand( String[] args ) {
		
		if ( args.length != 5 ) {
			System.out.println( args );
			printWrong();
			return;
		}
		MemberRegisterService regService = assembler.getMemberRegisterService();
		RegisterRequest req = new RegisterRequest();
		req.setEmail(args[1]);
		req.setName(args[2]);
		req.setPassword(args[3]);
		req.setConfirmPassword(args[4]);
		
		if( !req.isPasswordEqualToConfirmPassword() ) {
			System.out.println( "비밀번호가 서로 일치하지 않습니다." );
			return;
		}
		try {
			regService.regist(req);
			System.out.println("등록 완료");
		} catch( DuplicateMemberException e) {
			System.out.println("이미 존재하는 메일입니다.");
		}
	}
	
	/**
	 * 회원 정보 변경합니다.
	 */
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
	
	/**
	 * 회원 목록을 보여줍니다. 
	 */
	private static void processShowList() {
		MemberListService listService = assembler.getMemberListService();
		listService.showList();
	}

	private static void printWrong() {
		System.out.println();
		System.out.println("잘못된 명령입니다. 아래 사용법을 확인하세요.");
		printHelp();
	}
	
	private static void printHelp() {
		System.out.println();
		System.out.println("각 인자값은 띄어쓰기 한칸으로 구분하여 입력합니다.");
		System.out.println("등록인 경우 : new 이메일 이름 암호 암호확인 ");
		System.out.println("변경인 경우 : change 이메일 현재암호 변경할암호");
		System.out.println("회원 목록 조회 : list");
		System.out.println("프로그램 종료 : exit");
		System.out.println();
		
	}

	
}
