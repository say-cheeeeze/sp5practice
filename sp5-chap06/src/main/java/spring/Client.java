package spring;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * 빈 객체의 초기화와 소멸 / 빈 라이프사이클과 범위
 * 빈을 초기화하고 소멸시키는 클래스를 상속받은 Client Class 는 
 * send() 메소드를 실행시키기 전에 
 * 스프링 컨테이너에 의해 빈 객체가 초기화 및 주입된다.
 * 
 * @author cheeeeze
 *
 */
public class Client implements InitializingBean, DisposableBean{
	
	private String host;
	
	public void setHost( String host ) {
		this.host = host;
	}
	
	public void send() {
		System.out.println( "Client.send() to " + host);
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println( "Client.afterPropertiesSet() 실행됨");
	}

	@Override
	public void destroy() throws Exception {
		System.out.println( "Client.destroy() 실행");
	}

}
