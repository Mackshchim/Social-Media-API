package tatar.mackshchim.sm.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import tatar.mackshchim.sm.dto.post.PostDTO;
import tatar.mackshchim.sm.dto.post.PostsPage;
import tatar.mackshchim.sm.models.Post;
import tatar.mackshchim.sm.repositories.PostsRepository;
import tatar.mackshchim.sm.repositories.UsersRepository;
import tatar.mackshchim.sm.services.ActivityFeedService;


@Service
@RequiredArgsConstructor
public class ActivityFeedServiceImpl implements ActivityFeedService {

    private final PostsRepository postsRepository;
    private final UsersRepository usersRepository;

    @Value("${default.page-size.posts}")
    private int DEFAULT_PAGE_SIZE;

    @Override
    public PostsPage getActivityFeed(int page) {

        PageRequest pageRequest = PageRequest.of(page, DEFAULT_PAGE_SIZE);
        Page<Post> postPage = postsRepository.findAllFriendsPostsByPageOfUser(pageRequest, getCurrentUserID());

        return PostsPage.builder()
                .posts(PostDTO.from(postPage.getContent()))
                .totalPagesCount(postPage.getTotalPages())
                .build();
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
