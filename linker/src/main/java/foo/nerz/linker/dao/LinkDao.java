package foo.nerz.linker.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import foo.nerz.linker.entity.Link;

public class LinkDao {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional(readOnly=false)
	public void addPerson(Link p) {
		Session session = sessionFactory.openSession();
		session.save(p);
		session.close();		
	}
	
}
