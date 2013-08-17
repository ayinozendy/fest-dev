package com.teamcodeflux.devcup.android.festival.activity;

import android.widget.TextView;
import com.actionbarsherlock.app.SherlockActivity;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.Extra;
import com.googlecode.androidannotations.annotations.ViewById;
import com.teamcodeflux.devcup.android.festival.R;
import com.teamcodeflux.devcup.android.festival.model.Event;

@EActivity(R.layout.event_details_layout)
public class EventDetailsPageActivity extends SherlockActivity {

    @Extra
    Event event;

    @ViewById(R.id.title)
    TextView titleView;

    @ViewById(R.id.description)
    TextView descriptionView;

    @ViewById(R.id.address)
    TextView addressView;

    @ViewById(R.id.contact_no)
    TextView contactNoView;

    @ViewById(R.id.image_url)
    TextView imageUrlView;

    @ViewById(R.id.organizer)
    TextView organizerView;

    @ViewById(R.id.longitude)
    TextView longitudeView;

    @ViewById(R.id.latitude)
    TextView latitudeView;

    @AfterViews
    void afterViews() {
        titleView.setText(event.getTitle());
        descriptionView.setText(event.getDescription());
        addressView.setText(event.getAddress());
        contactNoView.setText(event.getContactNo());
        imageUrlView.setText(event.getImageUrl());
        organizerView.setText(event.getOrganizer());
        longitudeView.setText(event.getLongitude().toString());
        latitudeView.setText(event.getLatitude().toString());
    }
}
