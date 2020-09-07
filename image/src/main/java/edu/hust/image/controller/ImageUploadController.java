package edu.hust.image.controller;

import edu.hust.common.constant.ApiCodeEnum;
import edu.hust.common.vo.ApiResult;
import edu.hust.image.config.Config;
import edu.hust.image.domain.ImageInfo;
import edu.hust.image.domain.ReturnInfo;
import edu.hust.image.service.ImageUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @program: HomeCareCenter
 * @description: 图像上传控制器
 * @author: Derry Lin
 * @create: 2020-09-04 15:34
 **/
@RestController
@RequestMapping("home-care-center/image")
public class ImageUploadController {

    @Autowired
    private ImageUploadService imageUploadService;

    @Autowired
    private Config config;

    @PostMapping("upload")
    public ApiResult imageUpload(ImageInfo imageInfo){

        MultipartFile image = imageInfo.getImage();
        String userId = imageInfo.getUserId();

        long size = image.getSize();
        if (size > config.getMaxFileSize()){
            ApiCodeEnum apiCodeEnum = ApiCodeEnum.IMAGE_OVERFLOW;
            return ApiResult.buildError(apiCodeEnum.getMsg(), apiCodeEnum.getCode());
        }

        ReturnInfo returnInfo = imageUploadService.uploadImg(userId, image);

        if (returnInfo == null){
            ApiCodeEnum apiCodeEnum = ApiCodeEnum.FAIL_TO_UPLOAD;
            return ApiResult.buildError(apiCodeEnum.getMsg(), apiCodeEnum.getCode());
        }

        return ApiResult.buildSuccess(returnInfo);
    }

}
