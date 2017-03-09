package spring.microservice.user.persistence;


import spring.microservice.user.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findByName(@Param("name") String name);

}
