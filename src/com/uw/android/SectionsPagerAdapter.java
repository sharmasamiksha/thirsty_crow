package com.uw.android;

import android.speech.tts.TextToSpeech;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * A {@link android.support.v4.app.FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 *
 * Created by SamikshaSharma on 2/13/14.
 *
*/
public class SectionsPagerAdapter extends FragmentPagerAdapter {

	TextToSpeech tts;
	
    public SectionsPagerAdapter(FragmentManager fm, TextToSpeech tts) {
        super(fm);
        this.tts = tts;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        Fragment fragment = null;

        switch(position) {
            case 0:
                fragment = new Page1Fragment();
                break;
            case 1:
                fragment = new Page2Fragment();
                break;
            case 2:
                fragment = new Page3Fragment();
                break;
            case 3:
                fragment = new Page4Fragment();
                break;
            case 4:
                fragment = new Page5Fragment();
                break;
            case 5: 
            	fragment = new Page6Fragment();
            	break;
            case 6: 
            	fragment = new Page7Fragment();
            	break;
        }
        
        if (fragment instanceof AudioPlayable) {
        	((AudioPlayable) fragment).setTtsToPlay(tts);
        }
        
        return fragment;
    }

    @Override
    public int getCount() {
        // Show 7 total pages.
        return 7;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return null;
    }
}