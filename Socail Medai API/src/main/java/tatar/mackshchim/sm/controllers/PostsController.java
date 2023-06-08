package tatar.mackshchim.sm.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tatar.mackshchim.sm.controllers.api.PostsAPI;
import tatar.mackshchim.sm.dto.post.NewOrEditedPostDTO;
import tatar.mackshchim.sm.dto.post.PostDTO;
import tatar.mackshchim.sm.dto.post.PostsPage;
import tatar.mackshchim.sm.services.PostsService;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;



@RequiredArgsConstructor
@RestController
public class PostsController implements PostsAPI {

    private final PostsService postsService;

    @Override
    public ResponseEntity<PostsPage> getAllPosts(int page) {
        return ResponseEntity.ok(postsService.getAllPosts(page));
    }

    @Override
    public ResponseEntity<PostDTO> getPost(Long postID) {
        return ResponseEntity.ok(postsService.getPost(postID));
    }

    @Override
    public ResponseEntity<PostDTO> addPost(@Valid @RequestBody NewOrEditedPostDTO newPost) {
        return ResponseEntity.status(CREATED).body(postsService.addPost(newPost));
    }

    @Override
    public ResponseEntity<PostDTO> editPost(Long postID, @Valid NewOrEditedPostDTO editedPost) {
        return ResponseEntity.accepted().body(postsService.editPost(postID, editedPost));
    }

    @Override
    public ResponseEntity<?> deletePost(Long postID) {
        postsService.deletePost(postID);
        return ResponseEntity.noContent().build();
    }
}
