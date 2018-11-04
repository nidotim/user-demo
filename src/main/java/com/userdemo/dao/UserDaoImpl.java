package com.userdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.userdemo.entity.User;

@Repository
public class UserDaoImpl implements UserDao {
	@Autowired
	private SessionFactory sessionFactory;
			
	@Override
	public List<User> getAll() {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<User> theQuery = currentSession.createQuery("from User", User.class);
		List<User> users = theQuery.getResultList();
		return users;
	}

	@Override
	public void save(User user) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.save(user);
	}

	@Override
	public User get(Integer userId) {
		Session currentSession = sessionFactory.getCurrentSession();
		return currentSession.get(User.class, userId);
	}

	@Override
	public void update(Integer userId, User user) {
		// User user = null;
		Session currentSession = sessionFactory.getCurrentSession();
		User oriUser = currentSession.get(User.class, userId);
		if (null == oriUser) {
			return;
		}
		oriUser.updateAttrs(user);
		currentSession.save(oriUser);
	}

	@Override
	public void delete(Integer id) {
		Session currentSession = sessionFactory.getCurrentSession();
		User user = currentSession.get(User.class, id);
		if (null == user) {
			return;
		}
		currentSession.delete(user);
	}

}
