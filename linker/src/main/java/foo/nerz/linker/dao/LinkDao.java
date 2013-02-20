package foo.nerz.linker.dao;

import java.util.List;

import org.hibernate.Query;
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
				System.out.println("inserito");
	}
	
	@Transactional
	public List<Link> getAll(){
		List<Link> result = (List<Link>)sessionFactory.getCurrentSession().createQuery("From Link").list(); 
		return result;
	}
	
	@Transactional
	public List<Link> findByUrl(String url){
		Query q=sessionFactory.getCurrentSession().createQuery("From Link where Url=:url");
		q.setParameter("url", url);
		List<Link> current=(List<Link>)q.list();
		return current;
	}
	
	@Transactional
	public List<Link> findByUrlTitle(String url, String title){
		Query q=sessionFactory.getCurrentSession().createQuery("From Link where Url=:url and Title=:title");
		q.setParameter("url", url);
		q.setParameter("title", title);
		List<Link> current=(List<Link>)q.list();
		return current;
	}
	
	@Transactional
	public void deleteByLink(Link link){
		sessionFactory.getCurrentSession().delete(link);
	}
	
	@Transactional
	public void deleteByUrl(String url){
		List<Link> result=this.findByUrl(url);
		for(Link current : result){
			this.deleteByLink(current);
		}
	}
	
	@Transactional
	public void deleteByUrlTitle(String url, String title){
		List<Link> result=this.findByUrlTitle(url,title);
		for(Link current : result){
			this.deleteByLink(current);
		}
	}
	
}
