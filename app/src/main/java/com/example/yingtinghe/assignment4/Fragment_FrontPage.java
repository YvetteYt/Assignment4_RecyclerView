package com.example.yingtinghe.assignment4;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.yingtinghe.assignment4.R;


public class Fragment_FrontPage extends Fragment implements View.OnClickListener {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_front_page, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    public static Fragment_FrontPage newInstance(int index) {
        Fragment_FrontPage f = new Fragment_FrontPage();
        Bundle args = new Bundle();
        args.putInt("index", index);
        f.setArguments(args);
        return f;
    }

    private void initView(View view) {
        //task0
        Button button1 = (Button) view.findViewById(R.id.buttonAboutMe);
        button1.setOnClickListener(this);
        //task2
        Button button2 = (Button) view.findViewById(R.id.buttonRecyclerView);
        button2.setOnClickListener(this);
    }

    private void changeFragment() {
        getFragmentManager().beginTransaction().replace(R.id.fragment_front_page_layout, new Fragment_AboutMe())
                .addToBackStack(null).commit();
    }


    public Fragment_FrontPage() {
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.buttonAboutMe:
                changeFragment();
                break;
            case R.id.buttonRecyclerView:
                Intent intent2 = new Intent(getActivity(), Activity_RecyclerView.class);
                startActivity(intent2);
                break;
        }
    }
}
