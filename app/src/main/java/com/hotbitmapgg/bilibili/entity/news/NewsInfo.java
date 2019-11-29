package com.hotbitmapgg.bilibili.entity.news;

import java.util.List;

public class NewsInfo {

    private int code;
    private String message;
    private List<Result> result;

    public void setCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setResult(List<Result> data) {
        this.result = data;
    }

    public List<Result> getResult() {
        return result;
    }

    public static class Result {

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
