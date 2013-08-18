package com.teamcodeflux.devcup.android.festival.activity;

import android.widget.ImageView;
import android.widget.TextView;
import com.actionbarsherlock.app.SherlockActivity;
import com.googlecode.androidannotations.annotations.*;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.teamcodeflux.devcup.android.festival.R;
import com.teamcodeflux.devcup.android.festival.model.Event;

@EActivity(R.layout.event_details_layout)
public class EventDetailsPageActivity extends SherlockActivity {

    @Extra
    Event event;

    @ViewById(R.id.title)
    TextView titleView;

    @ViewById(R.id.banner)
    ImageView imageView;

    @ViewById(R.id.description)
    TextView descriptionView;

    @ViewById(R.id.location)
    TextView addressView;

    @AfterViews
    void afterViews() {
        titleView.setText(event.getTitle());

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisc(true)
                .build();

        ImageLoader.getInstance().displayImage(event.getImageUrl(), imageView, options);

        descriptionView.setText(event.getDescription());
        addressView.setText(event.getLocation());
    }

    @Click(R.id.add_comment)
    void addComment() {
        EventCommentPostingActivity_.intent(this).event(event).start();
    }

    @Click(R.id.view_comments)
    void viewComments() {
        EventCommentsActivity_.intent(this).event(event).start();
    }

    @Click(R.id.view_on_map)
    void viewMap() {
        EventMapViewActivity_.intent(this).event(event).start();
    }
}
