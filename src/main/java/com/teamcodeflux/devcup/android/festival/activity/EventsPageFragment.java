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
import com.googlecode.androidannotations.annotations.*;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.teamcodeflux.devcup.android.festival.R;
import com.teamcodeflux.devcup.android.festival.model.Event;
import com.teamcodeflux.devcup.android.festival.service.RestMethod;

import java.util.ArrayList;
import java.util.List;

@EFragment(R.layout.events_page_layout)
public class EventsPageFragment extends SherlockFragment {

    @ViewById(R.id.list_view)
    ListView listView;

    private EventListAdapter adapter;

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
        adapter = new EventListAdapter(getActivity());
        listView.setAdapter(adapter);
        reloadAdapter();
    }

    @Background
    void reloadAdapter() {
        adapter.setItems(loadEvents());
        refreshList();
    }

    private List<Event> loadEvents() {
        return RestMethod.getEvents();
    }

    @UiThread
    void refreshList() {
        adapter.notifyDataSetChanged();
    }

    private List<Event> loadMockEvents() {
        List<Event> events = new ArrayList<Event>();

        for (int i = 0; i < 10; i++) {
            events.add(Event.buildEvent(i, "imageUrl", "thumb", "Title" + i,
                    "Description" + i,
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

        public EventListAdapter(Context context) {
            layoutInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            if (listOfEvents == null) {
                return 0;
            }
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

            ImageView imageView = (ImageView) view.findViewById(R.id.image);
            ImageLoader.getInstance().displayImage(listOfEvents.get(position).getImageUrl(), imageView, options);

            TextView descriptionField = (TextView) view.findViewById(R.id.description);
            descriptionField.setText(listOfEvents.get(position).getDescription());

            return view;
        }

        public void setItems(List<Event> events) {
            listOfEvents = events;
        }
    }
}
