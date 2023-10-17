package com.ecommerce.ecommerce.interfaces;

import java.util.List;
import java.util.Optional;

public interface OperacionesInterface<T, N> {
    public T guardar(T miObjeto);

    public Optional<T> obtenerPorId(N id);

    public void actualizar(T miObjeto);

    public void eliminar(N id);

    public List<T> obtenerTodos();
}
