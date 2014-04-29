package com.uw.android;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple class extending AudioPlayableFragment to provide TTS audio.
 *
 * Created by SamikshaSharma on 2/13/14.
 */
public class Page2Fragment extends AudioPlayableFragment {

    public Page2Fragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return populateView(inflater, container, R.layout.fragment_page2);
    }
    
    @Override
    public String getStoryText() {
    	return getString(R.string.story_part2);
    }
}