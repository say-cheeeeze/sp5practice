package config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DBConfig {

	@Bean(destroyMethod = "close") // destroyMethod 로 close 를 설정했다, close 메소드는 풀에 보관된 connection 을 닫는다.
	public DataSource dataSource() {
		
		DataSource ds = new DataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");	//jdbc 드라이버 클래스를 지정한다. mysql 드라이버 클래스를 사용한다.
		ds.setUrl("jdbc:mysql://localhost/spring5fs?characterEncoding=utf8");	// jdbc url 을 지정한다. 
		ds.setUsername("root");
		ds.setPassword("dbswodi2");
		ds.setInitialSize(2);	// 커넥션 풀을 초기화할 때 생성할 초기 커넥션 갯수 ( 기본값 10)
		ds.setMaxActive(10);	// 커넥션 풀에서 가져올 수 있는 최대 커넥션 개수 ( 기본값 100 )
		
		return ds;
	}
}
