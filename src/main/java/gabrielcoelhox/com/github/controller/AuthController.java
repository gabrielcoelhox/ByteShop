package gabrielcoelhox.com.github.controller;

import gabrielcoelhox.com.github.dto.auth.AuthenticationRequest;
import gabrielcoelhox.com.github.dto.auth.AuthenticationResponse;
import gabrielcoelhox.com.github.dto.auth.RegisterRequest;
import gabrielcoelhox.com.github.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Autenticação", description = "Endpoints de autenticação e registro")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    @Operation(
        summary = "Registrar novo usuário",
        description = "Cria uma nova conta de usuário e retorna um token JWT para autenticação"
    )
    @ApiResponse(
        responseCode = "200", 
        description = "Usuário registrado com sucesso",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponse.class))
    )
    @ApiResponse(
        responseCode = "400", 
        description = "Dados inválidos ou usuário já existente"
    )
    public ResponseEntity<AuthenticationResponse> register(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    @Operation(
        summary = "Fazer login",
        description = "Autentica um usuário existente e retorna um token JWT para acesso ao sistema"
    )
    @ApiResponse(
        responseCode = "200", 
        description = "Login realizado com sucesso",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponse.class))
    )
    @ApiResponse(
        responseCode = "403", 
        description = "Credenciais inválidas"
    )
    public ResponseEntity<AuthenticationResponse> authenticate(@Valid @RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }
}