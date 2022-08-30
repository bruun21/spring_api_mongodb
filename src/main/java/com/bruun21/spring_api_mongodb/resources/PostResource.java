package com.bruun21.spring_api_mongodb.resources;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bruun21.spring_api_mongodb.domain.Post;
import com.bruun21.spring_api_mongodb.resources.util.URL;
import com.bruun21.spring_api_mongodb.services.PostService;

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
	
	
	@RequestMapping(value="/titlesearch", method=RequestMethod.GET) 
	public ResponseEntity<List<Post>> findByTitle(@RequestParam(value="text", defaultValue="") String text){
		text = URL.decodeParam(text);
		List<Post> listPost = service.findByTitle(text);
		return ResponseEntity.ok().body(listPost);
	}
	
	
	@RequestMapping(value="/fullsearch", method=RequestMethod.GET) 
	public ResponseEntity<List<Post>> fullSearch(
			@RequestParam(value="text", defaultValue="") String text,
			@RequestParam(value="minDate", defaultValue="") String minDate,
			@RequestParam(value="maxDate", defaultValue="") String maxDate) {
		text = URL.decodeParam(text);
		Date min = URL.convertDate(minDate, new Date(0L));
		Date max = URL.convertDate(maxDate, new Date());
		List<Post> listPost = service.fullSearch(text, min, max);
		return ResponseEntity.ok().body(listPost);
	}	
}
