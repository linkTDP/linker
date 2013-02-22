package foo.nerz.linker.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name="authorities")
public class Authorities implements Serializable {
	@Id
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="username", referencedColumnName="username")
	private Users username;
	@Id
	private String authority;

	
	public Authorities() {
		super();
	}

	public Authorities(Users username, String authority) {
		super();
		this.username = username;
		this.authority = authority;
	}

	public Users getUsers() {
		return username;
	}

	public void setUsers(Users username) {
		this.username = username;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
	
	
}
