package com.yd.wx.tuling;

import java.io.Serializable;
/**
 * @author wuyd
 * @date 2018/06/24
 */
public class InputMedia implements Serializable {
        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        @Override
        public String toString() {
            return "InputMedia{" +
                    "url='" + url + '\'' +
                    '}';
        }

}
