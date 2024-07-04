package com.oaksneon.workshopmongo.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oaksneon.workshopmongo.domain.Post;
import com.oaksneon.workshopmongo.repository.PostRepository;
import com.oaksneon.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {
	
	@Autowired
	private PostRepository repo;
	
	public Post findById(String id) {
		Optional<Post> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
		}
	
	public List<Post> findByTitle(String text){
		return repo.seachTitle(text);
	}
	
	public List<Post> searchFull(String text, Date minDate, Date maxDate){
		maxDate = new Date(maxDate.getTime()+ 24 * 60 * 60 * 1000);
		return repo.seachFull(text, minDate, maxDate);
	}
}
