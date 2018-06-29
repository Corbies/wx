package com.yd.wx.service;

import com.yd.wx.domain.Log;
import com.yd.wx.repository.LogRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author wuyd
 * @date 2018/06/27
 */
@Service
@Slf4j
public class LogService {

    private final LogRepository logRepository;

    public LogService(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    public void log(String str){
        log.info(str);
        try {
            logRepository.save(new Log(str));
        }catch (Exception e){
            log.error("日志入库失败");
        }
    }

}
