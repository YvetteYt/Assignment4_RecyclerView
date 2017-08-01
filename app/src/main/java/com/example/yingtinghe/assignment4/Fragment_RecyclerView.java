package com.example.yingtinghe.assignment4;

import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.SlideInBottomAnimationAdapter;
import jp.wasabeef.recyclerview.animators.LandingAnimator;


public class Fragment_RecyclerView extends Fragment {
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    MyRecyclerViewAdapter mRecyclerViewAdapter;
    MovieData movieData = new MovieData();

    OnListItemSelectedListener mListener;//new

    public Fragment_RecyclerView() {

    }

    public static Fragment_RecyclerView newInstance(int index) {
        Fragment_RecyclerView f = new Fragment_RecyclerView();
        Bundle args = new Bundle();
        args.putInt("index", index);
        f.setArguments(args);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        final Context contextThemeWrapper = new android.view.ContextThemeWrapper(getActivity(), R.style.Theme_AppCompat_DayNight_DarkActionBar);
//        LayoutInflater localInflater = inflater.cloneInContext(contextThemeWrapper);
//        final View rootView = localInflater.inflate(R.layout.fragment_recycler_view, container, false);

        final View rootView = inflater.inflate(R.layout.fragment_recycler_view, container, false);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.cardList);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getActivity());

        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerViewAdapter = new MyRecyclerViewAdapter(getActivity(), movieData.getMoviesList());
        mRecyclerView.setAdapter(mRecyclerViewAdapter);

//        mRecyclerViewAdapter.SetOnItemClickListener(new MyRecyclerViewAdapter.OnItemClickListener() {
        mRecyclerViewAdapter.SetOnItemClickListener(new MyRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                HashMap<String, ?> movie = (HashMap<String, ?>) movieData.getItem(position);
                mListener.onListItemSelected(position, movie);
                /*getFragmentManager().beginTransaction()
                        .replace(R.id.frag ment_recycler_view, Fragment_PageDetail.newInstance(movie))
                        .addToBackStack(null)
                        .commit();*/
            }

            @Override
            public void onItemLongClick(View view, int position) {
                movieData.addItem(position, (HashMap) ((HashMap) movieData.getItem(position)).clone());
                mRecyclerViewAdapter.notifyItemInserted(position + 1);
            }


        });


        //Item Animation
        itemAnimation();
        //Adapter Animation
        adapterAnimation();

        Button clearAll = (Button) rootView.findViewById(R.id.buttonClearAll);
        clearAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < mRecyclerViewAdapter.getItemCount(); i++) {
                    HashMap<String, Boolean> item = (HashMap<String, Boolean>) movieData.getItem(i);
                    item.put("selection", false);
                }
                mRecyclerViewAdapter.notifyDataSetChanged();
            }
        });

        Button selectAll = (Button) rootView.findViewById(R.id.buttonSelectAll);
        selectAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < mRecyclerViewAdapter.getItemCount(); i++) {
                    HashMap<String, Boolean> item = (HashMap<String, Boolean>) movieData.getItem(i);
                    item.put("selection", true);
                }
                mRecyclerViewAdapter.notifyDataSetChanged();
            }
        });

        Button delete = (Button) rootView.findViewById(R.id.buttonDelete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = mRecyclerViewAdapter.getItemCount() - 1; i >=0; i--) {
                    HashMap<String, Boolean> item = (HashMap<String, Boolean>) movieData.getItem(i);
                    boolean selection = item.get("selection");
                    if (selection == true) {
                        movieData.removeItem(i);
                        mRecyclerViewAdapter.notifyItemRemoved(i);
                    }
                }
            }
        });

        Button sort = (Button) rootView.findViewById(R.id.buttonSort);
        sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Collections.sort(movieData.getMoviesList(), new Comparator<Map<String, ?>>() {
                    @Override
                    public int compare(Map<String, ?> map, Map<String, ?> t1) {
                        HashMap<String, String> item1 = (HashMap<String, String>) map;
                        HashMap<String, String> item2 = (HashMap<String, String>) t1;
                        return Integer.valueOf(item2.get("year")) - Integer.valueOf(item1.get("year"));
                    }
                });
                for (int i = mRecyclerViewAdapter.getItemCount() - 1; i >=0; i--) {
                    HashMap<String, Boolean> item = (HashMap<String, Boolean>) movieData.getItem(i);
                    if (i < 3) {

                    }
                }
                mRecyclerViewAdapter.notifyDataSetChanged();
            }
        });


        return rootView;
    }

    public interface OnListItemSelectedListener {
        public void onListItemSelected(int position, HashMap<String, ?> movie);
    }

    public interface OnClickListener {
        void onClick(View v);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (OnListItemSelectedListener) getActivity();
    }

    public void itemAnimation() {
        LandingAnimator itemAnimator = new LandingAnimator();
        itemAnimator.setInterpolator(new OvershootInterpolator());
        itemAnimator.setAddDuration(400);
        itemAnimator.setRemoveDuration(400);
        mRecyclerView.setItemAnimator(itemAnimator);
    }

    public void adapterAnimation() {
        SlideInBottomAnimationAdapter slideAdapter = new SlideInBottomAnimationAdapter(mRecyclerViewAdapter);
        slideAdapter.setDuration(500);

        ScaleInAnimationAdapter scaleAdapter = new ScaleInAnimationAdapter(slideAdapter);
        scaleAdapter.setDuration(200);
        mRecyclerView.setAdapter(scaleAdapter);
    }

}


