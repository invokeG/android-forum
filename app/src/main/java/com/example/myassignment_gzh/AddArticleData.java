package com.example.myassignment_gzh;

import cn.bmob.v3.BmobObject;

public class AddArticleData extends BmobObject {
    private String title;
    private String textcontent;

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getTextcontent() {
        return textcontent;
    }
    public void setTextcontent(String textcontent) {
        this.textcontent = textcontent;
    }
}
