package com.userdemo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USER")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "email")
	private String email;

	@Column(name = "name")
	private String name;

	public User() {

	}

	public User(String name, String email) {
		this.name = name;
		this.email = email;
	}

	public User(Long id, String name) {
		this.id = id.intValue();
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void updateAttrs(User user) {
		if (null != user.getName()) {
			setName(user.getName());
		}
		if (null != user.getEmail()) {
			setEmail(user.getEmail());
		}
	}

}
