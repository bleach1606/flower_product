package ptit.edu.btl.filter;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ptit.edu.btl.entity.Token;
import ptit.edu.btl.repository.TokenRepository;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomSecurityFilter implements Filter {
    @Autowired
    TokenRepository tokenRepository;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        String token = httpRequest.getHeader("Token");

        Token tokenInfo = tokenRepository.findByToken(token).orElse(null);
        if(httpRequest.getServletPath().startsWith("/public") || tokenInfo != null && tokenInfo.isExpires() == false){
            filterChain.doFilter(servletRequest, servletResponse);
        }
        else{
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            response.setStatus(403);
        }
    }

    @Override
    public void destroy() {

    }
}
