package cn.zhangxd.oak.uaa.security;

import cn.zhangxd.oak.uaa.security.api.ApiUsernamePasswordAuthenticationToken;
import cn.zhangxd.oak.uaa.security.sys.SysUsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AbstractTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author zhangxd
 */
public class UaaUsernamePasswordTokenGranter extends AbstractTokenGranter {

    private static final String GRANT_TYPE = "password";

    private static final String REALM_SYS = "sys";

    private static final String REALM_API = "api";

    private final AuthenticationManager authenticationManager;

    public UaaUsernamePasswordTokenGranter(
        AuthenticationManager authenticationManager,
        AuthorizationServerTokenServices tokenServices,
        ClientDetailsService clientDetailsService,
        OAuth2RequestFactory requestFactory) {
        this(authenticationManager, tokenServices, clientDetailsService, requestFactory, GRANT_TYPE);
    }

    public UaaUsernamePasswordTokenGranter(
        AuthenticationManager authenticationManager,
        AuthorizationServerTokenServices tokenServices,
        ClientDetailsService clientDetailsService,
        OAuth2RequestFactory requestFactory, String grantType) {
        super(tokenServices, clientDetailsService, requestFactory, grantType);
        this.authenticationManager = authenticationManager;
    }

    @Override
    protected OAuth2Authentication getOAuth2Authentication(ClientDetails client, TokenRequest tokenRequest) {

        Map<String, String> parameters = new LinkedHashMap<>(tokenRequest.getRequestParameters());
        String username = parameters.get("username");
        String password = parameters.get("password");
        String realm = parameters.get("realm");
        // Protect from downstream leaks of password
        parameters.remove("password");

        Authentication userAuth;
        if (REALM_SYS.equalsIgnoreCase(realm)) {
            userAuth = new SysUsernamePasswordAuthenticationToken(username, password);
        } else if (REALM_API.equalsIgnoreCase(realm)) {
            userAuth = new ApiUsernamePasswordAuthenticationToken(username, password);
        } else {
            throw new InvalidGrantException("Unknown realm: " + realm);
        }

        ((AbstractAuthenticationToken) userAuth).setDetails(parameters);
        try {
            userAuth = authenticationManager.authenticate(userAuth);
        } catch (AccountStatusException ase) {
            //covers expired, locked, disabled cases (mentioned in section 5.2, draft 31)
            throw new InvalidGrantException(ase.getMessage());
        } catch (BadCredentialsException e) {
            // If the username/password are wrong the spec says we should send 400/invalid grant
            throw new InvalidGrantException(e.getMessage());
        }
        if (userAuth == null || !userAuth.isAuthenticated()) {
            throw new InvalidGrantException("Could not authenticate user: " + username);
        }

        OAuth2Request storedOAuth2Request = getRequestFactory().createOAuth2Request(client, tokenRequest);
        return new OAuth2Authentication(storedOAuth2Request, userAuth);
    }
}
