package com.teamcodeflux.devcup.android.festival.activity;

import android.widget.EditText;
import com.actionbarsherlock.app.SherlockActivity;
import com.googlecode.androidannotations.annotations.*;
import com.teamcodeflux.devcup.android.festival.R;
import com.teamcodeflux.devcup.android.festival.model.Event;
import com.teamcodeflux.devcup.android.festival.model.Post;
import com.teamcodeflux.devcup.android.festival.service.RestMethod;
import org.apache.commons.lang3.StringUtils;

@EActivity(R.layout.event_comment_posting_layout)
public class EventCommentPostingActivity extends SherlockActivity {

    @Extra
    Event event;

    @ViewById(R.id.username)
    EditText usernameField;

    @ViewById(R.id.comment)
    EditText commentField;

    @Click(R.id.post)
    void post() {
        validateFields();
        postComment();
    }

    private void validateFields() {
        if (StringUtils.isBlank(usernameField.getText()) || StringUtils.isBlank(commentField.getText())) {
            //Alert!
        }
    }

    @Background
    void postComment() {
        Post post = Post.buildPost(0, usernameField.getText().toString(), "", commentField.getText().toString(), event.getId());
        RestMethod.postComment(post);
    }
}
