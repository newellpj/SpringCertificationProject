package co.edureka.service;

import java.util.List;

import org.apache.solr.common.SolrDocumentList;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import co.edureka.solr.SolrSearchManager;

public class SolrSearchService {
	private ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
	private SolrSearchManager solrSearchManager = (SolrSearchManager) ctx.getBean("solrSearchManager");
	
	public SolrSearchService(){
		solrSearchManager.init();
	}
	
	public String[] getFieldsArray(){
		return solrSearchManager.getFieldArray();
	}
	
	public SolrDocumentList performQueryPaginated(String queryString, int rows, int offset){
		return solrSearchManager.performQueryPaginated(queryString, rows, offset);
	}
	
	public SolrDocumentList performQuery(String queryString){
		return solrSearchManager.performQuery(queryString);
	}
	
	public void addDocument(String... fields){
		solrSearchManager.addDocument(fields);
	}
	
	public void deleteDocument(List idList){
		solrSearchManager.deleteDocument(idList);
	}
	
	public String performFacetedQuery(String query){
		return solrSearchManager.performFacetedQuery(query);
	}
}
