package com.teamcodeflux.devcup.android.festival.activity;

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

import java.util.List;

@EFragment(R.layout.front_page_layout)
public class FrontPageFragment extends SherlockFragment {

    @ViewById(R.id.list_view)
    ListView listView;

    @AfterViews
    void afterViews() {
        loadPostMockData();
    }

    private List<Post> loadPostMockData() {
        return null;
    }

    private class PostListAdapter extends BaseAdapter {

        private LayoutInflater layoutInflater;

        private List<Post> listOfPosts;

        public PostListAdapter(List<Post> posts) {
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
