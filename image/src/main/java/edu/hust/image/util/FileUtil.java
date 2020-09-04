package edu.hust.image.util;

import java.io.File;

/**
 * @program: HomeCareCenter
 * @description: 文件工具类
 * @author: Derry Lin
 * @create: 2020-09-04 16:56
 **/
public class FileUtil {

    public static void createDir(String dirName){
        File dir = new File(dirName);
        if (!dirName.endsWith(File.separator)) {
            dirName = dirName + File.separator;
        }
        //创建目录
        dir.mkdirs();
    }
}
