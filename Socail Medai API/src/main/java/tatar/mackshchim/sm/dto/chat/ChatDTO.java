package tatar.mackshchim.sm.dto.chat;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Schema(description = "The messages page, all pages count")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatDTO {

    @Schema(description = "The one user's ID")
    private Long firstUserID;
    @Schema(description = "The another user's ID")
    private Long secondUserID;

    @Schema(description = "The page's messages list")
    private List<MessageDTO> messages;

    @Schema(description = "Total pages count")
    private Integer totalPagesCount;
}
