package com.ecommerce.ecommerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecommerce.ecommerce.models.Producto;
import com.ecommerce.ecommerce.services.ProductoService;

@Controller
@RequestMapping("/administrador")
public class AdministradorController {

    @Autowired
    private ProductoService serviceProducto;

    @GetMapping("")
    public String home(Model model) {
        List<Producto> productos = serviceProducto.obtenerTodos();
        System.out.println(productos);
        model.addAttribute("productos", productos);
        return "administrador/home";
    }
}
