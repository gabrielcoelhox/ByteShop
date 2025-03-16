package gabrielcoelhox.com.github.service;

import gabrielcoelhox.com.github.dto.UserDTO;
import gabrielcoelhox.com.github.dto.auth.AuthRequest;
import gabrielcoelhox.com.github.dto.auth.AuthResponse;
import gabrielcoelhox.com.github.dto.auth.RegisterRequest;
import gabrielcoelhox.com.github.model.User;
import gabrielcoelhox.com.github.repository.UserRepository;
import gabrielcoelhox.com.github.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new IllegalArgumentException("Username already exists. Please choose a different username");
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email already exists. Please choose a different email");
        }

        var user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .email(request.getEmail())
                .role(request.getRole())
                .build();

        userRepository.save(user);

        var jwt = jwtService.generateToken(user);

        return new AuthResponse(jwt, mapToDTO(user));
    }

    public AuthResponse authenticate(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("Invalid username or password"));

        var jwt = jwtService.generateToken(user);

        return new AuthResponse(jwt, mapToDTO(user));
    }

    private UserDTO mapToDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getUsername(),
                user.getName(),
                user.getEmail(),
                user.getRole()
        );
    }
}