package ca.bcit.ass1.newwesttraffictracker;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class CameraListActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Camera.cameras != null) {
            ArrayAdapter<Camera> adapter = new ArrayAdapter<>(CameraListActivity.this,
                    android.R.layout.simple_list_item_1, Camera.cameras);
            ListView view = getListView();
            view.setAdapter(adapter);
        } else {
            //Error Downloading message
        }
    }

    @Override
    public void onListItemClick(ListView list, View view, int index, long id) {
        Intent intent = new Intent(this, CameraActivity.class);
        intent.putExtra("index", index);
        startActivity(intent);
    }
}