package tatar.mackshchim.sm.dto.post;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tatar.mackshchim.sm.models.Post;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Schema(description = "Post info")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostDTO {

    @Schema(description = "The post ID")
    private Long id;

    @Schema(description = "The post header")
    private String header;

    @Schema(description = "The post's text")
    private String text;

    @Schema(description = "The post posted time")
    private Date postTime;

    @Schema(description = "The post's author ID")
    private Long authorID;

    @Schema(description = "Attached images")
    private List<ImageDTO> images;


    public static PostDTO from(Post post) {
        return PostDTO.builder()
                .id(post.getId())
                .header(post.getHeader())
                .text(post.getText())
                .postTime(post.getPostTime())
                .authorID(post.getAuthor().getId())
                .images(ImageDTO.from(post.getImages()))
                .build();
    }

    public static List<PostDTO> from(List<Post> posts) {
        return posts.stream()
                .map(PostDTO::from)
                .collect(Collectors.toList());
    }
}
