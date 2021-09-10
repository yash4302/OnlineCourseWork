package edu.vandy.mooc.aad3.framework.provider;

import android.database.Cursor;

import androidx.test.core.app.ApplicationProvider;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import edu.vandy.mooc.aad3.framework.orm.Entry;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class FeedDBAdapterTests {

    private final Entry mEntryA = newA();
    //private Entry mEntryB = newB();

    static final private long TEST_ID = 123456L;
    static final private String TEST_ENTRY_ID = "test entry ID";
    static final private String TEST_TITLE = "Lets party like its 1999";
    static final private String TEST_LINK = "https://www.vanderbilt.edu";
    static final private String TEST_PUBLISHED = "1999-12-31";
    static final private String TEST_AUTHOR = "CNN";

    private FeedDBAdapter mFeedDBAdapter;

    static private Entry newA() {
        return new Entry(TEST_ID, TEST_ENTRY_ID, TEST_TITLE, TEST_LINK, TEST_PUBLISHED, TEST_AUTHOR);
    }

    @SuppressWarnings("unused")
    static private void checkEntry(Entry testEntry, long id) {
        assertThat(testEntry.get_ID(), Matchers.is(id));
        assertThat(testEntry.getLINK(), Matchers.is(TEST_LINK));
        assertThat(testEntry.getPUBLISHED(), Matchers.is(TEST_PUBLISHED));
        assertThat(testEntry.getTITLE(), Matchers.is(TEST_TITLE));
        assertThat(testEntry.getENTRY_ID(), Matchers.is(TEST_ENTRY_ID));
        assertThat(testEntry.getAUTHOR(), Matchers.is(TEST_AUTHOR));
    }

    @Before
    public void setUp() {
        // how to delete database at start. (seems to delete db right now either way...)
        //ApplicationProvider.getApplicationContext().deleteDatabase(RssSchema.DATABASE_NAME);

        mFeedDBAdapter = new FeedDBAdapter(ApplicationProvider.getApplicationContext(), true);
        mFeedDBAdapter.open();

    }

    @After
    public void tearDown() {
        mFeedDBAdapter.close();
    }

    @Test
    public void simpleQueryTest() {
        assertNotNull(mFeedDBAdapter);

        Cursor cursor = mFeedDBAdapter.query(FeedContract.Entry.TABLE_NAME, null, null, null,
                null);

        assertNotNull(cursor);

        assertThat(cursor.getCount(), is(0));
    }

    @Test
    public void simpleInsertTest() {
        // just sanity check
        assertNotNull(mFeedDBAdapter);
        // set start point
        Cursor cursor = mFeedDBAdapter.query(FeedContract.Entry.TABLE_NAME, null, null, null, null);
        assertNotNull(cursor);
        assertThat(cursor.getCount(), is(0));
        // insert mEntryA
        mFeedDBAdapter.insert(FeedContract.Entry.TABLE_NAME, mEntryA.getContentValues());
        Cursor cursor2 = mFeedDBAdapter.query(FeedContract.Entry.TABLE_NAME, null, null, null, null);
        assertNotNull(cursor2);
        assertThat(cursor2.getCount(), is(1));
    }

    @Test
    public void inDepthInsertTest() {
        // just sanity check
        assertNotNull(mFeedDBAdapter);
        // set start point
        Cursor cursor = mFeedDBAdapter.query(FeedContract.Entry.TABLE_NAME, null, null, null, null);
        assertNotNull(cursor);
        assertThat(cursor.getCount(), is(0));
        // insert mEntryA
        mFeedDBAdapter.insert(FeedContract.Entry.TABLE_NAME, mEntryA.getContentValues());
        Cursor cursor2 = mFeedDBAdapter.query(FeedContract.Entry.TABLE_NAME, null, null, null, null);
        assertNotNull(cursor2);
        assertThat(cursor2.getCount(), is(1));

        List<Entry> testItems = Entry.CONVERTER.getFromCursor(cursor2);

        assertNotNull(testItems);

        assertThat(testItems.size(), is(1));

        Entry newItem = testItems.get(0);

        assertEquals(newItem, mEntryA);
    }
}
