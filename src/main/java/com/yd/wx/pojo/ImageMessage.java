package com.yd.wx.pojo;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author wuyd
 * @date 2018/06/28
 */
@Data
@ToString(callSuper = true)
public class ImageMessage extends Message implements Serializable {
    private String PicUrl;
    private String MediaId;
    private Image image;
    public ImageMessage() {
    }
}
