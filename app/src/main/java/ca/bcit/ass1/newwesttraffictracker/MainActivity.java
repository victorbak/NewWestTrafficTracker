package ca.bcit.ass1.newwesttraffictracker;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static String SERVICE_URL = "http://opendata.newwestcity.ca/downloads/webcam-links/WEBCAM_LINKS.csv";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Camera.cameras == null) {
            new GetCameras().execute();
        }

        Button listButton = (Button) findViewById(R.id.listButton);
        Button mapButton = (Button) findViewById(R.id.mapsButton);

        listButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, CameraListActivity.class);
                startActivity(i);
            }
        });

        mapButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(i);
            }
        });
    }

    private class GetCameras extends AsyncTask<Void, Void, Boolean> {
        private ArrayList<Camera> list;
        @Override
        protected Boolean doInBackground(Void... arg0) {
            list = new ArrayList<>();
            list.add(new Camera("Pattullo Bridge (South View)", "http://images.drivebc.ca/bchighwaycam/pub/html/www/679.html"));
            list.add(new Camera("Queensborough Connector (North View)", "http://images.drivebc.ca/bchighwaycam/pub/html/www/732.html"));
            list.add(new Camera("Alex Fraser Bridge Southbound", "http://images.drivebc.ca/bchighwaycam/pub/html/www/731.html"));
            list.add(new Camera("Annacis Channel Bridge Approach (East View)", "http://images.drivebc.ca/bchighwaycam/pub/html/www/735.html"));
            URL url;
            URLConnection connection;
            InputStreamReader input;
            try {
                url = new URL(SERVICE_URL);
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("GetCameras", "Cannot connect to URL");
                return false;
            }

            try {
                connection = url.openConnection();
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("GetCameras", "Cannot connect to URL");
                return false;
            }

            try {
                input = new InputStreamReader(connection.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
            BufferedReader buffer = null;
            String line = "";

            try {
                buffer = new BufferedReader(input);
                while ((line = buffer.readLine()) != null) {
                    String[] cam = line.split(",");
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
            Camera.cameras = list;
            return true;
        }
    }
}
