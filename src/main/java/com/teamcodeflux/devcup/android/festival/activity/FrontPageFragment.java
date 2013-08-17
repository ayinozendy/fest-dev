package com.teamcodeflux.devcup.android.festival.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.actionbarsherlock.app.SherlockFragment;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.EFragment;
import com.googlecode.androidannotations.annotations.ViewById;
import com.teamcodeflux.devcup.android.festival.R;
import com.teamcodeflux.devcup.android.festival.model.Post;

import java.util.ArrayList;
import java.util.List;

@EFragment(R.layout.front_page_layout)
public class FrontPageFragment extends SherlockFragment {

    @ViewById(R.id.list_view)
    ListView listView;

    @AfterViews
    void afterViews() {
        listView.setAdapter(new PostListAdapter(getActivity(), loadPostMockData()));
    }

    private List<Post> loadPostMockData() {
        List<Post> posts = new ArrayList<Post>();

        for (int i = 0; i < 10; i++) {
            posts.add(Post.buildPost("Name " + i, "Body" + i));
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

            TextView postBodyField = (TextView) view.findViewById(R.id.post_body);
            postBodyField.setText(listOfPosts.get(position).getPostBody());

            return view;
        }
    }
}
