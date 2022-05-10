package com.freepay.todo;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@CrossOrigin(origins="http://localhost:4200")
public class TodoController {
	
	@Autowired
	private TodoHardcodedService todoService;
	
	@Autowired
	private TodoJpaRepository todoJpaRepository;

	@GetMapping("/jpa/users/{username}/todos")
	public List<Todo> getAllTodos(@PathVariable String username){
		return todoJpaRepository.findByUsername(username);
//		return todoService.findAll();
	}
	
	@GetMapping("/jpa/users/{username}/todos/{id}")
	public Todo getAllTodos(@PathVariable String username,@PathVariable long id){
		return todoJpaRepository.findById(id).get();
//		return todoService.findById(id);
	}
	
	@PutMapping("/jpa/users/{username}/todos/{id}")
	public ResponseEntity<Todo> updateTodo(@PathVariable String username,@PathVariable long id, @RequestBody Todo todo){
//		Todo todoUpdated = todoService.save(todo);
		Todo todoUpdated = todoJpaRepository.save(todo);
		return new ResponseEntity<Todo>(todoUpdated,HttpStatus.OK);
	}
	
	/*
	 * @PostMapping("/jpa/users/{username}/todos") public ResponseEntity<Void>
	 * createTodo(@PathVariable String username, @RequestBody Todo todo){ // Todo
	 * createdTodo = todoService.save(todo); todo.setUsername(username); Todo
	 * createdTodo = todoJpaRepository.save(todo); URI uri =
	 * ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand
	 * (createdTodo.getId()).toUri();
	 * System.out.println(ResponseEntity.created(uri).build()); return
	 * ResponseEntity.created(uri).build(); }
	 */
	
	@PostMapping("/jpa/users/{username}/todos")
	public Todo createTodo(@PathVariable String username, @RequestBody Todo todo){
//		Todo createdTodo = todoService.save(todo);
		todo.setUsername(username);
		Todo createdTodo = todoJpaRepository.save(todo);
//		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdTodo.getId()).toUri();
//		System.out.println(ResponseEntity.created(uri).build());
		return createdTodo;
	}
	
	@DeleteMapping("/jpa/users/{username}/todos/{id}")
	public ResponseEntity<Void> deleteTodo(@PathVariable String username,@PathVariable long id){
//		Todo todo = todoService.deleteById(id);
		todoJpaRepository.deleteById(id);
		return ResponseEntity.noContent().build();
//		return ResponseEntity.notFound().build();
	}
}
