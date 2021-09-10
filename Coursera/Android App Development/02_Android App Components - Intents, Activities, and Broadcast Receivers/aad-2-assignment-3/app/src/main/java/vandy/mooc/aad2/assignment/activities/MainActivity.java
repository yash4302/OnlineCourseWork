package vandy.mooc.aad2.assignment.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.util.Log;

import java.util.ArrayList;

import vandy.mooc.aad2.assignment.R;
import vandy.mooc.aad2.framework.application.activities.MainActivityBase;
import vandy.mooc.aad2.framework.utils.ViewUtils;

/**
 * This is the main activity class for the application and will be created and
 * be displayed when the application is first started. It extends the base class
 * MainActivityBase which supports add and download FABs and a fragment that
 * contains a RecyclerView that manages a list of strings. This base class also
 * manages all configuration changes and also saving and restoring the displayed
 * list of strings between application sessions.
 * <p/>
 * In addition to managing the string list, the base class expects this sub
 * class to handle creating intents that are used to start a GalleryActivity. In
 * some assignments, there will also be a requirement to have the
 * GalleryActivity return a result intent back to the MainActivity so that the
 * two activities can keep the displayed lists in sync.
 */
public class MainActivity extends MainActivityBase {
    /**
     * Id used to identify a the source of a result intent sent to this
     * activity's onActivityResult() hook.
     * <p/>
     * Declared public for instrumentation testing.
     */
    @SuppressWarnings({"WeakerAccess", "unused"})
    public static final int DOWNLOAD_REQUEST_CODE = 2;
    /**
     * Id used by this classes broadcast receiver to identify a recognized
     * broadcast event.
     */
    public static final String ACTION_VIEW_LOCAL = "ActionViewLocalBroadcast";
    /**
     * String used in logging output.
     */
    private static final String TAG = "MainActivity";
    /**
     * An instance of a local broadcast receiver implementation that receives a
     * broadcast intent containing a list of local image URLs and then displays
     * the first image from this list in the layout's ImageView.
     */
    private BroadcastReceiver mBroadcastReceiver;

    /*
     * Activity lifecycle callback methods.
     */

    /**
     * Hook method called when a new instance of Activity is created. One time
     * initialization code goes here, e.g., UI layout initialization.
     *
     * @param savedInstanceState A Bundle object that contains saved state
     *                           information.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Always call super class method first unless you need to request
        // window features using the requestWindowFeature() method which always
        // must precede any call to super.onCreate(). Note that the framework's
        // super class implementation will automatically load the XML layout for
        // this activity.
        super.onCreate(savedInstanceState);

        // Call local helper method to setup a broadcast receiver
        // that will receive and display a list  of local image URLs.
        // TODO -- you fill in here.
        
    }

    /**
     * Hook method called when an activity is about to be destroyed.
     */
    @Override
    protected void onDestroy() {
        // Unregister the broadcast receiver.
        // TODO -- you fill in here.
        

        // Always call super method.
        // TODO -- you fill in here.
        
    }


    /**
     * Template method hook method required fro starting a download operation.
     *
     * @param urls list of URLs to download.
     */
    @Override
    protected void startDownload(ArrayList<Uri> urls) {
        // Start the Gallery Activity with the passed in Uri(s)
        // TODO -- you fill in here.
        
    }

    /**
     * Template method hook method required for starting a download operation
     * for returning a result.
     *
     * @param urls list of URLs to download.
     */
    @Override
    protected void startDownloadForResult(ArrayList<Uri> urls) {
        // Start the Gallery Activity for result with the passed in Uris(s).
        // TODO -- you fill in here.
        
    }

    /**
     * Template method hook method required fro starting a download
     * operation.
     * @param urls list of URLs to download.
     */

    /**
     * Creates a broadcast receiver instance that will receive a data intent
     * from the DownloadImages activity. This intent will contain a list of
     * downloaded images to display. The current assignment version simply
     * displays the first image in this list.
     */
    private void setupBroadcastReceiver() {
        // Create a new instance of the local broadcast receiver
        // class object (defined below).
        // TODO -- you fill in here.
        

        // Create a new broadcast intent filter that will
        // filter (receive) ACTION_VIEW intents.
        // TODO -- you fill in here.
        

        // Call the Activity class helper method to register
        // this local receiver instance.
        // TODO -- you fill in here.
        
    }

    /*
     * Activity starting helper methods.
     */

    /**
     * Hook method called by the application framework when a started activity
     * has completed and has a status to report and possible results to
     * receive.
     *
     * @param requestCode The request code identifying the started activity has
     *                    finished.
     * @param resultCode  The result code (RESULT_OK or RESULT_CANCELED)
     * @param data        An intent containing the results of the started
     *                    activity.
     */
    @Override
    protected void onActivityResult(
            int requestCode,
            int resultCode,
            Intent data) {
        Log.d(TAG, "onActivityResults() called.");

        // Check if the request code matches the expected
        // static DOWNLOAD_REQUEST_CODE field value. If so then ...
        // TODO -- you fill in here.
            
            // If the result code is RESULT_OK, then
            // call a local helper method to extract and display the returned
            // list of image URLs. Otherwise, call the ViewUtils show toast
            // helper to display the string resource with id
            // R.string.download_activity_cancelled. In either case, return
            // without calling the super class method.
            // TODO -- you fill in here.
                

                // Extract and display the downloaded images ...
                // TODO -- you fill in here.
                

                // Show a toast ...
                // TODO -- you fill in here.
            

            // Return ...
            // TODO -- you fill in here.
            
            // DownloadActivity was not started with correct request code.
            // TODO -- you fill in here.
        

        // Allow super class to handle results from unknown origins.
        // TODO -- you fill in here.
        
    }

    /**
     * Helper method that extracts a received list image URLs in the passed
     * intent and then updates the layout to show those URLs.
     *
     * @param intent A data intent containing a list of image URLs as an intent
     *               extra.
     */
    private void extractAndUpdateUrls(Intent intent) {
        // Extract the list of downloaded image URLs from the
        // passed intent.
        // TODO -- you fill in here.
        

        // If the list is empty, call ViewUtils show toast helper
        // to display the string resource R.string.no_images_received.
        // TODO -- you fill in here.
        

        // Always call the base class setItems() helper which
        // will refresh the layout to show the received list of URLs.
        // TODO -- you fill in here.
        
    }


    /**
     * Called from adapter when items are being pending loads. Loading is not
     * supported for a this adapter so we can safely ignore this callback (it
     * will never be made).
     *
     * @param notUsed not used
     */
    @Override
    public void onShowRefresh(boolean notUsed) {
        // this method is not used in this assignment.
    }

    /**
     * Custom broadcast receiver that receives and displays a list of local
     * images identified by an image URL list the is passed as an intent extra.
     */
    public class ImageViewBroadcastReceiver
            extends BroadcastReceiver {
        /**
         * Hook method called by the Android ActivityManagerService framework
         * when a broadcast has been sent.
         *
         * @param context The caller's context.
         * @param intent  An intent containing a list of local image URLs.
         */
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG, "Broadcast receiver onReceive() called.");

            // call helper method to extract and display
            // the received list of image URLs.
            // TODO -- you fill in here.
            
        }
    }
}
