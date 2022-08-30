package com.bruun21.spring_api_mongodb.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bruun21.spring_api_mongodb.domain.Post;
import com.bruun21.spring_api_mongodb.domain.User;
import com.bruun21.spring_api_mongodb.dto.UserDTO;
import com.bruun21.spring_api_mongodb.services.PostService;
import com.bruun21.spring_api_mongodb.services.UserService;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {
	
	@Autowired
	private PostService service; // Aplicação em camadas: O controlador Rest acessa o serviço, o serviço acessa o repositório
	
		
	@RequestMapping(value="/{id}", method=RequestMethod.GET) 
	public ResponseEntity<Post> findById(@PathVariable String id){
		Post obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	
}
