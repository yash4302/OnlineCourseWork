/*
 * Copyright 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package edu.vandy.mooc.aad3.assignment.activities;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.widget.ViewAnimator;
import android.widget.ViewFlipper;

import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import edu.vandy.mooc.aad3.R;
import edu.vandy.mooc.aad3.assignment.services.DownloadAtomFeedService;
import edu.vandy.mooc.aad3.framework.Interfaces.UpdateEntriesInterface;
import edu.vandy.mooc.aad3.framework.common.activities.CustomLoggingActivityBase;
import edu.vandy.mooc.aad3.framework.common.logger.Log;
import edu.vandy.mooc.aad3.framework.common.logger.LogFragment;
import edu.vandy.mooc.aad3.framework.common.logger.LogWrapper;
import edu.vandy.mooc.aad3.framework.common.logger.MessageOnlyLogFilter;
import edu.vandy.mooc.aad3.framework.orm.Entry;

/**
 * A simple launcher activity containing a summary sample description, sample log and a custom
 * {@link Fragment} which can display a view.
 * <p>
 * For devices with displays with a width of 720dp or greater, the sample log is always visible,
 * on other devices it's visibility is controlled by an item on the Action Bar.
 */
public class MainActivity extends CustomLoggingActivityBase {
    /**
     * String used for logging.
     */
    private static final String TAG = MainActivity.class.getCanonicalName();

    /**
     * This is CNN's Youtube Atom Feed:
     */
    private final static String CNN_YOUTUBE_ATOM_FEED_URL = "https://www.youtube.com"
            + "/feeds/videos.xml?channel_id=UCupvZG-5ko_eiXAupbDfxWw";

    /**
     * Whether the processing should be offline
     */
    private boolean mOffline;

    /**
     * Request Code for downloading youtube entries.
     */
    private final static int REQUEST_YOUTUBE_ENTRIES = 1;

    /**
     * View flipper support to show and hide a progressBar.
     */
    private ViewFlipper viewFlipper;

    /*
     * The BroadCastReceiver that enables returning of the Service's results.
     */
    private DownloadStateReceiver mDownloadStateReceiver;

    /**
     * reference to RecyclerViewFragment implementing this interface.
     */
    private UpdateEntriesInterface updateEntriesInterface;

    /**
     * Whether the Log Fragment is currently shown
     */
    private boolean mLogShown;
    private int mRecyclerFlipperIndex;

    private int mProgressFlipperIndex;

    /*
     * Android Activity Lifecycle methods
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView
        setContentView(R.layout.activity_main);
        // setupFragment
        setupFragment(savedInstanceState);
        // setup the FAB - Floating Action Button
        setupFAB();

        // get offline testing boolean from prefrences (if there) 'false' if not there.
        SharedPreferences prefs = getApplicationContext().getSharedPreferences("MyPref", 0);
        mOffline = prefs.getBoolean("offline_fetch", false);

        // Setup the view flipper.
        setupViewFlipper();
    }

    @Override
    protected void onPause() {
        // via the LocalBroadcastManager, unregister the receiver currently registered.
        // TODO - you fill in here.
        

        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Create an IntentFilter. The filter's action is BROADCAST_ACTION
        // TODO - you fill in here.
        

        // Instantiates a new DownloadStateReceiver
        // TODO - you fill in here.
        

        // Registers the DownloadStateReceiver and its intent filters via an
        // istance of LocalBroadcastManager
        // TODO - you fill in here.
        
    }


    /**
     * Hook method called when a configuration change is about to occur.
     *
     * @param outState Bundle used to save the state.
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        ArrayList<Entry> entries = updateEntriesInterface.getEntries();
        outState.putParcelableArrayList("Entries", entries);
        super.onSaveInstanceState(outState);
    }

    /**
     * This method is called after {@link #onStart} when the activity is being
     * re-initialized from a previously saved state, given here in
     * <var>savedInstanceState</var>.
     *
     * @param savedInstanceState the data most recently supplied in {@link
     *                           #onSaveInstanceState}.
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (updateEntriesInterface != null) {
            ArrayList<Entry> entries =
                    savedInstanceState.getParcelableArrayList("Entries");
            updateEntriesInterface.updateEntries(entries);
        }
        super.onRestoreInstanceState(savedInstanceState);
    }

    /**
     * Start a download.
     */
    private void startDownload(Uri url) {
        // Create an intent to download the YouTube Atom Feed for CNN.
        // This will involve calling DownloadAtomFeedService's makeIntent.
        // In addition, you will pass REQUEST_YOUTUBE_ENTRIES and
        // the URI parsed version of CNN_YOUTUBE_ATOM_FEED_URL obtained via Uri.parse(...)
        // TODO - you fill in here.
        

        Log.d(TAG,
                "starting the DownloadAtomFeedService for "
                        + url.toString()
        );

        // Show the indeterminate progressBar spinner.
        viewFlipper.setDisplayedChild(mProgressFlipperIndex);

        // call startService on that Intent.
        // TODO - you fill in here.
        
    }

    /**
     * Handle failure to download an image.
     *
     * @param data the Bundle containing the download attempt's information.
     */
    private void handleDownloadFailure(Bundle data) {
        // Extract the URL from the message.
        final Uri url = DownloadAtomFeedService.getRequestUri(data);

        Toast.makeText(this, "image at " + url.toString() + " failed to download!",
                Toast.LENGTH_LONG).show();
    }

    /*
     * Menu hook methods.
     */

    /**
     * Create Options Menu
     *
     * @param menu Menu to create
     * @return true if menu is created.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * Prepare the Screen's standard options menu to be displayed.  This is
     * called right before the menu is shown, every time it is shown.  You can
     * use this method to efficiently enable/disable items or otherwise
     * dynamically modify the contents.
     *
     * @param menu The options menu as last shown or first initialized by
     *             onCreateOptionsMenu().
     * @return You must return true for the menu to be displayed;
     * if you return false it will not be shown.
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        Log.d(TAG, "onPrepareOptionsMenu");
        MenuItem logToggle = menu.findItem(R.id.menu_toggle_log);
        logToggle.setVisible(findViewById(R.id.sample_output) instanceof ViewAnimator);
        logToggle.setTitle(mLogShown ? R.string.sample_hide_log : R.string.sample_show_log);
        return super.onPrepareOptionsMenu(menu);
    }

    /**
     * This hook is called whenever an item in your options menu is selected.
     * The default implementation simply returns false to have the normal
     * processing happen (calling the item's Runnable or sending a message to
     * its Handler as appropriate).  You can use this method for any items
     * for which you would like to do processing without those other
     * facilities.
     * <p>
     * <p>Derived classes should call through to the base class for it to
     * perform the default menu handling.</p>
     *
     * @param item The menu item that was selected.
     * @return boolean Return false to allow normal menu processing to
     * proceed, true to consume it here.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(TAG, "onOptionsItemSelected");
        android.util.Log.d(TAG, "onOptionsItemSelected");
        switch (item.getItemId()) {
            case R.id.menu_toggle_log:
                mLogShown = !mLogShown;
                ViewAnimator output = (ViewAnimator) findViewById(R.id.sample_output);
                if (mLogShown) {
                    output.setDisplayedChild(1);
                } else {
                    output.setDisplayedChild(0);
                }
                supportInvalidateOptionsMenu();
                return true;

            case R.id.menu_toggle_offline:

                mOffline = !mOffline;

                String toastText = "Now testing Online.";
                if (mOffline) {
                    toastText = "Now testing Offline.";
                }
                // We need an Editor object to make preference changes.
                // All objects are from android.context.Context
                SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
                SharedPreferences.Editor editor = pref.edit();
                editor.putBoolean("offline_fetch", mOffline);
                // Commit the edits!
                editor.apply();

                // alert user.
                Toast.makeText(this, toastText, Toast.LENGTH_LONG).show();

                supportInvalidateOptionsMenu();

                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /*
     * Fragment & FloatingActionButton setup
     */

    /**
     * Helper method to isolate the code for setting up the fragment(s).
     */
    @SuppressWarnings("UnusedParameters")
    private void setupFragment(Bundle savedInstanceState) {
        updateEntriesInterface = (UpdateEntriesInterface)
                getSupportFragmentManager().findFragmentById(R.id.recyclerFragment);
    }

    /**
     * Helper method to isolate the code for setting up the FAB (Floating Action Button).
     */
    private void setupFAB() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Log.d(TAG, "Sync FAB Pressed.");

            // call startDownload(...) with the Uri version of
            // CNN_YOUTUBE_ATOM_FEED_URL
            // TODO - you fill in here.
            
        });
    }

    /**
     * Sets up the custom Logging display fragment that will receive log data
     */
    @Override
    public void initializeLogging() {
        // Wraps Android's native log framework.
        LogWrapper logWrapper = new LogWrapper();
        // Using Log, front-end to the logging chain, emulates android.util.log method signatures.
        Log.setLogNode(logWrapper);

        // Filter strips out everything except the message text.
        MessageOnlyLogFilter msgFilter = new MessageOnlyLogFilter();
        logWrapper.setNext(msgFilter);

        // On screen logging via a fragment with a TextView.
        LogFragment logFragment = (LogFragment) getSupportFragmentManager()
                .findFragmentById(R.id.log_fragment);
        msgFilter.setNext(logFragment.getLogView());

        Log.i(TAG, "Ready");
    }

    /**
     * This method handles the data returned from the Intent service. This method will parse out
     * the information from the Bundle passed in, and then act accordingly if the download was
     * successful or not.
     *
     * @param data from the Intent returned by the IntentService.
     */
    private void serviceIntentReceived(Bundle data) {
        // Show the recycler layout.
        viewFlipper.setDisplayedChild(mRecyclerFlipperIndex);

        // create an 'int' with the name 'resultCode' via calling
        // DownloadAtomFeedService's getDownloadResultsCode(Bundle) method.
        // TODO - you fill in here.
        

        // check if resultCode = Activity.RESULT_CANCELED, if it does, then call
        // handleDownloadFailure and return;
        // TODO - you fill in here.
        

        // Otherwise **resultCode == Activity.RESULT_OK**
        // Handle a successful download.
        // Log to both the on-screen & logcat logs the requestUri from the data.
        // TODO - you fill in here.
        

        // Get the Entries from the 'data' and store them.
        // Log to the on-screen and logcat logs the number of entries downloaded.
        // TODO - you fill in here.
        

        // Update the RecyclerView fragment via calling updateEntries(...) on it.
        // TODO - you fill in here.
        
    }

    /**
     * This class defines the BroadcastReceiver that we will use locally within our App
     * to receive an Intent from the Service. This Intent will notify us of the results of the
     * Download operation. The broadcast receiver 'receives' the Intent and then notifies the
     * currently running instance of MainActivity
     */
    public class DownloadStateReceiver extends BroadcastReceiver {
        // Defines a custom Intent action
        public static final String BROADCAST_ACTION =
                "edu.vandy.mooc.aad_3_assg_1.assignment.activities.BROADCAST";

        // Called when the BroadcastReceiver gets an Intent it's registered to receive
        public void onReceive(Context context, Intent intent) {

            // use 'MainActivity.this' to call serviceIntentReceived(Bundle) to notify the
            // instance of MainActivity currently running that the service has returned
            // the results via the BroadcastReceiver. Note: Intent.getExtras will return the
            // Bundle stored with putExtras(Bundle)
            // TODO - you fill in here.
            
        }
    }

    /**
     * Helper method to create the Intent, along with its action that are requried for notifying
     * the Broadcast Receiver.
     *
     * @return Intent for Service to use to notify its results back to this Activity.
     */
    public static Intent makeBroadcastReceiverIntent() {
        // Create a new Intent. Then call 'setAction' on it, passing the 'BROADCAST_ACTION'
        // variable from the DownloadStateReceiver class. Then return the new Intent.
        // TODO - you fill in here replacing the following statement with your solution.
        return null;
    }

    /**
     * Sets up the view flipper which swaps the currently displayed view
     * between a progressBar and the recycler layout.
     */
    private void setupViewFlipper() {
        View view = findViewById(android.R.id.content);
        viewFlipper = (ViewFlipper) view.findViewById(R.id.viewFlipper);
        View recyclerFragment = viewFlipper.findViewById(R.id.recyclerFragment);
        View progressBar = viewFlipper.findViewById(R.id.progressBar);
        mRecyclerFlipperIndex = viewFlipper.indexOfChild(recyclerFragment);
        mProgressFlipperIndex = viewFlipper.indexOfChild(progressBar);
    }
}