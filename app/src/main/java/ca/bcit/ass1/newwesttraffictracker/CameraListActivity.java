package ca.bcit.ass1.newwesttraffictracker;

import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class CameraListActivity extends ListActivity {

    private String TAG = MainActivity.class.getSimpleName();
    private ArrayList<Camera> cameraList;
    private static String SERVICE_URL = "http://opendata.newwestcity.ca/downloads/webcam-links/WEBCAM_LINKS.csv";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new GetCameras().execute();
    }

    @Override
    public void onListItemClick(ListView list, View view, int index, long id) {
        Camera click = (Camera) list.getAdapter().getItem(index);
        Intent intent = new Intent(this, CameraActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("camera", cameraList.get(index));
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private class GetCameras extends AsyncTask<Void, Void, Void> {
        private ArrayList<Camera> list;
        @Override
        protected Void doInBackground(Void... arg0) {
            list = new ArrayList<>();
            URL url;
            URLConnection connection;
            InputStreamReader input;
            try {
                url = new URL(SERVICE_URL);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }

            try {
                connection = url.openConnection();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }

            try {
                input = new InputStreamReader(connection.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
            BufferedReader buffer = null;
            String line = "";

            try {
                buffer = new BufferedReader(input);
                while ((line = buffer.readLine()) != null) {
                    String[] cam = line.split(",");
                    //System.out.println(line);
                    if (cam[1].equals("DriveBC")) {
                        Camera newCam = new Camera(cam[0], cam[2]);
                        list.add(newCam);
                        System.out.println(newCam);
                    }
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (buffer != null) {
                    try {
                        buffer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            cameraList = list;
            ArrayAdapter<Camera> adapter = new ArrayAdapter<Camera>(CameraListActivity.this,
                    android.R.layout.simple_list_item_1, list);
            ListView view = getListView();
            view.setAdapter(adapter);
        }

    }
}