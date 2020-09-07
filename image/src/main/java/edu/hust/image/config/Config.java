package edu.hust.image.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @program: HomeCareCenter
 * @description: 配置类
 * @author: Derry Lin
 * @create: 2020-09-04 15:37
 **/
@Component
public class Config implements WebMvcConfigurer {

    @Value("${imgPath}")
    private String imgPath;

    @Value("${imgUrlPath}")
    private String imgUrlPath;

    @Value("${maxFileSize}")
    private long maxFileSize;

    public long getMaxFileSize() {
        return maxFileSize;
    }

    public void setMaxFileSize(long maxFileSize) {
        this.maxFileSize = maxFileSize;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getImgUrlPath() {
        return imgUrlPath;
    }

    public void setImgUrlPath(String imgUrlPath) {
        this.imgUrlPath = imgUrlPath;
    }

    @Override
    public String toString() {
        return "Config{" +
                "imgPath='" + imgPath + '\'' +
                ", imgUrlPath='" + imgUrlPath + '\'' +
                '}';
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/home-care-center/image/" + "**").addResourceLocations("file:"+imgPath);
    }


}
