package ir.srahnama.a504.a504;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by mrs on 10/6/16.
 */

public class SQLController {
    private DBHelper dbHelper;
    private Context ourcontext;
    private SQLiteDatabase database;

    public SQLController(Context c) {

        ourcontext = c;

    }
    public SQLController open() throws SQLException {
        dbHelper = new DBHelper(ourcontext);
        dbHelper.createDB();
        database = dbHelper.getWritableDatabase();
        return this;

    }

    public void close() {
        dbHelper.close();
    }

    public Cursor fetch() {
        String[] columns = new String[] { DBHelper._ID,DBHelper._TEXT, DBHelper._FAVORITE, DBHelper._TEXT };
        Cursor cursor = database.query(DBHelper.TABLE_NAME, columns, null,
                null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }
    public Integer fetchOne(String field, String _ID) {
        String whereClause=DBHelper._ID+"=?" ;
        String[] whereArgs = new String[] {
                _ID
        };
        if(field.equals(DBHelper._FAVORITE)) {
            String[] columns = new String[] { DBHelper._FAVORITE };
            Cursor cursor = database.query(DBHelper.TABLE_NAME, columns,  whereClause, whereArgs, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
            }
            return cursor.getInt(0);
        }
        else if(field.equals(DBHelper._READ)) {
            String[] columns = new String[] {  DBHelper._READ };
            Cursor cursor = database.query(DBHelper.TABLE_NAME, columns,  whereClause, whereArgs, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
            }
            return cursor.getInt(0);

        }
        return 100;

    }

    public void updateRead(long _id, int read) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper._READ, read);

        database.update(DBHelper.TABLE_NAME, contentValues,
                DBHelper._ID + " = " + _id, null);

    }

    public void updateFavorite(long _id, int fav) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper._FAVORITE, fav);

        database.update(DBHelper.TABLE_NAME, contentValues,
                DBHelper._ID + " = " + _id, null);

    }



}