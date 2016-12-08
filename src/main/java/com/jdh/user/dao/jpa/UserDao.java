package com.jdh.user.dao.jpa;

import com.jdh.user.domain.User;

import java.util.List;

public interface UserDao {

	public void persistUser(User user);
	public void persistUsers(List<User> users);
	public void updateUser(User user);
	public List<User> getAllUsers() throws Exception;
	public User getUserById(Integer userId) throws Exception;
	public User getUserByUsername(String username);
	public void deleteUserById(Integer userId);
	public void deleteAllUsers();

}
