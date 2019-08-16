package com.mazeed.lms.german.learning.app.presentation.ui.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.mazeed.lms.german.learning.app.BuildConfig;
import com.mazeed.lms.german.learning.app.R;
import com.mazeed.lms.german.learning.app.presentation.presenters.callbacks.UserCallback;
import com.mazeed.lms.german.learning.app.presentation.presenters.user.UserPresenter;
import com.mazeed.lms.german.learning.app.presentation.presenters.user.UserPresenterImp;
import com.mazeed.lms.german.learning.app.presentation.ui.fragments.AboutFragment;
import com.mazeed.lms.german.learning.app.presentation.ui.fragments.GradesFragment;
import com.mazeed.lms.german.learning.app.presentation.ui.fragments.WebViewFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends BaseActivity implements UserCallback, NavigationView.OnNavigationItemSelectedListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.navigation_view)
    NavigationView navigation;

    private UserPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        presenter = new UserPresenterImp(this);

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
        setActionBarTitle(R.string.menu_home);
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

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onLogoutComplete() {
        startActivity(new Intent(this, SplashActivity.class));
        finish();
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
                case R.id.navigation_contact_us:
                    display(1);
                    result = true;
                    break;
                case R.id.navigation_about:
                    display(2);
                    result = true;
                    break;
                case R.id.navigation_sign_out:
                    showConfirmLogout();
                    result = false;
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
                fragment = GradesFragment.newInstance();
                titleId = R.string.menu_home;
                break;
            case 1:
                fragment = WebViewFragment.newInstance(BuildConfig.COPY_RIGHT_URL);
                titleId = R.string.menu_contact_us;
                break;
            case 2:
                fragment = AboutFragment.newInstance();
                titleId = R.string.menu_about;
                break;
        }
        if (fragment != null) {
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.frame_container, fragment, tag).commit();
            setActionBarTitle(titleId);
        }
    }

    private void showConfirmLogout() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.message_sign_out_confirm);
        builder.setNeutralButton(R.string.title_sign_ou, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                presenter.logout();
            }
        }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface i) {
                dialog.getButton(AlertDialog.BUTTON_NEUTRAL).setTextColor(Color.RED);
                dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(ResourcesCompat.getColor(getResources(), R.color.green, null));
            }
        });
        dialog.show();
    }
}
