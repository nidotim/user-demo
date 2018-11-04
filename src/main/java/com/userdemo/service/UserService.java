package com.userdemo.service;

import java.util.List;

import com.userdemo.entity.User;

public interface UserService {

	public List<User> listUsers() throws Exception;

	public User createUser(String name, String email) throws Exception;

	public User getUser(Integer userId) throws Exception;

	public void updateUser(Integer userId, User user) throws Exception;

	public void deleteUser(Integer userId) throws Exception;

}
