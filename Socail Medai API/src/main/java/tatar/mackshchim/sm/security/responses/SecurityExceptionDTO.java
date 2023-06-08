package tatar.mackshchim.sm.security.responses;

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
public class SecurityExceptionDTO {

    @Schema(description = "Exception text")
    private String message;

    @Schema(description = "Exception's HTTP-code")
    private int status;
}
