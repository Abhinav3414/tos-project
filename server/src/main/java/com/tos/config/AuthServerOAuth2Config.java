package com.tos.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

/**
 * This class is Authorization server for OAuth2 security
 * sends Authorization code to clients which are registered with their cliend id and secret
 * 
 * @author Abhinav Gupta
 * @version 1.0 
 * @since 15-06-2018
 */

@Configuration
@EnableAuthorizationServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Import(ResourceServerConfig.class)
public class AuthServerOAuth2Config extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private AuthenticationManager authenticationManager;

  /*  @Autowired
    private PasswordEncoder oauthClientPasswordEncoder;
*/
    @Bean
    public OAuth2AccessDeniedHandler oauthAccessDeniedHandler() {
        return new OAuth2AccessDeniedHandler();
    }
    
    private final String CLIENT_ID = "TSCMIT-59D6948DS828F234KJHJKH9C584C749C75";
    private final String SECRET = "98D473608DD31CDF4AA7360805E483D47360";
    
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory()
			.withClient(CLIENT_ID)
			.secret(SECRET)
			.authorizedGrantTypes("client_credentials", "password")
			.authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT")
			.scopes("read","write","trust")
			.resourceIds("oauth2-resource")
			.accessTokenValiditySeconds(7200)
			.refreshTokenValiditySeconds(7200);
	}
	
	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
		oauthServer.checkTokenAccess("isAuthenticated()")
		//.passwordEncoder(oauthClientPasswordEncoder)
		;
		//oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.authenticationManager(authenticationManager);
	}

}
