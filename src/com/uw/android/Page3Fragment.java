package com.uw.android;

import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

//******************************************************************************
//player: Object of Mediaplayer class to play audio.
//isAudioPlaying: boolean value to determine if the audio is running.
//Samiksha Sharma(ss)
//-----------------------------------------------------------------------------
//Date     Notes                                                       who
//======== ==========================================================  ===
//02/16/14 Added animation                                             ss
//02/17/14 Added functions to play the background audio                ss
//02/18/14 Added functions to play text to speech functionality        ss
//******************************************************************************

/**
 * A placeholder fragment containing a simple view.
 *
 * Created by SamikshaSharma on 2/13/14.
 */

public class Page3Fragment extends AudioPlayableFragment {

boolean isAudioPlaying;
MediaPlayer player;
    
    public Page3Fragment() {
    	
    	isAudioPlaying = false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return populateView(inflater, container, R.layout.fragment_page3);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        
        View view = getView();
        
        // Animate flying crow
        ImageView iv = (ImageView) view.findViewById(R.id.ivFlyingCrow);
        iv.setBackgroundResource(R.drawable.crow_flying);
        ((AnimationDrawable) iv.getBackground()).start();
        player = MediaPlayer.create(this.getActivity(), R.raw.crow_caw);
        player.setLooping(true);
        
        
    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);
		if (isVisibleToUser) {
			// Do something when fragment is visible
			if (player != null && !isAudioPlaying) {
				isAudioPlaying = true;
				player.start();
			} 
		}
		else {
			// Stop audio if fragment is not visible
			if (player != null && isAudioPlaying) {
				isAudioPlaying = false;
				player.stop();
				try {
					player.prepare();
				} catch (Exception e) {
					Toast.makeText(this.getActivity(), "Unable to start audio.", Toast.LENGTH_SHORT).show();
				}
	    	}
		}
	}
    
    @Override
    public void onDestroy() {
    	super.onDestroy();
    	player.stop();
    }
    @Override
    public String getStoryText() {
    	return getString(R.string.story_part3);
    }
}