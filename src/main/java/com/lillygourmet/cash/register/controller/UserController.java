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
import com.lillygourmet.cash.register.Exception.ResourceNotFoundException;
import com.lillygourmet.cash.register.model.*;
import com.lillygourmet.cash.register.repository.RoleRepository;
import com.lillygourmet.cash.register.repository.UserRepository;
import org.springframework.boot.json.BasicJsonParser;
import org.springframework.boot.json.JsonParser;
import org.springframework.security.access.prepost.PreAuthorize;
import com.lillygourmet.cash.register.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UserController {

	private static Logger _log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	PasswordEncoder encoder;
	@Autowired
	private UserService userService;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
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

	// Updating user
	@PutMapping("api/Categories/{id}")
	public ResponseEntity<User> updateSessionPOS(@PathVariable Long id, @RequestBody String User) throws ResourceNotFoundException, ParseException {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("user not found for this id :: " + id));
		JsonParser parser = new BasicJsonParser();
		Map<String, Object> UserPOSJson = parser.parseMap(User);
		// Mapping Category object by JSON strategy
		user.setFirstName(UserPOSJson.get("FirstName").toString());
		user.setLastName(UserPOSJson.get("LastName").toString());
		user.setSexe(UserPOSJson.get("Sexe").toString());
		Date bdate=new SimpleDateFormat("yyyy-MM-dd").parse(UserPOSJson.get("Bdate").toString());
		user.setBdate(bdate);
		user.setAdress(UserPOSJson.get("Adress").toString());
		user.setEmail(UserPOSJson.get("Email").toString());
		user.setPhone(UserPOSJson.get("Phone").toString());
		user.setUsername(UserPOSJson.get("UserName").toString());
		user.setPassword(encoder.encode(UserPOSJson.get("Password").toString()));

		// get roles objects and add it to a list
		Set<Role> roles = new HashSet<>();
		((ArrayList) UserPOSJson.get("Roles")).forEach(role -> {
			String rolee = ((Map<String, Object>) role).get("RoleName").toString();
			switch (rolee) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				case "mod":
					Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(modRole);

					break;
				case "cashier":
					Role cachierRole = roleRepository.findByName(ERole.ROLE_CASHIER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(cachierRole);
					break;
				case "customer":
					Role customerRole = roleRepository.findByName(ERole.ROLE_CUSTOMER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(customerRole);
					break;
				case "waiter":
					Role waiterRole = roleRepository.findByName(ERole.ROLE_WAITER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(waiterRole);
					break;
				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
			}
		});
			user.setRoles(roles);
			user.setUpdatedBy(UserPOSJson.get("UpdatedBy").toString());
			User updatedUser = userService.updateUser(user);
			_log.info("update User controller...!");
			return new ResponseEntity<User>(updatedUser, new HttpHeaders(), HttpStatus.ACCEPTED);
		}
	}




