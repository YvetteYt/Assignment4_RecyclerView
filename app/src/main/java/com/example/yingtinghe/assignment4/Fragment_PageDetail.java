package com.example.yingtinghe.assignment4;


import android.media.Rating;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_PageDetail extends Fragment {
    TextView textView;//for test

    private static final String ARG_MOVIE = "movie";
    private HashMap<String, ?> movie;

    private int total = 0;

    public static Fragment_PageDetail newInstance(HashMap<String, ?> movie) {
        Fragment_PageDetail fragment_pageDetail = new Fragment_PageDetail();
        Bundle args = new Bundle();
        args.putSerializable(ARG_MOVIE, movie);
        fragment_pageDetail.setArguments(args);
//        fragment_pageDetail.movie = movie;

//        args.getSerializable(ARG_MOVIE);
        return fragment_pageDetail;
    }

    public Fragment_PageDetail() {
        // Required empty public constructor
    }

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            movie = (HashMap<String, ?>) getArguments().getSerializable(ARG_MOVIE);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_page_detail, container, false);

        ImageView image = (ImageView)rootView.findViewById(R.id.movieImage);
        TextView name = (TextView)rootView.findViewById(R.id.movieName);
        TextView year = (TextView)rootView.findViewById(R.id.movieYear);
        TextView length = (TextView)rootView.findViewById(R.id.movieLength);
        TextView director = (TextView)rootView.findViewById(R.id.movieDirector);
        TextView cast = (TextView)rootView.findViewById(R.id.movieStars);
        TextView rating = (TextView)rootView.findViewById(R.id.rating);
        TextView description = (TextView)rootView.findViewById(R.id.movieDescription);
        RatingBar ratingBar = (RatingBar)rootView.findViewById(R.id.ratingBar);

        image.setImageResource((Integer)movie.get("image"));
        name.setText((String)movie.get("name"));
        year.setText(("(" + (String)movie.get("year") + ")"));
        length.setText((String)movie.get("length"));
        director.setText((String)movie.get("director"));
        cast.setText((String)movie.get("stars"));
        rating.setText(movie.get("rating").toString());
        description.setText((String)movie.get("description"));

        ratingBar.setMax(5);
        float rating1 = Float.parseFloat(movie.get("rating").toString()) / 2;
        ratingBar.setStepSize(0.05f);
        ratingBar.setRating(rating1);




        /*
        textView = (TextView)view.findViewById(R.id.fragment_page_detail);
        Bundle bundle = getArguments();
        String message = Integer.toString(bundle.getInt("count"));
        textView.setText("This is the" + message);*/
        return rootView;
    }

}
