package com.teamcodeflux.devcup.android.festival.activity;

import com.actionbarsherlock.app.SherlockFragment;
import com.googlecode.androidannotations.annotations.EFragment;
import com.teamcodeflux.devcup.android.festival.R;

@EFragment(R.layout.front_page_layout)
public class FrontPageFragment extends SherlockFragment {


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
            postBodyField.setText(listOfPosts.get(position).getName());

            return view;
        }
    }
}
