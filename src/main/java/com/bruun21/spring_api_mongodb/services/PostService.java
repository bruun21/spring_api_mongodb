package com.bruun21.spring_api_mongodb.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruun21.spring_api_mongodb.domain.Post;
import com.bruun21.spring_api_mongodb.repository.PostRepository;
import com.bruun21.spring_api_mongodb.services.exception.ObjectNotFoundException;

@Service
public class PostService {
	
	@Autowired //injeção de dependência automática do Spring
	private PostRepository repo;  //Aplicação em camadas: O serviço acessa o repositório

	
	public Post findById(String id) {
		Optional<Post> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	
	public List<Post> findByTitle(String text){
		return repo.searchTitle(text);
	}
}
