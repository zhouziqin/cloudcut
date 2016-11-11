package com.mainiway.cloudcut.elsearch.es.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.mainiway.cloudcut.elsearch.es.entities.Post;
import com.mainiway.cloudcut.elsearch.es.repository.PostRepository;
import com.mainiway.cloudcut.elsearch.es.service.PostService;
@Service
public class PostServiceImpl implements PostService{
    @Autowired
    private PostRepository postRepository;

     public Post save(Post post) {
        postRepository.save(post);
        return post;
    }

     public Post findOne(String id) {
        return postRepository.findOne(id);
    }

     public Iterable<Post> findAll() {
        return postRepository.findAll();
    }

     public Page<Post> findByTagsName(String tagName, PageRequest pageRequest) {
        return postRepository.findByTagsName(tagName, pageRequest);
    }
}
