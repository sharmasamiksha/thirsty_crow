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
public class Page6Fragment extends AudioPlayableFragment {

    public Page6Fragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
    	return populateView(inflater, container, R.layout.fragment_page6);
    }

    @Override
    public String getStoryText() {
    	return getString(R.string.story_part6);
    }
}