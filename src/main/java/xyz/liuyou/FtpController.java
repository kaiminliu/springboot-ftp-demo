package xyz.liuyou;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @author liuminkai
 * @version 1.0
 * @datetime 2021/2/3 15:57
 * @decription
 **/
@Controller
public class FtpController {

    @ResponseBody
    @PostMapping("upload")
    public String upload(@RequestParam("file") MultipartFile file) throws IOException {
        String filePath = "/";
        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        String fileName = UUID.randomUUID().toString() + suffix;
        InputStream is = file.getInputStream();
        FtpUtil.uploadFile(filePath, fileName, is);
        return "ok";
    }

    @ResponseBody
    @GetMapping("download")
    public String download(){
        String filePath = "/";
        String fileName = "a.jpeg"; // 服务器上已有的文件
        String localFilePath = System.getProperty("user.dir") + "/b.jpeg";
        FtpUtil.downloadFile(filePath, fileName, localFilePath);
        return "ok";
    }
}