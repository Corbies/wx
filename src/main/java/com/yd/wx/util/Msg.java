package com.yd.wx.util;
/**
 * @author wuyd
 * @date 2018/06/26
 */
public interface Msg {

    /**
     * text
     */
    String RESP_MESSAGE_TYPE_TEXT = "text";

    /**
     * music
     */
    String RESP_MESSAGE_TYPE_MUSIC = "music";

    /**
     * news
     */
    String RESP_MESSAGE_TYPE_NEWS = "news";

    /**
     * text
     */
    String REQ_MESSAGE_TYPE_TEXT = "text";

    /**
     * image
     */
    String REQ_MESSAGE_TYPE_IMAGE = "image";

    /**
     * link
     */
    String REQ_MESSAGE_TYPE_LINK = "link";

    /**
     * location
     */
    String REQ_MESSAGE_TYPE_LOCATION = "location";

    /**
     * voice
     */
    String REQ_MESSAGE_TYPE_VOICE = "voice";

    /**
     * video
     */
    String REQ_MESSAGE_TYPE_VIDEO = "video";

    /**
     * shortvideo
     */
    String REQ_MESSAGE_TYPE_SHORTVIDEO = "shortvideo";

    /**
     * event
     */
    String REQ_MESSAGE_TYPE_EVENT = "event";

    /**
     * subscribe
     */
    String EVENT_TYPE_SUBSCRIBE = "subscribe";

    /**
     * unsubscribe
     */
    String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";

    /**
     * CLICK
     */
    String EVENT_TYPE_CLICK = "CLICK";

    /**
     *  开发者微信号
     */
    String TO_USER_NAME = "ToUserName";

    /**
     *  发送方帐号（一个OpenID）
     */
    String FROM_USER_NAME = "FromUserName";

    /**
     *  消息创建时间;// （整型）
     */
    String CREATE_TIME = "CreateTime";

    /**
     *  消息类型
     */
    String MSGTYPE = "MsgType";

    /**
     *  消息创建时间;// （整型）
     */
    String MSGID = "MsgId";

    /**
     * 文本消息内容
     */
    String CONTENT="Content";

    /**
     *  消息创建时间;// （整型）
     */
    String HELP = "我是一个机器人,你可以随意和我聊天";

    String JUHE_CAIPIAO_APPKey = "d37a839cc95c9f00f387f1377e707bdd";
}
