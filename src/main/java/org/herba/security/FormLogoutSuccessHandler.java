package org.herba.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.herba.model.dto.SimpleResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("formLogoutSuccessHandler")
public class FormLogoutSuccessHandler implements LogoutSuccessHandler {
    @Autowired
    ObjectMapper objectMapper;
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        logger.info("注销成功");
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(new SimpleResponse(1, "注销成功")));
    }
}
