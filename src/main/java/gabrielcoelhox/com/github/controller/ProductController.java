package gabrielcoelhox.com.github.controller;

import gabrielcoelhox.com.github.dto.ProductDTO;
import gabrielcoelhox.com.github.dto.product.ProductRequest;
import gabrielcoelhox.com.github.service.ProductService;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Produtos", description = "Endpoints relacionadas a produtos")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    @Operation(
        summary = "Listar todos os produtos",
        description = "Retorna uma lista com todos os produtos disponíveis na loja"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Lista de produtos retornada com sucesso",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductDTO.class))
    )
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        log.info("Requisição para listar todos os produtos");
        List<ProductDTO> products = productService.getAllProducts();
        log.info("Retornando {} produtos", products.size());
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Buscar produto por ID",
        description = "Retorna os detalhes completos de um produto específico a partir do ID fornecido"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Produto encontrado com sucesso",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductDTO.class))
    )
    @ApiResponse(
        responseCode = "404",
        description = "Produto não encontrado"
    )
    public ResponseEntity<ProductDTO> getProductById(
            @Parameter(description = "Product ID") @PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @GetMapping("/category/{category}")
    @Operation(
        summary = "Listar produtos por categoria",
        description = "Retorna uma lista de produtos que pertencem a uma determinada categoria"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Lista de produtos retornada com sucesso",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductDTO.class))
    )
    @ApiResponse(
        responseCode = "404",
        description = "Categoria não encontrada"
    )
    public ResponseEntity<List<ProductDTO>> getProductsByCategory(@PathVariable String category) {
        return ResponseEntity.ok(productService.getProductsByCategory(category));
    }

    @GetMapping("/search")
    @Operation(
        summary = "Buscar produtos por nome",
        description = "Retorna uma lista de produtos que possuem o nome fornecido"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Lista de produtos retornada com sucesso",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductDTO.class))
    )
    @ApiResponse(
        responseCode = "404",
        description = "Nenhum produto encontrado com o nome fornecido"
    )
    public ResponseEntity<List<ProductDTO>> searchProducts(@RequestParam String name) {
        return ResponseEntity.ok(productService.searchProducts(name));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(
        summary = "Criar novo produto",
        description = "Cria um novo produto na loja"
    )
    @ApiResponse(
        responseCode = "201",
        description = "Produto criado com sucesso",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductDTO.class))
    )
    @ApiResponse(
        responseCode = "400",
        description = "Dados inválidos ou produto já existente"
    )
    @ApiResponse(
        responseCode = "403",
        description = "Acesso negado - apenas administradores"
    )
    public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody ProductRequest request) {
        return new ResponseEntity<>(productService.createProduct(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(
        summary = "Atualizar produto",
        description = "Atualiza um produto existente na loja"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Produto atualizado com sucesso",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductDTO.class))
    )
    @ApiResponse(
        responseCode = "404",
        description = "Produto não encontrado"
    )
    @ApiResponse(
        responseCode = "403",
        description = "Acesso negado - apenas administradores"
    )
    public ResponseEntity<ProductDTO> updateProduct(
            @Parameter(description = "Product ID") @PathVariable Long id,
            @Valid @RequestBody ProductRequest request) {
        return ResponseEntity.ok(productService.updateProduct(id, request));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(
        summary = "Deletar produto existente",
        description = "Deleta um produto existente a partir do ID fornecido"
    )
    @ApiResponse(
        responseCode = "204",
        description = "Produto deletado com sucesso"
    )
    @ApiResponse(
        responseCode = "404",
        description = "Produto não encontrado"
    )
    @ApiResponse(
        responseCode = "403",
        description = "Acesso negado - apenas administradores"
    )
    public ResponseEntity<Void> deleteProduct(
            @Parameter(description = "Product ID") @PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}