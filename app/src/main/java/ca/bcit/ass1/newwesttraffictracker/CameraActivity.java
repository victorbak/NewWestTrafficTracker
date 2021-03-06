package ca.bcit.ass1.newwesttraffictracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class CameraActivity extends Activity {

    String camTitle = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        //Setting pop up window dimensions
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width*.7), (int)(height*.4));

        Intent intent = getIntent();

        //Checks if intent is from the list or from the map
        //List intent
        if(intent.getStringExtra("title") == null) {
            Camera camera = Camera.cameras.get((Integer) intent.getExtras().get("index"));
            TextView cameraName = findViewById(R.id.cameraName);
            ImageView cameraImage = findViewById(R.id.camera);
            cameraName.setText(camera.getName());
            camera.setImage(cameraImage);

        } else{
            //Map intent
            String camTitle = intent.getStringExtra("title");
            for(Camera cam : Camera.cameras) {
                if(cam.getName().equals(camTitle)) {
                    Camera camera = cam;
                    TextView cameraName = findViewById(R.id.cameraName);
                    ImageView cameraImage = findViewById(R.id.camera);
                    cameraName.setText(camera.getName());

                    camera.setImage(cameraImage);
                }
            }
        }

//        if (camera.needsRefresh()) {
//            new ImageDownloader(camera, cameraImage).execute(camera.getImgUrl());
//        } else {
//            cameraImage.setImageBitmap(camera.getBitmap());
//        }
    }
}
