package com.yd.wx.domain;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author wuyd
 * @date 2018/06/28
 */
@Slf4j
@Data
@Entity
public class File {

    @Id
    @GeneratedValue
    private Long id;
    private String mediaId;
    private String type;
}
