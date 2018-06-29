package com.yd.wx.util;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileOutputStream;

/**
 * @author wuyd
 * @date 2018/06/28
 */
@Slf4j
public class FileUtil {
    public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath+fileName);
        out.write(file);
        out.flush();
        out.close();
    }

    public static String getMediUrl(String accessToken, String mediaId) {
        String url = "https://api.weixin.qq.com/cgi-bin/media/get?access_token="+accessToken+"&media_id="+mediaId;
        log.info(url);
        String str = HttpUtil.doGet(url);
        try {
            return JSON.parseObject(str).getString("video_url");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
