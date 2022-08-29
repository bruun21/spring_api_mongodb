package com.bruun21.spring_api_mongodb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruun21.spring_api_mongodb.domain.User;
import com.bruun21.spring_api_mongodb.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired //injeção de dependência automática do Spring
	private UserRepository repo;  //Aplicação em camadas: O serviço acessa o repositório
	
	public List<User> findAll(){
		return repo.findAll();
	}

}
