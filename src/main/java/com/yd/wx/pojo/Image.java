package com.yd.wx.pojo;

import lombok.Data;

import javax.persistence.Entity;
import java.io.Serializable;

/**
 * @author wuyd
 * @date 2018/06/28
 */

@Data
public class Image implements Serializable{
    private String MediaId;
}
