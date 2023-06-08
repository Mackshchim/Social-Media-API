package tatar.mackshchim.sm.controllers.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tatar.mackshchim.sm.dto.post.PostsPage;

@Tags(value = {
        @Tag(name = "Activity Feed")
})
@RequestMapping("/feed")
public interface ActivityFeedAPI {

    @Operation(summary = "Getting The activity feed for current user")
    @ApiResponses(value = {

            @ApiResponse(responseCode = "200", description = "The current user following authors posts page",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = PostsPage.class))
                    })

    })
    @GetMapping
    ResponseEntity<PostsPage> getActivityFeed(
            @Parameter(description = "The number of page")
            @RequestParam("page") int page);

}
