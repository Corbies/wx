package com.yd.wx.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import com.alibaba.fastjson.JSONObject;
import com.yd.wx.JuHe.CaiPiaoResponse;
import com.yd.wx.pojo.*;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;


/**
 * 对消息的处理
 *
 * @author wuyd
 * @date 2018/06/26
 */
public class MessageUtil {
    private static Pattern  PATTERNINT = Pattern.compile("[0-9]*(\\.?)[0-9]*");
    private static Pattern PATTERNFLOAT = Pattern.compile("[0-9]+");
    public static Map<String, String> parseXml(HttpServletRequest request) {

        Map<String, String> messageMap = new HashMap(20);

        InputStream inputStream = null;
        try {
            inputStream = request.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        SAXReader reader = new SAXReader();
        Document document = null;
        try {
            document = reader.read(inputStream);
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Element root = document.getRootElement();
        List<Element> elementsList = root.elements();

        for (Element e : elementsList) {
            messageMap.put(e.getName(), e.getText());
        }
        try {
            inputStream.close();
            inputStream = null;
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        return messageMap;
    }

    public static String textToWxXml(Map<String, String> message, String content) {
        TextMessage textMessage = new TextMessage();
        textMessage.setContent(content);
        textMessage.setCreateTime(System.currentTimeMillis());
        textMessage.setToUserName(message.get(Msg.FROM_USER_NAME));
        textMessage.setFromUserName(message.get(Msg.TO_USER_NAME));
        textMessage.setMsgType(Msg.REQ_MESSAGE_TYPE_TEXT);
        textMessage.setMsgId(message.get(Msg.MSGID));
        xstream.alias("xml", textMessage.getClass());
        return xstream.toXML(textMessage);
    }

    public static String imageToWxXml(Map<String, String> message, String mediaId) {
        ResMessage resMessage = new ResMessage();
        resMessage.setCreateTime(System.currentTimeMillis());
        resMessage.setToUserName(message.get(Msg.FROM_USER_NAME));
        resMessage.setFromUserName(message.get(Msg.TO_USER_NAME));
        resMessage.setMsgType(Msg.REQ_MESSAGE_TYPE_IMAGE);
        Image image = new Image();
        image.setMediaId(mediaId);
        resMessage.setImage(image);
        xstream.alias("xml", resMessage.getClass());
        return xstream.toXML(resMessage);
    }
    public static String videoToWxXml(Map<String, String> message, String mediaId) {
        ResMessage resMessage = new ResMessage();
        resMessage.setCreateTime(System.currentTimeMillis());
        resMessage.setToUserName(message.get(Msg.FROM_USER_NAME));
        resMessage.setFromUserName(message.get(Msg.TO_USER_NAME));
        resMessage.setMsgType(Msg.REQ_MESSAGE_TYPE_VIDEO);
        resMessage.setTitle("数码宝贝");
        resMessage.setDescription("MV ");
        Video video = new Video();
        video.setMediaId(mediaId);
        resMessage.setVideo(video);
        xstream.alias("xml", resMessage.getClass());
        return xstream.toXML(resMessage);
    }

    public static String voiceToWxXml(Map<String, String> message, String mediaId) {
        ResMessage resMessage = new ResMessage();
        resMessage.setCreateTime(System.currentTimeMillis());
        resMessage.setToUserName(message.get(Msg.FROM_USER_NAME));
        resMessage.setFromUserName(message.get(Msg.TO_USER_NAME));
        resMessage.setMsgType(Msg.REQ_MESSAGE_TYPE_VOICE);
        Voice voice = new Voice();
        voice.setMediaId(mediaId);
        resMessage.setVoice(voice);
        xstream.alias("xml", resMessage.getClass());
        return xstream.toXML(resMessage);
    }



    /**
     * 定义xstream让value自动加上CDATA标签
     */
    private static XStream xstream = new XStream(new XppDriver() {
        @Override
        public HierarchicalStreamWriter createWriter(Writer out) {
            return new PrettyPrintWriter(out) {
                boolean cdata = false;

                @SuppressWarnings("unchecked")
                @Override
                public void startNode(String name, Class clazz) {

                    if (!name.equals("xml")) {
                        char[] arr = name.toCharArray();
                        if (arr[0] >= 'a' && arr[0] <= 'z') {
                            // arr[0] -= 'a' - 'A';
                            arr[0] = (char) ((int) arr[0] - 32);
                        }
                        name = new String(arr);
                    }

                    super.startNode(name, clazz);

                }

                @Override
                public void setValue(String text) {
                    if (text != null && !"".equals(text)) {
                        if (PATTERNINT.matcher(text).matches()
                                || PATTERNFLOAT.matcher(text).matches()) {
                            cdata = false;
                        } else {
                            cdata = true;
                        }
                    }
                    super.setValue(text);
                }

                @Override
                protected void writeText(QuickWriter writer, String text) {

                    if (cdata) {
                        writer.write("<![CDATA[");
                        writer.write(text);
                        writer.write("]]>");
                    } else {
                        writer.write(text);
                    }
                }
            };
        }

    });

    public static boolean CaiPiao(String str){
        String dlt = "大乐透";
        String ssq = "双色球";
        String fcsd = "福彩3D";
        if(str.contains(dlt)){return true;}
        if(str.contains(ssq)){return true;}
        if(str.contains(fcsd)){return true;}
        return false;
    }

    public static String CaiPiaoRes(String str){
        String lottery_id = "dlt";
        String ssq = "双色球";
        String fcsd = "福彩3D";
        if(str.contains(ssq)){lottery_id="ssq";}
        if(str.contains(fcsd)){lottery_id="fcsd";}
        String jsong = HttpUtil.doGet("http://apis.juhe.cn/lottery/query?key="+Msg.JUHE_CAIPIAO_APPKey+"&lottery_id="+lottery_id);
        CaiPiaoResponse caiPiaoResponse = JSONObject.parseObject(jsong, CaiPiaoResponse.class);
        return caiPiaoResponse.getResult().getLottery_name()+""+caiPiaoResponse.getResult().getLottery_res();
    }
}
