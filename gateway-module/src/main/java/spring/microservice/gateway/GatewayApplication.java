package spring.microservice.gateway;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import javax.servlet.http.HttpServletRequest;

@EnableZuulProxy
@SpringBootApplication
@EnableEurekaClient
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
                HttpServletRequest request = ctx.getRequest();

                log.info(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));

                return null;
            }

        };
    }
}
