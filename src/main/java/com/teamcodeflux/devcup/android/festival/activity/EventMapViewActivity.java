package com.teamcodeflux.devcup.android.festival.activity;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.Extra;
import com.teamcodeflux.devcup.android.festival.R;
import com.teamcodeflux.devcup.android.festival.model.Event;

@EActivity(R.layout.event_details_map_view_layout)
public class EventMapViewActivity extends SherlockFragmentActivity {

    @Extra
    Event event;

    private static final float ZOOM_LEVEL = 16f;

    private GoogleMap googleMap;

    @AfterViews
    void afterViews() {
        initMap();
    }

    private void initMap() {
        googleMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
        googleMap.setMyLocationEnabled(true);
        googleMap.moveCamera(CameraUpdateFactory.zoomTo(ZOOM_LEVEL));

        LatLng eventCoordinates = new LatLng(event.getLatitude(), event.getLongitude());
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(eventCoordinates));
        googleMap.addMarker(new MarkerOptions().position(eventCoordinates).title(event.getTitle()));
    }
}
