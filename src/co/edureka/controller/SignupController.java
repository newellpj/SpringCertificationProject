package co.edureka.controller;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import co.edureka.service.UsersRolesAuthoritiesService;
import co.edureka.viewmodel.UsersModel;


@Controller
public class SignupController {
	
	private final static Logger logger = Logger.getLogger(SignupController.class); 
	
	@RequestMapping("signup")
	public ModelAndView signup(){
		return new ModelAndView("signup", "usersModel", new UsersModel());
	}
	
	
	@RequestMapping(value = "/signupSubmitted", method = RequestMethod.POST)
	public ModelAndView signupDetailsSubmitted(Model model, @Valid UsersModel usersModel, BindingResult result){

		System.out.println("signupDetailsSubmitted : "+usersModel.getUsername()+" : "+usersModel.getPassword());	
		UsersRolesAuthoritiesService userService = new UsersRolesAuthoritiesService();		
		ModelAndView modelAndView = new ModelAndView();
		boolean userAvailable = userService.isUsernameAvailable(usersModel.getUsername());
		
		if(!userAvailable){
			modelAndView.addObject("message", "User name "+usersModel.getUsername()+" is not available please try another");
			modelAndView.setViewName("signup");
		}else{
			userService.addUser(usersModel);
			modelAndView.addObject("message", "Congratulations now please sign in");
			modelAndView.setViewName("login");
		}
		
		modelAndView.addObject("usersModel", usersModel);	
		System.out.println("is user available "+userService.isUsernameAvailable(usersModel.getUsername()));
		return modelAndView;
	}

}
