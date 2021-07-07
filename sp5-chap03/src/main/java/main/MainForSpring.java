package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.AppCtx;
import spring.ChangePasswordService;
import spring.DuplicateMemberException;
import spring.MemberInfoPrinter;
import spring.MemberListPrinter;
import spring.MemberListService;
import spring.MemberNotFoundException;
import spring.MemberRegisterService;
import spring.RegisterRequest;
import spring.VersionPrinter;
import spring.WrongIdPasswordException;

/**
 * @packageName : main
 * @fileName : MainForSpring.java
 * @author : cheeeeze
 * @date : 2021.06.28
 * @description : 스프링 빈 객체 생성 및 의존 주입역할
 *              =========================================================== DATE
 *              AUTHOR NOTE
 *              -----------------------------------------------------------
 *              2021.06.28 cheeeeze 최초 생성
 */
public class MainForSpring {

	// 앞서 Assembler클래스를 통해 작성한 클래스를 스프링 컨테이너를 사용하도록 변경함

	private static ApplicationContext ctx = null;

	public static void main(String[] args) throws IOException {

		ctx = new AnnotationConfigApplicationContext(AppCtx.class);

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		printHelp();
		
		while (true) {
			
			System.out.println("명령어를 입력하세요.");
			String command = reader.readLine();

			if (command.equalsIgnoreCase("exit")) {
				System.out.println("종료합니다...");
				break;
			}

			// readLine() 으로 입력받은 문자열은 " " 공백 한칸을 기준으로 배열을 return 한다.
			// 이 배열을 가지고 각기 다른 command 을 요청하게 된다.

			if (command.startsWith("new ")) {
				processNewCommand(command.split(" "));
				continue;
			} else if (command.startsWith("change ")) {
				processChangeCommand(command.split(" "));
				continue;
			} else if (command.equals("list")) {
				processShowList();
				continue;
			} else if (command.startsWith("info ")) {
				processInfoCommand(command.split(" "));
				continue;
			} else if (command.equals("version")) {
				processVersionCommand();
				continue;
			}
			printWrong();

		}

	}
	/**
	 * 회원 한명의 정보를 조회합니다.
	 * @param 이메일주소
	 */
	private static void processInfoCommand(String[] args) {
		if( args.length != 2 ) {
			printHelp();
			return;
		}
		System.out.println(args[1]);
		MemberInfoPrinter infoPrinter = ctx.getBean("infoPrinter", MemberInfoPrinter.class);
		infoPrinter.printMemberInfo(args[1]);
	}

	/**
	 * 회원 등록 처리합니다.
	 */
	private static void processNewCommand(String[] args) {

		if (args.length != 5) {
			printWrong();
			return;
		}

		MemberRegisterService regService 
						= ctx.getBean("memberRegService", MemberRegisterService.class);

		RegisterRequest request = new RegisterRequest();
		request.setEmail(args[1]);
		request.setName(args[2]);
		request.setPassword(args[3]);
		request.setConfirmPassword(args[4]);

		if (!request.isPasswordEqualToConfirmPassword()) {
			System.out.println("비밀번호가 서로 일치하지 않습니다.");
			return;
		}
		try {
			regService.regist(request);
			System.out.println("등록 완료");
		} catch (DuplicateMemberException e) {
			System.out.println("이미 존재하는 메일입니다.");
		}

	}

	/**
	 * 회원 정보 변경합니다.
	 */
	private static void processChangeCommand(String[] args) {
		
		if( args.length != 4 ) {
			printHelp();
			return;
		}
		
		ChangePasswordService changeService 
					= ctx.getBean("changePwdService", ChangePasswordService.class);
		
		try {
			changeService.changePassword(args[1], args[2], args[3]);
			System.out.println( "암호를 변경했습니다." );
		} catch( MemberNotFoundException e) {
			System.out.println( "존재하지 않는 이메일입니다." );
		} catch( WrongIdPasswordException e) {
			System.out.println( "이메일과 암호가 일치하지 않습니다." );
		}
	}

	/**
	 * 회원 목록을 보여줍니다.
	 */
	private static void processShowList() {
//		MemberListService listService = ctx.getBean("memberListService", MemberListService.class);
//		listService.showList();
		MemberListPrinter listPrinter = ctx.getBean("listPrinter", MemberListPrinter.class);
		listPrinter.printAll();
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
		System.out.println("회원 정보 조회(한명) : info 이메일");
		System.out.println("프로그램 버전 확인 : version");
		System.out.println("프로그램 종료 : exit");
		System.out.println();

	}
	
	public static void processVersionCommand() {
		VersionPrinter versionPrinter = ctx.getBean("versionPrinter", VersionPrinter.class );
		versionPrinter.print();
	}

}
