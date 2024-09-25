package com.synergisticit.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.synergisticit.domain.User;
@Repository(value="userDao")
public class UserDaoImpl implements UserDao {

	SessionFactory sessionFactory;

	public UserDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public User findById(Long userId) {
		User u = null;
		try(Session session = sessionFactory.openSession();){
			session.beginTransaction();
			u = session.get(User.class, userId);
			session.getTransaction().commit();
			System.out.println("u: " + u);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return u;
	}

}
