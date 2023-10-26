package com.ecommerce.ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecommerce.ecommerce.models.Producto;
import com.ecommerce.ecommerce.services.ProductoService;

@Controller
@RequestMapping("/")
public class HomeController {

    // private final Logger log = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private ProductoService serviceProducto;

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
    public String agregarCarrito() {
        return "usuario/carrito";
    }
}
