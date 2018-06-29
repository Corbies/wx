package com.yd.wx.tuling;

import java.io.Serializable;
/**
 * @author wuyd
 * @date 2018/06/24
 */
public class InputImage implements Serializable {
    private String url;

    @Override
    public String toString() {
        return "InputImage{" +
                "url='" + url + '\'' +
                '}';
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}