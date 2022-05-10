package com.freepay.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Comparator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.freepay.model.Orders;
import com.freepay.model.Payment;
import com.freepay.repository.OrdersRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class OrdersController {

	@Autowired
	private OrdersRepository ordersRepository;

	@GetMapping("/users/{username}/orders")
	public List<Orders> getAllOrders(@PathVariable String username) {
		return ordersRepository.findByUsername(username);
	}
	
	@GetMapping("/admin/orders")
	public List<Orders> getAllUserOrders() {
		return ordersRepository.findAll();
	}
	
	@GetMapping("/admin/totalsOrders")
	public long getAllUserOrdersCount() {
		List<Orders> order =  ordersRepository.findAll();
		 return order.stream().count();
	}

	@GetMapping("/users/{username}/orders/{id}")
	public Orders getAllOrders(@PathVariable String username, @PathVariable long id) {
		return ordersRepository.findByOrderId(id);
	}

	@PutMapping("/users/{username}/orders/{id}")
	public ResponseEntity<Orders> updateOrders(@PathVariable String username, @PathVariable long id,
			@RequestBody Orders orders) {
		Orders ordersUpdated = ordersRepository.save(orders);
		return new ResponseEntity<Orders>(ordersUpdated, HttpStatus.OK);
	}

	@PostMapping("/users/{username}/orders")
	public ResponseEntity<Void> createOrders(@PathVariable String username, @RequestBody Orders orders) {
		orders.setUsername(username);
		Orders createdOrders = ordersRepository.save(orders);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(createdOrders.getOrderId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@GetMapping("/users/{username}/orders/last")
	public Orders lastID(@PathVariable String username) {
		List <Orders> or = ordersRepository.findAll();
		Orders last = or.stream().max(Comparator.comparingLong(Orders::getOrderId)).get();
		return last;
	}
	
	@GetMapping("/admin/totalAmount")
	public double getTotalAmount() {
		List<Orders> order =  ordersRepository.findAll();
		 return order.stream().collect(Collectors.summingDouble(Orders::getAmount));
	}
	
//	@DeleteMapping("/users/{username}/orders/{id}")
//	public ResponseEntity<Void> deleteOrders(@PathVariable String username,@PathVariable long id){
//		ordersRepository.deleteByOrderId(id);
//		return ResponseEntity.noContent().build();
////		return ResponseEntity.notFound().build();
//	}
}
