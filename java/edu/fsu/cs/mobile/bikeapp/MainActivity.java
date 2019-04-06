package edu.fsu.cs.mobile.bikeapp;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager manager = getFragmentManager();
        FragmentTransaction trans = manager.beginTransaction();
        MapFragment fragment = new MapFragment();
        trans.add(R.id.fragment_container,fragment,"my_fragment");
        trans.commit();

    }
}
