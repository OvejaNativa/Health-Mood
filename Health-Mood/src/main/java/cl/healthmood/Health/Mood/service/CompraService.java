package com.bootcamp.service;

import com.bootcamp.model.Compra;

import java.util.List;
import java.util.Optional;

public interface CompraService {
    List<Compra> listarTodas();
    Optional<Compra> obtenerPorId(Long id);
    Compra guardar(Compra compra);
    void eliminar(Long id);
}

