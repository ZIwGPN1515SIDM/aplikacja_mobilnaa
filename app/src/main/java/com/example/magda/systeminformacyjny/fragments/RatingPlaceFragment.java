package com.example.magda.systeminformacyjny.fragments;

import android.databinding.DataBindingUtil;
import android.net.IpPrefix;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.magda.systeminformacyjny.R;
import com.example.magda.systeminformacyjny.databinding.FragmentInfoPlaceBinding;
import com.example.magda.systeminformacyjny.databinding.FragmentRatingPlaceBinding;
import com.example.magda.systeminformacyjny.models.IPlaceItem;

/**
 * Created by JB on 2017-04-09.
 */

public class RatingPlaceFragment extends Fragment {

    private static final String PLACE_TAG = "place";
    private IPlaceItem iPlaceItem;

    public static RatingPlaceFragment getInstance(IPlaceItem iPlaceItem) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(PLACE_TAG, iPlaceItem);
        RatingPlaceFragment ratingPlaceFragment = new RatingPlaceFragment();
        ratingPlaceFragment.setArguments(bundle);
        return ratingPlaceFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        iPlaceItem = (IPlaceItem) getArguments().getSerializable(PLACE_TAG);
    }

    @Override
    public View onCreateView (LayoutInflater inflater, @Nullable
            ViewGroup container, @Nullable
                                  Bundle savedInstanceState) {
        FragmentRatingPlaceBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_rating_place, null, false);
        binding.setPlace(iPlaceItem);
        return binding.getRoot();
    }

}
