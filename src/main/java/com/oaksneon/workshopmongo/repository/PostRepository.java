package com.oaksneon.workshopmongo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.oaksneon.workshopmongo.domain.Post;


@Repository
public interface PostRepository  extends MongoRepository<Post, String>{
	
	@Query("{ 'title': { $regex: ?0, $options: 'i' } }")
	List<Post> seachTitle(String text);
	
	@Query("{ $and: [ { date: { $gte: ?1 } }, { date: { $lte: ?2 } }, { $or: [ { 'title': { $regex: ?0, $options: 'i' } }, { 'body': { $regex: ?0, $options: 'i' } },{ 'comments.text': { $regex: ?0, $options: 'i' } } ] } ] }")
	List<Post> seachFull(String text,Date minDate, Date maxDate);
	
	List<Post> findByTitleContainingIgnoreCase(String text);
}
