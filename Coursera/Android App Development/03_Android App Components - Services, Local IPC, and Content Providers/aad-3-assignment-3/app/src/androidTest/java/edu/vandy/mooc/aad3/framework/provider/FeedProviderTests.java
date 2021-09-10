package edu.vandy.mooc.aad3.framework.provider;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.net.Uri;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.rule.provider.ProviderTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import edu.vandy.mooc.aad3.assignment.providers.FeedProvider;
import edu.vandy.mooc.aad3.framework.orm.Entry;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotEquals;

/**
 * https://developer.android.com/training/testing/integration-testing/content-provider-testing.html
 * https://developer.android.com/training/testing/start/index.html#run-instrumented-tests
 */
public class FeedProviderTests {

    @Rule
    public ProviderTestRule mProviderRule =
            new ProviderTestRule.Builder(FeedProvider.class, FeedProvider.AUTHORITY).build();

    // URI for Item(s)
    private static final Uri mEntryUri = FeedContract.Entry.CONTENT_URI;

    // ContentProvider to test
    private FeedProvider mFeedContentProvider;
    // Mock ContentResolver
    private ContentResolver mMockResolver;

    // Default values for test Entry(s)
    private static final long TEST_ID = 123456L;
    private static final String TEST_ENTRY_ID = "test entry ID";
    private static final String TEST_TITLE = "Lets party like its 1999";
    private static final String TEST_LINK = "https://www.vanderbilt.edu";
    private static final String TEST_PUBLISHED = "1999-12-31";
    private static final String TEST_AUTHOR = "CNN";

    // Entry with default values.
    static private final Entry testA = newA();
    static private final Entry testB = newB();

    // method to create a new Entry with default Values
    static private Entry newA() {
        return new Entry(TEST_ID, TEST_ENTRY_ID, TEST_TITLE, TEST_LINK, TEST_PUBLISHED, TEST_AUTHOR);
    }

    static private Entry newB() {
        return new Entry(TEST_ID, TEST_ENTRY_ID, TEST_TITLE, TEST_LINK, TEST_PUBLISHED, "Sherie");
    }

    /**
     * Setup that will run before every test method
     */
    @Before
    public void before() {
        // get Mock Context and Mock ContentResolver.
        Context mMockContext = ApplicationProvider.getApplicationContext();
        mMockResolver = mProviderRule.getResolver();

        // assign the authority for the provider we will create next.
        ProviderInfo providerInfo = new ProviderInfo();
        providerInfo.authority = FeedContract.AUTHORITY;
        // create provider
        mFeedContentProvider = new FeedProvider();
        // attach info to the provider
        mFeedContentProvider.attachInfo(mMockContext, providerInfo);

        // verify that mRssContentProvider exists.
        assertNotNull(mFeedContentProvider);
    }

    /**
     * Method that will run after every test
     */
    @After
    public void after() {
        mFeedContentProvider.shutdown();
    }

    /**
     * Test Insert of single Entry into ContentProvider.
     */
    public void testInsertEntry() {
        // get ContentValues from mItem
        ContentValues values = testA.getContentValues();
        // use mock ContentResolver to insert mItem's ContentValues
        Uri resultingUri = mMockResolver.insert(mEntryUri, values);
        // Then you can test the correct execution of your insert:
        assertNotNull(resultingUri);
        long id = ContentUris.parseId(resultingUri);
        assertTrue(id > 0);

        Cursor cursor = mMockResolver.query(mEntryUri,
                FeedContract.Entry.ALL_COLUMN_NAMES,
                null,
                null,
                null
        );

        ArrayList<Entry> entries = Entry.CONVERTER.getFromCursor(cursor);
        assertEquals(entries.get(0), testA);


    }

    /**
     * Very basic query test.
     * <p/>
     * Prerequisites:
     * <ul>
     * <li>A provider set up by the test framework
     * </ul>
     * <p/>
     * Expectations:
     * <ul>
     * <li> a simple query without any parameters, before any inserts returns a
     * non-null cursor
     * <li> a wrong uri results in {@link IllegalArgumentException}
     * </ul>
     */
    public void testQueryEmpty() {
        // query the mock ContentResolver on the Item URI, return all rows.
        Cursor cursor = mMockResolver.query(mEntryUri, null, null, null, null);
        // verify results
        assertNotNull(cursor);
        // results should be 0 because setUp doesn't insert anything into the provider to start
        assertThat(cursor.getCount(), is(0));
    }

    /**
     * Very basic query test.
     * <p/>
     * Prerequisites:
     * <ul>
     * <li>A provider set up by the test framework
     * </ul>
     * <p/>
     * Expectations:
     * <ul>
     * <li> a simple query without any parameters, after one insert returns a
     * non-null cursor with size 1 that returns an equal Entry compared to what was inserted.
     * </ul>
     */
    public void testQueryNonEmpty() {
        // get ContentValues and insert it into Provider via ContentResolver
        ContentValues values = testA.getContentValues();
        Uri resultUri = mMockResolver.insert(mEntryUri, values);


        // Query entire table.
        // Query the mock ContentResolver on the Item URI, return all rows.
        Cursor cursor = mMockResolver.query(mEntryUri, null, null, null, null);
        // verify cursor non-null
        assertNotNull(cursor);
        // verify results size = 1
        assertThat(cursor.getCount(), is(1));
        // get List<Entry> from the cursor.
        ArrayList<Entry> entries = Entry.CONVERTER.getFromCursor(cursor);
        // make sure 1 and only 1 entry was generated from the cursor
        assertThat(entries.size(), is(1));
        // get the inserted Entry
        Entry entry = entries.get(0);
        // check that returned Entry is non-null
        assertNotNull(entry);
        // verify that the returned Entry matches (other than _ID) the inserted Entry.
        assertEquals(entry, testA);


        // Check if query with uri with ID works
        cursor = mMockResolver.query(resultUri, null, null, null, null);
        // verify cursor non-null
        assertNotNull(cursor);
        // verify results size = 1
        assertThat(cursor.getCount(), is(1));
        // get List<Entry> from the cursor.
        entries = Entry.CONVERTER.getFromCursor(cursor);
        // make sure 1 and only 1 entry was generated from the cursor
        assertThat(entries.size(), is(1));
        // get the inserted Entry
        entry = entries.get(0);
        // check that returned Entry is non-null
        assertNotNull(entry);
        // verify that the returned Entry matches (other than _ID) the inserted Entry.
        assertEquals(entry, testA);
    }


    /**
     * Test of Provider ignores junk URIs
     * <p/>
     * Prerequisites:
     * <ul>
     * <li>A provider set up by the test framework
     * </ul>
     * <p/>
     * Expectations:
     * <ul>
     * <li> a simple query with a junk uri parameter
     * <li> a wrong uri results in {@link IllegalArgumentException}
     * </ul>
     */
    public void testJunkUris() {
        // verify that that query doesn't return on junk URIs.
        Cursor cursor = null;
        try {
            cursor = mMockResolver.query(
                    FeedContract.BASE_URI.buildUpon().appendPath("definitely_invalid").build(),
                    null, null, null, null);
            assertNotNull(cursor);
            // Should have thrown IllegalArgumentException, so now fail.
            fail("cursor.getCount: " + cursor.getCount());
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    @SuppressWarnings("UnusedAssignment") // Lint is confused w/factory methods.
    public void testDelete() {
        // get ContentValues and insert it into Provider via ContentResolver
        ContentValues values = testA.getContentValues();
        Uri insertedUri;

        // System.out.println("uriresult = " + uriResult);
        // query the mock ContentResolver on the Item URI, return all rows.
        int rowsDeleted = mMockResolver.delete(mEntryUri, null, null);

        // Test delete all rows when only 1 row, with table uri
        insertedUri = mMockResolver.insert(mEntryUri, values);
        rowsDeleted = mMockResolver.delete(mEntryUri, null, null);
        assertThat(rowsDeleted, is(1));
        Cursor cursor = mMockResolver.query(mEntryUri, null, null, null, null);
        assertThat(cursor.getCount(), is(0));


        // Test delete 1 row when only 1 row, with direct Uri.
        rowsDeleted = mMockResolver.delete(mEntryUri, null, null);
        insertedUri = mMockResolver.insert(mEntryUri, values);
        rowsDeleted = mMockResolver.delete(insertedUri, null, null);
        assertThat(rowsDeleted, is(1));
        cursor = mMockResolver.query(mEntryUri, null, null, null, null);
        assertThat(cursor.getCount(), is(0));

        // Test delete all rows when only 1 row, with table uri
        rowsDeleted = mMockResolver.delete(mEntryUri, null, null);
        insertedUri = mMockResolver.insert(mEntryUri, values);
        insertedUri = mMockResolver.insert(mEntryUri, values);
        rowsDeleted = mMockResolver.delete(mEntryUri, null, null);
        assertThat(rowsDeleted, is(2));
        cursor = mMockResolver.query(mEntryUri, null, null, null, null);
        assertThat(cursor.getCount(), is(0));


        // Test delete all rows when only 1 row, with table uri
        rowsDeleted = mMockResolver.delete(mEntryUri, null, null);
        insertedUri = mMockResolver.insert(mEntryUri, newB().getContentValues());
        insertedUri = mMockResolver.insert(mEntryUri, newA().getContentValues());
        rowsDeleted = mMockResolver.delete(insertedUri, null, null);
        assertThat(rowsDeleted, is(1));
        cursor = mMockResolver.query(mEntryUri, null, null, null, null);
        assertThat(cursor.getCount(), is(1));
        List<Entry> entries = Entry.CONVERTER.getFromCursor(cursor);
        assertEquals(entries.get(0), testB);


        // test delete single row when multiple rows, via uri w/number
        rowsDeleted = mMockResolver.delete(mEntryUri, null, null);
        insertedUri = mMockResolver.insert(mEntryUri, newB().getContentValues());
        long firstID = ContentUris.parseId(insertedUri);
        insertedUri = mMockResolver.insert(mEntryUri, newB().getContentValues());
        Uri insertedUriGoal = mMockResolver.insert(mEntryUri, newA().getContentValues());
        insertedUri = mMockResolver.insert(mEntryUri, newB().getContentValues());
        insertedUri = mMockResolver.insert(mEntryUri, newB().getContentValues());
        rowsDeleted = mMockResolver.delete(insertedUriGoal, null, null);
        assertThat(rowsDeleted, is(1));
        cursor = mMockResolver.query(mEntryUri, null, null, null, null);
        assertThat(cursor.getCount(), is(4));
        entries = Entry.CONVERTER.getFromCursor(cursor);
        assertThat(entries.get(0).get_ID(), is(firstID));
        assertThat(entries.get(1).get_ID(), is(firstID + 1));
        assertThat(entries.get(2).get_ID(), is(firstID + 3));
        assertThat(entries.get(3).get_ID(), is(firstID + 4));


        // delete single row with where clause of _ID from many
        rowsDeleted = mMockResolver.delete(mEntryUri, null, null);
        insertedUri = mMockResolver.insert(mEntryUri, newB().getContentValues());
        firstID = ContentUris.parseId(insertedUri);
        insertedUri = mMockResolver.insert(mEntryUri, newB().getContentValues());
        insertedUriGoal = mMockResolver.insert(mEntryUri, newA().getContentValues());
        insertedUri = mMockResolver.insert(mEntryUri, newB().getContentValues());
        insertedUri = mMockResolver.insert(mEntryUri, newB().getContentValues());
        rowsDeleted = mMockResolver.delete(mEntryUri, " _ID = ?", new String[]{"" + (firstID + 2)});
        assertThat(rowsDeleted, is(1));
        cursor = mMockResolver.query(mEntryUri, null, null, null, null);
        assertThat(cursor.getCount(), is(4));
        entries = Entry.CONVERTER.getFromCursor(cursor);
        assertEquals(entries.get(0), testB);
        assertThat(entries.get(0).get_ID(), is(firstID));
        assertThat(entries.get(1).get_ID(), is(firstID + 1));
        assertThat(entries.get(2).get_ID(), is(firstID + 3));
        assertThat(entries.get(3).get_ID(), is(firstID + 4));
    }


    @Test
    public void testUpdate() {
        ContentValues values = testA.getContentValues();
        Uri insertedUri;
        Cursor cursor;
        int rowsDeleted;
        ArrayList<Entry> entries;
        int updatedCount;

        // System.out.println("uriresult = " + uriResult);
        // query the mock ContentResolver on the Item URI, return all rows.
        rowsDeleted = mMockResolver.delete(mEntryUri, null, null);

        // setup
        mMockResolver.insert(mEntryUri, values);
        mMockResolver.insert(mEntryUri, values);
        insertedUri = mMockResolver.insert(mEntryUri, values);
        cursor = mMockResolver.query(insertedUri, null, null, null, null);
        entries = Entry.CONVERTER.getFromCursor(cursor);
        assertEquals(entries.get(0), testA);
        assertNotEquals(entries.get(0), testB);

        // test update 1 row with uri row ID
//        mMockResolver.insert(mEntryUri, values);
//        mMockResolver.insert(mEntryUri, values);
//        updatedCount = mMockResolver.update(insertedUri, testB.getContentValues(), null, null);
//        assertThat(updatedCount, is(1));
//        cursor = mMockResolver.query(insertedUri, null, null, null, null);
//        entries = Entry.CONVERTER.getFromCursor(cursor);
//        assertFalse(entries.get(0).equals(testA));
//        assertTrue(entries.get(0).equals(testB));

        // test update every row.
        rowsDeleted = mMockResolver.delete(mEntryUri, null, null);
        mMockResolver.insert(mEntryUri, testA.getContentValues());
        mMockResolver.insert(mEntryUri, testA.getContentValues());
        mMockResolver.insert(mEntryUri, testA.getContentValues());
        mMockResolver.insert(mEntryUri, testA.getContentValues());
        mMockResolver.insert(mEntryUri, testA.getContentValues());

        // update every row to B
        updatedCount = mMockResolver.update(mEntryUri, testB.getContentValues(), null, null);
        assertThat(updatedCount, is(5));
        cursor = mMockResolver.query(mEntryUri, null, null, null, null);
        entries = Entry.CONVERTER.getFromCursor(cursor);
        // check they all equal B
        for (int i = 0; i < 5; i++) {
            assertEquals(entries.get(i), testB);
        }
    }
}
