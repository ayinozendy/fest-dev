package com.teamcodeflux.devcup.android.festival.activity;

import android.widget.TextView;
import com.actionbarsherlock.app.SherlockActivity;
import com.googlecode.androidannotations.annotations.*;
import com.teamcodeflux.devcup.android.festival.R;
import com.teamcodeflux.devcup.android.festival.model.Event;

@EActivity(R.layout.event_details_layout)
public class EventDetailsPageActivity extends SherlockActivity {

    @Extra
    Event event;

    @ViewById(R.id.image_url)
    TextView imageUrlView;

    @ViewById(R.id.title)
    TextView titleView;

    @ViewById(R.id.description)
    TextView descriptionView;

    @ViewById(R.id.location)
    TextView addressView;

    @ViewById(R.id.longitude)
    TextView longitudeView;

    @ViewById(R.id.latitude)
    TextView latitudeView;

    @AfterViews
    void afterViews() {
        titleView.setText(event.getTitle());
        imageUrlView.setText(event.getImageUrl());
        descriptionView.setText(event.getDescription());
        addressView.setText(event.getLocation());
        longitudeView.setText(event.getLongitude().toString());
        latitudeView.setText(event.getLatitude().toString());
    }

    @Click(R.id.view_comments)
    void viewComments() {
        EventCommentsActivity_.intent(this).event(event).start();
    }
}
