package jdh;

import com.jdh.post.dto.PostDto;
import com.jdh.post.service.PostService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.assertNotNull;

public class PostTest {

	ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
	PostService postService = (PostService) context.getBean("postService");
	List<PostDto> testPosts;
	Boolean keepData = false;

/*	@Resource(name = PostServiceImpl.BEAN_NAME)
	private PostService postService;*/

/*	@Resource(name = PostServiceImpl.BEAN_NAME)
	private PostService postService;*/

/*	@Before
	public void setUp() throws Exception {
		testPosts = new ArrayList<>();
		PostDto testPost1 = new PostDto();
		testPost1.setTitle("TEST_POST_1");
		testPost1.setDescription("TEST_PASSWORD_1");
		testPost1.setDueDate(Calendar.getInstance());
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
	}
*/

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