package com.ecommerce.ecommerce.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecommerce.ecommerce.models.DetalleOrden;
import com.ecommerce.ecommerce.models.Orden;
import com.ecommerce.ecommerce.models.Producto;
import com.ecommerce.ecommerce.services.ProductoService;

@Controller
@RequestMapping("/")
public class HomeController {

    private final Logger log = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private ProductoService serviceProducto;

    // Para alamacenar los detalles de la orden
    List<DetalleOrden> detalles = new ArrayList<>();

    // Almacenar los datos de la orden
    Orden orden = new Orden();

    @GetMapping("")
    public String mostrarHome(Model model) {
        model.addAttribute("productos", serviceProducto.obtenerTodos());
        return "usuario/home";
    }

    @GetMapping("productohome/{id}")
    public String productoHome(@PathVariable Integer id, Model model) {
        // log.info("id enviado como parametro {}", id);
        Producto producto = serviceProducto.obtenerPorId(id).get();
        model.addAttribute("producto", producto);
        return "usuario/productohome.html";
    }

    @PostMapping("carrito")
    public String agregarCarrito(@RequestParam Integer id, @RequestParam Integer cantidad, Model model) {

        Producto producto = serviceProducto.obtenerPorId(id).get();
        double sumaTotal = 0;
        DetalleOrden detalleOrden = new DetalleOrden();
        detalleOrden.setCantidad(cantidad);
        detalleOrden.setPrecio(producto.getPrecio());
        detalleOrden.setNombre(producto.getNombre());
        detalleOrden.setTotal(producto.getPrecio() * cantidad);
        detalleOrden.setProducto(producto);
        detalles.add(detalleOrden);

        sumaTotal = detalles.stream().mapToDouble(dt -> dt.getTotal()).sum();

        orden.setTotal(sumaTotal);
        model.addAttribute("carrito", detalles);
        model.addAttribute("orden", orden);
        // log.info("Producto añadido: {}", producto);
        // log.info("Cantidad: {}", cantidad);
        return "usuario/carrito";
    }
}
