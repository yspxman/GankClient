package com.example.syan.gankclient;


import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.syan.gankclient.DI.Cloth;
import com.example.syan.gankclient.DI.Clothes;
import com.example.syan.gankclient.DI.DaggerMainComponent;
import com.example.syan.gankclient.DI.MainComponent;
import com.example.syan.gankclient.DI.MainModule;
import com.example.syan.gankclient.DI.Shoe;
import com.example.syan.gankclient.Fragments.AccountFragment;
import com.example.syan.gankclient.Fragments.QuickBetFragment;
import com.example.syan.gankclient.Fragments.RacingFragment;
import com.example.syan.gankclient.Fragments.SportsFragment;
import com.example.syan.gankclient.Helper.Utility;

import javax.inject.Inject;
import javax.inject.Named;

public class HomeActivity extends AppCompatActivity  implements QuickBetFragment.OnFragmentInteractionListener{

    private BottomNavigationView bottomNavigationView;

    @Inject
    @Named("red")
    Cloth clothRed;

    @Inject
    @Named("blue")
    Cloth clothBlue;

    @Inject
    Cloth clothBlack;

    @Inject
    Shoe shoe;

    @Inject
    Clothes clothes;

    @Inject
    Utility utility;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        init();
    }

    private void init()
    {
        //DaggerMainComponent build = DaggerMainComponent.builder().mainModule(new MainModule()).build();
        MainComponent build =  DaggerMainComponent.builder()
                .baseComponent(((MyApplication)getApplication()).getBaseComponent())
                .mainModule(new MainModule()).build();

        build.inject(this);

        shoe.toString();
        clothRed.toString();
        clothBlue.toString();
        clothes.toString();

        utility.toString();

        //boolean result = clothBlue ==
        setContentView(R.layout.home_activity);


        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_nav);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                onTabItemSelected(item.getItemId());
                return true;
            }
        });

        onTabItemSelected(R.id.tab_home);
    }


    private void onTabItemSelected(int id){
        Fragment fragment = null;//
        switch (id){
            case R.id.tab_home:
                fragment = QuickBetFragment.newInstance("1", "2");
                break;
            case R.id.tab_racing:
                fragment = new RacingFragment();
                break;
            case R.id.tab_sports:
                fragment = new SportsFragment();
                break;
            case R.id.tab_account:
                fragment = new AccountFragment();
                break;
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.home_container, fragment).commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        Toast.makeText(getBaseContext(), "toast", Toast.LENGTH_LONG);
    }
}
