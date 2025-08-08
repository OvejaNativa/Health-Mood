package cl.healthmood.Health.Mood.controller;

import cl.healthmood.Health.Mood.dto.PedidoRequest;
import cl.healthmood.Health.Mood.dto.PedidoResponse;
import cl.healthmood.Health.Mood.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin(origins = "*")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping
    public ResponseEntity<List<PedidoResponse>> getAllPedidos() {
        List<PedidoResponse> pedidos = pedidoService.getAllPedidos();
        return ResponseEntity.ok(pedidos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponse> getPedidoById(@PathVariable Integer id) {
        try {
            PedidoResponse pedido = pedidoService.getPedidoById(id);
            return ResponseEntity.ok(pedido);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<PedidoResponse> createPedido(@Valid @RequestBody PedidoRequest request) {
        try {
            PedidoResponse createdPedido = pedidoService.createPedido(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdPedido);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoResponse> updatePedido(@PathVariable Integer id,
                                                       @Valid @RequestBody PedidoRequest request) {
        try {
            PedidoResponse updatedPedido = pedidoService.updatePedido(id, request);
            return ResponseEntity.ok(updatedPedido);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePedido(@PathVariable Integer id) {
        try {
            pedidoService.deletePedido(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}