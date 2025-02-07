package com.example.WhoWasIts.File.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.InputStream;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service

public class FileStorageService {
    private final Path root = Paths.get("uploads");

    public void init(){
        try{
            Files.createDirectory(root);
        }catch (IOException e){
            throw new RuntimeException("No se puede iniciar el storage ");
        }
    }

    public void save(MultipartFile file){
        try {
            Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
        }catch (IOException e){
            throw new RuntimeException("No se puede guardar el archivo");
        }
    }

    public Resource load(String filename) {
        try{
            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()){
                return resource;
            }else {
                throw new RuntimeException("No se puede leer el archivo" +filename);
            }
        }catch (MalformedURLException e){
            throw new RuntimeException("Error "+e.getMessage());
        }
    }


    public void deletAll(){
        FileSystemUtils.deleteRecursively(root.toFile());
    }

    public Stream<Path> loadAll(){
        try{
            return Files.walk(this.root,1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
        }catch (RuntimeException | IOException e){
            throw new RuntimeException("no se puede cargar los arhcivos");
        }
    }

    public String deleteFile(String fileName){
        try{
            Boolean delete = Files.deleteIfExists(this.root.resolve(fileName));
            return "Borrado";
        }catch (IOException e){
            e.printStackTrace();
            return "Error borrando";
        }
    }


}
