package com.bootcamp.repository;

import com.bootcamp.model.DetalleCompra;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetalleCompraRepository extends JpaRepository<DetalleCompra, Long> {
}

