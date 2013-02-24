package foo.nerz.linker.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import foo.nerz.linker.entity.Users;

public class UserDao {
	
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public SessionFactory getSessionFactory() {
		
		  return sessionFactory;
		
	}
	
	
	//TODO controlli
	@Transactional
	public void addUser(Users u){
		sessionFactory.getCurrentSession().save(u);
	}
	
	@Transactional
	public boolean existUsername(String username){
		Query query;
		query=sessionFactory.getCurrentSession().createQuery("From Users where Username=:username");
		query.setParameter("username", username);
		List<Users> result=query.list();
		if(result.size()>0)return true;
		else return false;
	}
	
	@Transactional
	public void deleteUserByUsername(String username){
		Query query;
		query=sessionFactory.getCurrentSession().createQuery("From Users where Username=:username");
		query.setParameter("username", username);
		List<Users> result=query.list();
		Users u=result.get(0);
		sessionFactory.getCurrentSession().delete(u);
	}

	@Transactional
	public boolean existMail(String mail) {
		Query query;
		query=sessionFactory.getCurrentSession().createQuery("From Users where Email=:mail");
		query.setParameter("mail", mail);
		List<Users> result=query.list();
		if(result.size()>0)return true;
		else return false;
	}
	
	

}
