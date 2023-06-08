package tatar.mackshchim.sm.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tatar.mackshchim.sm.models.User;

import java.util.List;
import java.util.stream.Collectors;

@Schema(description = "User info")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

    @Schema(description = "User's id", example = "1")
    private Long id;
    @Schema(description = "User's nickname", example = "Ildus")
    private String username;
    @Schema(description = "User's email", example = "ildus@xat.tatar")
    private String email;


    public static UserDTO from(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }

    public static List<UserDTO> from(List<User> users) {
        return users.stream()
                .map(UserDTO::from)
                .collect(Collectors.toList());
    }
}
