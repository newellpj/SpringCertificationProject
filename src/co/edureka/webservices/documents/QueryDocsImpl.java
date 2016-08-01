package co.edureka.webservices.documents;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

import org.apache.log4j.Logger;
import org.apache.solr.common.SolrDocument;

import co.edureka.service.SolrSearchService;
import co.edureka.solr.SolrSearchData;

@WebService(endpointInterface = "co.edureka.webservices.documents.QueryDocs")
public class QueryDocsImpl implements QueryDocs {

	private final static Logger log = Logger.getLogger(QueryDocsImpl.class); 
	
	private SolrSearchService sss;
	
	@Override
	public SolrSearchData[] queryTitle(String title){
		return performQuery("title:", title);
	}
	
	@Override
	public SolrSearchData[] queryAuthor(String authorName) {
		return performQuery("author:", authorName);
	}
	
	private SolrSearchData[] performQuery(String type, String value){
		sss = new SolrSearchService();
		List<SolrDocument> list = sss.performQuery(type+value);
		
		SolrSearchData ssd = null;
		
		SolrSearchData[] ssdArr = new SolrSearchData[list.size()];
		
		ArrayList<SolrSearchData> ssdList = new ArrayList<SolrSearchData>();
		
		int count = 0;
		
		for(SolrDocument solrD : list){
			
			ssd = new SolrSearchData();
			
			for(String field : sss.getFieldsArray()){
				String fieldToSet = (solrD.getFieldValue(field) != null) ? solrD.getFieldValue(field).toString() : "";
				
				try{
					Method method = ssd.getClass().getDeclaredMethod("set"+field, String.class);
					method.invoke(ssd, fieldToSet);
				}catch(Exception e){
					e.printStackTrace();
					log.error(e.getMessage());
				}
			}
			
			ssdArr[count] = ssd;
			count++;
		}
		
		log.info("size of results returning to service request : "+ssdList.size());
		
		return ssdArr;
	}

}
