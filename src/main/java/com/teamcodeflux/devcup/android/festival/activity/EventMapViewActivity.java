package com.teamcodeflux.devcup.android.festival.activity;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.EActivity;
import com.teamcodeflux.devcup.android.festival.R;

@EActivity(R.layout.event_details_map_view_layout)
public class EventMapViewActivity extends SherlockFragmentActivity {

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
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
