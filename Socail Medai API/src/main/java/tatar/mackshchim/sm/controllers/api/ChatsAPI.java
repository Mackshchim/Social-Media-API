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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tatar.mackshchim.sm.dto.chat.ChatDTO;
import tatar.mackshchim.sm.dto.exception.ExceptionDto;

@Tags(value = {
        @Tag(name = "Chats")
})
@RequestMapping("/chat")
public interface ChatsAPI {

    @Operation(summary = "Getting the chat with certain user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Current User ID, Interlocutor ID, messages",
            content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ChatDTO.class))
            }),
            @ApiResponse(responseCode = "404", description = "Exception info",
            content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionDto.class))
            })
    })
    @GetMapping("/{interlocutor-id}")
    ResponseEntity<ChatDTO> getChat(
            @Parameter(description = "Interlocutor's ID")
            @PathVariable("interlocutor-id") Long interlocutorID,

            @Parameter(description = "The number of page")
            @RequestParam("page") int page);

}
