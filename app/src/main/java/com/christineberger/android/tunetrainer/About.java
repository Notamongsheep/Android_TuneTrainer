/* ABOUT FRAGMENT
 * Fragment that displays the informational screen of the application.
 * Created by: Christine Berger
 * Last Modified: 10/5/2017
 */
package com.christineberger.android.tunetrainer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class About extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ///Return the fragment view contents.
        return inflater.inflate(R.layout.fragment_about, container, false);
    }
}
