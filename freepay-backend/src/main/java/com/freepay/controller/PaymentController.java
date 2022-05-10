package com.freepay.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.freepay.model.Orders;
import com.freepay.model.Payment;
import com.freepay.repository.PaymentRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class PaymentController {

	@Autowired
	private PaymentRepository paymentRepository;

	@GetMapping("/getPayments")
	public List<Payment> getAllOrders(@PathVariable String username) {
		return paymentRepository.findByUsername(username);
	}
	
	@GetMapping("/admin/payments")
	public List<Payment> getAllUserOrders() {
		return paymentRepository.findAll();
	}

	@GetMapping("/admin/totalsPayments")
	public long getAllUserOrdersCount() {
		List<Payment> order =  paymentRepository.findAll();
		 return order.stream().count();
	}
	
	@PostMapping("/createPayment")
	public Payment createOrders(@RequestBody Payment payment) {
//		payment.setUsername(username);
		Payment createdpayment = paymentRepository.save(payment);
//		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
//				.buildAndExpand(createdpayment.getOrderId()).toUri();
		return createdpayment;
	}

	/*
	 * @PostMapping("/createPayment") public ResponseEntity<Void>
	 * createOrders(@RequestBody Payment payment) { //
	 * payment.setUsername(username); Payment createdpayment =
	 * paymentRepository.save(payment); URI uri =
	 * ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
	 * .buildAndExpand(createdpayment.getOrderId()).toUri(); return
	 * ResponseEntity.created(uri).build(); }
	 */
}
