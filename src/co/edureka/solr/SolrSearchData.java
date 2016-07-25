package co.edureka.solr;

public class SolrSearchData {
	private String title;
	private String author; 
	private String keywordText;
	private String stream_content_type;
	private String id; //contains the URL to the doc
	
	public String gettitle() {
		return title;
	}
	public void settitle(String title) {
		this.title = title;
	}
	public String getauthor() {
		return author;
	}
	public void setauthor(String author) {
		this.author = author;
	}
	public String getKeywordText() {
		return keywordText;
	}
	public void setKeywordText(String keywordText) {
		this.keywordText = keywordText;
	}
	public String getstream_content_type() {
		return stream_content_type;
	}
	public void setstream_content_type(String stream_content_type) {
		this.stream_content_type = stream_content_type;
	}
	public String getid() {
		return id;
	}
	public void setid(String id) {
		this.id = id;
	}
	
	
}
