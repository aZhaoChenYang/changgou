package org.example.service.impl;

import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.common.QiniuException;
import com.qiniu.util.Auth;
import com.google.gson.Gson;
import org.example.config.CloudStorageConfig;
import org.example.service.QiniuImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;

@Service
public class QiniuServiceImpl implements QiniuImageService {
    @Autowired
    protected CloudStorageConfig config;
    private UploadManager uploadManager;
    private String token;
    public QiniuServiceImpl(CloudStorageConfig config){
        this.config = config;
        init();
    }
    private void init() {
        uploadManager = new UploadManager(new Configuration(Region.huabei()));
        Auth auth = Auth.create(config.getQiniuAccessKey(), config.getQiniuSecretKey());
        // 根据命名空间生成的上传token
        token = auth.uploadToken(config.getQiniuBucketName());
    }
    @Override
    public String uploadQiniuImage(FileInputStream file, String key) {
        try{
            // 上传图片文件
            Response res = uploadManager.put(file, key, token, null, null);
            if (!res.isOK()) {
                throw new RuntimeException("上传七牛出错：" + res);
            }
            // 解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(res.bodyString(), DefaultPutRet.class);

            // 这个returnPath是获得到的外链地址,通过这个地址可以直接打开图片
            return config.getQiniuDomain() + "/" + putRet.key;
        }catch (QiniuException e){
            e.printStackTrace();
        }
        return "";
    }
}
