package co.edureka.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class MethodBeforeAdvisor {

	@Pointcut("execution(* co.edureka.hibernate.*.*(..))")
   public void sessionInterceptor(){
	   
   }
	
	@Before("sessionInterceptor()")
	public void doBeforeTask(){
		
	}

}
