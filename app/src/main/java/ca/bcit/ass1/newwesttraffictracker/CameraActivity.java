package ca.bcit.ass1.newwesttraffictracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CameraActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        Intent intent = getIntent();
        Camera camera = (Camera) intent.getExtras().get("camera");

        TextView cameraName = (TextView) findViewById(R.id.cameraName);
        ImageView cameraImage = (ImageView) findViewById(R.id.camera);
        cameraName.setText(camera.getName());
        Toast.makeText(this, camera.getImg(), Toast.LENGTH_LONG).show();
        //new ImageDownloader(cameraImage).execute(camera.getImg());
    }
}
