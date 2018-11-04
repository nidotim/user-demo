package com.userdemo.service;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.userdemo.dao.UserDao;
import com.userdemo.entity.User;

@Service
public class UserServiceImpl implements UserService {

	private static Logger log = Logger.getLogger(UserServiceImpl.class);

	@Autowired
	private UserDao userDao;

	@Transactional
	@Override
	public List<User> listUsers() throws Exception {
		log.debug("enters listUsers ... ");
		try {
			List<User> users = userDao.getAll();
			log.trace("total users :" + users.size());
			return users;
		} catch (Exception e) {
			log.error("listUsers failed ", e);
			throw e;
		}
	}

	@Transactional
	@Override
	public User createUser(String name, String email) throws Exception {
		log.debug("enters createUser ... ");
		try {
			User user = new User(name, email);
			userDao.save(user);
			return user;
		} catch (Exception e) {
			log.error("createUser failed ", e);
			throw e;
		}
	}

	@Transactional
	@Override
	public User getUser(Integer userId) throws Exception {
		log.debug("enters getUser ... ");
		try {
			User user = userDao.get(userId);
			log.trace("user " + userId + " found:" + user == null);
			return user;
		} catch (Exception e) {
			log.error("getUser failed ", e);
			throw e;
		}

	}

	@Transactional
	@Override
	public void updateUser(Integer userId, User user) throws Exception {
		log.debug("enters updateUser ... ");
		try {
			userDao.update(userId, user);
			log.trace("user " + userId + " found:" + user == null);
		} catch (Exception e) {
			log.error("updateUser failed ", e);
			throw e;
		}
	}

	@Transactional
	@Override
	public void deleteUser(Integer userId) throws Exception {
		log.debug("enters deleteUser ... ");
		try {
			userDao.delete(userId);
		} catch (Exception e) {
			log.error("deleteUser failed ", e);
			throw e;
		}
	}

}
