package com.mainiway.cloudcut.elsearch.es.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.mainiway.cloudcut.elsearch.es.entities.Post;

public interface PostRepository extends ElasticsearchRepository<Post, String>{

    Page<Post> findByTagsName(String name, Pageable pageable);

}
