package foo.nerz.linker;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import foo.nerz.linker.dao.LinkDao;
import foo.nerz.linker.dao.UserDao;
import foo.nerz.linker.dao.AuthoritiesDao;
import foo.nerz.linker.entity.Authorities;
import foo.nerz.linker.entity.Link;
import foo.nerz.linker.entity.Users;

public class DaoTest {

	private static AbstractApplicationContext applicationContext;
	private static Log log = LogFactory.getLog(DaoTest.class);
	private static LinkDao linkDao;
	private static UserDao usersDao;
	private static AuthoritiesDao authDao;
	
	@BeforeClass
	public static void createApplicationContext(){
		try {
			applicationContext = new ClassPathXmlApplicationContext(
			        new String[] {"root-context.xml"});
			linkDao=(LinkDao)applicationContext.getBean("linkDao");
			usersDao=(UserDao)applicationContext.getBean("userDao");
			authDao=(AuthoritiesDao)applicationContext.getBean("authDao");
		}catch (Exception e) {
			log.error(e);
			Assert.assertNull(e);
		}
	}
	
	
	@Test
	public void testLinkDao(){
		
		if(usersDao.existUsername("prova"))usersDao.deleteUserByUsername("prova");
		
		Users u=new Users("prova", "password", true,"a");
		
		usersDao.addUser(u);
		
		assertTrue(usersDao.existUsername(u.getUsername()));
		
		linkDao.addLink(new Link("1","afafda",true, u));
		
		linkDao.addLink(new Link("2","aaaaa",true, u));
		
		linkDao.addLink(new Link("3","llll",true, u));
		
		linkDao.addLink(new Link("4","afafda",true, u));
		
		Authorities a=new Authorities(u, "PROVA");
		
		authDao.addAuth(a);
		
		assertTrue(authDao.existAuthByUser(u, "PROVA"));
		
		assertTrue(linkDao.getByUser(u).size()>2);
		
		List<Link> result= linkDao.getAll();
		
		assertTrue(result.size()>2);
	
		result=linkDao.findByUrl("3");
		
		assertTrue(result.size()==1);
		
		Link lin=linkDao.findByUrlTitle("3", "llll");
		
		assertNotNull(lin);
		
		linkDao.deleteByUrlTitle("3", "llll");
		
		lin=null;
		
		lin=linkDao.findByUrlTitle("3", "llll");
		
		assertNull(lin);
		
		linkDao.deleteByUrl("3");
		
		result=linkDao.findByUrl("3");
		
		assertTrue(result.size()==0);
		
		linkDao.deleteByUrl("1");
		
		linkDao.deleteByUrl("2");
		
		result= linkDao.getAll();
		
//		assertTrue(result.size()==(size-4));
		
		authDao.deleteAuth(a);
		
		assertFalse(authDao.existAuthByUser(u, "PROVA"));
		
		usersDao.deleteUserByUsername(u.getUsername());
		
		assertFalse(usersDao.existUsername(u.getUsername()));
		
		//cascade double
		
		if(usersDao.existUsername("prova"))usersDao.deleteUserByUsername("prova");
		
		u=new Users("prova", "password", true,"a");
		
		usersDao.addUser(u);
		
		assertTrue(usersDao.existUsername(u.getUsername()));
		
		linkDao.addLink(new Link("1","afafda",true, u));
		
		linkDao.addLink(new Link("2","aaaaa",true, u));
		
		linkDao.addLink(new Link("3","llll",true, u));
		
		linkDao.addLink(new Link("4","afafda",true, u));
		
		a=new Authorities(u, "PROVA");
		
		authDao.addAuth(a);
		
		assertTrue(authDao.existAuthByUser(u, "PROVA"));
		
		usersDao.deleteUserByUsername("prova");
		
		assertFalse(usersDao.existUsername("prova"));
		
		assertFalse(authDao.existAuthByUser(u, "PROVA"));
		
		assertTrue(linkDao.getByUser(u).size()==0);
		
		if(usersDao.existUsername("prova"))usersDao.deleteUserByUsername("prova");
		
		u=new Users("prova", "password", true,"a");
		
		usersDao.addUser(u);
		
		assertTrue(usersDao.existUsername(u.getUsername()));
		
		u.setEmail(null);
		
		linkDao.addLink(new Link("1","afafda",true, u));
		
		assertTrue(linkDao.existUrl("1"));
		
		linkDao.deleteByUrl("1");
		
		Date data=new Date();
		
		linkDao.addLink(new Link("1","afafda",true, u, data));
		
		assertTrue(linkDao.existUrl("1"));
		
		linkDao.deleteByUrl("1");
	}
	
	@Test
	public void testUser(){
		
		if(usersDao.existUsername("prova"))usersDao.deleteUserByUsername("prova");
		
		Users u=new Users("prova", "password", true,"a");
		
		usersDao.addUser(u);
		
		assertTrue(usersDao.existUsername(u.getUsername()));
		
		usersDao.deleteUserByUsername(u.getUsername());
		
		assertFalse(usersDao.existUsername(u.getUsername()));
		
		
		
	}
	
	
	@Test
	public void testAuth(){
		if(usersDao.existUsername("prova"))usersDao.deleteUserByUsername("prova");
		
		Users u=new Users("prova", "password", true,"a");
		
		usersDao.addUser(u);
		
		assertTrue(usersDao.existUsername(u.getUsername()));
		
		Authorities a=new Authorities(u, "PROVA");
		
		authDao.addAuth(a);
		
		assertTrue(authDao.existAuthByUser(u, "PROVA"));
		
		authDao.deleteAuth(a);
		
		assertFalse(authDao.existAuthByUser(u, "PROVA"));
		
		// cascade!!!
		
		if(usersDao.existUsername("prova"))usersDao.deleteUserByUsername("prova");
		
		u=new Users("prova", "password", true,"a");
		
		usersDao.addUser(u);
		
		assertTrue(usersDao.existUsername(u.getUsername()));
		
		a=new Authorities(u, "PROVA");
		
		authDao.addAuth(a);
		
		usersDao.deleteUserByUsername("prova");
		
		assertFalse(usersDao.existUsername("prova"));
		
		assertFalse(authDao.existAuthByUser(u, "PROVA"));
		
	}
	
}
