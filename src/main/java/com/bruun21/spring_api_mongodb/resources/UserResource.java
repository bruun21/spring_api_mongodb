package com.bruun21.spring_api_mongodb.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bruun21.spring_api_mongodb.domain.User;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	@RequestMapping(method=RequestMethod.GET) //GETMAPPING
	public ResponseEntity<List<User>> findAll(){ //Objeto Spring que já encapsula toda a estrutura necessária para retono de respostas http já com possíveis cabeçalhos, erros etc.
		User maria = new User("1", "Maria Brown", "maria@gmail.com");
		User alex = new User("2", "Alex Green", "alex@gmail.com");
		List<User> list = new ArrayList<>();
		list.addAll(Arrays.asList(maria, alex));
		return ResponseEntity.ok().body(list); //.ok é um método que irá instanciar o ResponseEntity já com o código de resposta http de sucesso da operação
		//.body Define o corpo da resposta, e no caso, com o objeto criado irá retorná-lo
	}
	

}
