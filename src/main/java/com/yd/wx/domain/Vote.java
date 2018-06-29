package com.yd.wx.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author wuyd
 * @date 2018/06/27
 */
@Entity
@Data
public class Vote implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Integer voteCount;

    public Vote(String name, Integer voteCount){
        this.name = name;
        this.voteCount = voteCount;
    }
}
