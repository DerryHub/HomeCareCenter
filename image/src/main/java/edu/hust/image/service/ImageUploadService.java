package edu.hust.image.service;

import edu.hust.image.config.Config;
import edu.hust.image.domain.ReturnInfo;
import edu.hust.image.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @program: HomeCareCenter
 * @description: 图像上传服务类
 * @author: Derry Lin
 * @create: 2020-09-04 15:34
 **/
@Service
public class ImageUploadService {

    @Autowired
    private Config config;

    public ReturnInfo uploadImg(String userId, MultipartFile image){
        String sep = File.separator;
        String filename = image.getOriginalFilename();
        String filenameSuffix = filename.substring(filename.lastIndexOf('.') + 1);
        String randomId = UUID.randomUUID().toString();
        String newFileName = randomId + '.' + filenameSuffix;

        String path = config.getImgPath() + userId;

        FileUtil.createDir(path);

        File targetImage = new File(path + sep + newFileName);
        try {
            image.transferTo(targetImage);
            String imageUrl = config.getImgUrlPath() + userId + "/" + newFileName;
            ReturnInfo returnInfo = new ReturnInfo(imageUrl);
            return returnInfo;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
