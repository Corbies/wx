package com.yd.wx.tuling;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yd.wx.util.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author wuyd
 * @date 2018/06/24
 */
public class TL {
    static Logger logger = LoggerFactory.getLogger(TL.class);
    public static String send(String str) {
        TLReqMessage tlReqMessage = new TLReqMessage();
        tlReqMessage.setRepType(0);
        UserInfo userInfo = new UserInfo();
        userInfo.setApiKey("5e3e73d9176847c2b73572f531a97da7");
        userInfo.setUserId("282150");
        tlReqMessage.setUserInfo(userInfo);
        Perception perception = new Perception();
        perception.setInputText(new InputText(str));
        tlReqMessage.setPerception(perception);
        String resul = null;
        String text="";
        try {
            resul = HttpUtil.doPost("http://openapi.tuling123.com/openapi/api/v2", JSONObject.toJSONString(tlReqMessage));
            String results = JSONObject.parseObject(resul).getString("results");
            JSONArray jsar = JSONArray.parseArray(results);
            text = JSONObject.parseObject(JSONObject.parseObject(jsar.get(0).toString()).getString("values")).getString("text");
            logger.info("机器人回复："+text);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return text;
    }

}
