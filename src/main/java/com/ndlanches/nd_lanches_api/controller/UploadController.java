package com.ndlanches.nd_lanches_api.controller;

import com.cloudinary.Cloudinary;
import com.ndlanches.nd_lanches_api.config.AdminKeyValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/api/upload")
public class UploadController {

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private AdminKeyValidator adminKey;

    @PostMapping("/imagem")
    public ResponseEntity<?> uploadImagem(
            @RequestHeader("Admin-Key") String key,
            @RequestParam("file") MultipartFile file) {

        if (adminKey.invalido(key)) return adminKey.negado();

        try {
            Map uploadResult = cloudinary.uploader().upload(file.getBytes(), Map.of());
            String imageUrl = uploadResult.get("secure_url").toString();
            return ResponseEntity.ok(Map.of("url", imageUrl));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro no upload: " + e.getMessage());
        }
    }
}