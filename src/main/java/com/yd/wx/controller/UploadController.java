package com.yd.wx.controller;

import com.alibaba.fastjson.JSONObject;
import com.yd.wx.repository.FileRepository;
import com.yd.wx.util.FileUtil;
import com.yd.wx.util.HttpUtil;
import com.yd.wx.util.Token;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * @author wuyd
 * @date 2018/06/28
 */
@RestController
@Slf4j
public class UploadController {

    private final Token token;
    private final FileRepository fileRepository;

    public UploadController(Token token, FileRepository fileRepository) {
        this.token = token;
        this.fileRepository = fileRepository;
    }

    /**
    * file upload
    * @param file file
    * @return json
    * */
    @PostMapping(value="/upload")
    public  String upload(@RequestParam("file") MultipartFile file,
                                          HttpServletRequest request) {
        String contentType = file.getContentType();
        String fileName = file.getOriginalFilename();

        try {
            FileUtil.uploadFile(file.getBytes(), ".//uploadFiles/", fileName);
        } catch (Exception e) {
            // TODO: handle exception
        }
        //返回json
        File wxfile=new File(".//uploadFiles/"+fileName);
        String url = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token="
                +token.getAccessToken()
                +"&type="+getType(file.getOriginalFilename());
       String json =  HttpUtil.doPost(url,wxfile);
        log.info("微信返回参数："+json);
        if(json!=null &&!"".equals(json)){
            com.yd.wx.domain.File resultFile = JSONObject.parseObject(json, com.yd.wx.domain.File.class);
            if(resultFile != null){
                fileRepository.save(resultFile);
            }
        }
        return "success";
    }

    public String getType(String str){
        if(str.contains("mp4")||str.contains("wav")||str.contains("mkv")){
            return "video";
        }
        if(str.contains("mp3")){
            return "voice";
        }
        return "image";
    }
}
