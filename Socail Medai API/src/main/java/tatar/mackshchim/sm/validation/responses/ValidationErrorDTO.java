package tatar.mackshchim.sm.validation.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Exception info")
public class ValidationErrorDTO {

    @Schema(description = "Field name")
    private String field;
    @Schema(description = "Object name")
    private String object;
    @Schema(description = "Exception text")
    private String message;

}
