package tatar.mackshchim.sm.dto.post;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Size;
import java.util.List;

@Schema(description = "New post")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewOrEditedPostDTO {

    @Schema(description = "Post's header")
    @Size(min = 2, max = 255, message = "{newOrEditedPost.header.size}")
    private String header;

    @Schema(description = "Post's text")
    @Size(min = 2, max = 65535, message = "{newOrEditedPost.text.size}")
    private String text;

    @Schema(description = "Attached images")
    @Size(max = 10)
    private List<MultipartFile> images;
}
