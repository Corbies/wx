package com.yd.wx.tuling;

import java.io.Serializable;
/**
 * @author wuyd
 * @date 2018/06/24
 */
public class TLReqMessage implements Serializable {
    private int repType;
    private Perception perception;
    private UserInfo userInfo;




    @Override
    public String toString() {
        return "TLReqMessage{" +
                "repType=" + repType +
                ", perception=" + perception +
                ", userinfo=" + userInfo +
                '}';
    }

    public int getRepType() {
        return repType;
    }

    public void setRepType(int repType) {
        this.repType = repType;
    }

    public Perception getPerception() {
        return perception;
    }

    public void setPerception(Perception perception) {
        this.perception = perception;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
