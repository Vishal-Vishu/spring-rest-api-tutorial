package com.controller.hateoas;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.exception.UserNotFoundException;
import com.model.User;
import com.repository.UserRepository;
import com.service.UserService;

@RestController
@RequestMapping("/hateoas/users")
@Validated
public class HateoasUserController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/findall")
	public List<User> returnAllUsers(){
		return userService.findAllUsers();
	}
	
	/*
	 * @GetMapping("/getUserById/{id}") public ResponseEntity<Resource<User>>
	 * getByUserId(@PathVariable Long id){ try { Optional<User> userOptional =
	 * userService.getById(id); User user = userOptional.get(); Long userid =
	 * user.getUserId(); Link selfLink =
	 * ControllerLinkBuilder.linkTo(this.getClass()).slash(userid).withSelfRel();
	 * user.add(selfLink); Resource<User> finalResource = new Resource<User>(user);
	 * return ResponseEntity.ok(finalResource); }catch(UserNotFoundException e) {
	 * System.out.println(e.getMessage()); throw new
	 * ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage()); } }
	 */
}
