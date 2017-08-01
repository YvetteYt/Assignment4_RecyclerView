package com.example.yingtinghe.assignment4;

import android.content.ClipData;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManagerNonConfig;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class Activity_FrontPage extends AppCompatActivity {
    android.app.Fragment mContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //load activity
        setContentView(R.layout.activity_front_page);


        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        if (savedInstanceState != null) {
            mContent = getFragmentManager().getFragment(savedInstanceState, "mContent");
        } else {
            mContent = Fragment_FrontPage.newInstance(R.id.fragment_front_page_layout);
        }
        //load fragment   //newInstance: create a new fragment
        getFragmentManager().beginTransaction()
                .replace(R.id.container,mContent)
                .commit();

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getFragmentManager().putFragment(outState, "mContent", mContent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_one:
                getFragmentManager().beginTransaction().replace(R.id.fragment_front_page_layout, new Fragment_AboutMe())
                        .addToBackStack(null).commit();
                return true;
            case R.id.action_two:
                Intent intent2 = new Intent(Activity_FrontPage.this, Activity_RecyclerView.class);
                startActivity(intent2);
                return true;
/*            case R.id.action_three:
                Intent intent3 = new Intent(Activity_FrontPage.this, Activity_MasterDetail.class);
                startActivity(intent3);
                return true;*/
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
