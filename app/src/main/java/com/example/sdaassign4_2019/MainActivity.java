package com.example.sdaassign4_2019;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

/*
 * @author Chris Coughlan 2019
 */
public class MainActivity extends AppCompatActivity {
    public static final int BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT = 1;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set the toolbar we have overridden
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = findViewById(R.id.pager);
        ViewPageAdapter adapter = new ViewPageAdapter(getSupportFragmentManager(), BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, getApplicationContext());
        viewPager.setAdapter(adapter);
        //viewPager.getCurrentItem();

        Intent myCheckout = new Intent(this, CheckOut.class);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String checkString = extras.getString("SWITCH");

            if (checkString != "SETTINGS") {
                viewPager.setCurrentItem(2);
            }
        }


        //FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
        //trans.add(adapter.getItem(0),"First");
        //trans.add(adapter.getItem(1),"Second");
        //trans.add(adapter.getItem(2),"Third");
        //trans.replace(R.id.pager, adapter.getItem(2));
        //trans.commit();

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

    }

}

