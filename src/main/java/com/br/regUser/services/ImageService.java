package com.br.regUser.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class ImageService {
    @Value("${local.images.path}")
    private String folderPath;

    public String uploadImage(MultipartFile image){
        Path imagePath = Paths.get(folderPath, image.getOriginalFilename());
        try{
            image.transferTo(imagePath.toFile());
            return imagePath.toString();
        } catch (IOException e){
            throw new RuntimeException("Falha ao salvar imagem!");
        }
    }
}
