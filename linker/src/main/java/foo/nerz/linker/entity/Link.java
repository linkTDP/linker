package foo.nerz.linker.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="link")
public class Link {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private int pid;
	
	private String url;
	private String title;
	private boolean readed;
	
	
	
	
	public Link() {
		super();
	}
	public Link(String url, String title, boolean readed) {
		super();
		this.url = url;
		this.title = title;
		this.readed = readed;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public boolean isReaded() {
		return readed;
	}
	public void setReaded(boolean readed) {
		this.readed = readed;
	}
	
	
	

}
