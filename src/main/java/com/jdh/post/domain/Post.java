package com.jdh.post.domain;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "POST")
public class Post implements java.io.Serializable {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "TITLE", nullable = true)
	private String title;

	@Column(name = "DESCRIPTION", nullable = true)
	private String description;

	@Column(name = "DUE_DATE", nullable = true)
	private Calendar dueDate;

	public Post() {
		super();
	}

	public Post(String title, String description, Calendar dueDate) {
		this.title = title;
		this.description = description;
		this.dueDate = dueDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Calendar getDueDate() {
		return dueDate;
	}

	public void setDueDate(Calendar dueDate) {
		this.dueDate = dueDate;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Post post = (Post) o;

		if (description != null ? !description.equals(post.description) : post.description != null) return false;
		if (dueDate != null ? !dueDate.equals(post.dueDate) : post.dueDate != null) return false;
		if (title != null ? !title.equals(post.title) : post.title != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = title != null ? title.hashCode() : 0;
		result = 31 * result + (description != null ? description.hashCode() : 0);
		result = 31 * result + (dueDate != null ? dueDate.hashCode() : 0);
		return result;
	}
}
