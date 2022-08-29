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
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET) 
	public ResponseEntity<UserDTO> findById(@PathVariable String id){
		List<User> list = service.findAll();
		User obj = service.findById(id);
		return ResponseEntity.ok().body(new UserDTO(obj));
	}
	
	@RequestMapping(method=RequestMethod.POST) //POSTMAPPING
	public ResponseEntity<Void> insert(@RequestBody UserDTO userDTO){
		User user = service.fromDTO(userDTO);
		user = service.isert(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE) 
	public ResponseEntity<Void> delete(@PathVariable String id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody UserDTO userDTO, @PathVariable String id){
		User user = service.fromDTO(userDTO);
		user.setId(id);
		user = service.update(user);
		return ResponseEntity.noContent().build();
	}
}
