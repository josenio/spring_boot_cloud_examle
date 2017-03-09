package spring.microservice.blog.feign;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spring.microservice.blog.to.User;

import java.util.List;

/**
 * Created by josenio_camelo on 08/03/2017.
 */
public class UserRestClientFallback implements UserRestClient {
    Logger log = LoggerFactory.getLogger(UserRestClientFallback.class);

    @Override
    public List<User> listPersons() {
        log.error("Error: listPersons");
        throw new RuntimeException();
    }

    @Override
    public List<User> findByName(String name) {
        log.error("Error: listPersons");
        throw new RuntimeException();
    }

    @Override
    public User add(User phoneEntry) {
        log.error("Error on listPersons");
        throw new RuntimeException();
    }
}
