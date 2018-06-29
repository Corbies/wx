package com.yd.wx.pojo;

import lombok.*;

import java.io.Serializable;

/**
 * @author wuyd
 * @date 2018/06/24
 */
@Data
@ToString(callSuper = true)
public class TextMessage extends Message implements Serializable {
     private String Content;

    public TextMessage() {
        super();
    }
}