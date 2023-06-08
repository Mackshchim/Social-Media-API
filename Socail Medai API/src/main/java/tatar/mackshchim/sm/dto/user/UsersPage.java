package tatar.mackshchim.sm.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Schema(description = "The users page and all pages count")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsersPage {

    @Schema(description = "The page's users list")
    private List<UserDTO> users;

    @Schema(description = "Total count of pages", example = "1" )
    private int totalPagesCount;
}
