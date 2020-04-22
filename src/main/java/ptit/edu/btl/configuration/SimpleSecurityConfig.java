package ptit.edu.btl.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ptit.edu.btl.filter.CustomSecurityFilter;

@Configuration
public class SimpleSecurityConfig {
    @Autowired
    CustomSecurityFilter customSecurityFilter;

    @Bean
    public FilterRegistrationBean securityFilter(){
        FilterRegistrationBean bean = new FilterRegistrationBean(customSecurityFilter);
        bean.setOrder(0);
        return bean;
    }
}
