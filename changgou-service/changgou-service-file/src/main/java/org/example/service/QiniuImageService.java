package org.example.service;

import java.io.FileInputStream;

public interface QiniuImageService {
    String uploadQiniuImage(FileInputStream file, String key);

}
