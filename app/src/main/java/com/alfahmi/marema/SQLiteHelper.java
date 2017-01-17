package com.alfahmi.marema;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {
	
    static String DATABASE_NAME="AlfahmiDataBase";
    
    public static final String KEY_ID="id";
    
    public static final String TABLE_NAME="transactionTable";
    
    public static final String KEY_NAME="NAME";
    
    public static final String KEY_PHONE="PHONE";
    
    public static final String KEY_PRICE="PRICE";
    
    public SQLiteHelper(Context context) {
    	
        super(context, DATABASE_NAME, null, 1);
        
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
    	
        String CREATE_TABLE="CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" ("+KEY_ID+" INTEGER PRIMARY KEY, "+KEY_NAME+" VARCHAR, "+KEY_PHONE+" VARCHAR, "+KEY_PRICE+" VARCHAR)";
        database.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);

    }

}
