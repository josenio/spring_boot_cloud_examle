package spring.microservice.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import spring.microservice.user.entity.User;
import spring.microservice.user.persistence.UserRepository;

import javax.annotation.PostConstruct;
import java.util.Date;

@Component
public class DemoService {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    UserRepository phoneRepository;

    public Date getDbDate() {
        return jdbcTemplate.queryForObject("select CURRENT_TIMESTAMP() from dual", Date.class);
    }

    @PostConstruct
    public void initDB() {
        if (phoneRepository.findOne(1L) == null) {
            User entity = new User(1);
            entity.setName("user");
            entity.setPsw("psw");
            phoneRepository.save(entity);
        }
    }
}

