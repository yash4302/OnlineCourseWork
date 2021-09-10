package edu.vandy.mooc.aad3.framework.orm;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import edu.vandy.mooc.aad3.framework.provider.FeedContract;

import java.util.ArrayList;

/**
 *
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class Entry implements Parcelable {

    private static final String LOG_TAG = Entry.class.getCanonicalName();

    public long _ID;
    public String ENTRY_ID;
    public String TITLE;
    public String LINK;
    public String PUBLISHED;
    public String AUTHOR;

    public Entry(){
        this._ID = -1L;
        this.TITLE = "";
        this.ENTRY_ID = "";
        this.LINK = "";
        this.PUBLISHED = "";
        this.AUTHOR = "";
    }

    public Entry(long _ID, String ENTRY_ID, String TITLE, String LINK, String PUBLISHED, String
            AUTHOR) {
        this._ID = _ID;
        this.TITLE = TITLE;
        this.ENTRY_ID = ENTRY_ID;
        this.LINK = LINK;
        this.PUBLISHED = PUBLISHED;
        this.AUTHOR = AUTHOR;
    }

    public Entry(String ENTRY_ID, String TITLE, String LINK, String PUBLISHED, String AUTHOR) {
        this._ID = -1L;
        this.TITLE = TITLE;
        this.ENTRY_ID = ENTRY_ID;
        this.LINK = LINK;
        this.PUBLISHED = PUBLISHED;
        this.AUTHOR = AUTHOR;
    }

    @Override
    @SuppressWarnings("SimplifiableIfStatement")
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Entry)) return false;

        Entry entry = (Entry) o;

        if (getENTRY_ID() != null ? !getENTRY_ID().equals(entry.getENTRY_ID()) : entry.getENTRY_ID() != null)
            return false;
        if (getTITLE() != null ? !getTITLE().equals(entry.getTITLE()) : entry.getTITLE() != null)
            return false;
        if (getLINK() != null ? !getLINK().equals(entry.getLINK()) : entry.getLINK() != null)
            return false;
        if (getPUBLISHED() != null ? !getPUBLISHED().equals(entry.getPUBLISHED()) : entry.getPUBLISHED() != null)
            return false;
        return getAUTHOR() != null ? getAUTHOR().equals(entry.getAUTHOR()) : entry.getAUTHOR() == null;

    }

    @Override
    public int hashCode() {
        int result = getENTRY_ID() != null ? getENTRY_ID().hashCode() : 0;
        result = 31 * result + (getTITLE() != null ? getTITLE().hashCode() : 0);
        result = 31 * result + (getLINK() != null ? getLINK().hashCode() : 0);
        result = 31 * result + (getPUBLISHED() != null ? getPUBLISHED().hashCode() : 0);
        result = 31 * result + (getAUTHOR() != null ? getAUTHOR().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Entry{" +
                "_ID=" + _ID +
                ", TITLE='" + TITLE + '\'' +
                ", ENTRY_ID='" + ENTRY_ID + '\'' +
                ", LINK='" + LINK + '\'' +
                ", PUBLISHED='" + PUBLISHED + '\'' +
                ", AUTHOR='" + AUTHOR + '\'' +
                '}';
    }

    public long get_ID() {
        return _ID;
    }

    public void set_ID(long _ID) {
        this._ID = _ID;
    }

    public String getTITLE() {
        return TITLE;
    }

    public void setTITLE(String TITLE) {
        this.TITLE = TITLE;
    }

    public String getENTRY_ID() {
        return ENTRY_ID;
    }

    public void setENTRY_ID(String ENTRY_ID) {
        this.ENTRY_ID = ENTRY_ID;
    }

    public String getLINK() {
        return LINK;
    }

    public void setLINK(String LINK) {
        this.LINK = LINK;
    }

    public String getPUBLISHED() {
        return PUBLISHED;
    }

    public void setPUBLISHED(String PUBLISHED) {
        this.PUBLISHED = PUBLISHED;
    }

    public String getAUTHOR() {
        return AUTHOR;
    }

    public void setAUTHOR(String AUTHOR) {
        this.AUTHOR = AUTHOR;
    }

    // these are for parcelable interface
    @Override
    /**
     * Used for writing a copy of this object to a Parcel, do not manually call.
     */
    public int describeContents() {
        return 0;
    }

    @Override
    /**
     * Used for writing a copy of this object to a Parcel, do not manually call.
     */
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(_ID);
        dest.writeString(ENTRY_ID);
        dest.writeString(TITLE);
        dest.writeString(LINK);
        dest.writeString(PUBLISHED);
        dest.writeString(AUTHOR);
    }

    /**
     * Used for writing a copy of this object to a Parcel, do not manually call.
     */
    public static final Creator<Entry> CREATOR = new Creator<Entry>() {
        public Entry createFromParcel(Parcel in) {
            return new Entry(in);
        }

        public Entry[] newArray(int size) {
            return new Entry[size];
        }
    };

    /**
     * Used for writing a copy of this object to a Parcel, do not manually call.
     */
    private Entry(Parcel in) {
        _ID = in.readLong();
        ENTRY_ID = in.readString();
        TITLE = in.readString();
        LINK = in.readString();
        PUBLISHED = in.readString();
        AUTHOR = in.readString();
    }

    /**
     * Create a ContentValues from this Entry.
     *
     * @return ContentValues that is created from the Entry object
     */
    public ContentValues getContentValues() {
        ContentValues rValue = new ContentValues();
        rValue.put(FeedContract.Entry.Cols._ID, this._ID);
        rValue.put(FeedContract.Entry.Cols.ENTRY_ID, this.ENTRY_ID);
        rValue.put(FeedContract.Entry.Cols.TITLE, this.TITLE);
        rValue.put(FeedContract.Entry.Cols.LINK, this.LINK);
        rValue.put(FeedContract.Entry.Cols.PUBLISHED, this.PUBLISHED);
        rValue.put(FeedContract.Entry.Cols.AUTHOR, this.AUTHOR);
        return rValue;
    }

    public static class CONVERTER {

        /**
         * Get all of the Entry from the passed in cursor.
         *
         * @param cursor passed in cursor
         * @return ArrayList<Entry> The set of Entry
         */
        public static ArrayList<Entry> getFromCursor(
                Cursor cursor) {
            ArrayList<Entry> rValue = new ArrayList<>();
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        rValue.add(getEntryFromCursor(cursor));
                    } while (cursor.moveToNext());
                }
            }
            return rValue;
        }

        /**
         * Get the first TagsData from the passed in cursor.
         *
         * @param cursor passed in cursor
         * @return TagsData object
         */
        private static Entry getEntryFromCursor(Cursor cursor) {

            long _ID = cursor.getLong(cursor
                    .getColumnIndex(FeedContract.Entry.Cols._ID));

            String entry_id = cursor.getString(cursor
                    .getColumnIndex(FeedContract.Entry.Cols.ENTRY_ID));
            String title = cursor.getString(cursor
                    .getColumnIndex(FeedContract.Entry.Cols.TITLE));
            String link = cursor.getString(cursor
                    .getColumnIndex(FeedContract.Entry.Cols.LINK));
            String published = cursor.getString(cursor
                    .getColumnIndex(FeedContract.Entry.Cols.PUBLISHED));
            String author = cursor.getString(cursor
                    .getColumnIndex(FeedContract.Entry.Cols.AUTHOR));

            // construct the returned object
            return new Entry(
                    _ID,
                    entry_id,
                    title,
                    link,
                    published,
                    author
            );
        }
    }
}
