package com.yd.wx.tuling;
/**
 * @author wuyd
 * @date 2018/06/24
 */
public class InputText {
    private String text;

    public InputText(){}
    public InputText(String text){
        this.text=text;
    }
    @Override
    public String toString() {
        return "InputText{" +
                "text='" + text + '\'' +
                '}';
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
