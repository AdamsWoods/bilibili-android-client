package com.hotbitmapgg.bilibili.entity.news;

import java.util.List;

public class NewsInfo {

    private int code;
    private List<ResultBean>  data;

    public void setCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setData(List<ResultBean> data) {
        this.data = data;
    }

    public List<ResultBean> getData() {
        return data;
    }

    public static class ResultBean {

        private String path;
        private String image;
        private String title;
        private String passtime;

        public void setPath(String path) {
            this.path = path;
        }

        public String getPath() {
            return path;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getImage() {
            return image;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }

        public void setPasstime(String passtime) {
            this.passtime = passtime;
        }

        public String getPasstime() {
            return passtime;
        }

    }
}
