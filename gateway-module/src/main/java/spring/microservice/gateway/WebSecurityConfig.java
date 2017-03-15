package spring.microservice.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.UserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import spring.microservice.gateway.feign.UserRestClient;
import spring.microservice.gateway.to.User;

import java.util.ArrayList;
import java.util.List;



@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    UserRestClient userRest;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .regexMatchers(".*")
                .authenticated()
            .and()
                .httpBasic()
            .and().csrf().disable();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                User user = userRest.findByName(username);
                if(user == null) {
                    return null;
                }
                List<GrantedAuthority> gas = new ArrayList<GrantedAuthority>();
                gas.add(new SimpleGrantedAuthority("ROLE_USER"));
                UserDetails result = new org.springframework.security.core.userdetails.User(
                        username, user.getPsw(), true, true, true, true, gas);
                return result;
            }
        });
    }
}
