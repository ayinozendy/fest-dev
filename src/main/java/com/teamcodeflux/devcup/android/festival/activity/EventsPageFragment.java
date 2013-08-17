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
import com.googlecode.androidannotations.annotations.ItemClick;
import com.googlecode.androidannotations.annotations.ViewById;
import com.teamcodeflux.devcup.android.festival.R;
import com.teamcodeflux.devcup.android.festival.model.Event;

import java.util.ArrayList;
import java.util.List;

@EFragment(R.layout.events_page_layout)
public class EventsPageFragment extends SherlockFragment {

    @ViewById(R.id.list_view)
    ListView listView;

    @AfterViews
    void afterViews() {
        listView.setAdapter(new EventListAdapter(getActivity(), loadMockEvents()));
    }

    private List<Event> loadMockEvents() {
        List<Event> events = new ArrayList<Event>();

        for (int i = 0; i < 10; i++) {
            events.add(Event.buildEvent(i, "Title" + i,
                    "Description" + i,
                    "" + i,
                    "" + i,
                    "" + i,
                    "" + i,
                    0d,
                    0d));
        }

        return events;
    }

    @ItemClick(R.id.list_view)
    void list(int position) {
        EventDetailsPageActivity_.intent(getActivity()).event((Event) listView.getAdapter().getItem(position)).start();
    }

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
            View view = this.layoutInflater.inflate(R.layout.event_page_item_layout, parent, false);

            TextView titleField = (TextView) view.findViewById(R.id.title);
            titleField.setText(listOfEvents.get(position).getTitle());

            TextView descriptionField = (TextView) view.findViewById(R.id.description);
            descriptionField.setText(listOfEvents.get(position).getDescription());

            return view;
        }
    }
}
