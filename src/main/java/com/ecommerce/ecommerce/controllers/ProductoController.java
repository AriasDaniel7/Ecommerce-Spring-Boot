package com.ecommerce.ecommerce.controllers;

import java.io.IOException;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.ecommerce.models.Producto;
import com.ecommerce.ecommerce.models.Usuario;
import com.ecommerce.ecommerce.services.ProductoService;
import com.ecommerce.ecommerce.services.SubirArchivoService;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService serviceProducto;

    @Autowired
    private SubirArchivoService serviceSubirArchivo;

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
    public String guardar(Producto producto, @RequestParam("img") MultipartFile archivo) throws IOException {
        // LOGGER.info("Este es el objeto de la vista {}", producto);
        Usuario u = new Usuario(1, "", "", "", "", "", "", "");
        producto.setUsuario(u);

        // Guardar Imagen
        if (producto.getId() == null) { // Cuando se crea un producto
            String nombreImagen = serviceSubirArchivo.guardarImagen(archivo);
            producto.setImagen(nombreImagen);
        } else {

        }

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
    public String actualizar(Producto producto, @RequestParam("img") MultipartFile archivo) throws IOException {
        if (archivo.isEmpty()) {// Editamos el producto pero no cambiamos la imagen
            Producto p = serviceProducto.obtenerPorId(producto.getId()).get();
            producto.setImagen(p.getImagen());
        } else {// Cuando se edita la imagen
            Producto p = serviceProducto.obtenerPorId(producto.getId()).get();
            // Eliminar cuando la imagen no sea por defecto
            if (!p.getImagen().equals("default.jpg")) {
                serviceSubirArchivo.eliminarImagen(p.getImagen());
            }
            String nombreImagen = serviceSubirArchivo.guardarImagen(archivo);
            producto.setImagen(nombreImagen);
        }
        serviceProducto.actualizar(producto);
        return "redirect:/productos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        Producto p = serviceProducto.obtenerPorId(id).get();
        // Eliminar cuando la imagen no sea por defecto
        if (!p.getImagen().equals("default.jpg")) {
            serviceSubirArchivo.eliminarImagen(p.getImagen());
        }
        serviceProducto.eliminar(id);
        return "redirect:/productos";
    }
}
