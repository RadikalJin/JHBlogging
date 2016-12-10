package com.jdh.user.domain;

import javax.persistence.*;

@Entity
@Table(name = "USER")
public class User implements java.io.Serializable {

	@Id
	@Column(name = "USER_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "USERNAME", nullable = false)
	private String username;

	@Column(name = "PASSWORD", nullable = true)
	private String password;

	@Column(name = "EMAIL", nullable = true)
	private String emailAddress;

	public User() {
		super();
	}

	public User(String username, String password){
		this.username = username;
		this.password = password;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	@Override
	public String toString() {
		return "User details: {" +
				"Id = " + id +
				", Username = '" + username + '\'' +
				", Email = '" + emailAddress + '\'' +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		User user = (User) o;

		if (id != null ? !id.equals(user.id) : user.id != null) return false;
		if (username != null ? !username.equals(user.username) : user.username != null) return false;
		return !(emailAddress != null ? !emailAddress.equals(user.emailAddress) : user.emailAddress != null);

	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (username != null ? username.hashCode() : 0);
		result = 31 * result + (emailAddress != null ? emailAddress.hashCode() : 0);
		return result;
	}
}
