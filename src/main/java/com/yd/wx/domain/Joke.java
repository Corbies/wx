package com.yd.wx.domain;

import lombok.Data;
import lombok.Builder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author wuyd
 * @date 2018/06/27
 */
@Entity
@Data
@Builder
public class Joke {
    @Id
    @GeneratedValue
    private Long id;
    private String joke;
}
