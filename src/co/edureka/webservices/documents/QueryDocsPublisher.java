package co.edureka.webservices.documents;

import javax.xml.ws.Endpoint;

public class QueryDocsPublisher {
	
	public static void main(String[] args) {
		Endpoint endpoint = Endpoint.publish("http://localhost:8080/ws/queryDocs", new QueryDocsImpl());
		
		System.out.println("binding : "+endpoint.getBinding());
		System.out.println("is published : "+endpoint.isPublished());
		
    }
}
