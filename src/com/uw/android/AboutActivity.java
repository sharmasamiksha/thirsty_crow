package com.uw.android;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

//******************************************************************************
//player: Object of Mediaplayer class to play audio.
//Samiksha Sharma(ss)
//-----------------------------------------------------------------------------
//Date     Notes                                                       who
//======== ==========================================================  ===
//02/18/14 Added background audio for button click					   ss
//******************************************************************************
	
public class AboutActivity extends Activity {

	MediaPlayer player;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        player = MediaPlayer.create(this, R.raw.wood_sound);
      
	}
	public void onClick(View view)
	{
		this.finish();
		if(player != null)
		{
			player.start();
		}
	}
}

