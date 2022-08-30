package com.bruun21.spring_api_mongodb.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bruun21.spring_api_mongodb.domain.Post;

@Repository //repositório padrão mongo no spring Data já informando o tipo da classe de domínio que irá gerenciar, no caso "User" e o tipo de dado do id dessa classe
public interface PostRepository extends MongoRepository<Post, String> {
	
	List<Post> findByTitleContainingIgnoreCase(String text);
}
