package tatar.mackshchim.sm.dto.post;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tatar.mackshchim.sm.models.Image;

import java.util.List;
import java.util.stream.Collectors;

@Schema(description = "Image and info")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageDTO {

    @Schema(description = "Original file name", example = "sm_logo.png")
    private String originalFileName;
    @Schema(description = "Image size")
    private Long size;
    @Schema(description = "Content type", example = "multipart/form-data")
    private String contentType;
    @Schema(description = "The bytes of Image")
    private byte[] bytes;

    public static ImageDTO from(Image image) {
        return ImageDTO.builder()
                .originalFileName(image.getOriginalFileName())
                .size(image.getSize())
                .contentType(image.getContentType())
                .bytes(image.getBytes())
                .build();
    }

    public static List<ImageDTO> from(List<Image> images) {
        if (images == null) {
            return null;
        }
        return images.stream()
                .map(ImageDTO::from)
                .collect(Collectors.toList());
    }

}
