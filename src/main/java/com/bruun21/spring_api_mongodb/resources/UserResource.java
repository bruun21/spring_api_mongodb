package com.bruun21.spring_api_mongodb.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bruun21.spring_api_mongodb.domain.User;
import com.bruun21.spring_api_mongodb.dto.UserDTO;
import com.bruun21.spring_api_mongodb.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	@Autowired
	private UserService service; // Aplicação em camadas: O controlador Rest acessa o serviço, o serviço acessa o repositório
	
	@RequestMapping(method=RequestMethod.GET) //GETMAPPING
	public ResponseEntity<List<UserDTO>> findAll(){ //Objeto Spring que já encapsula toda a estrutura necessária para retono de respostas http já com possíveis cabeçalhos, erros etc.
		List<User> list = service.findAll();
		List <UserDTO> listDTO = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO); //.ok é um método que irá instanciar o ResponseEntity já com o código de resposta http de sucesso da operação
		//.body Define o corpo da resposta, e no caso, com o objeto criado irá retorná-lo
	}
	

}
