package edu.vandy.mooc.aad3.assignment.services;

import android.app.Activity;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;

import androidx.annotation.NonNull;

import android.util.Log;

import org.xmlpull.v1.XmlPullParserException;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import edu.vandy.mooc.aad3.framework.net.NetEntry;
import edu.vandy.mooc.aad3.framework.net.YoutubeFeedParser;
import edu.vandy.mooc.aad3.framework.orm.Entry;

/**
 * This service downloads the metadata of the last 15 videos from CNN's YouTube page,
 * converts that information into a List of {@link Entry} objects and returns it via the Message
 */

@SuppressWarnings("unused")
public class DownloadAtomFeedService extends IntentService {

    /**
     * Debugging tag used by the Android logger.
     */
    private static final String TAG = DownloadAtomFeedService.class.getCanonicalName();

    /**
     * String constant used to extract the Messenger "extra" from an
     * intent.
     */
    private static final String MESSENGER_KEY = "MESSENGER_KEY";

    /**
     * String constant used to extract the request code.
     */
    private static final String REQUEST_CODE = "REQUEST_CODE";

    /**
     * String constant used to extract the URL to an ATOM Feed from a Bundle.
     */
    private static final String FEED_URL = "FEED_URL";

    /**
     * String constant used to extract the parcelable array of Entry(s) from a Bundle.
     */
    private static final String ENTRY_ARRAY_KEY = "ENTRY_ARRAY_KEY";

    /**
     * Constructor that sets the name of the IntentService.
     */
    @SuppressWarnings("WeakerAccess")
    public DownloadAtomFeedService() {
        super("DownloadAtomFeedService");
    }

    /**
     * Factory method that returns an explicit Intent for downloading an image.
     * <p>
     * This method is called by other components (Activities, etc.) to create an Intent that will
     * launch this Service. The reason it is done this way is to localize "KeyWord" variables
     * such as: REQUEST_CODE and MESSENGER_KEY, that way it increases the level of decoupling of
     * the code. (IE: Any change to those variables would impact many (any & all) class that
     * would launch this service if those variables were public and had to be used by the calling
     * component. However, as is, those variables are private and won't impact any other class if
     * they are changed.)
     */
    @SuppressWarnings("SameParameterValue")
    public static Intent makeIntent(Context context,
                                    int requestCode,
                                    Uri url,
                                    Handler downloadHandler) {
        // Create an intent that will download the image from the web.
        // which involves (1) setting the URL as "data" to the
        // intent, (2) putting the request code as an "extra" to the
        // intent, (3) creating and putting a Messenger as an "extra"
        // to the intent so the DownloadAtomFeedService can send the
        // Entry Object back to the Calling Activity
        // TODO - you fill in here replacing the following statement with your solution.
        return null;
    }

    /**
     * Helper method to ease in using this class by localizing values needed for extracting data
     * from reply messages. Helps in getting Result Code.
     *
     * @param message message returned from this Service.
     * @return the result code.
     */
    public static int getResultCode(Message message) {
        // Check to see if the download succeeded.
        // TODO - you fill in here replacing the following statement with your solution.
        return -1;
    }

    /**
     * Helper method to ease in using this class by localizing values needed for extracting data
     * from reply messages. Helps in getting Request Uri.
     *
     * @param message message returned from this Service.
     * @return the request Uri.
     */
    public static Uri getRequestUri(Message message) {
        // Extract the data from Message, which is in the form of a
        // Bundle that can be passed across processes.
        // TODO - you fill in here.
        

        // call getRequestUri(Bundle) on the data bundle and return the Uri it returns.
        // TODO - you fill in here replacing the following statement with your solution.
        return null;
    }

    public static Uri getRequestUri(Bundle data) {
        // use 'FEED_URL' to extract the string representation of the Uri from the Message
        // TODO - you fill in here.
        

        // Parse the String of the url to get a Uri and return it.
        // TODO - you fill in here replacing the following statement with your solution.
        return null;
    }


    /**
     * Helper method to ease in using this class by localizing values needed for extracting data
     * from reply messages. Helps in getting Request Code.
     *
     * @param message message returned from this Service.
     * @return the request code.
     */
    public static int getRequestCode(Message message) {
        // Extract the data from Message, which is in the form of a
        // Bundle that can be passed across processes.
        // TODO - you fill in here.
        

        // Extract the request code and return it.
        // TODO - you fill in here replacing the following statement with your solution.
        return -1;
    }

    /**
     * Helper method to ease in using this class by localizing values needed for extracting data
     * from reply messages. Helps in getting the Entry(s) from the message.
     *
     * @param message message returned from this Service.
     * @return an ArrayList of the Entry(s) downloaded from the Uri.
     */
    public static ArrayList<Entry> getEntries(@NonNull Message message) {
        // we give you this method since it isn't covered in full detail since it is beyond the
        // scope of this course.
        if (message.getData() == null) {
            return null;
        }
        return getEntries(message.getData());
    }

    /**
     * Helper method to ease in using this class by localizing values needed for extracting data
     * from reply messages. Helps in getting the Entry(s) from the Bundle.
     *
     * @param data the bundle that the data is in.
     * @return the ArrayList of Entry(s)
     */
    public static ArrayList<Entry> getEntries(@NonNull Bundle data) {
        // we give you this method since it isn't covered in full detail since it is beyond the
        // scope of this course.
        return data.getParcelableArrayList(ENTRY_ARRAY_KEY);
    }

    /**
     * Hook method dispatched by the IntentService framework to
     * download the image requested via data in an intent, store the
     * image in a local file on the local device, and return the image
     * file's URI back to the MainActivity via the Messenger passed
     * with the intent.
     */
    @Override
    public void onHandleIntent(Intent intent) {
        // Get the URL associated with the Intent data.
        // TODO - you fill in here.
        

        // Download the requested YouTube Atom Feed.
        // TODO - you fill in here.
        

        // Extract the request code.
        // TODO - you fill in here.
        

        // Extract the Messenger stored as an extra in the
        // intent under the key MESSENGER_KEY.
        // TODO - you fill in here.
        

        // Send the YouTube Atom Feed Entries back to the
        // MainActivity via the messenger.
        // TODO - you fill in here.
        
    }

    /**
     * Send the pathname back to the MainActivity via the
     * messenger.
     */
    private void sendEntries(Messenger messenger, ArrayList<Entry> entries,
                             Uri url,
                             int requestCode) {
        // Call the makeReplyMessage() factory method to create Message.
        // TODO - you fill in here.
        

        try {
            // Send the path to the image file back to the MainActivity.
            // TODO - you fill in here.
            
        } catch (Exception e) {
            Log.e(getClass().getName(),
                    "Exception while sending reply message back to Activity.",
                    e);
        }
    }

    /**
     * A factory method that creates a Message to return to the
     * MainActivity with the pathname of the downloaded image.
     */
    private Message makeReplyMessage(ArrayList<Entry> entries,
                                     Uri url,
                                     int requestCode) {
        // Get a new message via the obtain() factory method.
        // TODO - you fill in here.
        

        // Create a new Bundle named 'data' to handle the result.
        // TODO - you fill in here.
        

        // use 'putParcelableArrayList(...)' to store the ArrayList of Entry(s) in the bundle.
        // TODO - you fill in here
        

        // Put the requestCode into the Bundle via the REQUEST_CODE key.
        // TODO - you fill in here.
        

        // Put the requestCode into the Bundle via the REQUEST_CODE key.
        // TODO - you fill in here.
        

        // Set a field in the Message to indicate whether the download
        // succeeded or failed.
        // sucess: Activity.RESULT_OK
        // otherwise: Activity.RESULT_CANCELED
        // TODO - you fill in here.
        

        // Set the Bundle to be the data in the message.
        // TODO - you fill in here.
        

        // return the message.
        // TODO - you fill in here replacing the following statement with your solution.
        return null;
    }

    /*
     * These Helper Methods are provided for you, so that you can focus on implementing the
     * service, and not have to worry about the specifics of downloading and parsing an ATOM feed
     * from YouTube.
     */

    /**
     * Download the YouTube AtomFeed and generate the Entry(s) that were in the Feed.
     *
     * @param feedURL The url of the YouTube feed
     * @return a List of type Entry that were in the Feed url provided.
     */
    private List<Entry> downloadAtomFeed(final String feedURL) {
        android.util.Log.d(TAG, "downloadAtomFeed()");
        final List<Entry> entries = new ArrayList<>();

        // Download Entries
        try {
            // Make sure that this thread has not been interrupted.
            checkHaMeRThreadNotInterrupted();
            // download and process the feed from the given url.
            List<NetEntry> netEntries = downloadNetEntries(feedURL);
            Log.d(TAG, "netEntries size = " + netEntries.size());
            List<Entry> newEntries = convertToOrmEntries(netEntries);
            Log.d(TAG, "entries size = " + newEntries.size());
            entries.addAll(newEntries);
            // check that thread wasn't interrupted again before returning results.
            checkHaMeRThreadNotInterrupted();
        } catch (Exception ex) {
            Log.e(TAG, "Exception downloading Atom Feed: " + ex.getMessage());
        }

        // Return the List<Entry> return object created above.
        return entries;
    }

    /**
     * Helper method to check if thread has been interrupted & throw exception if it has
     *
     * @throws Exception Thrown Exception notifying that the HaMeR Thread was interrupted.
     */
    private void checkHaMeRThreadNotInterrupted() throws Exception {
        if (Thread.currentThread().isInterrupted()) {
            Log.e(TAG, "Download thread interrupted");
            throw new Exception("Thread interrupted, halting Download execution.");
        }
    }

    /**
     * Helper Method to convert from {@link NetEntry}(s) to {@link Entry}(s). We have two different
     * POJO (Plain Old Java Object(Classes that do not inherit from anything other than Object))
     * Classes because this allows for better de-coupling of our internal 'Entry's and external
     * (Web) 'Entry's. This allows us to more easily make changes to either one of the two
     * classes in the future if need be without impacting the other.
     *
     * @param netEntries ArrayList of NetEntry(s) to convert to ArrayList of Entry(s)
     * @return converted ArrayList of Entry(s)
     */
    private List<Entry> convertToOrmEntries(List<NetEntry> netEntries) {
        // Create an empty ArrayList of type "Entry".
        List<Entry> entries = new ArrayList<>();
        // Loop through each 'NetEntry', and convert it to an 'Entry' object.
        for (NetEntry netEntry : netEntries) {
            entries.add(
                    new Entry(netEntry.id,
                            netEntry.title,
                            netEntry.link,
                            netEntry.published,
                            netEntry.author
                    )
            ); // end of 'add'
        } // end of loop through each NetEntry
        // return the ArrayList of newly created 'Entry' Objects.
        return entries;
    }

    /**
     * Download List of {@link NetEntry}(Network version of {@link Entry} from a provided URL
     * <p>
     * There is two different classes used, because it is convienient to have independant
     * representations, even of the same data, that way one representation can be varried if
     * need be. (such as the server version changing slightly))
     *
     * @param urlString the url to grab the ATOM feed from.
     * @return the NetEntry(s) that were in the ATOM feed.
     * @throws IOException
     * @throws XmlPullParserException
     */
    @SuppressWarnings("ThrowFromFinallyBlock")
    private List<NetEntry> downloadNetEntries(final String urlString)
            throws IOException, XmlPullParserException {

        List<NetEntry> rValues;

        BufferedInputStream in = null;

        try {
            // 0 - for private mode
            SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);

            boolean offlineFetch = pref.getBoolean("offline_fetch", false);

            if (offlineFetch) {
                in = new BufferedInputStream(getAssets().open("videos_offline.xml"));
            } else {
                in = new BufferedInputStream(new URL(urlString).openStream());
            }


            YoutubeFeedParser parser = new YoutubeFeedParser();

            rValues = parser.parse(in);
            android.util.Log.d(TAG, "downloadNetEntries rValue size = " + rValues.size());


        } finally {
            if (in != null) {
                in.close();
            }
        }
        return rValues;
    }
}