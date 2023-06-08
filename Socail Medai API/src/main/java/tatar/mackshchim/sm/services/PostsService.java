package tatar.mackshchim.sm.services;

import org.springframework.stereotype.Service;
import tatar.mackshchim.sm.dto.post.NewOrEditedPostDTO;
import tatar.mackshchim.sm.dto.post.PostDTO;
import tatar.mackshchim.sm.dto.post.PostsPage;

@Service
public interface PostsService {
    PostDTO getPost(Long postID);

    PostDTO addPost(NewOrEditedPostDTO newPost);

    PostDTO editPost(Long postID, NewOrEditedPostDTO editedPost);

    void deletePost(Long postID);

    PostsPage getAllPosts(int page);
}
