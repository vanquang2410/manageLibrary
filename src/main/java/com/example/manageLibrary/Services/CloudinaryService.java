package com.example.manageLibrary.Services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.IIOException;
import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryService {
    private Cloudinary cloudinary;

    public CloudinaryService() {
        this.cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dqtgzpig7",
                "api_key", "817711498241841",
                "api_secret", "n9KLNzVDHhV2tNh_I_84AbjjiSU"));
    }
    public String uploadImage(MultipartFile file) throws IOException {
        Map uploadResult = this.cloudinary.uploader().upload(file.getBytes(),ObjectUtils.emptyMap());
        return (String) uploadResult.get("url");
    }
    }
