package foo.nerz.linker;

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
		linkDao.addLink(new Link("sasa","afafda",true));
		
		linkDao.addLink(new Link("sasa","afafda",true));
		
		linkDao.addLink(new Link("sasa","afafda",true));
		
		linkDao.addLink(new Link("sasa","afafda",true));
		
		List<Link> result= linkDao.getAll();
		
		for(Link current : result ){
			System.out.println(current.getPid());
		}
	}
	
}
