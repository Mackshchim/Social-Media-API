package tatar.mackshchim.sm.controllers;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import tatar.mackshchim.sm.dto.post.PostsPage;
import tatar.mackshchim.sm.services.ActivityFeedService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ActivityFeedControllerTest {

    public static final int page = 0;
    @InjectMocks
    private ActivityFeedController activityFeedController;

    @Mock
    public ActivityFeedService activityFeedService;

    @Test
    public void getActivityFeed() {
        final PostsPage postsPage = mock(PostsPage.class);
        when(activityFeedService.getActivityFeed(page)).thenReturn(postsPage);

        final ResponseEntity<PostsPage> actual = activityFeedController.getActivityFeed(page);

        assertNotNull(actual);
        assertEquals(actual,ResponseEntity.ok(postsPage));
        verify(activityFeedService).getActivityFeed(page);
    }
}
