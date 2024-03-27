package com.example.manageLibrary.Services;

import com.example.manageLibrary.Entities.Image;
import com.example.manageLibrary.Repositories.ImageRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Service
public class ImageService {
    @Autowired
    private ImageRepository imageRepository;

}
