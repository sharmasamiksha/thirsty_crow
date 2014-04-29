package com.uw.android;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * A placeholder fragment containing a simple view.
 *
 * Created by SamikshaSharma on 2/13/14.
 */
public class Page7Fragment extends AudioPlayableFragment {
//******************************************************************************
//player: Object of Mediaplayer class to play audio.
//isAudioPlaying: boolean value to determine if the audio is running.
//Samiksha Sharma(ss)
//-----------------------------------------------------------------------------
//Date     Notes                                                       who
//======== ==========================================================  ===
//02/17/14 Added functions to play the background audio                ss
//02/18/14 Added functions to play text to speech functionality        ss
//******************************************************************************

	MediaPlayer player;
	boolean isAudioPlaying;
	
    public Page7Fragment() {
    	
    	isAudioPlaying = false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
    	return populateView(inflater, container, R.layout.fragment_page7);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);
		
		if (isVisibleToUser) {
			// Do something when fragment is visible
			startBackgroundAudio();
		} else {
			// Stop audio if fragment is not visible
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
			 player = MediaPlayer.create(this.getActivity(), R.raw.happy_bird);
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
    	return getString(R.string.story_part7);
    }
}