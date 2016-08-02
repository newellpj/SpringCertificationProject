package co.edureka.webservices.documents;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

public class QueryDocsClient {
	public static void main(String[] args) throws Exception {
		   
		URL url = new URL("http://localhost:8080/ws/queryDocs?wsdl");
		
	        //1st argument service URI, refer to wsdl document above
		//2nd argument is service name, refer to wsdl document above
	        QName qname = new QName("http://documents.webservices.edureka.co/", "QueryDocsImplService");

	        Service service = Service.create(url, qname);

	        QueryDocs queryDocs = service.getPort(QueryDocs.class);

	        System.out.println(queryDocs.queryAuthor("newelly"));

	    }
}
