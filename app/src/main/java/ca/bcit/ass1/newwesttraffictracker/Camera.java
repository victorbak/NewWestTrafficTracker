package ca.bcit.ass1.newwesttraffictracker;

import android.graphics.Bitmap;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Robbie on 10-Oct-2017.
 */
public class Camera {

    private String name;
    private String url;
    private String imgUrl;
    private Bitmap bitmap;
    private long imageTime;
    public static ArrayList<Camera> cameras;

    public Camera(String name, String url) {
        this.name = name;
        this.url = url;
        String temp = url.replaceAll("/html/www/", "/cameras/");
        temp = temp.replaceAll(".html", ".jpg");
        imgUrl = temp;
        bitmap = null;
    }

    public void refreshCamera() {
        if (bitmap == null) {
            Log.d("Camera", "Camera is null");
            if ((System.nanoTime() - imageTime) >= (5 * 60 * 1000000000)) {
                Log.d("Camera", "Camera is old");
            }
        }
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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Bitmap getBitmap() {
        return bitmap; }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
        imageTime = System.nanoTime();
    }

    @Override
    public String toString() {
        return name;
    }
}
