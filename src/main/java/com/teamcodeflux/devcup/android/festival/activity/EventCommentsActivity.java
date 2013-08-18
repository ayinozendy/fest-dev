package com.teamcodeflux.devcup.android.festival.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.actionbarsherlock.app.SherlockActivity;
import com.googlecode.androidannotations.annotations.*;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.teamcodeflux.devcup.android.festival.R;
import com.teamcodeflux.devcup.android.festival.model.Event;
import com.teamcodeflux.devcup.android.festival.model.Post;
import com.teamcodeflux.devcup.android.festival.service.RestMethod;

import java.util.ArrayList;
import java.util.List;

@EActivity(R.layout.event_comments_layout)
public class EventCommentsActivity extends SherlockActivity {

    @Extra
    Event event;

    @ViewById(R.id.list_view)
    ListView listView;

    CommentsListAdapter adapter;

    private DisplayImageOptions options;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisc(true)
                .build();
    }

    @AfterViews
    void afterViews() {
        adapter = new CommentsListAdapter(this);
        listView.setAdapter(adapter);
        reloadAdapter();
    }

    @Background
    void reloadAdapter() {
        adapter.setItems(loadAllPosts());
        refreshList();
    }

    @UiThread
    void refreshList() {
        adapter.notifyDataSetChanged();
    }

    private List<Post> loadAllPosts() {
        return RestMethod.getPostsForEvent(event);
    }

    private List<Post> loadMockPosts() {
        List<Post> posts = new ArrayList<Post>();

        for (int i = 0; i < 10; i++) {
            posts.add(Post.buildPost(i, "Comment Name" + i, "image url", "Comment Body" + 1, 1));
        }

        return posts;
    }

    private class CommentsListAdapter extends BaseAdapter {

        private LayoutInflater layoutInflater;

        private List<Post> listOfPosts;

        public CommentsListAdapter(Context context) {
            layoutInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            if (listOfPosts == null) {
                return 0;
            }
            return listOfPosts.size();
        }

        @Override
        public Object getItem(int position) {
            return listOfPosts.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = this.layoutInflater.inflate(R.layout.event_comments_item_layout, parent, false);

            TextView nameField = (TextView) view.findViewById(R.id.name);
            nameField.setText(listOfPosts.get(position).getUsername());

            ImageView imageView = (ImageView) view.findViewById(R.id.image);
            imageView.setVisibility(View.VISIBLE);
            ImageLoader.getInstance().displayImage(listOfPosts.get(position).getImageUrl(), imageView, options);

            TextView postBodyField = (TextView) view.findViewById(R.id.post_body);
            postBodyField.setText(listOfPosts.get(position).getPostBody());

            return view;
        }

        public void setItems(List<Post> posts) {
            listOfPosts = posts;
        }
    }
}
