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
import tatar.mackshchim.sm.dto.user.NewOrEditedUserDTO;
import tatar.mackshchim.sm.dto.user.UserDTO;
import tatar.mackshchim.sm.dto.user.UsersPage;

@Tags(value = {
        @Tag(name = "Users")
})
@RequestMapping("/users")
public interface UsersAPI {

    @Operation(summary = "Getting the user by ID")
    @ApiResponses(value = {

            @ApiResponse(responseCode = "200", description = "The user info",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = UserDTO.class))
                    }),

            @ApiResponse(responseCode = "404", description = "Exception info",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionDto.class))
                    })
    })
    @GetMapping("/{user-id}")
    ResponseEntity<UserDTO> getUser(
            @Parameter(description = "The desired user's ID")
            @PathVariable("user-id") Long userID);

    @Operation(summary = "Getting the page of All users")
    @ApiResponses(value = {

            @ApiResponse(responseCode = "200", description = "The users page",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = UsersPage.class))
                    }),

    })
    @GetMapping
    ResponseEntity<UsersPage> getAllUsers(
            @Parameter(description = "The number of page")
            @RequestParam("page") int page);


    @Operation(summary = "Creating the new user")
    @ApiResponses(value = {

            @ApiResponse(responseCode = "201", description = "Created user",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = UserDTO.class))
                    }),

            @ApiResponse(responseCode = "409", description = "Exception info",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionDto.class))
                    })
    })
    @PostMapping
    ResponseEntity<UserDTO> addUser(@RequestBody NewOrEditedUserDTO newUser);


    @Operation(summary = "Following the user")
    @ApiResponses(value = {

            @ApiResponse(responseCode = "204", description = "The user is followed"),

            @ApiResponse(responseCode = "404", description = "Exception info",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionDto.class))
                    }),

            @ApiResponse(responseCode = "409", description = "Exception info",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionDto.class))
                    })
    })
    @PutMapping("/{user-id}/follow")
    ResponseEntity<?> followUser(@PathVariable("user-id") Long UserID);


    @Operation(summary = "Unfollowing the user")
    @ApiResponses(value = {

            @ApiResponse(responseCode = "204", description = "The user is unfollowed"),

            @ApiResponse(responseCode = "404", description = "Exception info",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionDto.class))
                    })

    })
    @PutMapping("/{user-id}/unfollow")
    ResponseEntity<?> unfollowUser(@PathVariable("user-id") Long UserID);



}
