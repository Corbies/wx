package com.yd.wx.tuling;

import java.io.Serializable;
/**
 * @author wuyd
 * @date 2018/06/24
 */
public class Perception implements Serializable {

    private InputText inputText;

    private InputImage inputImage;

    private InputMedia inputMedia;

    @Override
    public String toString() {
        return "Perception{" +
                "inputText=" + inputText +
                ", inputImage=" + inputImage +
                ", inputMedia=" + inputMedia +
                '}';
    }

    public InputText getInputText() {
        return inputText;
    }

    public void setInputText(InputText inputText) {
        this.inputText = inputText;
    }

    public InputImage getInputImage() {
        return inputImage;
    }

    public void setInputImage(InputImage inputImage) {
        this.inputImage = inputImage;
    }

    public InputMedia getInputMedia() {
        return inputMedia;
    }

    public void setInputMedia(InputMedia inputMedia) {
        this.inputMedia = inputMedia;
    }
}
