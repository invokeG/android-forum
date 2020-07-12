package com.example.myassignment_gzh;

/**
 * Created by g on 2020/3/30.
 */
public class ItemBean {
    private int ItemImageResourceId;
    private String ItemTitle;
    private String ItemContent;
    private int BackgroundColor;

    public ItemBean(int itemImageResourceId, String itemTitle, String itemContent, int backgroundColor){
        this.ItemImageResourceId = itemImageResourceId;
        this.ItemTitle = itemTitle;
        this.ItemContent = itemContent;
        this.BackgroundColor = backgroundColor;
    }
    public String getItemTitle(){
        return ItemTitle;
    }
    public String getItemContent(){
        return ItemContent;
    }
    public int getItemImageResourceId(){
        return ItemImageResourceId;
    }
    public int getBackgroundColor(){
        return BackgroundColor;
    }
}