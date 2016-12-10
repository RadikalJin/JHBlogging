package com.jdh.post.domain;

import com.jdh.blog.domain.Blog;
import com.jdh.user.domain.User;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "POST")
public class Post implements java.io.Serializable {

	@Id
	@Column(name = "POST_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

    @OneToOne
	@JoinColumn(name="BLOG_ID", nullable = false)
    private Blog blog;

    @OneToOne
    @JoinColumn(name="USER_ID", nullable = false)
    private User user;

    @Column(name = "TITLE", nullable = true)
	private String title;

    @Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_DATE", nullable = false)
	private Date createdDate;

	@Column(name = "BANNER_IMAGE_URL", nullable = true)
	private String bannerImageURL;

    @Column(name = "CONTENT", nullable = true)
    private String content;

    @PrePersist
    protected void onCreate() {
        createdDate = new Date();
    }

    public Post() {
        super();
    }

    public Post(Blog blog, User user, String title, Calendar createdDate, String bannerImageURL, String content) {
        this.blog = blog;
        this.user = user;
        this.title = title;
        this.createdDate = createdDate.getTime();
        this.bannerImageURL = bannerImageURL;
        this.content = content;
    }

    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Calendar getCreatedDate() {
        Calendar instance = Calendar.getInstance();
        instance.setTime(createdDate);
        return instance;
    }

    public void setCreatedDate(Calendar createdDate) {
        this.createdDate = createdDate.getTime();
    }

    public String getBannerImageURL() {
        return bannerImageURL;
    }

    public void setBannerImageURL(String bannerImageURL) {
        this.bannerImageURL = bannerImageURL;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Post post = (Post) o;

        if (blog != null ? !blog.equals(post.blog) : post.blog != null) return false;
        if (user != null ? !user.equals(post.user) : post.user != null) return false;
        if (title != null ? !title.equals(post.title) : post.title != null) return false;
        if (createdDate != null ? !createdDate.equals(post.createdDate) : post.createdDate != null) return false;
        if (bannerImageURL != null ? !bannerImageURL.equals(post.bannerImageURL) : post.bannerImageURL != null)
            return false;
        return !(content != null ? !content.equals(post.content) : post.content != null);

    }

    @Override
    public int hashCode() {
        int result = blog != null ? blog.hashCode() : 0;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (bannerImageURL != null ? bannerImageURL.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        return result;
    }

}
