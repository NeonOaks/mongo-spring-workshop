package com.oaksneon.workshopmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.oaksneon.workshopmongo.domain.Post;

@Repository
public interface PostRepository  extends MongoRepository<Post, String>{
}
