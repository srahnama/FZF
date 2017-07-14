package ir.srahnama.a504.a504;



import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
* Created by mrs on 10/6/16.
*/

public class DBHelper extends SQLiteOpenHelper {
static String DB_PATH = "data/data/ir.srahnama.sherdoon.sherdoon/databases/";

private final Context mContext;

// Table Name
public static final String TABLE_NAME = "poems";

// Table columns
public static final String _ID = "id";
public static final String _TITLE = "title";
public static final String _TEXT = "text";
public static final String _READ = "readcount";
public static final String _FAVORITE = "favorite";
//public static final String TODO_SUBJECT = "subject";
//public static final String TODO_DESC = "description";
// Database Information
static final String DB_NAME = "love2";

// database version
static final int DB_VERSION = 1;

public DBHelper(Context context){
	super(context, DB_NAME, null, DB_VERSION);
	this.mContext = context;
}
public void createDB(){
	boolean dbExist = checkDB();
	if (dbExist) {

	} else {
		this.getReadableDatabase();

		try {
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
private boolean checkDB() {
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
