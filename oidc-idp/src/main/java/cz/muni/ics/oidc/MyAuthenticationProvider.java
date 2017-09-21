package cz.muni.ics.oidc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.HashSet;
import java.util.Set;

/**
 * Authenticates a user and sets its roles (ROLE_USER always and possibly ROLE_ADMIN.
 * Currently accepts any username and password, sets ROLE_ADMIN for username "admin".
 *
 * @author Martin Kuba makub@ics.muni.cz
 */
public class MyAuthenticationProvider implements AuthenticationProvider {

	private final static Logger log = LoggerFactory.getLogger(MyAuthenticationProvider.class);

	private static GrantedAuthority ROLE_USER = new SimpleGrantedAuthority("ROLE_USER");
	private static GrantedAuthority ROLE_ADMIN = new SimpleGrantedAuthority("ROLE_ADMIN");

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		log.info("authenticate({})",authentication);
		if(authentication instanceof UsernamePasswordAuthenticationToken) {
			UsernamePasswordAuthenticationToken userToken = (UsernamePasswordAuthenticationToken) authentication;
			//get username and password
			String username = userToken.getName();
			String password = (String) authentication.getCredentials();
			log.info("UsernamePasswordAuthenticationToken: {}:{}",username,password);
			//give the user role user
			Set<GrantedAuthority> roles = new HashSet<>();
			roles.add(ROLE_USER);
			if("admin".equals(username)) {
				roles.add(ROLE_ADMIN);
			}
			//no authentication is done
			//tokens are immutable, a new one must be created.
			UsernamePasswordAuthenticationToken result
					= new UsernamePasswordAuthenticationToken(username, password,roles);
			result.setDetails(authentication.getDetails());
			return result;
		}
		return null;
	}

	@Override
	public boolean supports(Class<?> aClass) {
		log.info("supports("+aClass.getName()+")");
		return true;
	}
}
