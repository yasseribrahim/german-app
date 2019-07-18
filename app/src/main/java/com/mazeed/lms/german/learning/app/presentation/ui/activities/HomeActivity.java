package com.mazeed.lms.german.learning.app.presentation.ui.activities;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.mazeed.lms.german.learning.app.R;
import com.mazeed.lms.german.learning.app.presentation.ui.fragments.VideosFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.navigation_view)
    NavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        setupUI();
    }

    @Override
    protected View getSnackBarAnchorView() {
        return navigation;
    }

    private void setupUI() {
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigation.setNavigationItemSelectedListener(this);

        display(0);
        setActionBarTitle(R.string.title_videos);
        navigation.setCheckedItem(R.id.navigation_home);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        boolean result = false;
        if (navigation.getCheckedItem() == null || item.getItemId() != navigation.getCheckedItem().getItemId()) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    display(0);
                    result = true;
                    break;
            }
        }

        drawer.closeDrawer(GravityCompat.START);
        return result;
    }

    private void display(int position) {
        Fragment fragment = null;
        int titleId = -1;
        String tag = null;
        switch (position) {
            case 0:
                fragment = VideosFragment.newInstance();
                titleId = R.string.title_videos;
                break;
        }
        if (fragment != null) {
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.frame_container, fragment, tag).commit();
            setActionBarTitle(titleId);
        }
    }
}
