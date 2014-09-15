package com.dipankar.androidtutorials;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by ddutta on 9/15/2014.
 * https://gist.github.com/kwent/5875749
 */
public class ActivityTransitionAnimations extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //opening transition animations
        overridePendingTransition(R.anim.activity_open_translate,R.anim.activity_close_scale);
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        //closing transition animations
        overridePendingTransition(R.anim.activity_open_scale,R.anim.activity_close_translate);
    }
}