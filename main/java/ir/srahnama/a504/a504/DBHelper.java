package ir.srahnama.a504.a504;



import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static android.content.ContentValues.TAG;

/**
* Created by mrs on 10/6/16.
*/

public class DBHelper extends SQLiteOpenHelper {
static String DB_PATH = "data/data/ir.srahnama.a504.a504/databases/";

private final Context mContext;

// Table Name
public static final String TABLE_NAME = "words";

// Table columns
	public static final String _ID = "id";
	public static final String _ENword = "ENword";
	public static final String _FAword = "FAword";
	public static final String _READ = "read";
	public static final String _FAVORITE = "favorite";
	public static final String _CODING = "coding";
	public static final String _SYNON= "synon";
	public static final String _PRONUN= "pronun";
	public static final String _EXAMPLE = "Example";
	public static final String _EXMEAN = "Exmean";

//public static final String TODO_SUBJECT = "subject";
//public static final String TODO_DESC = "description";
// Database Information
static final String DB_NAME = "504";

// database version
static final int DB_VERSION = 1;

public DBHelper(Context context){
	super(context, DB_NAME, null, DB_VERSION);
	this.mContext = context;
}

public void createDB(){
	boolean dbExist = checkDB();
	Log.d(TAG, "open1: create db");
	if (dbExist) {
		Log.d(TAG, "check1 : notcopydb");
	} else {
		this.getReadableDatabase();

		try {
			Log.d(TAG, "check1 : copydb");
			copyDB();

		} catch (Exception e) {
			throw new Error("Error copying DB");

		}

	}
}
public void createDataBase() throws IOException {
	boolean mDataBaseExist = checkDB();
	if (!mDataBaseExist) {
		this.getReadableDatabase();
		try {

			copyDB();
		} catch (IOException mIOException) {
			mIOException.printStackTrace();
			throw new Error("Error copying database");
		} finally {
			this.close();
		}
	}
}
public boolean checkDB() {
	SQLiteDatabase check = null;
	try {
		String dbPath = DB_PATH+DB_NAME;
		check = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READONLY);
	} catch (Exception e) {
		// TODO: handle exception
	}
	if (check!=null) {
		check.close();
	}
	if(check!=null)
		Log.d(TAG, "check1 : true");
	else
		Log.d(TAG, "check1 : false");
	return check != null ? true : false;
}
private void copyDB() throws IOException {
	InputStream dbInput = mContext.getAssets().open(DB_NAME);
	String outFile = DB_PATH + DB_NAME;
	OutputStream dbOutput = new FileOutputStream(outFile);

	byte[] buffer = new byte[1024];
	int length;
	while ((length = dbInput.read(buffer))>0) {
		dbOutput.write(buffer,0,length);
	}

	dbOutput.flush();
	dbOutput.close();
	dbInput.close();

}

@Override
public void onCreate(SQLiteDatabase db) {

}

@Override
public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

}


}
