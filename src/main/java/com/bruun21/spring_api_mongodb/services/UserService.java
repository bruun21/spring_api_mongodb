package com.bruun21.spring_api_mongodb.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruun21.spring_api_mongodb.domain.User;
import com.bruun21.spring_api_mongodb.dto.UserDTO;
import com.bruun21.spring_api_mongodb.repository.UserRepository;
import com.bruun21.spring_api_mongodb.services.exception.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired //injeção de dependência automática do Spring
	private UserRepository repo;  //Aplicação em camadas: O serviço acessa o repositório
	
	public List<User> findAll(){
		return repo.findAll();
	}
	
	public User findById(String id) {
		Optional<User> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public User isert (User userEntry) {
		return repo.insert(userEntry);
	}
	
	public User fromDTO(UserDTO userDTO) {
		return new User(userDTO.getId(), userDTO.getName(), userDTO.getEmail());
	}

}
