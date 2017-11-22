package ca.bcit.ass1.newwesttraffictracker;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Robbie on 21-Nov-2017.
 */

public class CameraMarker {

    private LatLng coordinate;
    private Camera camera;

    private static HashMap<String, LatLng> hardcodedMarkers;
    private static ArrayList<CameraMarker> markers;

    public enum Direction{
        EAST,
        WEST,
        NORTH,
        SOUTH
    }
    public Direction direction;

    public CameraMarker(LatLng coordinate, Camera camera, Direction direction) {
        this.coordinate = coordinate;
        this.camera = camera;
        this.direction = direction;
    }

    private static void populateHardcodedMarkers() {
        hardcodedMarkers = new HashMap<String, LatLng>();
        hardcodedMarkers.put("Hwy 91A at Howes St (East View)", new LatLng(49.188672, -122.946970));
        hardcodedMarkers.put("Hwy 91A at Howes St (West View)", new LatLng(49.188257, -122.948204));
        hardcodedMarkers.put("Hwy91A at Boundary Rd (East View)", new LatLng(49.183540, -122.956480));
        hardcodedMarkers.put("Hwy91A at Boundary Rd (West View)", new LatLng(49.183219, -122.957893));
        hardcodedMarkers.put("Hwy91A at QB Bridge (East View)", new LatLng(49.199140, -122.949515));
        hardcodedMarkers.put("Hwy91A at QB Bridge (South View)", new LatLng(49.198770, -122.949696));
        hardcodedMarkers.put("Pattullo Bridge (South View)", new LatLng(49.208805, -122.897641));
        hardcodedMarkers.put("Queensborough Connector (North View)", new LatLng(49.175904, -122.960674));
        hardcodedMarkers.put("Alex Fraser Bridge Southbound", new LatLng(49.175255, -122.959490));
        hardcodedMarkers.put("Annacis Channel Bridge Approach (East View)", new LatLng(49.175955, -122.962165));
    }

    public static void makeMarkers() {
        if (markers == null) {
            markers = new ArrayList<>();
        }
        if (hardcodedMarkers == null) {
            populateHardcodedMarkers();
        }
        for (Camera cam : Camera.cameras) {
            if (hardcodedMarkers.containsKey(cam.getName())) {
                String camName = cam.getName();
                Direction direction;

                if (camName.contains("North")) {
                    direction = Direction.NORTH;
                } else if (camName.contains("East")) {
                    direction = Direction.EAST;
                } else if (camName.contains("South")) {
                    direction = Direction.SOUTH;
                } else {
                    direction = Direction.WEST;
                }
                markers.add(new CameraMarker(hardcodedMarkers.get(cam.getName()), cam, direction));
            }
        }
    }

    public static void placeMarkers(GoogleMap map) {
        if (markers == null) {
            makeMarkers();
        }
        for (CameraMarker marker : markers) {
            map.addMarker(new MarkerOptions().position(marker.coordinate).title(marker.camera.getName()));
        }
    }
}
