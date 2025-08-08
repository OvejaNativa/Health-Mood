package cl.healthmood.Health.Mood.service;

import cl.healthmood.Health.Mood.model.Pedido;

import java.util.List;
import java.util.Optional;

public interface PedidoService {
    List<Pedido> getAllPedidos();
    Optional<Pedido> getPedidoById(Integer id);
    Pedido savePedido(Pedido pedido);
    void deletePedido(Integer id);
}