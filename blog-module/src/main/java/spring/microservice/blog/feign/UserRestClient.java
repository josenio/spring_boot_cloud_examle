package spring.microservice.blog.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import spring.microservice.blog.to.User;

import java.util.List;

@FeignClient(value = "user-module", fallback = UserRestClientFallback.class)
public interface  UserRestClient {

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public List<User> listPersons();

    @RequestMapping(value = "/user/{name}", method = RequestMethod.GET)
    public List<User> findByName(@PathVariable String name);

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public User add(@RequestBody User phoneEntry);
}
