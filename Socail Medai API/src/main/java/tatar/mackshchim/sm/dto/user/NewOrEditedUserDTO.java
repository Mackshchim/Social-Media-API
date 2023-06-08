package tatar.mackshchim.sm.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Schema(description = "New user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewOrEditedUserDTO {

    @Schema(description = "User's email")
    @Email(message = "{newOrEditedUser.email.email}")
    private String email;

    @Schema(description = "Username")
    @Size(min = 2, max = 63, message = "{newOrEditedUser.username.size}")
    private String username;

    @Schema(description = "User's password(is not hashcode)")
    @Size(min = 8, max = 255, message = "{newOrEditedUser.password.size}")
    private String password;

}
