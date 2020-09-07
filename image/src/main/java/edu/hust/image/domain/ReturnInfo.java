package edu.hust.image.domain;

/**
 * @program: HomeCareCenter
 * @description: 图片信息实体类
 * @author: Derry Lin
 * @create: 2020-09-04 15:32
 **/

public class ReturnInfo {

    //图像地址
    private String imgUrl;

    public ReturnInfo(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
