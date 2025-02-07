package com.example.WhoWasIts.File.Controller;

import com.example.WhoWasIts.File.service.FileStorageService;
import com.example.WhoWasIts.Postear.model.File;
import com.example.WhoWasIts.Postear.model.FileMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class FileController {

    private final FileStorageService fileStorageService;


    @PostMapping("usuario/upload")
    public ResponseEntity<FileMessage>uploadFiles(@RequestParam("files")MultipartFile file){
        String message = "";
        try {
            List<String> fileName = new ArrayList<>();
            Arrays.asList(file).stream().forEach(o -> {
                fileStorageService.save(o);
                fileName.add(o.getOriginalFilename());
            });
            message = "se subieron las imagenes correctamente "+fileName;
            return ResponseEntity.ok(new FileMessage(message));
        }catch (Exception e){
            message = "Fallo al subir las imagenes";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new FileMessage(message));
        }
    }

    @GetMapping("usuario/files")
    public ResponseEntity<List<File>> getFiles(){
        List<File> fileInfo = fileStorageService.loadAll().map(path -> {
            String fileName = path.getFileName().toString();
            String url = MvcUriComponentsBuilder.fromMethodName(FileController.class,"getFile",path.getFileName().toString()).build().toString();
            return new File(fileName,url);
        }).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(fileInfo);
    }

    @GetMapping("usuario/file/{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = fileStorageService.load(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+ file.getFilename() + "\"").body(file);
    }
}
