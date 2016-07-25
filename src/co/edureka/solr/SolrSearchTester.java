package co.edureka.solr;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SolrSearchTester {

	
	public static void main(String args[]){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		SolrSearchManager solrSearchManager = (SolrSearchManager) ctx.getBean("solrSearchManager");
		solrSearchManager.init();
		//solrSearchManager.query("author:Newelly");
		//solrSearchManager.addDocument("id:C:/Users/newelly/Pictures/ebay/wallet/DSC_0001.JPG", "author:Smithfield", "extended_properties_application:Microsoft Office Word");
		solrSearchManager.performQuery("author:Newelly");
		
//		List ids = new ArrayList();
//		ids.add("C:/Users/newelly/Pictures/ebay/wallet/DSC_0001.JPG");		
//		solrSearchManager.deleteDocument(ids);
//		solrSearchManager.performQuery("author:Smithfield");
	}
}

