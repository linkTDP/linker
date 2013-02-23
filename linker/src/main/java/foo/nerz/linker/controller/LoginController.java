package foo.nerz.linker.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import foo.nerz.linker.dao.AuthoritiesDao;
import foo.nerz.linker.dao.UserDao;
import foo.nerz.linker.entity.Authorities;
import foo.nerz.linker.entity.Users;
import foo.nerz.linker.model.NewUser;

@Controller
public class LoginController {
	@Autowired
	UserDao userDao;
	@Autowired
	AuthoritiesDao authDao;
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Locale locale, ModelMap model) {
		logger.info("Welcome login! The client locale is {}.", locale);
		
		
		return "login";
	}
	
	@RequestMapping(value = "/newUser", method = RequestMethod.POST)
	public String newUser(@ModelAttribute("newUser") NewUser newUser,
			BindingResult result, SessionStatus status) {
		logger.info("Adding User Request");
		
		logger.info(newUser.getUsernamesignup());
		
		Users u=new Users(newUser.getUsernamesignup(), newUser.getPasswordsignup(), true, newUser.getEmailsignup());
		Authorities a=new Authorities(u, "ROLE_USER");
		
		userDao.addUser(u);
		
		authDao.addAuth(a);
 
		//TODO mandare un messaggio!
		
		return "login";
	}
	
	
	

}
