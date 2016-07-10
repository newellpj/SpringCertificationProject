package co.edureka.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController implements AuthenticationSuccessHandler{

	private final static Logger logger = Logger.getLogger(LoginController.class); 
	
	private String defaultTargetUrl;
	
	@RequestMapping(value = { "/"}, method = RequestMethod.GET)
	public ModelAndView welcomePage() {

		ModelAndView model = new ModelAndView();		
		model.setViewName("login");
		return model;

	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		System.out.println("logout!!!!!!");
		
		request.getSession().invalidate();
	    SecurityContextHolder.getContext().setAuthentication(null);
		
		ModelAndView model = new ModelAndView();
		model.addObject("error", "Successfully logged out!");
		model.setViewName("logout");
		
		System.out.println(" we here again logout!!!!!!");
		
		//response.sendRedirect("logout");
		
		
		
		return model;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error) {
		
		//request.getSession(true);
		System.out.println(" we again logining!!!!!!");
		ModelAndView model = new ModelAndView();
		if (error != null) {
			System.out.println("error != null : "+error);
			model.addObject("error", "Incorrect username and password!");
			model.setViewName("login");	
		}else{
			model.setViewName("login");
		}
		
		return model;

	}
	
	@RequestMapping("accessDenied")
	public String accessDenied(){
		System.out.println("accessDenied");
		return "accessDenied";
		
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest arg0, HttpServletResponse response, Authentication arg2)
			throws IOException, ServletException {
		System.out.println("onAuthenticationSuccess");
		response.sendRedirect("reviews");
	}

	public String getDefaultTargetUrl() {
		return defaultTargetUrl;
	}

	public void setDefaultTargetUrl(String defaultTargetUrl) {
		this.defaultTargetUrl = defaultTargetUrl;
	}

	
	
}
