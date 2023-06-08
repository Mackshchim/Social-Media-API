package tatar.mackshchim.sm.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tatar.mackshchim.sm.dto.post.NewOrEditedPostDTO;
import tatar.mackshchim.sm.dto.post.PostDTO;
import tatar.mackshchim.sm.dto.post.PostsPage;
import tatar.mackshchim.sm.services.PostsService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PostsControllerTest {

    private final static Long ID = 1L;
    private final static int page = 0;

    private final static HttpStatus status = HttpStatus.OK;

    @InjectMocks
    private PostsController postsController;

    @Mock
    private PostsService postsService;

    @Test
    public void getAllPosts() {

        final PostsPage postsPage = mock(PostsPage.class);
        when(postsService.getAllPosts(page)).thenReturn(postsPage);

        final ResponseEntity<PostsPage> actual = postsController.getAllPosts(page);

        assertNotNull(actual);
        assertEquals(actual, ResponseEntity.ok(postsPage));
        verify(postsService).getAllPosts(page);

    }

    @Test
    public void getPost() {

        final PostDTO postDTO = mock(PostDTO.class);
        when(postsService.getPost(ID)).thenReturn(postDTO);

        final ResponseEntity<PostDTO> actual = postsController.getPost(ID);

        assertNotNull(actual);
        assertEquals(ResponseEntity.ok(postDTO),actual);
        verify(postsService).getPost(ID);
    }

    @Test
    public void addPost() {

        final NewOrEditedPostDTO newPostDTO = mock(NewOrEditedPostDTO.class);
        final PostDTO postDTO = mock(PostDTO.class);
        when(postsService.addPost(newPostDTO)).thenReturn(postDTO);

        final ResponseEntity<PostDTO> actual = postsController.addPost(newPostDTO);

        assertNotNull(actual);
        assertEquals(actual,ResponseEntity.status(HttpStatus.CREATED).body(postDTO));
        verify(postsService).addPost(newPostDTO);

    }

    @Test
    public void editPost() {

        final NewOrEditedPostDTO editedPostDTO = mock(NewOrEditedPostDTO.class);
        final PostDTO postDTO = mock(PostDTO.class);
        when(postsService.editPost(ID,editedPostDTO)).thenReturn(postDTO);

        final ResponseEntity<PostDTO> actual = postsController.editPost(ID,editedPostDTO);

        assertNotNull(actual);
        assertEquals(actual, ResponseEntity.status(HttpStatus.ACCEPTED).body(postDTO));
        verify(postsService).editPost(ID,editedPostDTO);
    }

    @Test
    public void deletePost() {

        final ResponseEntity<?> actual = postsController.deletePost(ID);

        assertNotNull(actual);
        assertEquals(actual, ResponseEntity.status(HttpStatus.NO_CONTENT).build());
    }

}
