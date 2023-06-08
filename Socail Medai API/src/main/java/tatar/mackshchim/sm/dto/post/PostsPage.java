package tatar.mackshchim.sm.dto.post;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Schema(description = "The posts page and all pages count")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostsPage {

    @Schema(description = "The page's posts list")
    private List<PostDTO> posts;

    @Schema(description = "Total pages count", example = "1")
    private Integer totalPagesCount;

}
