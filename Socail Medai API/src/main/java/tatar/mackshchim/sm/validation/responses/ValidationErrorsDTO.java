package tatar.mackshchim.sm.validation.responses;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "All validation exceptions")
public class ValidationErrorsDTO {

    @Schema(description = "All validation exceptions list")
    private List<ValidationErrorDTO> errors;
}
