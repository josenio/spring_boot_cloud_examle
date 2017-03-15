package spring.microservice.gateway;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpServletRequest;

@EnableZuulProxy
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = { "spring.microservice.gateway.feign" })
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Bean
    public ZuulFilter simpleFilter() {
        return new ZuulFilter () {
            private Logger log = LoggerFactory.getLogger(GatewayApplication.class);

            @Override
            public String filterType() {
                return "pre";
            }

            @Override
            public int filterOrder() {
                return 1;
            }

            @Override
            public boolean shouldFilter() {
                return true;
            }

            @Override
            public Object run() {
                RequestContext ctx = RequestContext.getCurrentContext();

                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                ctx.addZuulRequestHeader("userName", authentication.getName());

                HttpServletRequest request = ctx.getRequest();
                String currentUser = authentication.getName();
                log.info(String.format("[%s] %s request to %s ",
                        currentUser, request.getMethod(), request.getRequestURL().toString())
                        + request.getSession(false).getId());

                return null;
            }

        };
    }
}
