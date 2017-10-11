package ca.bcit.ass1.newwesttraffictracker;

import java.io.Serializable;

/**
 * Created by Robbie on 10-Oct-2017.
 */

public class Camera implements Serializable {

    private String name;
    private String url;
    private String img;

    public Camera(String name, String url) {
        this.name = name;
        this.url = url;
        String temp = url.replaceAll("/html/www/", "/cameras/");
        temp = temp.replaceAll(".html", ".jpg");
        img = temp;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return name;
    }
}
