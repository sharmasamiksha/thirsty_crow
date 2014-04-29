package com.uw.android;

import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * A fragment class extending AudioPlayableFragment
 *
 * Created by SamikshaSharma on 2/13/14.
 */

//******************************************************************************
//player: Object of Mediaplayer class to play audio.
//isAudioPlaying: boolean value to determine if the audio is running.
//Samiksha Sharma(ss)
//-----------------------------------------------------------------------------
//Date     Notes                                                       who
//======== ==========================================================  ===
//02/16/14 Added animation                                             ss
//01/17/14 Added functions to play the background audio                ss
//01/18/14 Added functions to play text to speech functionality        ss
//******************************************************************************
public class Page1Fragment extends AudioPlayableFragment {

	MediaPlayer player;
	boolean isAudioPlaying;
	public Page1Fragment() {
		isAudioPlaying = false;
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
    	return this.populateView(inflater, container, R.layout.fragment_page1);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        
        View view = getView();
        
        // Animate glowing sun
        ImageView iv = (ImageView) view.findViewById(R.id.ivSun);
        iv.setBackgroundResource(R.drawable.sun);
        ((AnimationDrawable) iv.getBackground()).start();
    }
    
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);
		
		if (isVisibleToUser) {
			// Start background audio when fragment is visible
			startBackgroundAudio();
		} else {
			// Stop background audio if fragment is not visible
			stopBackgroundAudio();
		}
	}

	private void stopBackgroundAudio() {
		if (player != null && isAudioPlaying) {
			isAudioPlaying = false;
			player.stop();
			try {
				player.prepare();
			} catch (Exception e) {
				Toast.makeText(this.getActivity(),
						"Unable to start audio.", Toast.LENGTH_SHORT).show();
			}
		}
	}

	private void startBackgroundAudio() {
		if (player==null)
		{
			 player = MediaPlayer.create(this.getActivity(), R.raw.wind1);
		     player.setLooping(true);
		}
		if (player != null && !isAudioPlaying) {
			isAudioPlaying = true;
			player.start();
		}
	}

    @Override
    public void onDestroy() {
    	super.onDestroy();
    	if(player!=null)
    	{
    		player.stop();
    		player.release();
    	}
    }
    @Override
    public String getStoryText() {
    	return getString(R.string.story_part1);
    }
}