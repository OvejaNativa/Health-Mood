package com.bootcamp.service;

import com.bootcamp.model.DetalleCompra;
import com.bootcamp.repository.DetalleCompraRepository;
import com.bootcamp.service.DetalleCompraService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DetalleCompraServiceImpl implements DetalleCompraService {

    private final DetalleCompraRepository detalleCompraRepository;

    @Override
    public List<DetalleCompra> listarTodos() {
        return detalleCompraRepository.findAll();
    }

    @Override
    public Optional<DetalleCompra> obtenerPorId(Long id) {
        return detalleCompraRepository.findById(id);
    }

    @Override
    public DetalleCompra guardar(DetalleCompra detalleCompra) {
        return detalleCompraRepository.save(detalleCompra);
    }

    @Override
    public void eliminar(Long id) {
        detalleCompraRepository.deleteById(id);
    }
}
