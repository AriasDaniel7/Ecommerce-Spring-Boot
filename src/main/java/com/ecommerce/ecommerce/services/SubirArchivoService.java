package com.ecommerce.ecommerce.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service("SubirArhivoService")
public class SubirArchivoService {
    private String carpeta = "images//";

    public String guardarImagen(MultipartFile archivo) throws IOException {
        if (!archivo.isEmpty()) {
            byte[] bytes = archivo.getBytes();
            Path path = Paths.get(carpeta + archivo.getOriginalFilename());
            Files.write(path, bytes);
            return archivo.getOriginalFilename();
        }
        return "default.jpg";
    }

    public void eliminarImagen(String nombreImagen) {
        String ruta = "images//";
        File file = new File(ruta, nombreImagen);
        file.delete();
    }
}
