package ca.bcit.ass1.newwesttraffictracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class CameraActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        Intent intent = getIntent();
        Camera camera = (Camera) intent.getExtras().get("camera");

        TextView cameraName = findViewById(R.id.cameraName);
        ImageView cameraImage = findViewById(R.id.camera);
        cameraName.setText(camera.getName());
        new ImageDownloader(cameraImage).execute(camera.getImg());
    }
}
