package co.edureka.config;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

import co.edureka.controller.PaginationController;

public class SessionListener implements HttpSessionListener  {
	
	private final static Logger log = Logger.getLogger(SessionListener.class); 
	
	@Override
    public void sessionCreated(HttpSessionEvent event) {
		log.info("==== Session is created ====");
		System.out.println("==== Session is created ====");
        event.getSession().setMaxInactiveInterval(1*60);
        log.info("event source obj : "+event.getSource().getClass());

    }
 
    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        log.info("==== Session is destroyed ====");
    }
}
