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
import org.springframework.web.bind.annotation.*;
import tatar.mackshchim.sm.dto.exception.ExceptionDto;
import tatar.mackshchim.sm.dto.post.NewOrEditedPostDTO;
import tatar.mackshchim.sm.dto.post.PostDTO;
import tatar.mackshchim.sm.dto.post.PostsPage;

@Tags(value = {
        @Tag(name = "Posts")
})
@RequestMapping("/posts")
public interface PostsAPI {


    @Operation(summary = "Getting the page of All posts")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The page of All posts",
            content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = PostsPage.class))
            })
    })
    @GetMapping
    ResponseEntity<PostsPage> getAllPosts(
            @Parameter(description = "The number of page")
            @RequestParam("page") int page);


    @Operation(summary = "Getting the post by ID")
    @ApiResponses(value = {

            @ApiResponse(responseCode = "200", description = "The page of All posts",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = PostsPage.class))
                    }),

            @ApiResponse(responseCode = "404", description = "Exception info",
            content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionDto.class))
            })
    })
    @GetMapping("/{post-id}")
    ResponseEntity<PostDTO> getPost(
            @Parameter(description = "The desired post's ID")
            @PathVariable("post-id") Long postID);


    @Operation(summary = "Creating new post")
    @ApiResponses(value = {

            @ApiResponse(responseCode = "201", description = "Created post",
            content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = PostDTO.class))
            }),

            @ApiResponse(responseCode = "409", description = "Exception info",
            content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionDto.class))
            })

    })
    @PostMapping
    ResponseEntity<PostDTO> addPost(@RequestBody NewOrEditedPostDTO newPost);

    @Operation(summary = "Editing the certain post")
    @ApiResponses(value = {

            @ApiResponse(responseCode = "202", description = "Edited post",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = PostDTO.class))
                    }),

            @ApiResponse(responseCode = "404", description = "Exception info",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionDto.class))
                    })

    })
    @PutMapping("/{post-id}")
    ResponseEntity<PostDTO> editPost(
            @Parameter(description = "ID of post for edit")
            @PathVariable("post-id") Long postID,

            @RequestBody NewOrEditedPostDTO editedPost);


    @Operation(summary = "Deleting the post")
    @ApiResponses(value = {

            @ApiResponse(responseCode = "204", description = "The post is deleted"),

            @ApiResponse(responseCode = "404", description = "Exception info",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionDto.class))
                    })

    })
    @DeleteMapping("/{post-id}")
    ResponseEntity<?> deletePost(
            @Parameter(description = "ID of post for delete")
            @PathVariable("post-id") Long postID);


}
