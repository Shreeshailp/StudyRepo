package com.shree.ws.userservice;

import java.util.List;

import com.shree.ws.model.User;
import com.shree.ws.userdao.UserDAO;

public class UserService {
	UserDAO userDAO = new UserDAO();

	public List<User> getAllUsers() {

		List<User> userList = userDAO.getAllUsers();

		return userList;
	}

	public User getUserForId(String id) {
		User user = userDAO.getUserForId(id);
		return user;
	}

	public User createUser(User user) {
		User userResponse = userDAO.createUser(user);
		return userResponse;
	}

	public User updateUser(User user) {
		User userResponse = userDAO.updateUser(user);
		return userResponse;
	}

	public User deleteUser(String id) {
		User userResponse = userDAO.deleteUser(id);
		return userResponse;
	}

}
