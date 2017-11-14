package ca.bcit.ass1.newwesttraffictracker;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
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
    private static final long MIN_5 = 5 * 60 * 1000000000;
    public static ArrayList<Camera> cameras;

    public Camera(String name, String url) {
        this.name = name;
        this.url = url;
        String temp = url.replaceAll("/html/www/", "/cameras/");
        temp = temp.replaceAll(".html", ".jpg");
        imgUrl = temp;
        bitmap = null;
    }

    public boolean needsRefresh() {
        if (imageTime == 0) {
            return (bitmap == null);
        }
        return ((System.nanoTime() - imageTime) / 1000000000) > 60;
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

    public Bitmap getBitmap() {
        return bitmap; }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
        imageTime = System.nanoTime();
    }

    public long getImageTime() {
        return imageTime;
    }

    public void setImageTime(long imageTime) {
        this.imageTime = imageTime;
    }

    @Override
    public String toString() {
        return name;
    }
}
