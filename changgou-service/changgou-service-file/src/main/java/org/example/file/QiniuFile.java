package org.example.file;


import lombok.Data;

import java.io.Serializable;

/**
 * 封装文件上传信息
 * 时间
 * Author
 * type
 * size
 * 附加信息
 * 文件内容
 */
@Data
public class QiniuFile implements Serializable {
    private String name;
    private byte[] content;


    public QiniuFile(String name, byte[] content) {
        this.name = name;
        this.content = content;
    }
}
