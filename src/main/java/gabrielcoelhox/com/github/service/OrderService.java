package gabrielcoelhox.com.github.service;

import gabrielcoelhox.com.github.dto.OrderDTO;
import gabrielcoelhox.com.github.dto.OrderItemDTO;
import gabrielcoelhox.com.github.dto.order.OrderItemRequest;
import gabrielcoelhox.com.github.dto.order.OrderRequest;
import gabrielcoelhox.com.github.model.*;
import gabrielcoelhox.com.github.repository.OrderItemRepository;
import gabrielcoelhox.com.github.repository.OrderRepository;
import gabrielcoelhox.com.github.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;
    private final ProductService productService;

    public List<OrderDTO> getOrdersByUser(User user) {
        return orderRepository.findByUser(user).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public OrderDTO getOrderById(Long id, User user) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado com o id: " + id));

        if (!order.getUser().getId().equals(user.getId()) && user.getRole() != UserRole.ADMIN) {
            throw new IllegalStateException("Você não tem permissão para visualizar este pedido");
        }
        return mapToDTO(order);
    }

    @Transactional
    public OrderDTO createOrder(OrderRequest request, User user) {
        // Verificar estoque antes de criar o pedido
        for (OrderItemRequest itemRequest : request.getItems()) {
            if (!productService.hasEnoughStock(itemRequest.getProductId(), itemRequest.getQuantity())) {
                throw new IllegalStateException("Estoque insuficiente para o produto com o id: " + itemRequest.getProductId());
            }
        }

        Order order = new Order();
        order.setUser(user);
        order.setStatus(OrderStatus.PENDING);
        order.setTotalAmount(BigDecimal.ZERO);
        order.setCreatedAt(LocalDateTime.now());

        Order savedOrder = orderRepository.save(order);

        List<OrderItem> orderItems = new ArrayList<>();

        // Cria os itens do pedido
        // Se o produto não for encontrado, lança uma exceção
        // Cria o item do pedido
        // Seta o pedido, o produto, a quantidade e o preço
        // Adiciona o item do pedido à lista
        for (OrderItemRequest itemRequest : request.getItems()) {
            Product product = productRepository.findById(itemRequest.getProductId())
                    .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado com o id: " + itemRequest.getProductId()));

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(savedOrder);
            orderItem.setProduct(product);
            orderItem.setQuantity(itemRequest.getQuantity());
            orderItem.setPrice(product.getPrice());

            orderItems.add(orderItem);
        }

        orderItemRepository.saveAll(orderItems);

        savedOrder.setItems(orderItems);
        savedOrder.calculateTotalAmount();

        return mapToDTO(orderRepository.save(savedOrder));
    }

    @Transactional
    public OrderDTO processPayment(Long orderId, User user) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado com o id: " + orderId));

        if (!order.getUser().getId().equals(user.getId()) && user.getRole() != UserRole.ADMIN) {
            throw new IllegalStateException("Você não tem permissão para pagar este pedido");
        }

        // Se o pedido não estiver como PENDING, lança uma exceção
        if (order.getStatus() != OrderStatus.PENDING) {
            throw new IllegalStateException("O pedido não está em status PENDING");
        }

        // Verifica o estoque novamente antes de processar o pagamento
        // Se o estoque não for suficiente, cancela o pedido
        // Seta o status do pedido para CANCELED
        // Seta a data de cancelamento
        // Salva o pedido
        // Lança uma exceção
        for (OrderItem item : order.getItems()) {
            if (!productService.hasEnoughStock(item.getProduct().getId(), item.getQuantity())) {
                order.setStatus(OrderStatus.CANCELED);
                order.setCanceledAt(LocalDateTime.now());
                orderRepository.save(order);

                throw new IllegalStateException("Estoque insuficiente para o produto: " + item.getProduct().getName());
            }
        }

        // Atualizar estoque
        for (OrderItem item : order.getItems()) {
            productService.updateStock(item.getProduct().getId(), item.getQuantity());
        }

        order.setStatus(OrderStatus.PAID);
        order.setPaidAt(LocalDateTime.now());

        return mapToDTO(orderRepository.save(order));
    }

    @Transactional
    public OrderDTO cancelOrder(Long orderId, User user) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado com o id: " + orderId));

        if (!order.getUser().getId().equals(user.getId()) && user.getRole() != UserRole.ADMIN) {
            throw new IllegalStateException("Você não tem permissão para cancelar este pedido");
        }

        if (order.getStatus() != OrderStatus.PENDING) {
            throw new IllegalStateException("Apenas pedidos PENDING podem ser cancelados");
        }

        order.setStatus(OrderStatus.CANCELED);
        order.setCanceledAt(LocalDateTime.now());

        return mapToDTO(orderRepository.save(order));
    }

    // Converte o pedido para o DTO
    // Cria uma lista de itens do pedido
    // Seta o id, id do produto, nome do produto, quantidade, preço e o preço total
    // Adiciona o item do pedido à lista
    // Seta o id do pedido, id do usuário, nome do usuário, itens do pedido, status do pedido, valor total do pedido, data de criação do pedido, data de pagamento e a data de cancelamento do pedido
    // Retorna o DTO do pedido
    private OrderDTO mapToDTO(Order order) {
        List<OrderItemDTO> itemDTOs = order.getItems().stream()
                .map(item -> new OrderItemDTO(
                        item.getId(),
                        item.getProduct().getId(),
                        item.getProduct().getName(),
                        item.getQuantity(),
                        item.getPrice(),
                        item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity()))
                ))
                .collect(Collectors.toList());

        return new OrderDTO(
                order.getId(),
                order.getUser().getId(),
                order.getUser().getName(),
                itemDTOs,
                order.getStatus(),
                order.getTotalAmount(),
                order.getCreatedAt(),
                order.getPaidAt(),
                order.getCanceledAt()
        );
    }
}