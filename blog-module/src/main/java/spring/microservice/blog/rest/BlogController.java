package spring.microservice.blog.rest;

import spring.microservice.blog.entity.Blog;
import spring.microservice.blog.feign.UserRestClient;
import spring.microservice.blog.persistence.BlogRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.microservice.blog.to.User;

import java.util.List;
import java.util.Map;

@RestController
public class BlogController {
    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private UserRestClient userClient;

    @RequestMapping(value = "/blog", method = RequestMethod.GET)
    public List<Blog> listPersons() {
        return Lists.newArrayList(blogRepository.findAll());
    }

    @RequestMapping(value = "/blog/userId/{userId}", method = RequestMethod.GET)
    public List<Blog> findByName(@PathVariable String userId) {
        return blogRepository.findByUserId(userId);
    }

    @RequestMapping(value = "/blog", method = RequestMethod.POST)
    public Blog add(@RequestBody Blog blogEntry, @RequestHeader(value="user-id") Long userId) {
        blogEntry.setUserId(userId);
        blogEntry.setComments(null);
        blogRepository.save(blogEntry);
        return blogEntry;
    }

    @RequestMapping(value = "/blog/comments", method = RequestMethod.POST)
    public Blog addComment(@RequestBody Map<String, String> commentEntry, @RequestHeader(value="user-id") String userId) {
        Blog dbBlog = blogRepository.findOne(Long.parseLong(commentEntry.get("blogId")));
        User user = userClient.findById(Long.parseLong(userId));
        dbBlog.getComments().add(user.getName() + ": " + commentEntry.get("commentText"));
        blogRepository.save(dbBlog);
        return dbBlog;
    }

}
