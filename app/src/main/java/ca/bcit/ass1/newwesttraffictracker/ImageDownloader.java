package ca.bcit.ass1.newwesttraffictracker;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Robbie on 10-Oct-2017.
 */
class ImageDownloader extends AsyncTask<String, Void, Void> {
    private Camera cam;
    ImageView view;

    public ImageDownloader(Camera camera, ImageView view) {
        cam = camera;
        this.view = view;
    }

    @Override
    protected Void doInBackground(String... params) {
        System.out.println(params[0]);
        cam.setBitmap(downloadBitmap(params[0]));
        return null;
    }

    @Override
    protected void onPostExecute(Void v){
        view.setImageBitmap(cam.getBitmap());
        view.invalidate();
        cam.setImageTime(System.nanoTime());
    }

    private Bitmap downloadBitmap(String url) {
        HttpURLConnection urlConnection = null;
        try {
            URL uri = new URL(url);
            urlConnection = (HttpURLConnection) uri.openConnection();
            int statusCode = urlConnection.getResponseCode();
            if (statusCode != HttpURLConnection.HTTP_OK) {
                Log.e("ImageDownloader", "Error connecting to url, status " + statusCode);
                return null;
            }

            InputStream inputStream = urlConnection.getInputStream();
            if (inputStream != null) {
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                Log.d("ImageDownloader", "Success");
                return bitmap;
            }
        } catch (Exception e) {
            urlConnection.disconnect();
            Log.w("ImageDownloader", "Error downloading image from " + url);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return null;
    }
}