package co.edureka.solr;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;


public class SolrSearchManager {
	
	private final static Logger log = Logger.getLogger(SolrSearchManager.class); 
	
	private SolrClient solr;
	
	@Autowired
	private String connectionString;
	
	public SolrSearchManager(){
	}

	public String getConnectionString() {
		return connectionString;
	}

	public void setConnectionString(String connectionString) {
		this.connectionString = connectionString;
	}
	
	public void init(){
		log.info("connection string : "+connectionString);
		
		solr = new HttpSolrClient(connectionString);
	}
	
	public String query(String queryString){
		
		SolrQuery query = new SolrQuery();
		query.setQuery(queryString);
		query.setFields("id","content_type","extended_properties_application", "page_count", "author");
		
		try{
			QueryResponse response = solr.query(query);
			log.info(response.toString());
		}catch(Exception e){
			e.printStackTrace();
			log.error("Error occured when querying Solr : "+e.getMessage());
		}
		return "";
	}
	
	public void  addDocument(){
		SolrInputDocument document = new SolrInputDocument();
		document.addField("id", "552199");
		document.addField("name", "Gouda cheese wheel");
		document.addField("price", "49.99");
		
		try{
			UpdateResponse response = solr.add(document);
			solr.commit();
		}catch(Exception e){	
			log.error("error occured while trying to add doc to SOLR : "+e.getMessage());
			e.printStackTrace();
		}
	}
	
}
