package spring.microservice.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DemoService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public Date getDbDate() {
        return jdbcTemplate.queryForObject("select CURRENT_TIMESTAMP() from dual", Date.class);
    }
}

