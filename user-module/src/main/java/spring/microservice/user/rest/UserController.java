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

    @RequestMapping(value = "/user/id/{id}", method = RequestMethod.GET)
    public User findById(@PathVariable("id") Long id) {
        return phoneRepository.findOne(id);
    }


    @RequestMapping(value = "/user/name/{name}", method = RequestMethod.GET)
    public User findByIName(@PathVariable("name") String name) {
        List<User> users = phoneRepository.findByName(name);
        return users.size() == 0 ? null : users.get(0);
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public User add(@RequestBody User phoneEntry) {
        phoneRepository.save(phoneEntry);
        return phoneEntry;
    }

}
