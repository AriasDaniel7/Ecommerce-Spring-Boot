package com.ecommerce.ecommerce.controllers;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecommerce.ecommerce.models.Producto;
import com.ecommerce.ecommerce.models.Usuario;
import com.ecommerce.ecommerce.services.ProductoService;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService serviceProducto;

    private final Logger LOGGER = LoggerFactory.getLogger(ProductoController.class);

    @GetMapping("")
    public String verProductos(Model model) {
        model.addAttribute("productos", serviceProducto.obtenerTodos());
        return "productos/show";
    }

    @GetMapping("/crear")
    public String crearProductos() {
        return "productos/create";
    }

    @PostMapping("/guardar")
    public String guardar(Producto producto) {
        LOGGER.info("Este es el objeto de la vista {}", producto);
        Usuario u = new Usuario(1, "", "", "", "", "", "", "");
        producto.setUsuario(u);
        serviceProducto.guardar(producto);
        return "redirect:/productos";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        Producto p = serviceProducto.obtenerPorId(id).get();
        model.addAttribute("producto", p);
        return "productos/edit";
    }

    @PostMapping("/actualizar")
    public String actualizar(Producto producto) {
        serviceProducto.actualizar(producto);
        return "redirect:/productos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        serviceProducto.eliminar(id);
        return "redirect:/productos";
    }
}
