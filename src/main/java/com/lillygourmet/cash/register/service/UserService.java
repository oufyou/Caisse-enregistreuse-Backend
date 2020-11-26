/*
 * Caisse Enregistreuse App - API REST
 * COded By Alias King - Younes OUFRID !!
 * Mail : oufridyounes@gmail.com
 * MNS team coders
 * */

package com.lillygourmet.cash.register.service;
/**
 * User Service
 *
 * @author Alias King - Younes OUFRID
 */
import com.lillygourmet.cash.register.model.User;
import com.lillygourmet.cash.register.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService {
	@Autowired
	PasswordEncoder encoder;

	@Autowired
	private UserRepository userRepository;


	public List<User> retrieveAllUsers() {
		return userRepository.findAll();
	}

	public User getUser(Long id) {
		return userRepository.getOne(id);
	}

	public User createUser(User user) {
		return userRepository.save(user);
	}

	@Transactional
	public void deleteUser(Long id) throws Exception {

		User user = userRepository.getOne(id);
		if (user != null) {
			userRepository.deleteById(id);
		} else {
			throw new RuntimeException("Record not found");
		}
	}

	public User updateUser(User user) {
		User oldUser = userRepository.getOne(user.getId());

		if(oldUser.getPassword().equals(user.getPassword())){
			System.out.println("Already hashed");
		}else{
			user.setPassword(encoder.encode(user.getPassword()));
		}
		return userRepository.save(user);
	}
}