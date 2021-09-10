package edu.vandy.mooc.aad3.assignment.activities;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import edu.vandy.mooc.aad3.framework.Interfaces.UpdateEntriesInterface;
import edu.vandy.mooc.aad3.framework.common.logger.Log;
import edu.vandy.mooc.aad3.framework.orm.Entry;
import edu.vandy.mooc.aad3.framework.provider.FeedContract;

/**
 * This class contains helper methods and utils for accessing the Content Provider data.
 */

class ProviderUtils {

    /**
     * String used for logging.
     */
    private static final String TAG = ProviderUtils.class.getCanonicalName();


    /**
     * This method loads the display fragment with the values from content provider (if any).
     */
    static void loadFragmentFromProvider(final AppCompatActivity context,
                                         final UpdateEntriesInterface updateEntriesInterface) {

        ContentResolver resolver = context.getContentResolver();
        android.util.Log.d(TAG, "query started");
        Cursor result = resolver.query(
                FeedContract.Entry.CONTENT_URI, // URI for "Entry(s)"
                FeedContract.Entry.ALL_COLUMN_NAMES, // Select every column from the table
                null, // "where" argument
                null, // "whereArgs" arguments
                null  // "sort order" argument
        );

        android.util.Log.d(TAG, "queried");
        final ArrayList<Entry> entryList = Entry.CONVERTER.getFromCursor(result);
        android.util.Log.d(TAG, "queried, entryList size = " + entryList.size());

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                updateEntriesInterface.updateEntries(entryList);
            }
        };
        context.runOnUiThread(runnable);
    }


    /**
     * Helper method to easily "Insert" a List of Entry objects into the Content Provider.
     *
     * @param entries the List<Entry> to insert
     */
    @SuppressWarnings("unused")
    static void addEntriesToProvider(final Context context,
                                     final List<Entry> entries) {

        android.util.Log.d(TAG, "addEntriesToProvider: ");

        Thread thread = new Thread() {
            @Override
            public void run() {

                ContentResolver resolver = context.getContentResolver();

                ContentValues[] contentValues = new ContentValues[entries.size()];
                int location = 0;

                for (Entry entry :
                        entries) {
                    contentValues[location] = entry.getContentValues();
                    location++;
                }

                int inserted = resolver.bulkInsert(FeedContract.Entry.CONTENT_URI, contentValues);

                // log to the on-screen log.
                Log.d(TAG, "Bulk Inserted Entries into Provider, "
                        + "Rows Created = "
                        + inserted
                );
            }
        };
        thread.start();
    }

    /**
     * This method deletes the contents of the Content Provider (Actually only of the Entry table,
     * but since that is the only table in the Provider... it is deleting the entire Provider's
     * contents.
     *
     * @param context The Context to use to access a {@link ContentResolver}
     */
    @SuppressWarnings("unused")
    static void deleteProviderContents(final Context context) {
        ContentResolver resolver = context.getContentResolver();

        int rowsDeleted = resolver.delete(FeedContract.Entry.CONTENT_URI, // URI for "Entry(s)"
                null, // Select every column from the table
                null);

        // log to logcat
        android.util.Log.d(TAG, "Deleted Provider Contents, Rows Deleted = " + rowsDeleted);
        // log to the on-screen log.
        Log.d(TAG, "Deleted Provider Contents, Rows Deleted = " + rowsDeleted);

    }


    /**
     * This method takes an Entry object and updates the Content Provider to store those values
     * in the row which matches rowID. In general, Updates to a Content Provider are not guaranteed
     * to work, for several possible reasons, not least of which have to do with the fact that the
     * combination of the content Uri, the Selection Clause and the Selection Arguments might not
     * match any rows in the database currently... OR it might match several rows... in which
     * case an Update call to the Content Provider could affect multiple rows.
     * <p>
     * This method is not used in the application but is presented to you as a learning material.
     *
     * @param rowID which row of the Entry table to update
     * @param entry the Entry object to update the row with.
     */
    @SuppressWarnings("unused")
    static public void updateEntry(final Context context, final long rowID, final Entry
            entry) {
        ContentResolver resolver = context.getContentResolver();
        android.util.Log.d(TAG, "update started");

        // Defines an object to contain the updated values
        ContentValues mUpdateValues = entry.getContentValues();

        // NOTE: the selection arguments and selection clause are separated on purpose to help
        // defend against a class of attacks called "SQL Injection". The specifics of this attack
        // are beyond the scope of this course, but it is sufficient for now to be aware that it was
        // done for a reason.
        //
        // For more info, read: http://www.w3schools.com/sql/sql_injection.asp &
        // https://en.wikipedia.org/wiki/SQL_injection

        // Defines selection criteria for the rows you want to update
        String mSelectionClause = "_ID=";
        // The variable arguments of the selection
        // Note: Generally the SelectionClause is static and the SelectionArgs are dynamic.
        String[] mSelectionArgs = {"" + rowID};

        // number of rows updated.
        // NOTE: The number of rows updated is from 0 to unbound, meaning it could update 0 rows,
        // or it could update every row in the database. Depending on how you structured your
        // database, and the SelectionClause & SelectionArgs you use. So be careful.
        int rowsUpdated = resolver.update(
                FeedContract.Entry.CONTENT_URI, // The content Uri of the Table to update.
                mUpdateValues,                  // The Columns to update, and their Values
                mSelectionClause,               // The Columns to Select on
                mSelectionArgs                  // The value to compare to
        );

        Log.d(TAG, "Deleted Provider Contents, Rows Deleted = " + rowsUpdated);
    }

}
