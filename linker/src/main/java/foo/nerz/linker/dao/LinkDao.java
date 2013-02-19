package foo.nerz.linker.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import foo.nerz.linker.entity.Link;

public class LinkDao {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public SessionFactory getSessionFactory() {
		
		  return sessionFactory;
		
		 }

	@Transactional(readOnly=false)
	public void addLink(Link p) {
		
		sessionFactory.getCurrentSession().save(p);
				
	}
	
	@Transactional
	public List<Link> getAll(){
		List<Link> result = (List<Link>)sessionFactory.getCurrentSession().createQuery("From Link").list(); 
		return result;
	}
	
}
