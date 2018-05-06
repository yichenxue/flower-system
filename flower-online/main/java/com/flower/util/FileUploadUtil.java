package com.flower.util;

import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by yumaoying on 2018/4/30.
 * 文件上传工具
 */
public class FileUploadUtil {

    /***
     * 文件上传
     * @param path 文件路径
     * @param file 上传文件
     * @return 上传消息, 文件名
     */
    public static String upload(String path, MultipartFile file) {
        String msg = "";
        try {
            if (file != null && !file.isEmpty()) {
                String type = null;
                String fileName = file.getOriginalFilename();
                type = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()) : null;
                if (type != null) {
                    if ("gif".equalsIgnoreCase(type) || "png".equalsIgnoreCase(type) || "jpg".equalsIgnoreCase(type)) {
                        String name = "f_" + String.valueOf(System.currentTimeMillis()) + "." + type;
                        Path filePath = Paths.get(path + name);
                        if (!filePath.toFile().getParentFile().exists()) filePath.toFile().getParentFile().mkdirs();
                        file.transferTo(filePath.toFile());
                        msg = name;
                    }
                } else {
                    msg = "图片格式错误";
                }
            }
            return msg;
        } catch (Exception e) {
            e.printStackTrace();
            msg = "图片上传错误";
            return msg;
        }
    }
}
