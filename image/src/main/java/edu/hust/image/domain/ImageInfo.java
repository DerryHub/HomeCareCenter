package edu.hust.image.domain;

import org.springframework.web.multipart.MultipartFile;

/**
 * @program: HomeCareCenter
 * @description: 图像实体类
 * @author: Derry Lin
 * @create: 2020-09-04 16:20
 **/
public class ImageInfo {

    private String userId;
    private MultipartFile image;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
