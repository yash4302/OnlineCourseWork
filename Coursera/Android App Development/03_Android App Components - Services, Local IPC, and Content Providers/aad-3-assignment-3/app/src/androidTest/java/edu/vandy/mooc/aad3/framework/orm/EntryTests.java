package edu.vandy.mooc.aad3.framework.orm;

import android.os.Parcel;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;


public class EntryTests {

    private Entry mEntryA;
    private Entry mEntryB;

    static private Entry newA() {
        return newEntry(TEST_ID);
    }

    static private Entry newB() {
        return newEntry();
    }


    @Before
    public void setUp() {
        mEntryA = newA();
        mEntryB = newB();
    }

    @Test
    public void testParcelable() {

        // Write the data.
        Parcel parcel = Parcel.obtain();
        mEntryA.writeToParcel(parcel, mEntryA.describeContents());

        // After you're done with writing, you need to reset the parcel for reading.
        parcel.setDataPosition(0);

        // Read the data.
        Entry resultingEntry = Entry.CREATOR.createFromParcel(parcel);

        System.out.println(mEntryA);
        System.out.println(resultingEntry);

        // Verify that the received data is correct.
        checkEntry(resultingEntry, TEST_ID);
    }

    @Test
    public void feed_ParcelableWriteReadArray() {
        // Write the data.
        Parcel parcel = Parcel.obtain();
        mEntryA.writeToParcel(parcel, mEntryA.describeContents());
        mEntryB.writeToParcel(parcel, mEntryB.describeContents());

        // After you're done with writing, you need to reset the parcel for reading.
        parcel.setDataPosition(0);

        // create an array of appropriate size.
        Entry[] resultingArray = Entry.CREATOR.newArray(2);

        assertThat(resultingArray.length, is(2));

        // Read the data.
        resultingArray[0] = Entry.CREATOR.createFromParcel(parcel);
        resultingArray[1] = Entry.CREATOR.createFromParcel(parcel);

        // Verify that the received data is correct.
        checkEntry(resultingArray[0], TEST_ID);
        checkEntry(resultingArray[1], -1L);
    }

    static final public long TEST_ID = 123456L;
    static final public String TEST_ENTRY_ID = "test entry ID";
    static final public String TEST_TITLE = "Lets party like its 1999";
    static final public String TEST_LINK = "https://www.vanderbilt.edu";
    static final public String TEST_PUBLISHED = "1999-12-31";
    static final public String TEST_AUTHOR = "CNN";

    static public Entry newEntry(long id) {
        return new Entry(id, TEST_ENTRY_ID, TEST_TITLE, TEST_LINK, TEST_PUBLISHED, TEST_AUTHOR);
    }

    static public Entry newEntry() {
        return new Entry(TEST_ENTRY_ID, TEST_TITLE, TEST_LINK, TEST_PUBLISHED, TEST_AUTHOR);
    }


    static public void checkEntry(Entry testEntry, long id) {
        assertThat(testEntry.get_ID(), is(id));
        assertThat(testEntry.getLINK(), is(TEST_LINK));
        assertThat(testEntry.getPUBLISHED(), is(TEST_PUBLISHED));
        assertThat(testEntry.getTITLE(), is(TEST_TITLE));
        assertThat(testEntry.getENTRY_ID(), is(TEST_ENTRY_ID));
        assertThat(testEntry.getAUTHOR(), is(TEST_AUTHOR));
    }
}



















