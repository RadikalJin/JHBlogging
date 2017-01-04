package com.jdh.post.domain;

import com.jdh.blog.domain.Blog;
import com.jdh.tag.domain.Tag;
import com.jdh.user.domain.User;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

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

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "POST_TAG",
            joinColumns =
                    { @JoinColumn(name = "POST_ID", referencedColumnName = "POST_ID") },
            inverseJoinColumns =
                    { @JoinColumn(name = "TAG_ID", referencedColumnName = "TAG_ID")}
    )
    private List<Tag> tags;

    @PrePersist
    protected void onCreate() {
        createdDate = new Date();
    }

    public Post() {
        super();
    }

    public Post(Blog blog, User user, String title, Calendar createdDate, String bannerImageURL, String content, List<Tag> tags) {
        this.blog = blog;
        this.user = user;
        this.title = title;
        this.createdDate = createdDate.getTime();
        this.bannerImageURL = bannerImageURL;
        this.content = content;
        this.tags = tags;
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

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Post post = (Post) o;

        if (id != null ? !id.equals(post.id) : post.id != null) return false;
        if (blog != null ? !blog.equals(post.blog) : post.blog != null) return false;
        if (user != null ? !user.equals(post.user) : post.user != null) return false;
        if (title != null ? !title.equals(post.title) : post.title != null) return false;
        if (createdDate != null ? !createdDate.equals(post.createdDate) : post.createdDate != null) return false;
        if (bannerImageURL != null ? !bannerImageURL.equals(post.bannerImageURL) : post.bannerImageURL != null)
            return false;
        if (content != null ? !content.equals(post.content) : post.content != null) return false;
        return !(tags != null ? !tags.equals(post.tags) : post.tags != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (blog != null ? blog.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (bannerImageURL != null ? bannerImageURL.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (tags != null ? tags.hashCode() : 0);
        return result;
    }
}
