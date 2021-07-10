package chap02;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	public static void main( String[] args ) {
		
		// 1. 컨테이너 초기화
		AnnotationConfigApplicationContext ctx = 
				new AnnotationConfigApplicationContext(AppContext.class);
		
		// AnnotationConfigApplicationContext 의 생성자를 이용해서 컨텍스트 객체를 생성한다.
		// 이 때 스프링 컨테이너가 초기화된다.
		// 스프링 컨테이너는 설정 클래스에서 정보를 읽어와 알맞는 Bean 객체를 생성하고
		// 각 Bean 을 연결 ( = 의존주입 ) 하는 작업을 수행한다.

		// 컨테이너 초기화가 완료되면 컨테이너를 사용할 수 있다.
		// 컨테이너를 사용한다는 것은 getBean() 과 같은 메서드를 이용해 컨테이너에 보관된
		// Bean 객체를 구한다는 것을 뜻한다. 
		
		// 2. 컨테이너에서 Bean 객체를 구해서 사용
		Greeter g1 = ctx.getBean("greeter", Greeter.class);
		Greeter g2 = ctx.getBean("greeter2", Greeter.class);
		String msg = g1.greet("스프링");
		String msg2 = g2.greet("스프링");
		System.out.println(" g1 === g2 : " + ( g1 == g2) );
		System.out.println(msg);
		System.out.println(msg2);
		
		// 3. 컨테이너 종료 
		ctx.close();
		
		// 컨테이너 초기화 -> Bean 객체의 생성, 의존 주입, 초기화
		// 컨테이너 종료 -> Bean 객체의 소멸
		
	}
}
