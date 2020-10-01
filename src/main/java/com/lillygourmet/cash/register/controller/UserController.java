/*
 * Caisse Enregistreuse App - API REST
 * COded By Alias King - Younes OUFRID !!
 * Mail : oufridyounes@gmail.com
 * MNS team coders
 * */

package com.lillygourmet.cash.register.controller;
/**
 * User Controller
 *
 * @author Alias King - Younes OUFRID
 */
import org.springframework.security.access.prepost.PreAuthorize;
import com.lillygourmet.cash.register.model.User;
import com.lillygourmet.cash.register.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UserController {

	private static Logger _log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@GetMapping("api/users")
	public ResponseEntity<List<User>> retrieveAllUsers() {
		_log.info("retrieve all users controller...!");
		List<User> users = userService.retrieveAllUsers();
		return new ResponseEntity<List<User>>(users, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("api/users/{id}")
	public ResponseEntity<User> retrieveUserById(@PathVariable Long id) {

		_log.info("retrieve user controller...!");
		User user = userService.getUser(id);
		return new ResponseEntity<User>(user, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping("api/users")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<User> createUser(@RequestBody User user) {

		_log.info("create user controller...!");
		User savedUser = userService.createUser(user);
		return new ResponseEntity<User>(savedUser, new HttpHeaders(), HttpStatus.CREATED);
	}

	@DeleteMapping("api/users/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public HttpStatus deleteUser(@PathVariable Long id) throws Exception {

		_log.info("delete User controller...!");
		userService.deleteUser(id);
		return HttpStatus.ACCEPTED;
	}

	@PutMapping("api/users")
	public ResponseEntity<User> updateUser(@RequestBody User user) {

		_log.info("update user controller...!");
		User updatedUser= userService.updateUser(user);
		return new ResponseEntity<User>(updatedUser, new HttpHeaders(), HttpStatus.ACCEPTED);
	}
}
