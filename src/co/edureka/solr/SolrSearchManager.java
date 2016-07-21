package co.edureka.solr;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;


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
		log.info("querying solr..");
		SolrQuery query = new SolrQuery();
		query.setQuery(queryString);
		query.setFields("id","content_type","extended_properties_application", "page_count", "author");
		
		try{
			QueryResponse response = solr.query(query);
			SolrDocumentList solrList = response.getResults();
			for(SolrDocument doc : solrList){
				log.info(doc.toString());
			}
			
			log.info(response.toString());
		}catch(Exception e){
			e.printStackTrace();
			log.error("Error occured when querying Solr : "+e.getMessage());
		}
		return "";
	}
	
	public void addDocument(String... fields){
		log.info("adding document to solr..");
		SolrInputDocument document = new SolrInputDocument();
		
		for(String field : fields){
			String[] fieldValuePair = StringUtils.split(field, ":");
			document.addField(fieldValuePair[0], fieldValuePair[1]);
		}
		
		try{
			UpdateResponse response = solr.add(document);
			solr.commit();
		}catch(Exception e){	
			log.error("error occured while trying to add doc to SOLR : "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void deleteDocument(List idList){
		
		try{
			solr.deleteById(idList);
			solr.commit();
		}catch(Exception e){
			e.printStackTrace();
			log.error("error deleting records : "+e.getMessage());
		}
	}
	
}
