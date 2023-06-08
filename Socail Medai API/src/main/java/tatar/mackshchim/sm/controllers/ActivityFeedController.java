package tatar.mackshchim.sm.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import tatar.mackshchim.sm.controllers.api.ActivityFeedAPI;
import tatar.mackshchim.sm.dto.post.PostsPage;
import tatar.mackshchim.sm.services.ActivityFeedService;

@RestController
@RequiredArgsConstructor
public class ActivityFeedController implements ActivityFeedAPI {

    private final ActivityFeedService activityFeedService;

    @Override
    public ResponseEntity<PostsPage> getActivityFeed(int page) {
        return ResponseEntity.ok(activityFeedService.getActivityFeed(page));
    }
}
