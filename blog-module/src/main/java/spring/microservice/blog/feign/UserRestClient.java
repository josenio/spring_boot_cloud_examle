package spring.microservice.blog.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import spring.microservice.blog.to.User;

import java.util.List;

@FeignClient(value = "user-module")
public interface  UserRestClient {

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public List<User> listPersons();

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public User findById(@PathVariable("id") Long id);

    @RequestMapping(value = "/user/name/{name}", method = RequestMethod.GET)
    public User findByName(@PathVariable("name") String name);

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public User add(@RequestBody User phoneEntry);
}