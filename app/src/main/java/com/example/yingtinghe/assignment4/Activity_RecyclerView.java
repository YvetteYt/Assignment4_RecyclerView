package com.example.yingtinghe.assignment4;

import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.CheckBox;
import android.widget.ListView;

import java.util.HashMap;

public class Activity_RecyclerView extends AppCompatActivity implements Fragment_RecyclerView.OnListItemSelectedListener {
    Fragment mContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        if (savedInstanceState != null) {
            mContent = getFragmentManager().getFragment(savedInstanceState, "mContent");
        } else {
            mContent = Fragment_RecyclerView.newInstance(R.id.fragment_recycler_view);
        }
        //load fragment   //newInstance: create a new fragment
        getFragmentManager().beginTransaction()
                .replace(R.id.recycler_view_container, mContent)
                .commit();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getFragmentManager().putFragment(outState, "mContent", mContent);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState)
    {
        super.onRestoreInstanceState(savedInstanceState);
        getFragmentManager().getFragment(savedInstanceState, "mContent");
    }

    @Override
    public void onListItemSelected(int position, HashMap<String, ?> movie) {
        getFragmentManager().beginTransaction()
                .replace(R.id.fragment_recycler_view, Fragment_PageDetail.newInstance(movie))
                .addToBackStack(null)
                .commit();
    }
}
