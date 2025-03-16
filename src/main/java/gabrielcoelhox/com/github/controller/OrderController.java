package gabrielcoelhox.com.github.controller;

import gabrielcoelhox.com.github.dto.OrderDTO;
import gabrielcoelhox.com.github.dto.order.OrderRequest;
import gabrielcoelhox.com.github.model.User;
import gabrielcoelhox.com.github.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Pedidos", description = "Operações relacionadas a pedidos")
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    @Operation(
        summary = "Listar meus pedidos",
        description = "Retorna todos os pedidos do usuário autenticado"
    )
    @ApiResponse(
        responseCode = "200", 
        description = "Lista de pedidos retornada com sucesso"
    )
    public ResponseEntity<List<OrderDTO>> getMyOrders(@AuthenticationPrincipal User user) {
        log.info("Buscando pedidos do usuário: {}", user.getUsername());
        return ResponseEntity.ok(orderService.getOrdersByUser(user));
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Buscar pedido por ID",
        description = "Retorna os detalhes de um pedido específico do usuário autenticado"
    )
    @ApiResponse(
        responseCode = "200", 
        description = "Pedido encontrado com sucesso"
    )
    @ApiResponse(
        responseCode = "403", 
        description = "Usuário não tem permissão para visualizar este pedido"
    )
    @ApiResponse(
        responseCode = "404", 
        description = "Pedido não encontrado"
    )
    public ResponseEntity<OrderDTO> getOrderById(
            @Parameter(description = "ID único do pedido") 
            @PathVariable UUID id, 
            @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(orderService.getOrderById(id, user));
    }

    @PostMapping
    @Operation(
        summary = "Criar novo pedido",
        description = "Cria um novo pedido para o usuário autenticado com os produtos especificados"
    )
    @ApiResponse(
        responseCode = "201", 
        description = "Pedido criado com sucesso"
    )
    @ApiResponse(
        responseCode = "400", 
        description = "Dados inválidos ou estoque insuficiente"
    )
    public ResponseEntity<OrderDTO> createOrder(@Valid @RequestBody OrderRequest request, @AuthenticationPrincipal User user) {
        return new ResponseEntity<>(orderService.createOrder(request, user), HttpStatus.CREATED);
    }

    @PostMapping("/{id}/payment")
    @Operation(
        summary = "Processar pagamento",
        description = "Processa o pagamento de um pedido pendente e atualiza o estoque"
    )
    @ApiResponse(
        responseCode = "200", 
        description = "Pagamento processado com sucesso"
    )
    @ApiResponse(
        responseCode = "400", 
        description = "Pedido não está pendente ou estoque insuficiente"
    )
    public ResponseEntity<OrderDTO> processPayment(@PathVariable UUID id, @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(orderService.processPayment(id, user));
    }

    @PostMapping("/{id}/cancel")
    @Operation(
        summary = "Cancelar pedido",
        description = "Cancela um pedido pendente"
    )
    @ApiResponse(
        responseCode = "200", 
        description = "Pedido cancelado com sucesso"
    )
    @ApiResponse(
        responseCode = "400", 
        description = "Pedido não pode ser cancelado"
    )
    public ResponseEntity<OrderDTO> cancelOrder(@PathVariable UUID id, @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(orderService.cancelOrder(id, user));
    }

    @GetMapping("/admin/all")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(
        summary = "Listar todos os pedidos (Admin)",
        description = "Retorna todos os pedidos de todos os usuários (apenas para administradores)"
    )
    @ApiResponse(
        responseCode = "200", 
        description = "Lista de pedidos retornada com sucesso"
    )
    @ApiResponse(
        responseCode = "403", 
        description = "Acesso negado - apenas administradores"
    )
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        log.info("Buscando todos os pedidos (acesso administrativo)");
        return ResponseEntity.ok(orderService.getAllOrders());
    }
}