package com.uw.android;

import java.util.HashMap;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnUtteranceCompletedListener;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * This class extends Fragment and implements AudioPlayable. This allows 
 * it to play TextToSpeech audio which is required by all other
 * fragments to play story audio.
 * 
 * @author SamikshaSharma
 *
 */
public abstract class AudioPlayableFragment extends Fragment implements AudioPlayable {

	TextToSpeech tts = null;
	Button bPlayAudio;
	Button bStopAudio;
	private HashMap<String, String> ttsMap;

	public AudioPlayableFragment() {
		// Initialize map used in TTS
		ttsMap = new HashMap<String, String>();
		ttsMap.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, this.getClass().getSimpleName());
	}

	@Override
    public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		View view = getView();
		
		// Get Audio buttons and set listeners
	    bPlayAudio = (Button) view.findViewById(R.id.bPlay);
	    bPlayAudio.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String text = getStoryText();
				playTtsAudio(text);
				bPlayAudio.setBackgroundResource(R.drawable.button_background);
				bStopAudio.setBackgroundResource(R.drawable.button_background_crow);
			}
		});
	    
	    bStopAudio = (Button) view.findViewById(R.id.bStop);
	    bStopAudio.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				stopTtsAudio();
				bPlayAudio.setBackgroundResource(R.drawable.button_background_crow);
				bStopAudio.setBackgroundResource(R.drawable.button_background);
			}
		});
	    
	    // Set story text
        TextView tvStory = (TextView) view.findViewById(R.id.tvStory);
        tvStory.setText(getStoryText());
	}
	
	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);
		
		if (isVisibleToUser) {
			// Do something when fragment is visible
			registerUtteranceListener();
		} else {
			// Stop audio if fragment is not visible
			stopTtsAudio();	
		}
	}
	
	@Override
	public void onPause() {
		super.onPause();
		stopTtsAudio();
	}

	@Override
	public void setTtsToPlay(TextToSpeech tts) {
		this.tts = tts;
	}

	/**
	 * Function to listen to onUtteranceCompletedListener.
	 * We use this to reset stop and start buttons.
	 */
	@SuppressWarnings("deprecation")
	private void registerUtteranceListener() {
		if (tts != null) {
			tts.setOnUtteranceCompletedListener(new OnUtteranceCompletedListener() {
				@Override
				public void onUtteranceCompleted(String arg0) {
					AudioPlayableFragment.this.getActivity().runOnUiThread(new Runnable() {
						@Override
						public void run() {
							bPlayAudio.setBackgroundResource(R.drawable.button_background_crow);
							bStopAudio.setBackgroundResource(R.drawable.button_background);
						}
					});
				}
			});
		}
	}
	
	/**
	 * Get story text. This is abstract so that 
	 * classes who implement AudioPlayableListener provide
	 * this story string. 
	 * @return
	 */
	public abstract String getStoryText();
	
	/**
	 * Common function to populate view for main activity fragments.
	 * 
	 * @param inflater
	 * @param container
	 * @param resId
	 * @return
	 */
	protected View populateView(LayoutInflater inflater, ViewGroup container, int resId) {
		View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        RelativeLayout rlImage = (RelativeLayout) rootView.findViewById(R.id.rlImages);
        View viewPage1 = inflater.inflate(resId, null);
        rlImage.addView(viewPage1, rlImage.getLayoutParams());
        return rootView;
	}

	private void playTtsAudio(String message) {
		stopTtsAudio();
		if (tts != null) {
			tts.speak(message, TextToSpeech.QUEUE_FLUSH, ttsMap);
		}
	}

	private void stopTtsAudio() {
		if (tts != null && tts.isSpeaking()) {
			tts.stop();
		}
	}
}