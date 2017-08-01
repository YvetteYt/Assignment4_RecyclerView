package com.example.yingtinghe.assignment4;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yingtinghe on 7/28/17.
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {
    private List<Map<String, ?>> mData;
    private Context mContext;
    OnItemClickListener mItemClickListener;

    //constructor
    public MyRecyclerViewAdapter(Context context, List<Map<String, ?>> data) {
        mContext = context;
        mData = data;
    }


    public interface OnListItemSelectedListener {
        public void onListItemSelected(int position, HashMap<String, ?> movie);
    }

    public void SetOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
        public void onItemLongClick(View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView icon;
        public TextView title;
        public TextView description;
        public CheckBox checkBox;

        public ViewHolder(View view) {
            super(view); //always need
            icon = (ImageView) view.findViewById(R.id.card_image);
            title = (TextView) view.findViewById(R.id.card_title);
            description = (TextView) view.findViewById(R.id.card_description);
            checkBox = (CheckBox) view.findViewById(R.id.card_checkBox);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mItemClickListener != null) {
                        //mItemClickListener is the handler, and onItemClick is an API
                        mItemClickListener.onItemClick(view, getAdapterPosition());
                        //mItemClickListener.onItemClick(view, getAdapterPosition());
                    }
                }
            });

            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    if (mItemClickListener != null) {
                        //mItemClickListener is the handler, and onItemClick is an API
                        mItemClickListener.onItemLongClick(view, getAdapterPosition());
                        //mItemClickListener.onItemClick(view, getAdapterPosition());
                    }
                    return true;
                }
            });

            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //mItemClickListener is the handler, and onItemClick is an API
                    HashMap<String, Boolean> item = (HashMap<String, Boolean>) mData.get(getAdapterPosition());
                    boolean origin = item.get("selection");
                    item.put("selection", !origin);
                }

            });
        }


        public void bindMovieData(Map<String, ?> movie, int position) {
            title.setText((String)movie.get("name"));
            description.setText((String)movie.get("description"));
            icon.setImageResource((Integer)movie.get("image"));
            checkBox.setChecked((Boolean)movie.get("selection"));
            /*
            int i;
            if (position < 3) {
                i = 0;
            } else if (position >= mData.size() - 3){
                i = 2;
            } else {
                i = 1;
            }
            switch (i) {
                case 0:
                    title.setText((String)movie.get("name"));
                    description.setText((String)movie.get("description"));
                    icon.setImageResource((Integer)movie.get("image"));
                    checkBox.setChecked((Boolean)movie.get("selection"));
                    title.setTextColor(Color.RED);
                    break;
                case 1:
                    title.setText((String)movie.get("name"));
                    description.setText((String)movie.get("description"));
                    icon.setImageResource((Integer)movie.get("image"));
                    checkBox.setChecked((Boolean)movie.get("selection"));
                    break;
                case 2:
                    title.setText((String)movie.get("name"));
                    description.setText((String)movie.get("description"));
                    icon.setImageResource((Integer)movie.get("image"));
                    checkBox.setChecked((Boolean)movie.get("selection"));
                    title.setTextColor(Color.WHITE);
                    break;
                default:
                    title.setText((String)movie.get("name"));
                    description.setText((String)movie.get("description"));
                    icon.setImageResource((Integer)movie.get("image"));
                    checkBox.setChecked((Boolean)movie.get("selection"));
            }*/
        }

    }
/*
    @Override
    public int getItemViewType(int position) {
        if (position < 3) {
            return 0;
        } else if (position >= mData.size() - 3){
            return 2;
        }
        return 1;
    }
*/
    /*The next two are APIs*/
    /*Create new views(If not the first time, the method will be called)*/
    @Override
    public MyRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_card_view, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }
    /*Replace the contents of a view*/
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Map<String, ?> movie = mData.get(position);
        holder.bindMovieData(movie, position);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

}



