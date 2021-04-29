package com.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exception.OrderNotFoundException;
import com.exception.UserNotFoundException;
import com.model.Order;
import com.model.User;
import com.repository.OrderRepository;
import com.repository.UserRepository;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@GetMapping("/user/{userid}")
	public List<Order> getAllOrders(@PathVariable Long userid) throws UserNotFoundException{
		Optional<User> userOptional= userRepository.findById(userid);
		
		if(userOptional.isEmpty())
			throw new UserNotFoundException("User not found");
		
		return userOptional.get().getOrder();
		
	}
	
	@PostMapping("/createorder/{userid}")
	public ResponseEntity<String> createOrder(@PathVariable Long userid,@RequestBody Order order)throws UserNotFoundException{
Optional<User> userOptional= userRepository.findById(userid);
		
		if(userOptional.isEmpty())
			throw new UserNotFoundException("User not found");
		
		User user = userOptional.get();
		order.setUser(user);
		orderRepository.save(order);
		
		return ResponseEntity.ok("Order created for user "+user.getFirstName());
		
	}
	
	@GetMapping("/getOrderByOrderId/{orderid}")
	public ResponseEntity<Order> getOrderById(@PathVariable Long orderid) throws OrderNotFoundException{
		Optional<Order> optionalOrder = orderRepository.findById(orderid);
		if(optionalOrder.isEmpty())
			throw new OrderNotFoundException("order not found");
		
		return ResponseEntity.ok(optionalOrder.get());
		
	}
}
