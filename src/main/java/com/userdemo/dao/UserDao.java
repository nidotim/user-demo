package com.userdemo.dao;

import java.util.List;

import com.userdemo.entity.User;

public interface UserDao {

	public List<User> getAll();

	public User get(Integer id);

	public void save(User user);

	public void update(Integer id, User user);

	public void delete(Integer id);
}
