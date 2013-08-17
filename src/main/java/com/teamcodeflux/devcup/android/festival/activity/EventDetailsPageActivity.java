package com.teamcodeflux.devcup.android.festival.activity;

import com.actionbarsherlock.app.SherlockActivity;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.Extra;
import com.teamcodeflux.devcup.android.festival.R;
import com.teamcodeflux.devcup.android.festival.model.Event;

@EActivity(R.layout.event_details_layout)
public class EventDetailsPageActivity extends SherlockActivity {

    @Extra
    Event event;
}
