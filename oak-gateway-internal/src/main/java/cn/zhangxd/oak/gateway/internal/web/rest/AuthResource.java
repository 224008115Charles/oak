package cn.zhangxd.oak.gateway.internal.web.rest;

import cn.zhangxd.oak.gateway.internal.security.OAuth2AuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Authentication endpoint for web client.
 * Used to authenticate a user using OAuth2 access tokens or log him out.
 *
 * @author zhangxd
 */
@RestController
@RequestMapping("/auth")
public class AuthResource {

    private final Logger log = LoggerFactory.getLogger(AuthResource.class);

    private OAuth2AuthenticationService authenticationService;

    public AuthResource(OAuth2AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    /**
     * Authenticates a user setting the access and refresh token cookies.
     *
     * @param request  the HttpServletRequest holding - among others - the headers passed from the client.
     * @param response the HttpServletResponse getting the cookies set upon successful authentication.
     * @param params   the login params (username, password, rememberMe).
     * @return the access token of the authenticated user. Will return an error code if it fails to authenticate the user.
     */
    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OAuth2AccessToken> authenticate(HttpServletRequest request, HttpServletResponse response,
                                                          @RequestBody Map<String, String> params) {
        return authenticationService.authenticate(request, response, params);
    }

    /**
     * Logout current user deleting his cookies.
     *
     * @param request  the HttpServletRequest holding - among others - the headers passed from the client.
     * @param response the HttpServletResponse getting the cookies set upon successful authentication.
     * @return an empty response entity.
     */
    @PostMapping(value = "/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
        log.info("logging out user {}", SecurityContextHolder.getContext().getAuthentication().getName());
        authenticationService.logout(request, response);
        return ResponseEntity.ok(null);
    }
}
