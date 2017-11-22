/* GUITARSTANDARD FRAGMENT
 * Fragment that displays the Standard Guitar tuning screen functions in the Tune Trainer app.
 * Created by: Christine Berger
 * Last Modified: 10/5/2017
 */
package com.christineberger.android.tunetrainer;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.io.IOException;

import static android.content.Context.AUDIO_SERVICE;


public class GuitarStandard extends Fragment {

    //Private member variable
    private MediaPlayer media;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Inflate the fragment's view and assign it to a variable that can be worked with and then passed.
        final FrameLayout fragmentLayout = (FrameLayout) inflater.inflate(R.layout.fragment_guitar_standard, container, false);

        //Reference the buttons from fragment_guitar_standard.xml.
        Button btnLowE = $btn(fragmentLayout, R.id.btn_stnd_low_e);
        Button btnA = $btn(fragmentLayout, R.id.btn_stnd_a);
        Button btnD = $btn(fragmentLayout, R.id.btn_stnd_d);
        Button btnG = $btn(fragmentLayout, R.id.btn_stnd_g);
        Button btnB = $btn(fragmentLayout, R.id.btn_stnd_b);
        Button btnHighE = $btn(fragmentLayout, R.id.btn_stnd_high_e);



        /* Set the onClick events for each button. When a button is clicked, the sound resource is
         * passed to setMedia(), which then handles the media functions and switches the media to
         * the resource passed.
         */
        btnLowE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setMedia(R.raw.guitar_e);
            }
        });

        btnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setMedia(R.raw.guitar_a);
            }
        });

        btnD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setMedia(R.raw.guitar_d);
            }
        });

        btnG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setMedia(R.raw.guitar_g);
            }
        });

        btnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setMedia(R.raw.guitar_b);
            }
        });

        btnHighE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setMedia(R.raw.guitar_high_e);
            }
        });

        //Return the fragment view contents.
        return fragmentLayout;
    }

    /*========== $btn() ==========//
     * Parameters : FrameLayout, Int
     * Returns a button by receiving a view and resource id and using them to reference the button
     * from the view given.
     */
    private Button $btn(FrameLayout view, int btnId) {
        return (Button) view.findViewById(btnId);
    }

    /*========== setMedia() ==========//
     * Parameters : Int
     * Takes a resource id (that is a supported file of MediaPlayer) and manages the playback and
     * switching of the audio media files.
     */
    private void setMedia(int resourceId) {

        //If a media file has already been loaded AND the media is still playing, then stop the
        //media player, reset the parameters, and release all resources to free up space.
        if(media != null) {
            if (media.isPlaying()) {
                media.stop();
                media.release();
            }
        }

        //Create a new MediaPlayer object with the audio resource file given.
        media = MediaPlayer.create(getActivity().getApplicationContext(), resourceId);

        //Set a listener for when the audio file completes playback.
        media.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {

                //Stop the media player, reset the parameters, release the resources, and reset the
                //media player variable to null.
                media.stop();
                media.reset();
                media.release();

                media = null;
            }
        });

        //Play the loaded media.
        media.start();

    }
}
