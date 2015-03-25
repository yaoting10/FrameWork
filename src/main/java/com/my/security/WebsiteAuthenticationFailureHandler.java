package com.my.security;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * Created by Administrator on 2014/9/15.
 */
public class WebsiteAuthenticationFailureHandler implements AuthenticationFailureHandler {
    private String targetUrlParam;
    private String defaultFailureUrl;
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    public WebsiteAuthenticationFailureHandler(String errorPath, String defaultFailureUrl){
        super();
        this.targetUrlParam = targetUrlParam;
        this.defaultFailureUrl = defaultFailureUrl;
    }
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        if (defaultFailureUrl == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authentication Failed: " + exception.getMessage());
        } else {
            saveException(request, exception);
            String callBackUrl = request.getParameter(targetUrlParam);
            String redirectTo = defaultFailureUrl;
            if (StringUtils.isNotBlank(callBackUrl)) {
                redirectTo = defaultFailureUrl + "?" + targetUrlParam + "=" + URLEncoder.encode(callBackUrl, "utf-8");
            }
            redirectStrategy.sendRedirect(request, response, redirectTo);
        }
    }

    protected final void saveException(HttpServletRequest request, AuthenticationException exception) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            request.getSession().setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, exception);
        }
    }
}
