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
import com.actionbarsherlock.app.SherlockFragment;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.EFragment;
import com.googlecode.androidannotations.annotations.ViewById;
import com.googlecode.androidannotations.annotations.res.StringRes;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.teamcodeflux.devcup.android.festival.R;
import com.teamcodeflux.devcup.android.festival.model.Post;

import java.util.ArrayList;
import java.util.List;

@EFragment(R.layout.front_page_layout)
public class FrontPageFragment extends SherlockFragment {

    @StringRes(R.string.lorem_ipsum)
    String loremIpsum;

    @StringRes(R.string.test_image_url)
    String testImageUrl;

    @ViewById(R.id.list_view)
    ListView listView;

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
        listView.setAdapter(new PostListAdapter(getActivity(), loadPostMockData()));
    }

    private List<Post> loadPostMockData() {
        List<Post> posts = new ArrayList<Post>();

        for (int i = 0; i < 10; i++) {
            posts.add(Post.buildPost("Name " + i, i + " " + loremIpsum));
        }

        return posts;
    }

    private class PostListAdapter extends BaseAdapter {

        private LayoutInflater layoutInflater;

        private List<Post> listOfPosts;

        public PostListAdapter(Context context, List<Post> posts) {
            layoutInflater = LayoutInflater.from(context);
            listOfPosts = posts;
        }

        @Override
        public int getCount() {
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
            View view = this.layoutInflater.inflate(R.layout.front_page_item_layout, parent, false);

            TextView nameField = (TextView) view.findViewById(R.id.name);
            nameField.setText(listOfPosts.get(position).getName());

            TextView eventTitleField = (TextView) view.findViewById(R.id.event_title);
            eventTitleField.setText("The Quick Brown Fox Event");

            ImageView imageView = (ImageView) view.findViewById(R.id.image);
            imageView.setVisibility(View.VISIBLE);
            ImageLoader.getInstance().displayImage(testImageUrl, imageView, options);

            TextView postBodyField = (TextView) view.findViewById(R.id.post_body);
            postBodyField.setText(listOfPosts.get(position).getPostBody());

            return view;
        }
    }
}
