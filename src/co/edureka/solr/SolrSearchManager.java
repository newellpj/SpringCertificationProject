package co.edureka.solr;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;


public class SolrSearchManager {
	
	private final static Logger log = Logger.getLogger(SolrSearchManager.class); 
	
	private SolrClient solr;
	
	@Autowired
	private String solrFieldsList;
	
	@Autowired
	private String connectionString;
	
	public SolrSearchManager(){
	}

	public String getSolrFieldsList() {
		return solrFieldsList;
	}

	
	public String[] getFieldArray(){
		return solrFieldsList.split(":");
	}
	
	public void setSolrFieldsList(String solrFieldsList) {
		this.solrFieldsList = solrFieldsList;
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
	
	public String performFacetedQuery(String query){
		return null;
	}
	
	
	public SolrDocumentList performQuery(String queryString){
		return performQueryPaginated(queryString, 1000, 0);
	}
	
	
	
	public String extractAllDocumentContent(String documentURI){
		File file = new File(documentURI);
		
		if(file.length() < 2048000){
		
		    Tika tika = new Tika();
		      
		    try{
		    	return tika.parseToString(file);
		    }catch(Exception e){
	    	  e.printStackTrace();
	    	  log.error("Tika parse exception occured!");
	    	  log.error(e.getMessage());
	    	  return null;
		    }
		}else{
			return "file too large to parse please download document";
		}
		
      
	     
	}
	
	/**
	 * 
	 * @param documentURI
	 * @param characterCount - this does count white spaces
	 * @return
	 */
	public String extractSpecifiedDocumentContent(String documentURI, int characterCount){
		 File file = new File(documentURI);
	      Tika tika = new Tika();
	      
	      if(file.length() < 2048000){
	      
	      try{
	    	  String fileContent = tika.parseToString(file);
	    	  StringBuffer contentAppender = new StringBuffer();
	    	  
	    	  if(fileContent.length() > characterCount){
	    		  return fileContent.substring(0, characterCount);
	    	  }else{
	    		  return fileContent;
	    	  }

	    	  
	      }catch(Exception e){
	    	  e.printStackTrace();
	    	  log.error("Tika parse exception occured!");
	    	  log.error(e.getMessage());
	      }
	      
	      }else{
	    	  return "File too large to extract content. Please download file";
	      }
	      
	      return "No content extracted";
	}
	
	public SolrDocumentList performQueryPaginated(String queryString, int rows, int offset){
		log.info("querying solr.."+queryString+" with offset : "+offset+" with rows : "+rows);
		
		SolrQuery query = new SolrQuery();
		query.setQuery(queryString);
		query.setStart(offset);
		query.setRows(rows);
		//query.setFields("id","content_type","extended_properties_application","stream_content_type", "page_count", "author", "title");
		
		try{
			QueryResponse response = solr.query(query);
			
			log.info("request url :::: "+response.getRequestUrl());
			
			SolrDocumentList solrList = response.getResults();
			for(SolrDocument doc : solrList){
				
				log.info("content found by tika parser : "+this.extractSpecifiedDocumentContent(doc.getFieldValue("id").toString(), 1000));
				
				log.info(doc.toString());
			}
			
			log.info(response.toString());
			
			log.info("total results found : "+solrList.size());
			
			return solrList;
			
		}catch(Exception e){
			e.printStackTrace();
			log.error("Error occured when querying Solr : "+e.getMessage());
		}
		return null;
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
