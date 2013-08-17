package com.teamcodeflux.devcup.android.festival.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.actionbarsherlock.app.SherlockFragment;
import com.googlecode.androidannotations.annotations.EFragment;
import com.teamcodeflux.devcup.android.festival.R;
import com.teamcodeflux.devcup.android.festival.model.Event;

import java.util.List;

@EFragment(R.layout.events_page)
public class EventsPageFragment extends SherlockFragment {

    private class EventListAdapter extends BaseAdapter {

        private LayoutInflater layoutInflater;

        private List<Event> listOfEvents;

        public EventListAdapter(Context context, List<Event> events) {
            layoutInflater = LayoutInflater.from(context);
            listOfEvents = events;
        }

        @Override
        public int getCount() {
            return listOfEvents.size();
        }

        @Override
        public Object getItem(int position) {
            return listOfEvents.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = this.layoutInflater.inflate(R.layout.front_page_item_layout, parent, false);

            TextView nameField = (TextView) view.findViewById(R.id.name);
            nameField.setText(listOfEvents.get(position).getTitle());

            TextView postBodyField = (TextView) view.findViewById(R.id.post_body);
            postBodyField.setText(listOfEvents.get(position).getDescription());

            return view;
        }
    }
}
