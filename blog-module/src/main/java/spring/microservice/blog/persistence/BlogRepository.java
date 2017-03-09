package spring.microservice.blog.persistence;


import spring.microservice.blog.entity.Blog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface BlogRepository extends CrudRepository<Blog, Long> {

    List<Blog> findByUserId(@Param("userId") String userId);

}
