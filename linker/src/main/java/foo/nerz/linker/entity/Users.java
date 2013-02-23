package foo.nerz.linker.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="users")
public class Users {
	
	@Id	
	private String username;
	
	@Column(nullable=false)
	private String password;
	
	@Column(nullable=false)
	private boolean enabled;
	
	private String email;
	
	@OneToMany(mappedBy="username",cascade=CascadeType.ALL)
	  private List<Authorities> auth;
	
	@OneToMany(mappedBy="username",cascade=CascadeType.ALL)
	  private List<Link> link;
	

	public Users() {
		super();
	}

	public Users(String username, String password, boolean enabled, String email) {
		super();
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
