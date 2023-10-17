package com.ecommerce.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.ecommerce.models.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

}
