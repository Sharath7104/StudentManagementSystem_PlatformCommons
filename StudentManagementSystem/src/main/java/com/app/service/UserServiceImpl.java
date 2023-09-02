package com.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.exception.UserException;
import com.app.model.User;
import com.app.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	 private final UserRepository userRepository;

	    @Autowired
	    public UserServiceImpl(UserRepository userRepository) {
	        this.userRepository = userRepository;
	    }

	    @Override
	    public User getUserDetailsByEmail(String email) throws UserException {
	        // Find the user by email
	        Optional<User> userOptional = userRepository.findByEmail(email);

	        if (userOptional.isPresent()) {
	            // Return the user if found
	            return userOptional.get();
	        } else {
	            // Handle the case where the user with the specified email is not found
	            throw new UserException("User with email " + email + " not found");
	        }
	    }

}
