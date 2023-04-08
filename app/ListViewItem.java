package com.example.myapplication2;

import android.graphics.drawable.Drawable;

public class ListViewItem {
    private Drawable iconDrawable ;
    private String titleStr ;
    private String adStr ;

    public void setIcon(Drawable icon) {
        iconDrawable = icon ;
    }
    public void setTitle(String title) {
        titleStr = title ;
    }
    public void setDesc(String desc) {
        adStr = desc ;
    }

    public Drawable getIcon() {
        return this.iconDrawable ;
    }
    public String getTitle() {
        return this.titleStr ;
    }
    public String getDesc() {
        return this.adStr ;
    }
}