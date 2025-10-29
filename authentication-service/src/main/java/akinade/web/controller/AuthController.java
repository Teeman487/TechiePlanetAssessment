package akinade.web.controller;
import akinade.domain.model.AuthRequest;
import akinade.domain.model.AuthResponse;;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final akinade.domain.service.AuthService authService;

    public AuthController(akinade.domain.service.AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<akinade.domain.model.AuthResponse> register(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}
