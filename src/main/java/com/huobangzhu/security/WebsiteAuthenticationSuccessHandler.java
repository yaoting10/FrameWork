package com.huobangzhu.security;

import com.huobangzhu.core.domain.User;
import com.huobangzhu.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * User: yt
 * Date: 14-11-10
 */
public class WebsiteAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private UserService userService;

    public WebsiteAuthenticationSuccessHandler(UserService userService, String targetUrl, boolean alwaysUse,String targetUrlParam) {
        super();
        this.userService = userService;
        setDefaultTargetUrl(targetUrl);
        setAlwaysUseDefaultTargetUrl(alwaysUse);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            User user = userService.findByUserNumber(authentication.getName());
            session.setAttribute("user", user);
        }
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
