package com.example.blogsite.domain.dto;

import com.example.blogsite.domain.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequestDto {
    @NotNull(message = "User email should not be empty!")
    @Email(message = "User email is not in the expected format!")
    private String email;

    @NotNull(message = "Password should not be empty!")
    @Pattern(
            regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,30}$",
            message = "Password must contain at least one letter, one number, and one special character!"
    )
    @Size(min = 8, max = 30, message = "Password should contain between {min} and {max} characters!")
    private String password;

    @NotNull(message = "User role should not be empty!")
    private Role role;

    @NotNull(message = "Username should not be empty!")
    @Size(min = 3, max = 20, message = "Username should be between {min} and {max} characters!")
    private String username;
}
