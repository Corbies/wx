package com.yd.wx.tuling;

import java.io.Serializable;

/**
 * @author wuyd
 * @date 2018/06/24
 */
public class UserInfo implements Serializable {
    private String apiKey;
    private String userId;
    private String groupId;
    private String userIdName;

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getUserIdName() {
        return userIdName;
    }

    public void setUserIdName(String userIdName) {
        this.userIdName = userIdName;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "apiKey='" + apiKey + '\'' +
                ", userId='" + userId + '\'' +
                ", groupId='" + groupId + '\'' +
                ", userIdName='" + userIdName + '\'' +
                '}';
    }
}
