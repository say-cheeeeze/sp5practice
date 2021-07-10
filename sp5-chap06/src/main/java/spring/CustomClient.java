package spring;
/**
 * Bean태그의 initMethod와 destroMethod속성을 이용해서
 * 빈 객체의 초기화와 소멸 메소드를 커스텀할 수 있다.
 * @author cheeeeze
 *
 */

public class CustomClient {

	private String host;
	
	public void setHost( String host ) {
		this.host = host;
	}
	
	public void connected() {
		System.out.println( "CustomClient.connected() 실행");
	}
	
	public void send() {
		System.out.println( "CustomClient.send() 실행");
	}
	
	public void close() {
		System.out.println( " CustomClient.close() 실행");
	}
	
}
