package com.yd.wx.config;
import com.alibaba.fastjson.JSON;
import com.yd.wx.util.HttpUtil;
import com.yd.wx.util.Token;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;


/**
 * @author wuyd
 * @date 2018/06/28
 */
@Configuration
@Slf4j
@EnableScheduling
public class SchedulingConfig {
    private final Token token;
    public SchedulingConfig(Token token) {
        this.token = token;
    }
    @Scheduled(initialDelay = 2000,fixedRate = 7000000)
    public void getToken() {
        String str = HttpUtil.doGet("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx6f41feeb796d964e&secret=" + token.getSECRET());
        try {
            token.setAccessToken(JSON.parseObject(str).getString("access_token"));
            log.info("token: {}",token.getAccessToken());
        } catch (Exception e) {
            log.error("获取token失败");
        }
    }
}
