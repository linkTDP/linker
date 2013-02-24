package foo.nerz.linker.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.Type;



@Entity
@Table(name="link")
public class Link {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private int pid;
	
	@Column(nullable=false)
	private String url;
	
	private String title;
	private boolean readed;
	
	@Type(type="timestamp")
	private Date date;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="username",nullable=false)
	private Users username;
	
	
	public Link() {
		super();
	}
	
	
	
	public Link(String url) {
		super();
		this.url = url;
	}



	public Link(String url, String title, boolean readed,Users user) {
		super();
		this.url = url;
		this.title = title;
		this.readed = readed;
		this.username = user;
	}


	public Link( String url, String title, boolean readed, 
			Users username, Date date) {
		super();

		this.url = url;
		this.title = title;
		this.readed = readed;
		this.date = date;
		this.username = username;
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

	public Users getUsername() {
		return username;
	}

	public void setUsername(Users username) {
		this.username = username;
	}
		
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
