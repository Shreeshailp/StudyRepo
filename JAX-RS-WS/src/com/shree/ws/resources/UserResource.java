package com.shree.ws.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.shree.ws.model.User;
import com.shree.ws.userservice.UserService;

@Path(value = "/users")
public class UserResource {
	private UserService userService = new UserService();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}
	
	/*@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllUsers() {
		return "HI Shreeshail";
	}*/

}
