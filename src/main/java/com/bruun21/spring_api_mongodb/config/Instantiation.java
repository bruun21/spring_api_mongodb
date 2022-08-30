package com.bruun21.spring_api_mongodb.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.bruun21.spring_api_mongodb.domain.Post;
import com.bruun21.spring_api_mongodb.domain.User;
import com.bruun21.spring_api_mongodb.dto.AuthorDTO;
import com.bruun21.spring_api_mongodb.repository.PostRepository;
import com.bruun21.spring_api_mongodb.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
		sd.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		
		Post post1 = new Post(null, sd.parse("21/03/2018"), "Partiu Viagem", "Vou viajara para São Paulo. Abraços!", new AuthorDTO(maria));
		Post post2 = new Post(null, sd.parse("23/03/2018"), "Bom dia", "Acorde Feliz", new AuthorDTO(maria));
		
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		maria.getPosts().addAll(Arrays.asList(post1, post2));
		userRepository.save(maria);
		
	}
	

}
