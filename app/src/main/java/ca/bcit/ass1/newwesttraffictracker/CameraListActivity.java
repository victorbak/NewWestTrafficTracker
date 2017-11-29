package ca.bcit.ass1.newwesttraffictracker;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class CameraListActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_list);


        if (Camera.cameras != null) {
            ArrayAdapter<Camera> adapter = new ArrayAdapter<>(CameraListActivity.this,
                    android.R.layout.simple_list_item_1, Camera.cameras);
            ListView view = findViewById(R.id.list);
            view.setAdapter(adapter);

            view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    TextView tv = (TextView) view;
                    String cam = tv.getText().toString();
                    tv.setTextColor(Color.BLACK);
                    Intent intent = new Intent(CameraListActivity.this, CameraActivity.class);
                    intent.putExtra("index", i);
                    startActivity(intent);
                }
            });
        } else {
            //Error Downloading message
        }
    }

    public void onListItemClick(ListView list, View view, int index, long id) {

    }
}