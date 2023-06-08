package tatar.mackshchim.sm.dto.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Exception info")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExceptionDto {

    @Schema(description = "Exception text", example = "User not found")
    private String message;

    @Schema(description = "Exception's HTTP-code", example = "404")
    private int status;
}
