package cl.healthmood.Health.Mood.service;

import cl.healthmood.Health.Mood.dto.PedidoRequest;
import cl.healthmood.Health.Mood.dto.PedidoResponse;

import java.util.List;

public interface PedidoService {
    List<PedidoResponse> getAllPedidos();
    PedidoResponse getPedidoById(Integer id);
    PedidoResponse createPedido(PedidoRequest request);
    PedidoResponse updatePedido(Integer id, PedidoRequest request);
    void deletePedido(Integer id);
}