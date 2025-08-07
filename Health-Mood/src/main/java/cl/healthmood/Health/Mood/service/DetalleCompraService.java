package com.bootcamp.service;

import com.bootcamp.model.DetalleCompra;

import java.util.List;
import java.util.Optional;

public interface DetalleCompraService {
    List<DetalleCompra> listarTodos();
    Optional<DetalleCompra> obtenerPorId(Long id);
    DetalleCompra guardar(DetalleCompra detalleCompra);
    void eliminar(Long id);
}
