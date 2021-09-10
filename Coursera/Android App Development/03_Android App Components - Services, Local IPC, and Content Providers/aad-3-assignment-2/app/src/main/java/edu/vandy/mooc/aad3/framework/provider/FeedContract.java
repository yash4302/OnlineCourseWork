package edu.vandy.mooc.aad3.framework.provider;

import android.content.ContentValues;
import android.content.UriMatcher;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Field and table name constants for a content provider.
 */
public class FeedContract {

    // org name in java package format
    private static final String ORGANIZATIONAL_NAME = "edu.vanderbilt.mooc";
    // name of this provider's project
    private static final String PROJECT_NAME = "atom_reader";
    // name of the database file
    public static final String DATABASE_NAME = "atom_data.db";
    // version of database
    public static final int DATABASE_VERSION = 1;

    /**
     * ContentProvider Related Constants
     */
    private static final String AUTHORITY = ORGANIZATIONAL_NAME + "."
            + PROJECT_NAME + ".provider";
    private static final Uri BASE_URI = Uri.parse("content://" + AUTHORITY);
    public static final UriMatcher URI_MATCHER = buildUriMatcher();

    // register identifying URIs for Rss entity
    // the TOKEN value is associated with each URI registered
    private static UriMatcher buildUriMatcher() {
        // add default 'no match' result to matcher
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        // Entries URIs
        matcher.addURI(AUTHORITY, Entry.PATH, Entry.PATH_TOKEN);
        matcher.addURI(AUTHORITY, Entry.PATH_FOR_ID, Entry.PATH_FOR_ID_TOKEN);
        return matcher;
    }

    public static class Entry {
        // this table/pojo's unique name
        public static final String NAME = "entry";
        // SQLite table name
        public static final String TABLE_NAME = NAME + "_table";
        // PATH & TOKEN for entire table
        public static final String PATH = NAME;
        public static final int PATH_TOKEN = 110;
        // PATH & TOKEN for single row of table
        public static final String PATH_FOR_ID = PATH + "/#";
        public static final int PATH_FOR_ID_TOKEN = 120;
        // URI for this content.
        public static final Uri CONTENT_URI = BASE_URI.buildUpon()
                .appendPath(PATH).build();

        // TOPIC for this content.
        public static final String CONTENT_TOPIC = "topic/edu.vanderbilt." + PATH;

        // CONTENT/MIME TYPE for this content
        private final static String MIME_TYPE_END = PATH;
        public static final String CONTENT_TYPE_DIR = ORGANIZATIONAL_NAME
                + ".cursor.dir/" + ORGANIZATIONAL_NAME + "." + MIME_TYPE_END;
        public static final String CONTENT_ITEM_TYPE = ORGANIZATIONAL_NAME
                + ".cursor.item/" + ORGANIZATIONAL_NAME + "." + MIME_TYPE_END;

        // the names and order of ALL columns, including internal use ones
        public static final String[] ALL_COLUMN_NAMES = {
                Cols._ID,
                Cols.ENTRY_ID,
                Cols.TITLE,
                Cols.LINK,
                Cols.PUBLISHED,
                Cols.AUTHOR,
        };

        public static class Cols {
            public static final String _ID = BaseColumns._ID; // convention
            public static final String ENTRY_ID = "entry_id";
            public static final String TITLE = "title";
            public static final String LINK = "link";
            public static final String PUBLISHED = "published";
            public static final String AUTHOR = "author";
        }

        public static ContentValues initializeWithDefault(
                final ContentValues assignedValues) {

            final ContentValues setValues = (assignedValues == null) ? new ContentValues()
                    : assignedValues;

            if (!setValues.containsKey(Cols.TITLE)) {
                setValues.put(Cols.TITLE, "");
            }

            if (!setValues.containsKey(Cols.LINK)) {
                setValues.put(Cols.LINK, "");
            }

            if (!setValues.containsKey(Cols.ENTRY_ID)) {
                setValues.put(Cols.ENTRY_ID, "");
            }

            if (!setValues.containsKey(Cols.PUBLISHED)) {
                setValues.put(Cols.PUBLISHED, "");
            }

            if (!setValues.containsKey(Cols.AUTHOR)) {
                setValues.put(Cols.AUTHOR, "");
            }

            return setValues;
        }
    }

}