package com.yd.wx.pojo;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author wuyd
 * @date 2018/06/24
 */
@Data
@ToString
public class Message implements Serializable {
    private String ToUserName;
    private String FromUserName;
    private Long CreateTime;
    private String MsgType;
    private String MsgId;

    public Message() {
    }
}
