package com.ecommerce.ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecommerce.ecommerce.services.ProductoService;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private ProductoService serviceProducto;

    @GetMapping("")
    public String mostrarHome(Model model) {
        model.addAttribute("productos", serviceProducto.obtenerTodos());
        return "usuario/home";
    }
}
