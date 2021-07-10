package chap06;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring.Client;
import spring.CustomClient;

@Configuration
public class AppContext {
	
	@Bean
	public Client client() {
		Client client = new Client();
		client.setHost( "host" );
		return client;
	}
	
	@Bean(initMethod = "connected", destroyMethod = "close")
	public CustomClient customClient() {
		CustomClient client = new CustomClient();
		client.setHost( "host" );
		return client;
	}
}
