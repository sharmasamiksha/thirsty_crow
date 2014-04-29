package com.uw.android;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A placeholder fragment containing a simple view.
 *
 * Created by SamikshaSharma on 2/13/14.
 */
public class Page4Fragment extends AudioPlayableFragment {

    public Page4Fragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
    	return populateView(inflater, container, R.layout.fragment_page4);
    }

    @Override
    public String getStoryText() {
    	return getString(R.string.story_part4);
    }
}