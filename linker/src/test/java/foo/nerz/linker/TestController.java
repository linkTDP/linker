package foo.nerz.linker;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import foo.nerz.linker.controller.HomeController;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="root-context.xml")
@WebAppConfiguration
public class TestController {
	
	@Autowired private WebApplicationContext ctx;
	 
    private MockMvc mockMvc;
 
    @Before public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    }
    
    @Configuration
    public static class TestConfiguration {
 
    	
        @Bean public HomeController homeController() {
            return new HomeController();
        }
 
    }
    
    @Test public void helloWorld() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/app/").accept(MediaType.ALL))
                .andExpect(MockMvcResultMatchers.status().isOk());
        
//        mockMvc.perform(
//                MockMvcRequestBuilders.post("/app/getAll").accept(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isCreated());
//                
                
    }
    
    @Test public void login() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/login").accept(MediaType.ALL))
                .andExpect(MockMvcResultMatchers.status().isOk());
        

                
                
    }
	
   
}
