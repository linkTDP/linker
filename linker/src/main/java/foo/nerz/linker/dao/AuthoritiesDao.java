package foo.nerz.linker.dao;

import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import foo.nerz.linker.entity.Authorities;
import foo.nerz.linker.entity.Users;

public class AuthoritiesDao {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public SessionFactory getSessionFactory() {
		
		  return sessionFactory;
		
	}
	
	//TODO controlli
	@Transactional
	public void addAuth(Authorities a){
		sessionFactory.getCurrentSession().save(a);
	}
	
	@Transactional
	public void deleteAuth(Authorities a){
		sessionFactory.getCurrentSession().delete(a);
	}
	
	@Transactional
	public boolean existAuthByUser(Users u, String auth){
		Authorities a =new Authorities(u, auth);
		Authorities b=null;
		b=(Authorities)sessionFactory.getCurrentSession().get(Authorities.class, a);
		if(b==null)return false;
		else return true;
	}
	
}
