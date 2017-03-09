package spring.microservice.blog.rest;

import spring.microservice.blog.entity.Blog;
import spring.microservice.blog.persistence.BlogRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class BlogController {
    @Autowired
    private BlogRepository blogRepository;

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
    public Blog addComment(@RequestBody Map<String, String> commentEntry, @RequestHeader(value="user-id") Long userId) {
        Blog dbBlog = blogRepository.findOne(Long.parseLong(commentEntry.get("id")));
        dbBlog.getComments().add(commentEntry.get("text"));//TODO concat user name with text
        blogRepository.save(dbBlog);
        return dbBlog;
    }

}
