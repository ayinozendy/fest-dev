package com.teamcodeflux.devcup.android.festival.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.ViewById;
import com.googlecode.androidannotations.annotations.res.StringArrayRes;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.teamcodeflux.devcup.android.festival.R;
import com.viewpagerindicator.TitlePageIndicator;

@EActivity(R.layout.main)
public class MainIndexActivity extends SherlockFragmentActivity {

    @StringArrayRes
    String[] tab_list;

    @ViewById(R.id.view_pager)
    ViewPager viewPager;

    @ViewById(R.id.indicator)
    TitlePageIndicator titlePageIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initImageLoader();
    }

    private void initImageLoader() {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration
                .Builder(getApplicationContext())
                .build();
        ImageLoader.getInstance().init(config);
    }

    @AfterViews
    void afterViews() {
        viewPager.setAdapter(new CustomFragmentPagerAdapter(getSupportFragmentManager()));
        titlePageIndicator.setViewPager(viewPager);
        viewPager.setCurrentItem(0);
    }

    private class CustomFragmentPagerAdapter extends FragmentStatePagerAdapter {

        public CustomFragmentPagerAdapter(android.support.v4.app.FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new FrontPageFragment_();
                case 1:
                    return new EventsPageFragment_();
            }
            return null;
        }

        @Override
        public int getCount() {
            return tab_list.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tab_list[position];
        }
    }
}
