package com.oaksneon.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.oaksneon.workshopmongo.domain.Post;
import com.oaksneon.workshopmongo.domain.User;
import com.oaksneon.workshopmongo.dto.AuthorDTO;
import com.oaksneon.workshopmongo.dto.CommentDTO;
import com.oaksneon.workshopmongo.repository.PostRepository;
import com.oaksneon.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;

	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria,alex,bob));
		
		Post post1 = new Post(null,new AuthorDTO(maria) ,sdf.parse("21/03/2018"),"Partiu viagem", "Vou viajar para São Paulo. Abraços!");
		Post post2 = new Post(null,new AuthorDTO(maria),sdf.parse("21/03/2018"),"Bom dia", "Acordei feliz hoje!");
				
		CommentDTO c1 = new CommentDTO("Boa viajem mano!",sdf.parse("21/03/2018"),new AuthorDTO(alex));
		CommentDTO c2 = new CommentDTO("Aproveita.....",sdf.parse("25/03/2018"),new AuthorDTO(bob));
		CommentDTO c3 = new CommentDTO("Tenha um ótimo dia!!!!",sdf.parse("28/03/2018"),new AuthorDTO(alex));
		
		post1.getComments().addAll(Arrays.asList(c1,c2));
		post2.getComments().addAll(Arrays.asList(c3));

		
		postRepository.saveAll(Arrays.asList(post1,post2));
		
		maria.getPosts().addAll(Arrays.asList(post1,post2));
		userRepository.save(maria);
	}

}
