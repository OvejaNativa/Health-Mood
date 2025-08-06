package com.bootcamp.service;

import com.bootcamp.model.Compra;
import com.bootcamp.repository.CompraRepository;
import com.bootcamp.service.CompraService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompraServiceImpl implements CompraService {

    private final CompraRepository compraRepository;

    @Override
    public List<Compra> listarTodas() {
        return compraRepository.findAll();
    }

    @Override
    public Optional<Compra> obtenerPorId(Long id) {
        return compraRepository.findById(id);
    }

    @Override
    public Compra guardar(Compra compra) {
        if (compra.getDetalles() != null) {
            compra.getDetalles().forEach(det -> det.setCompra(compra));
        }
        return compraRepository.save(compra);
    }

    @Override
    public void eliminar(Long id) {
        compraRepository.deleteById(id);
    }
}

