/* MAINACTIVITY - Tune Trainer (Fragment Activity)
 * Tune Trainer helps tune your instrument and train your ears to various popular tuning styles and
 * instruments.
 * Created by: Christine Berger
 * Last Modified: 10/05/2017
 */
package com.christineberger.android.tunetrainer;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Private member variables to reference the button elements in the UI.
    private Button ukeBtn;
    private Button stndBtn;
    private Button dropDBtn;
    private Button aboutBtn;

    //Private member variables to manage the fragments.
    private FragmentManager fragManager;
    private FragmentTransaction fragTrans;

    private static final String TAG = "BUTTON PRESS: ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Reference the buttons in activity_main.xml
        ukeBtn = $btn(R.id.uke_btn);
        stndBtn = $btn(R.id.stnd_btn);
        dropDBtn = $btn(R.id.dropd_btn);
        aboutBtn = $btn(R.id.about_btn);

        //Create a fragment manager and assign a variable to a new transaction.
        fragManager = getSupportFragmentManager();
        fragTrans = fragManager.beginTransaction();

        //Set the initial tab to disabled and add the initial fragment.
        switchButtons(ukeBtn, stndBtn, dropDBtn, aboutBtn);
        fragTrans.add(R.id.content_fragment, new Ukulele());
        fragTrans.commit();

        /* Set onClick listeners for each button.
         * --- setSelected(int resourceId) : Sets the selected tab based on the id passed.
         * --- fragTrans = fragManager.beginTransaction : Begins a new fragment transaction.
         * --- fragTrans.replace() : Gets a view and replaces the fragment in that view.
         * --- fragTrans.commit() : Applies the changes.
         */
        ukeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSelected(R.id.uke_btn);
                fragTrans = fragManager.beginTransaction();
                fragTrans.replace(R.id.content_fragment, new Ukulele());
                fragTrans.commit();

            }
        });

        stndBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSelected(R.id.stnd_btn);
                fragTrans = fragManager.beginTransaction();
                fragTrans.replace(R.id.content_fragment, new GuitarStandard());
                fragTrans.commit();

            }
        });

        dropDBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSelected(R.id.dropd_btn);
                fragTrans = fragManager.beginTransaction();
                fragTrans.replace(R.id.content_fragment, new GuitarDropD());
                fragTrans.commit();
            }
        });

        aboutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSelected(R.id.about_btn);
                fragTrans = fragManager.beginTransaction();
                fragTrans.replace(R.id.content_fragment, new About());
                fragTrans.commit();
            }
        });


    }

    /*========== $btn() ==========//
    * Parameters : FrameLayout, Int
    * Returns a button by receiving a view and resource id and using them to reference the button
    * from the view given.
    */
    private Button $btn(int btnId) {
        return ((Button) findViewById(btnId));
    }

    /*========== setSelected() ==========//
    * Parameters : Int
    * Gives the appearance of a tab selected and disables the button of the active selection.
    */
    private void setSelected(int resourceId) {

        //Reference the colored containers located under the buttons in activity_main.xml.
        View uke_selected = (View) findViewById(R.id.uke_selected);
        View stnd_selected = (View) findViewById(R.id.standard_selected);
        View dropd_selected = (View) findViewById(R.id.dropd_selected);
        View about_selected = (View) findViewById(R.id.about_selected);

        /* Based on the resourceId given, set the colored containers to indicate which tab is selected
         * and which are not. At the time of this comment, the active color is the primary dark color
         * color is the background color. Disable the active tab's button.
         */
        switch (resourceId) {
            case R.id.uke_btn:
                switchColors(stnd_selected, dropd_selected, about_selected, uke_selected);
                disableBtn(R.id.uke_btn);
                break;
            case R.id.stnd_btn:
                switchColors(dropd_selected,about_selected,uke_selected, stnd_selected);
                disableBtn(R.id.stnd_btn);
                break;
            case R.id.dropd_btn:
                switchColors(about_selected, uke_selected, stnd_selected, dropd_selected);
                disableBtn(R.id.dropd_btn);
                break;
            case R.id.about_btn:
                switchColors(uke_selected, stnd_selected, dropd_selected, about_selected);
                disableBtn(R.id.about_btn);
                break;
        }
    }

    /*========== switchColors() ==========//
    * Parameters : View, View, View, View
    * Takes 3 views that are to be inactive and one view that is to be active, respective of the
    * above parameters. It sets the inactive elements to the background color and the active element
    * to the primary dark color.
    */
    private void switchColors(View dark1, View dark2, View dark3, View selected) {

        //Put the inactive tabs in an array for processing.
        View darkTabs[] = new View[]{ dark1, dark2, dark3 };

        //Counter variable.
        int count;

        //Process all views in the array by setting each element's color to the background color.
        for(count = 0; count < 3; count++) {
            darkTabs[count].setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorDark));
        }

        //Set the selected element's color to primary dark.
        selected.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryDark));
    }

    /*========== disableBtn() ==========//
    * Parameters : Int
    * Disables the active button based on the resource id given.
    */
    private void disableBtn(int resourceId) {

        //Compares the resource id given and sets the active/disabled buttons appropriately.
        switch (resourceId) {
            case R.id.uke_btn:
                switchButtons(ukeBtn, stndBtn, dropDBtn, aboutBtn);
                break;
            case R.id.stnd_btn:
                switchButtons(stndBtn, dropDBtn, aboutBtn, ukeBtn);
                break;
            case R.id.dropd_btn:
                switchButtons(dropDBtn, aboutBtn, ukeBtn, stndBtn);
                break;
            case R.id.about_btn:
                switchButtons(aboutBtn, ukeBtn, stndBtn, dropDBtn);
                break;
        }
    }

    /*========== switchButtons() ==========//
    * Parameters : Button, Button, Button, Button
    * Sets a Button reference to be disabled and three Button references to be enabled, respective
    * of the above parameters.
    */
    private void switchButtons(Button disabled, Button active1, Button active2, Button active3) {

        //Put the Buttons to be active in a Button array for processing.
        Button activeBtns[] = new Button[] { active1, active2, active3 };

        //Counter variable.
        int count;

        //Set the Button to be disabled to disabled.
        disabled.setEnabled(false);

        //Set each Button item in the Button array to be enabled.
        for(count = 0; count < 3; count++) {
            activeBtns[count].setEnabled(true);
        }

    }
}