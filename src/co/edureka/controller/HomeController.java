package co.edureka.controller;



import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class HomeController {

	private final static Logger logger = Logger.getLogger(HomeController.class); 
	
	@RequestMapping(value = { "/home"}, method = RequestMethod.GET)
	public ModelAndView homePage() {
		
		System.out.println("homePage()");
		System.out.println("we here??? home controller");
       
		ModelAndView model = new ModelAndView();		
		model.setViewName("home");
		return model;

	}

	

	
}
