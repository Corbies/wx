package com.yd.wx.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author wuyd
 * @date 2018/06/27
 */
@Data
@Entity
public class Log implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String content;
    private LocalDateTime localDateTime;

    public Log(String str){
        this.content = str;
        this.localDateTime = LocalDateTime.now();
    }
}
