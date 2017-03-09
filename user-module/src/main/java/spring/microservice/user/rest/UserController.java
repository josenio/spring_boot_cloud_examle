package spring.microservice.user.rest;

import org.springframework.cloud.client.discovery.DiscoveryClient;
import spring.microservice.user.entity.User;
import spring.microservice.user.persistence.UserRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserRepository phoneRepository;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public List<User> listPersons() {
        return Lists.newArrayList(phoneRepository.findAll());
    }

    @RequestMapping(value = "/user/{name}", method = RequestMethod.GET)
    public List<User> findByName(@PathVariable String name) {
        return phoneRepository.findByName(name);
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public User add(@RequestBody User phoneEntry) {
        phoneRepository.save(phoneEntry);
        return phoneEntry;
    }

}
