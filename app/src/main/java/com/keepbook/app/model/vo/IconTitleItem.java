package com.keepbook.app.model.vo;

/**
 * com.keepbook.app.view.fragment.record.PayFragment 的 recyclerview 的图标文字item
 */
//IconTitleItem,图片和标题类
public class IconTitleItem {
    private String  title;

    public IconTitleItem() {

    }

    public IconTitleItem(String title, Integer imageId) {
        this.title = title;
        ImageId = imageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getImageId() {
        return ImageId;
    }

    public void setImageId(Integer imageId) {
        ImageId = imageId;
    }
    private Integer ImageId;
}
