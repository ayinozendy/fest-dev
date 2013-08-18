package com.teamcodeflux.devcup.android.festival.activity;

import android.widget.EditText;
import android.widget.Toast;
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
        if (!validateFields()) {
            Toast.makeText(this, "Please don't leave blanks", 3000).show();
            return;
        }

        postComment();
    }

    private boolean validateFields() {
        boolean isValid = true;

        if (StringUtils.isBlank(usernameField.getText())) {
            usernameField.setError("Must not be blank");
            isValid = false;
        } else if (StringUtils.isBlank(commentField.getText())) {
            commentField.setError("Must not be blank");
            isValid = false;
        }

        return isValid;
    }

    @Background
    void postComment() {
        Post post = Post.buildPost(0, usernameField.getText().toString(), "", commentField.getText().toString(), event.getId());
        Post resultPost = RestMethod.postComment(post);

        if (resultPost == null) {
            showFailToast();
            return;
        }

        showSuccessToast();
    }

    @UiThread
    void showSuccessToast() {
        Toast.makeText(this, "Comment Post Successful", 3000).show();
        finish();
    }

    @UiThread
    void showFailToast() {
        Toast.makeText(this, "Comment Posting Unsuccessful", 3000).show();
    }
}