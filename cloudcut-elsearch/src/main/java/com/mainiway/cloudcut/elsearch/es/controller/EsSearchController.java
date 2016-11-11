package com.mainiway.cloudcut.elsearch.es.controller;



import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mainiway.cloudcut.elsearch.es.entities.Post;
import com.mainiway.cloudcut.elsearch.es.entities.Tag;
import com.mainiway.cloudcut.elsearch.es.service.PostService;

@RestController
@RequestMapping(value = "/search")
public class EsSearchController {
 
	@Autowired
	public PostService postService; 
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;
	 @RequestMapping("/dd")
	 public void dd(){
    	System.out.println(111111);
    }
	  @RequestMapping("/test")
	 public void testSave() {
		    elasticsearchTemplate.deleteIndex(Post.class);
	        elasticsearchTemplate.createIndex(Post.class);
	        elasticsearchTemplate.putMapping(Post.class);
	        elasticsearchTemplate.refresh(Post.class);
	        Tag tag = new Tag();
	        tag.setId("1");
	        tag.setName("tech");
	        Tag tag2 = new Tag();
	        tag2.setId("2");
	        tag2.setName("elasticsearch");

	        Post post = new Post();
	        post.setId("1");
	        post.setTitle("Bigining with spring boot application and elasticsearch");
	        post.setTags(Arrays.asList(tag, tag2));
	        postService.save(post);

 
	        Post post2 = new Post();
	        post2.setId("2");
	        post2.setTitle("Bigining with spring boot application");
	        post2.setTags(Arrays.asList(tag));
	        postService.save(post2);
 	        System.out.println("save success!!");



	    } 
	  @RequestMapping("/find")
	  public void testFindByTagsName() throws Exception {
	  

	        Page<Post> posts  = postService.findByTagsName("tech", new PageRequest(0,10));
	        Page<Post> posts2  = postService.findByTagsName("tech", new PageRequest(0,10));
	        Page<Post> posts3  = postService.findByTagsName("maz", new PageRequest(0,10));

 
	    }
 
}
