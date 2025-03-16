package gabrielcoelhox.com.github.controller;

import gabrielcoelhox.com.github.dto.OrderDTO;
import gabrielcoelhox.com.github.dto.order.OrderRequest;
import gabrielcoelhox.com.github.model.User;
import gabrielcoelhox.com.github.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderDTO>> getMyOrders(@AuthenticationPrincipal User user) {
        log.info("Buscando pedidos do usuário: {}", user.getUsername());
        return ResponseEntity.ok(orderService.getOrdersByUser(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable UUID id, @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(orderService.getOrderById(id, user));
    }

    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@Valid @RequestBody OrderRequest request, @AuthenticationPrincipal User user) {
        return new ResponseEntity<>(orderService.createOrder(request, user), HttpStatus.CREATED);
    }

    @PostMapping("/{id}/payment")
    public ResponseEntity<OrderDTO> processPayment(@PathVariable UUID id, @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(orderService.processPayment(id, user));
    }

    @PostMapping("/{id}/cancel")
    public ResponseEntity<OrderDTO> cancelOrder(@PathVariable UUID id, @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(orderService.cancelOrder(id, user));
    }

    @GetMapping("/admin/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        log.info("Buscando todos os pedidos (acesso administrativo)");
        return ResponseEntity.ok(orderService.getAllOrders());
    }
}