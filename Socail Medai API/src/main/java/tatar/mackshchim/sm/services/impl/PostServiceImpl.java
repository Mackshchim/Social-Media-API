package tatar.mackshchim.sm.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tatar.mackshchim.sm.dto.post.NewOrEditedPostDTO;
import tatar.mackshchim.sm.dto.post.PostDTO;
import tatar.mackshchim.sm.dto.post.PostsPage;
import tatar.mackshchim.sm.exceptions.NotFoundException;
import tatar.mackshchim.sm.models.Image;
import tatar.mackshchim.sm.models.Post;
import tatar.mackshchim.sm.models.User;
import tatar.mackshchim.sm.repositories.PostsRepository;
import tatar.mackshchim.sm.repositories.UsersRepository;
import tatar.mackshchim.sm.services.PostsService;

import java.util.Date;


@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostsService {

    private final PostsRepository postsRepository;

    private final UsersRepository usersRepository;


    @Value("${default.page-size.posts}")
    private int DEFAULT_PAGE_SIZE;

    @Override
    public PostDTO getPost(Long postID) {
        Post post = getPostOrElseThrow(postID);
        return PostDTO.from(post);
    }

    @Transactional
    @Override
    public PostDTO addPost(NewOrEditedPostDTO newPost) {


        Post createdPost = Post.builder()
                .header(newPost.getHeader())
                .text(newPost.getText())
                .state(Post.State.POSTED)
                .postTime(getPresentTime())
                .author(getUserOrElseThrow(getCurrentUserID()))
                .images(Image.from(newPost.getImages()))
                .build();

        postsRepository.save(createdPost);

        return PostDTO.from(createdPost);
    }

    @Transactional
    @Override
    public PostDTO editPost(Long postID, NewOrEditedPostDTO editedPost) {

        Post postForEdit = getPostOrElseThrow(postID);

        postForEdit.setHeader(editedPost.getHeader());
        postForEdit.setText(editedPost.getText());
        postForEdit.setPostTime(getPresentTime());
        postForEdit.setImages(Image.from(editedPost.getImages()));

        postsRepository.save(postForEdit);

        return PostDTO.from(postForEdit);
    }

    @Transactional
    @Override
    public void deletePost(Long postID) {

        Post postForDelete = getPostOrElseThrow(postID);

        postForDelete.setState(Post.State.DELETED);

        postsRepository.save(postForDelete);

    }

    @Override
    public PostsPage getAllPosts(int page) {
        PageRequest pageRequest = PageRequest.of(page, DEFAULT_PAGE_SIZE);
        Page<Post> postsPage = postsRepository.findAllByStateOrderByPostTime(pageRequest, Post.State.POSTED);
        return PostsPage.builder()
                .posts(PostDTO.from(postsPage.getContent()))
                .totalPagesCount(postsPage.getTotalPages())
                .build();
    }

    private Post getPostOrElseThrow(Long postID) {
        return postsRepository.findById(postID)
                .orElseThrow(() -> new NotFoundException("The post with ID <" + postID + "> is not found"));
    }

    private static Date getPresentTime() {
        return new Date(System.currentTimeMillis());
    }

    private User getUserOrElseThrow(Long userID) {

        return usersRepository.findById(userID)
                .orElseThrow(
                        () -> new NotFoundException("The User with ID <" + userID + "> is not found")
                );
    }

    public Long getCurrentUserID() {
        return usersRepository.findByEmail(
                ((UserDetails) SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal())
                        .getUsername()
        ).get().getId();
    }

}
