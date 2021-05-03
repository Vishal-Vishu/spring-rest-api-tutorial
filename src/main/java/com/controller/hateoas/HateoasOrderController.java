package com.controller.hateoas;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exception.UserNotFoundException;
import com.model.Order;
import com.model.User;
import com.repository.OrderRepository;
import com.repository.UserRepository;

@RestController
@RequestMapping("/hateoas/users")
public class HateoasOrderController {
	
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
}
