package com.uw.android;

import android.speech.tts.TextToSpeech;
/** 
 * Interface for fragments to implement.
 * This allows MainActivity to send tts object to fragments 
 * for them to use.
 * 
 * @author SamikshaSharma
 *
 */
public interface AudioPlayable {
	
	public void setTtsToPlay(TextToSpeech tts);
}
