package tatar.mackshchim.sm.services;

import tatar.mackshchim.sm.dto.post.PostsPage;

public interface ActivityFeedService {
    PostsPage getActivityFeed(int page);
}
