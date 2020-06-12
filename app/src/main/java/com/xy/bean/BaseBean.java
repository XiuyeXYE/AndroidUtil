package com.xy.bean;

/**
 * 作者：Abeing
 * <p>
 * 时间： 2019/3/30 17:27
 */
public class BaseBean<T> {

    /**
     * Allow_Flag : 1
     * info : 成功!
     */

    private int Allow_Flag;
    private String info;
    private String img;
    private String content;
    private T t;

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public int getAllow_Flag() {
        return Allow_Flag;
    }

    public void setAllow_Flag(int allow_Flag) {
        this.Allow_Flag = allow_Flag;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
