package edu.vandy.mooc.aad3.framework.provider;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

import java.util.Arrays;

/**
 * Database Adapter for Feed(s)
 */
@SuppressWarnings({"SameParameterValue", "JavaDoc", "UnusedReturnValue", "unused"})
public class FeedDBAdapter {

    // LOG_TAG for logging.
    private static final String LOG_TAG = FeedDBAdapter.class.getCanonicalName();

    // Variable to hold the database instance.
    private SQLiteDatabase db;
    // Context of the application using the database.
    private final Context context;
    // Database open/upgrade helper
    final private myDbHelper dbHelper;
    // if the DB is in memory-only or written to file.
    private boolean MEMORY_ONLY_DB = false;

    /*
     * variables for SQLite table creation.
     */

    // Create Table
    private static final String CREATE_TABLE = "create table ";
    // start/end parenthesis
    private static final String START_PARENTHESIS = " (";
    private static final String END_PARENTHESIS = " );";
    // row '_ID' value and comma.
    private static final String PRIMARY_KEY = " integer primary key autoincrement ";
    private static final String COMMA = " , ";

    // Each DataType supported
    private static final String TEXT = " TEXT ";

    /*
     * SQLite commands for creating each table in the database.
     */

    // SQL Statement to create database table for 'Entry'(s)
    private static final String DATABASE_TABLE_ENTRY = CREATE_TABLE
            // table name
            + FeedContract.Entry.TABLE_NAME + START_PARENTHESIS
            // setup auto-inc.
            + FeedContract.Entry.Cols._ID + PRIMARY_KEY + COMMA
            // ST:tableCreateVariables:start
            + FeedContract.Entry.Cols.ENTRY_ID + TEXT + COMMA
            + FeedContract.Entry.Cols.TITLE + TEXT + COMMA
            + FeedContract.Entry.Cols.LINK + TEXT + COMMA
            + FeedContract.Entry.Cols.PUBLISHED + TEXT + COMMA
            + FeedContract.Entry.Cols.AUTHOR + TEXT
            // ST:tableCreateVariables:finish
            + END_PARENTHESIS; // end table

    /**
     * constructor that accepts the context to be associated with
     *
     * @param _context
     */
    public FeedDBAdapter(Context _context) {
        Log.d(LOG_TAG, "MyDBAdapter constructor");

        context = _context;
        dbHelper = new myDbHelper(context, FeedContract.DATABASE_NAME, null,
                FeedContract.DATABASE_VERSION);
    }

    /**
     * constructor that accepts the context to be associated with, and if this
     * DB should be created in memory only(non-persistent).
     *
     * @param _context
     */
    public FeedDBAdapter(Context _context, boolean memory_only_db) {
        Log.d(LOG_TAG, "MyDBAdapter constructor w/ mem only =" + memory_only_db);

        context = _context;
        MEMORY_ONLY_DB = memory_only_db;
        if (MEMORY_ONLY_DB) {
            dbHelper = new myDbHelper(context, null, null, FeedContract.DATABASE_VERSION);
        } else {
            dbHelper = new myDbHelper(context, FeedContract.DATABASE_NAME, null,
                    FeedContract.DATABASE_VERSION);
        }
    }

    /**
     * open the DB Get Memory or File version of DB, and write/read access or
     * just read access if that is all that is possible.
     *
     * @return this MoocDataDBAdaptor
     * @throws SQLException
     */
    public FeedDBAdapter open() throws SQLException {
        Log.d(LOG_TAG, "open()");
        try {
            db = dbHelper.getWritableDatabase();
        } catch (SQLException ex) {
            db = dbHelper.getReadableDatabase();
        }
        return this;
    }

    /**
     * Remove a row of the DB where the rowIndex matches.
     *
     * @param table table to delete from
     * @param _id   row to remove from DB
     * @return how many rows were deleted.
     */
    public int delete(final String table, long _id) {
        Log.d(LOG_TAG, "delete(" + table + " " + _id + ") ");
        return getDB().delete(table, android.provider.BaseColumns._ID + " = " + _id,
                null);
    }


    /**
     * Delete row(s) that match the whereClause and whereArgs(if used).
     * <p/>
     * the whereArgs is an String[] of values to substitute for the '?'s in the
     * whereClause
     *
     * @param whereClause
     * @param whereArgs
     * @return
     */
    public int delete(final String table, final String whereClause,
                      final String[] whereArgs) {
        Log.d(LOG_TAG, "delete(" + table + " " + whereClause + ") ");
        return getDB().delete(table, whereClause, whereArgs);
    }

    /**
     * Query the Database with the provided specifics.
     *
     * @param projection
     * @param selection
     * @param selectionArgs
     * @param sortOrder
     * @return Cursor of results
     */

    public Cursor query(final String table, final String[] projection,
                        final String selection, final String[] selectionArgs,
                        final String sortOrder) {
        Log.d(LOG_TAG, "query: " + Arrays.toString(projection) + ", " + selection
                + ", " + Arrays.toString(selectionArgs) + ", " + sortOrder);

        return getDB().query(table, projection, selection, selectionArgs, null,
                null, sortOrder);
    }

    /**
     * close the DB.
     */
    public void close() {
        Log.d(LOG_TAG, "close()");
        getDB().close();
    }

    /**
     * Start a transaction.
     */
    public void startTransaction() {
        Log.d(LOG_TAG, "startTransaction()");
        getDB().beginTransaction();
    }

    /**
     * End a transaction.
     */
    public void endTransaction() {
        Log.d(LOG_TAG, "endTransaction()");
        getDB().endTransaction();
    }

    /**
     * Get the underlying Database.
     *
     * @return
     */
    public SQLiteDatabase getDB() {
        return db;
    }

    /**
     * Insert a ContentValues into the DB.
     *
     * @param table
     * @return row's '_id' of the newly inserted ContentValues
     */
    public long insert(final String table, final ContentValues cv) {
        Log.d(LOG_TAG, "insert(CV)");
        cv.remove(BaseColumns._ID);
        return getDB().insert(table, null, cv);
    }

    /**
     * Update Value(s) in the DB.
     *
     * @param values
     * @param whereClause
     * @param whereArgs
     * @return number of rows changed.
     */
    public int update(final String table, final ContentValues values,
                      final String whereClause, final String[] whereArgs) {
        values.remove("_ID");
        return getDB().update(table, values, whereClause, whereArgs);
    }

    /**
     * This class can support running the database in a non-persistent mode,
     * this tells you if that is happening.
     *
     * @return boolean true/false of if this DBAdaptor is persistent or in
     * memory only.
     */
    public boolean isMemoryOnlyDB() {
        return MEMORY_ONLY_DB;
    }

    /**
     * DB Helper Class.
     *
     * @author mwalker
     */
    private static class myDbHelper extends SQLiteOpenHelper {

        public myDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                          int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.w(LOG_TAG, "DATABASE_CREATE: version: " + FeedContract.DATABASE_VERSION + " " + DATABASE_TABLE_ENTRY);
            // create all table(s) for the database.
            db.execSQL(DATABASE_TABLE_ENTRY);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // Log version upgrade.
            Log.w(LOG_TAG + "DBHelper", "Upgrading from version " + oldVersion
                    + " to " + newVersion + ", which will destroy all old data");

            // **** Upgrade DB ****
            // This is where you would manually migrate your data from old DB version to new

            // For now, drop old DB table(s)
            db.execSQL(String.format("DROP TABLE IF EXISTS %s", DATABASE_TABLE_ENTRY));

            // Create a the new database.
            onCreate(db);
        }

    }

}
