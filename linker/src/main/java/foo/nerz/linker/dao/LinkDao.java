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
	@Transactional
	public boolean existUrl(String url){
		if(this.findByUrl(url).size()>0)return true;
		else return false;
	}

	@Transactional(readOnly=false)
	public int addLink(Link p) {
		int result=-1;
		if(!this.existUrl(p.getUrl())){
			sessionFactory.getCurrentSession().save(p);
			result=this.getIdfromLink(p.getUrl(), p.getTitle());
		}
		System.out.println("inserito - "+result);
		return result;		
		
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
	public Link findByUrlTitle(String url, String title){
		Query q=sessionFactory.getCurrentSession().createQuery("From Link where Url=:url and Title=:title");
		q.setParameter("url", url);
		q.setParameter("title", title);
		List<Link> current=(List<Link>)q.list();
		if(current.size()==0)return null;
		else return current.get(0);
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
	public void deletById(int id){
		Link lin=this.findById(id);
		if(lin!=null)this.deleteByLink(lin);
	}
	
	@Transactional
	public Link findById(int id) {
		Query q=sessionFactory.getCurrentSession().createQuery("From Link where Pid=:id");
		q.setParameter("id", id);
		
		List<Link> current=(List<Link>)q.list();
		if(current.size()==0)return null;
		else return current.get(0);
		
	}

	@Transactional
	public void deleteByUrlTitle(String url, String title){
		Link result=this.findByUrlTitle(url,title);
		
		this.deleteByLink(result);
		
	}
	
	@Transactional
	public int getIdfromLink(String url, String title){
		Link result=this.findByUrlTitle(url, title);
		if(result!=null){
			return result.getPid();
		}else return -1;
	}
	
}
