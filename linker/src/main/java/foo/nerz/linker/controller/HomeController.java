package foo.nerz.linker.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import foo.nerz.linker.dao.LinkDao;
import foo.nerz.linker.entity.Link;

import foo.nerz.linker.entity.Users;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	Gson gson = new Gson();
	
	@Autowired
	LinkDao linkDao;
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
//		Date date = new Date();
//		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
//		
//		String formattedDate = dateFormat.format(date);
//		
//		model.addAttribute("serverTime", formattedDate );
		
		return "index";
	}
	
	
	/**
     * Handles request for adding two numbers
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<String> add(@RequestParam(value="urll", required=true) String url,
    							@RequestParam(value="title", required=true) String title,
    							@RequestParam(value="readed", required=true) boolean readed,
    							Model model) {
		logger.debug("Received request to add two numbers");
		

		// Delegate to service to do the actual adding
		int id=linkDao.addLink(new Link(url, title, readed,getUsers()));

		// @ResponseBody will automatically convert the returned value into JSON format
		// You must have Jackson in your classpath
		
		System.out.println("Fatto!!");
		
		return createJsonResponse( id );

	}
    
    @RequestMapping(value = "/getAll", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<String> getAll(
    							Model model) {
		logger.debug("Received request to add two numbers");
		
		// Delegate to service to do the actual adding
		List<Link> result=linkDao.getByUser(getUsers());

		List<Link> list = Collections.synchronizedList(new ArrayList<Link>() );
		
		for(Link current : result){
			current.setUsername(null);
			list.add(current);
		}
		
		// @ResponseBody will automatically convert the returned value into JSON format
		// You must have Jackson in your classpath
		
		System.out.println("Fatto!!");
		
		return createJsonResponse( list );

	}
    
	/**
     * Handles request for adding two numbers
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<String> add(@RequestParam(value="id", required=true) int id,
    							
    							Model model) {
		logger.debug("Received request to delete "+id);
		
		//TODO controllo sull utente
		
		linkDao.deletById(id);

		System.out.println("Fatto!! cancellato "+id);
		
		return createJsonResponse( true );

	}
    
    
    private ResponseEntity<String> createJsonResponse( Object o )
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String json = gson.toJson( o );
        return new ResponseEntity<String>( json, headers, HttpStatus.CREATED );
    }
    
    private Users getUsers(){
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    String name = user.getUsername(); //get logged in username
	    Users u=new Users(user.getUsername(),user.getPassword(),user.isEnabled());
	    return u;
    }
	
}
