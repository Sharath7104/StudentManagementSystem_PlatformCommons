package com.app.service;

import com.app.exception.UserException;
import com.app.model.User;

public interface UserService {
	
	public User getUserDetailsByEmail(String email)throws UserException;
}
