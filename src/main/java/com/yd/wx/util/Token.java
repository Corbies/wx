package com.yd.wx.util;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author wuyd
 * @date 2018/06/28
 */
@Slf4j
@Component
@Data
public class Token {

    private   String accessToken = "";

    private  final String SECRET = "5f92a53a79760cfe65c0bcb21d1bcf47";

}
