package com.uw.android;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

//******************************************************************************
//player: Object of Mediaplayer class to play audio.
//Samiksha Sharma(ss)
//-----------------------------------------------------------------------------
//Date     Notes                                                       who
//======== ==========================================================  ===
//02/18/14 Added background audio for button click					   ss
//02/19/14 Added animation                                             ss
//******************************************************************************

public class TitleActivity extends Activity {

	MediaPlayer player;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);
        player = MediaPlayer.create(this, R.raw.wood_sound);
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.rlTitle);
        layout.setBackgroundResource(R.drawable.background_crow_blink);
        AnimationDrawable drawable = (AnimationDrawable) layout.getBackground();
        drawable.start();
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (player != null) {
			player.release();
		}
	}

	public void onClick(View view)
	{
		switch(view.getId()){
			case R.id.button_start:
				if(player != null)
				{
					player.start();
				}
				Intent intentStart = new Intent(this, MainActivity.class);
				startActivity(intentStart);
				break;	
			case R.id.button_about:
				if (player != null) 
				{
					player.start();
				}
				Intent intentAbout = new Intent(this, AboutActivity.class);
				startActivity(intentAbout);
		        break;
		}
	}
}
