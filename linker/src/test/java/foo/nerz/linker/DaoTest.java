package foo.nerz.linker;

import static org.junit.Assert.*;

import java.util.List;

import junit.framework.Assert;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import foo.nerz.linker.dao.LinkDao;
import foo.nerz.linker.entity.Link;

public class DaoTest {

	private static AbstractApplicationContext applicationContext;
	private static Log log = LogFactory.getLog(DaoTest.class);
	private static LinkDao linkDao;
	@BeforeClass
	public static void createApplicationContext(){
		try {
			applicationContext = new ClassPathXmlApplicationContext(
			        new String[] {"root-context.xml"});
			linkDao=(LinkDao)applicationContext.getBean("linkDao");
		}catch (Exception e) {
			log.error(e);
			Assert.assertNull(e);
		}
	}
	
	
	@Test
	public void testLinkDao(){
		
		linkDao.addLink(new Link("1","afafda",true));
		
		linkDao.addLink(new Link("2","aaaaa",true));
		
		linkDao.addLink(new Link("3","llll",true));
		
		linkDao.addLink(new Link("4","afafda",true));
		
		List<Link> result= linkDao.getAll();
		
		assertTrue(result.size()>3);
		
		int size=result.size();
		
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
		
		
	}
	
}
