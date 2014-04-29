package com.uw.android;

import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

//******************************************************************************
//player: Object of Mediaplayer class to play audio.
//isAudioPlaying: boolean value to determine if the audio is running.
//animationDrawable: object of class AnimationDrawable used for making onclick animations.
//Samiksha Sharma(ss)
//-----------------------------------------------------------------------------
//Date     Notes                                                       who
//======== ==========================================================  ===
//02/16/14 Added animation                                             ss
//02/17/14 Added functions to play the background audio                ss
//02/18/14 Added functions to play text to speech functionality        ss
//******************************************************************************
/**
 * A fragment with ability to play text to speech audio.
 *
 * Created by SamikshaSharma on 2/13/14.
 */
public class Page5Fragment extends AudioPlayableFragment {

	MediaPlayer player;
	AnimationDrawable animationDrawable;
	boolean isAudioPlaying;
	
	public Page5Fragment() {
		isAudioPlaying = false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
    	return populateView(inflater, container, R.layout.fragment_page5);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View view = getView();
        ImageView iv = (ImageView) view.findViewById(R.id.ivDropStone);
        iv.setBackgroundResource(R.drawable.drop_stone);
        animationDrawable = (AnimationDrawable) iv.getBackground();
        
        // Create stone drop audio and set to loop infinitely
        player = MediaPlayer.create(this.getActivity(), R.raw.stone_drop);
        player.setLooping(true);
     
        // Set on click listener to start animation every time a user presses 
        // on the image.
        iv.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// Toggle animation
				if (animationDrawable.isRunning()) {
					stopAnimation();
					stopAudio();
				} else {
					startAnimation();
					startAudio();
				}
			}
		});
    }
    
    @Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);
		
		if (isVisibleToUser) {
			// Do something when fragment is visible
		} else {
			// Stop audio if fragment is not visible
			stopAudio();
			stopAnimation();
		}
	}
    
    @Override
	public void onResume() {
		super.onResume();
		// Create player on resume
		player = MediaPlayer.create(this.getActivity(), R.raw.stone_drop);
        player.setLooping(true);
	}

	@Override
	public void onPause() {
		super.onPause();
		// Stop all audio and animations
		stopAudio();
		stopAnimation();
		if (player != null) {
			player.release();
			player = null;
		}
	}
	
	private void startAnimation() {
		if (animationDrawable != null) {
			animationDrawable.start();
		}
	}
	
	private void stopAnimation() {
		if (animationDrawable != null) {
			animationDrawable.stop();
			animationDrawable.selectDrawable(0);
		}
	}
	
	private void startAudio() {
		if (player != null && !isAudioPlaying) {
			isAudioPlaying = true;
			player.start();
    	}
	}

	private void stopAudio() {
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
    
    @Override
    public String getStoryText() {
    	return getString(R.string.story_part5);
    }
}