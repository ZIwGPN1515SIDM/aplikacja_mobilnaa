package com.example.magda.systeminformacyjny.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.magda.systeminformacyjny.R;
import com.example.magda.systeminformacyjny.databinding.ActivityMainBinding;
import com.example.magda.systeminformacyjny.models.FacebookUser;
import com.example.magda.systeminformacyjny.utils.DrawerCreator;
import com.example.magda.systeminformacyjny.view_models.ActivityMainViewModel;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.mikepenz.materialdrawer.Drawer;

/**
 * Created by piotrek on 05.04.17.
 */

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private Drawer drawer;
    private FacebookUser user;
    private ActivityMainViewModel viewModel;
    private Fragment currentFragment;
    public String title;

    private static final String FRAGMENT_TAG = "mainFragment";
    public static final String USER_TAG = "userFacebook";
    private static final String SELECTED_DRAWER_ID = "selectedDrawerId";
    private static final String TOOLBAR_TITLE = "toolbarTitle";
    private static final String HOME_TITLE = "Strona główna";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = new ActivityMainViewModel(this);
        if (savedInstanceState != null) {
            user = savedInstanceState.getParcelable(USER_TAG);
            viewModel.setSelectedDrawerId(savedInstanceState.getLong(SELECTED_DRAWER_ID));
            title = savedInstanceState.getString(TOOLBAR_TITLE);
        } else {
            user = getIntent().getParcelableExtra(USER_TAG);
            viewModel.setSelectedDrawerId(DrawerCreator.HOME_PAGE);
            title = HOME_TITLE;
        }
        toolbar = binding.toolbarLayout.toolbar;
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        replaceFragment(viewModel.createFragment(viewModel.getSelectedDrawerId()));
        drawer = DrawerCreator.createDrawer(this, toolbar, viewModel, user);
    }

    @Override
    public void onBackPressed() {
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(USER_TAG, user);
        outState.putLong(SELECTED_DRAWER_ID, viewModel.getSelectedDrawerId());
        outState.putString(TOOLBAR_TITLE, title);
    }

    public void replaceFragment(Fragment fragment) {
        this.currentFragment = fragment;
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragmentContainer, fragment, FRAGMENT_TAG);
        ft.commit();
    }

    public void setToolbarTitle(String title) {
        this.title = title;
        toolbar.setTitle(title);
    }

}