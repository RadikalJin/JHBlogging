package com.jdh.blog.domain;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "BLOG")
public class Blog implements java.io.Serializable {

	@Id
	@Column(name = "BLOG_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "BLOG_NAME", nullable = true)
	private String blogName;


	public Blog() {
		super();
	}

	public Blog(String blogName) {
		this.blogName = blogName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBlogName() {
		return blogName;
	}

	public void setBlogName(String blogName) {
		this.blogName = blogName;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Blog blog = (Blog) o;

		return id.equals(blog.id);

	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}
}
