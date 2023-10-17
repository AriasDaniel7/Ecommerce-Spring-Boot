package com.ecommerce.ecommerce.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce.interfaces.OperacionesInterface;
import com.ecommerce.ecommerce.models.Producto;
import com.ecommerce.ecommerce.repositories.ProductoRepository;

@Service("ProductoService")
public class ProductoService implements OperacionesInterface<Producto, Integer> {

    @Autowired
    private ProductoRepository repositoryProducto;

    @Override
    public Producto guardar(Producto miObjeto) {
        return repositoryProducto.save(miObjeto);
    }

    @Override
    public Optional<Producto> obtenerPorId(Integer id) {
        return repositoryProducto.findById(id);
    }

    @Override
    public void actualizar(Producto miObjeto) {
        repositoryProducto.save(miObjeto);
    }

    @Override
    public void eliminar(Integer id) {
        repositoryProducto.deleteById(id);
    }

}
