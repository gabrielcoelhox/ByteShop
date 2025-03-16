package gabrielcoelhox.com.github.dto;

import gabrielcoelhox.com.github.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private UUID id;
    private String username;
    private String name;
    private String email;
    private UserRole role;
}