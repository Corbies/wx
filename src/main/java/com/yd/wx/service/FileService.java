package com.yd.wx.service;

import com.yd.wx.domain.File;
import com.yd.wx.repository.FileRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wuyd
 * @date 2018/06/28
 */
@Service
@Slf4j
public class FileService {

    private final FileRepository fileRepository;

    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public String getFile(String type){
        List<File> files =fileRepository.findAllByType(type);
        if(files.isEmpty()){
            return "";
        }else {
            int i = (int)Math.random()*files.size();
            return files.get(i).getMediaId();
        }
    }
}
