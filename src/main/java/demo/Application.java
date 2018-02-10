package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableResourceServer
@RestController
public class Application {
	
/*	@Bean
	public JwtTokenStore tokenStore() throws Exception {
		JwtAccessTokenConverter enhancer = new JwtAccessTokenConverter();
		// N.B. in a real system you would have to configure the verifierKey (or use JdbcTokenStore)
		enhancer.afterPropertiesSet();
		return new JwtTokenStore(enhancer);
	} */
	
	@Bean
	public ResourceServerTokenServices tokenService() {
	   RemoteTokenServices tokenServices = new RemoteTokenServices();
	   tokenServices.setClientId("my-client-with-secret");
	   tokenServices.setClientSecret("secret");
	   tokenServices.setCheckTokenEndpointUrl("http://localhost:8080/oauth/check_token");
	   return tokenServices;
	}	
	

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@RequestMapping("/")
	public String home() {
		return "Hello World";
	}

}
