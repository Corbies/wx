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
public class ResMessage extends Message implements Serializable {
    private String Title;
    private String Description;
    private Voice voice;
    private Video video;
    private Image image;
    private String Content;
}
