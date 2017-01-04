package jdh;

import com.jdh.blog.dto.BlogDto;
import com.jdh.blog.service.BlogService;
import com.jdh.blog.service.BlogServiceImpl;
import com.jdh.post.dto.PostDto;
import com.jdh.post.service.PostService;
import com.jdh.post.service.PostServiceImpl;
import com.jdh.tag.dto.TagDto;
import com.jdh.tag.service.TagService;
import com.jdh.tag.service.TagServiceImpl;
import com.jdh.user.dao.jpa.UserDao;
import com.jdh.user.domain.User;
import com.jdh.user.dto.UserDto;
import com.jdh.user.service.UserService;
import com.jdh.user.service.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/test-context.xml")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class PostTest {

	List<PostDto> testPosts;
	Boolean keepData = false;

	@Resource(name = BlogServiceImpl.BEAN_NAME)
	private BlogService blogService;

	@Resource(name = PostServiceImpl.BEAN_NAME)
	private PostService postService;

	@Resource(name = TagServiceImpl.BEAN_NAME)
	private TagService tagService;

	@Resource(name = UserServiceImpl.BEAN_NAME)
	private UserService userService;

/*	@Before
	public void setUp() throws Exception {
		testPosts = new ArrayList<>();
		PostDto testPost1 = new PostDto();
		testPost1.setTitle("TEST_TITLE");
		testPost1.setContent("TEST_CONTENT");
		testPost1.sesetDueDate(Calendar.getInstance());
		PostDto testPost2 = new PostDto();
		testPost2.setTitle("TEST_POST_2");
		testPost2.setDescription("TEST_PASSWORD_2");
		testPost2.setDueDate(Calendar.getInstance());
		PostDto testPost3 = new PostDto();
		testPost3.setTitle("TEST_POST_3");
		testPost3.setDescription("TEST_PASSWORD_3");
		testPost3.setDueDate(Calendar.getInstance());
		testPosts.add(testPost1);
		testPosts.add(testPost2);
		testPosts.add(testPost3);
	}*/

	@Test
	public void shouldFindUsersByUsername() {
		String testUsername = "TEST USERNAME";
		UserDto testUser = new UserDto();
		testUser.setUsername(testUsername);
		testUser.setEmailAddress("TEST EMAIL");
		testUser.setPassword("TEST PASSWORD");
		try {
			userService.persistUser(testUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
		UserDto userByUsername = userService.getUserByUsername(testUsername);
		assertTrue(testUser.getUsername().equals(userByUsername.getUsername()));
	}

	private UserDto addTestUser(String testUsername) {
		UserDto testUser = new UserDto();
		testUser.setUsername(testUsername);
		testUser.setEmailAddress("TEST EMAIL");
		testUser.setPassword("TEST PASSWORD");
		try {
			userService.persistUser(testUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
		UserDto userByUsername = userService.getUserByUsername(testUsername);
		return userByUsername;
	}

	private BlogDto addTestBlog(String blogName) {
		BlogDto testBlog = new BlogDto();
		testBlog.setBlogName(blogName);
		try {
			blogService.persistBlog(testBlog);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<BlogDto> blogsByBlogName = blogService.getBlogsByBlogName(blogName);
		BlogDto persistedBlog = blogsByBlogName.get(0);
		return persistedBlog;
	}

	@Test
	public void saveNewPost() {
		String testUsername = "TEST USERNAME";
		UserDto testUser = addTestUser(testUsername);

		String testBlogName = "TEST_BLOG";
		BlogDto testBlog = addTestBlog(testBlogName);

		PostDto testPost = new PostDto();
		testPost.setBlogId(testBlog.getId());
		String testPostTitle = "TEST_TITLE";
		testPost.setTitle(testPostTitle);
		testPost.setContent("TEST_CONTENT");
		testPost.setUserId(testUser.getId());
		try {
			postService.persistPost(testPost);
		} catch (Exception e) {
			e.printStackTrace();
		}

		List<PostDto> postsByUsername = postService.getPostsByUsername(testUsername);
		PostDto postByUsername = postsByUsername.get(0);
		assertTrue(postByUsername.getTitle().equals(testPostTitle));
	}


	@Test
	public void saveNewPostWithTestTag() {
		String testUsername = "TEST USERNAME";
		UserDto testUser = addTestUser(testUsername);

		String testBlogName = "TEST_BLOG";
		BlogDto testBlog = addTestBlog(testBlogName);

		PostDto testPost = new PostDto();
		testPost.setBlogId(testBlog.getId());
		String testPostTitle = "TEST_TITLE";
		testPost.setTitle(testPostTitle);
		testPost.setContent("TEST_CONTENT");
		testPost.setUserId(testUser.getId());

		try {
			postService.persistPost(testPost);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}

		List<PostDto> postsByUsername = postService.getPostsByUsername(testUsername);
		PostDto postByUsername = postsByUsername.get(0);

		List<TagDto> tags = new ArrayList<TagDto>();
		TagDto tag = new TagDto();
		tag.setTagName("TEST TAG NAME");
		tags.add(tag);
		postByUsername.setTags(tags);

		try {
			postService.updatePost(postByUsername);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}

		List<PostDto> updatedPostsByUsername = postService.getPostsByUsername(testUsername);
		PostDto updatedPostByUsername = updatedPostsByUsername.get(0);
		assertTrue(updatedPostByUsername.getTags().size() > 0);
	}

	@Test
	public void saveNewTag() {
		TagDto testTag = new TagDto();
		testTag.setTagName("TEST");
		try {
			tagService.persistTag(testTag);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

/*	*//*
	*  PERSISTENCE
	*//*
	@Test
	public void testPersistingSinglePostUsingHibernate() throws Exception {
		PostDto testPost = testPosts.get(0);
		try {
			postService.getPostsByPostTitle(testPost.getTitle());
		} catch (NoResultException e) {
			System.out.println("Caught exception as expected when finding post by name, when does not exist");
		}
		postService.persistPost(testPost);
		assertNotNull(postService.getPostsByPostTitle(testPost.getTitle()));
	}*/

/*	@Test
	public void testUpdatingPersistedPostUsingHibernate() throws Exception {
		PostDto prePersistPost = testPosts.get(0);
		postService.persistPost(prePersistPost);
		PostDto postPersistPost = postService.getPostsByPostTitle(prePersistPost.getTitle());
		assertNotNull(postPersistPost);
		assertEquals(prePersistPost.getPassword(), postPersistPost.getPassword());
		postPersistPost.setPassword("Something new");
		postService.updatePost(postPersistPost);
		PostDto updatedPost = postService.getPostByPostname(prePersistPost.getPostname());
		assertNotNull(updatedPost);
		assertNotEquals(prePersistPost.getPassword(), postPersistPost.getPassword());
	}

	@Test
	public void testGetPersistedPostById() throws Exception {
		postService.persistPosts(testPosts);
		PostDto persistedPost = postService.getAllPosts().get(0);
		assertNotEquals(null, postService.getPostById(Integer.parseInt(persistedPost.getId())));
	}

	@Test
	public void testDeletePersistedPostById() throws Exception {
		postService.persistPosts(testPosts);
		PostDto persistedPost = postService.getAllPosts().get(0);
		Integer persistedPostId = Integer.parseInt(persistedPost.getId());
		assertNotNull(postService.getPostById(persistedPostId));
		postService.deletePostById(persistedPostId);
		assertNull(postService.getPostById(persistedPostId));
	}

	@Test
	public void testGetPostsByPostId() throws Exception {
		postService.persistPosts(testPosts);
		PostDto persistedPost = postService.getAllPosts().get(0);
		PostDto testPost = new PostDto();
		testPost.setArtist("TEST_ARTIST");
		testPost.setTitle("TEST_TITLE");
		testPost.setReleaseDate(Calendar.getInstance());
		postService.persistPost(testPost);
		List<PostDto> postsByArtistName = postService.getPostsByArtistName(testPost.getArtist());
		PostDto persistedPost = postsByArtistName.get(0);
		postService.persistPostToPost(Integer.parseInt(persistedPost.getId()), Integer.parseInt(persistedPost.getId()));
		List<PostPostDto> postPostsByPostId = postService.getPostPostsByPostId(Integer.parseInt(persistedPost.getId()));
		assertTrue(postPostsByPostId.size() > 0);
	}
	*/

}