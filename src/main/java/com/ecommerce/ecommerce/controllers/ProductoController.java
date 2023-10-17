package com.ecommerce.ecommerce.controllers;

import org.slf4j.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecommerce.ecommerce.models.Producto;

@Controller
@RequestMapping("/productos")
public class ProductoController {
    private final Logger LOGGER = LoggerFactory.getLogger(ProductoController.class);

    @GetMapping("")
    public String verProductos() {
        return "productos/show";
    }

    @GetMapping("/crear")
    public String crearProductos() {
        return "productos/create";
    }

    @PostMapping("/guardar")
    public String guardar(Producto producto) {
        LOGGER.info("Este es el objeto de la vista {}", producto);
        return "redirect:/productos";
    }
}
