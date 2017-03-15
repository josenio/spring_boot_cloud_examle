package spring.microservice.blog.feign;

import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import spring.microservice.blog.to.User;

import java.util.List;

/**
 * Created by josenio_camelo on 08/03/2017.
 */
@Component
public class UserRestClientFallback implements FallbackFactory<UserRestClient> {
    Logger log = LoggerFactory.getLogger(UserRestClientFallback.class);


    @Override
    public UserRestClient create(Throwable cause) {
        return new UserRestClient() {


            @Override
            public List<User> listPersons() {
                return null;
            }

            @Override
            public User findById(@PathVariable("id") Long id) {
                return null;
            }

            @Override
            public User add(@RequestBody User phoneEntry) {
                return null;
            }
        };
    }
}
