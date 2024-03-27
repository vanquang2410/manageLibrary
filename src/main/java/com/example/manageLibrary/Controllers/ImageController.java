package com.example.manageLibrary.Controllers;

import com.cloudinary.Cloudinary;
import com.example.manageLibrary.Services.CloudinaryService;
import com.example.manageLibrary.Services.RespondObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/image")
public class ImageController {
    @Autowired
    private CloudinaryService cloudinaryService;

    @PostMapping("/upload")
    public ResponseEntity<RespondObject> uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
        return ResponseEntity.status(HttpStatus.OK).body(
                new RespondObject("ok","upload image sucessfully",cloudinaryService.uploadImage(file))
        );
    }

}
