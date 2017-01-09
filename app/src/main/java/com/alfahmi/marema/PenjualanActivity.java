package com.alfahmi.marema;

import java.util.ArrayList;

import android.support.v7.app.AppCompatActivity;
import android.app.AlertDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.FilterQueryProvider;
import android.widget.ListView;
import android.widget.TextView;
import android.view.*;

public class PenjualanActivity extends AppCompatActivity {
	
	SQLiteHelper SQLITEHELPER;
    SQLiteDatabase SQLITEDATABASE;
    Cursor cursor;
    SQLiteListAdapter ListAdapter ;

    ArrayList<String> ID_ArrayList = new ArrayList<String>();
    ArrayList<String> NAME_ArrayList = new ArrayList<String>();
    ArrayList<String> PHONE_ArrayList = new ArrayList<String>();
    ArrayList<String> PRICE_ArrayList = new ArrayList<String>();
    ListView LISTVIEW;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
		
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setTitle("Penjualan"); 
		LISTVIEW = (ListView) findViewById(R.id.listView1);

        SQLITEHELPER = new SQLiteHelper(this);
        
    }

    @Override
    protected void onResume() {

    	ShowSQLiteDBdata() ;
        super.onResume();
    }

    private void ShowSQLiteDBdata() {
    	
    	SQLITEDATABASE = SQLITEHELPER.getWritableDatabase();
    	
        cursor = SQLITEDATABASE.rawQuery("SELECT * FROM transactionTable", null);

        ID_ArrayList.clear();
        NAME_ArrayList.clear();
        PHONE_ArrayList.clear();
        PRICE_ArrayList.clear();
        
        if (cursor.moveToFirst()) {
            do {
            	ID_ArrayList.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.KEY_ID)));
            	
            	NAME_ArrayList.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.KEY_NAME)));
            	
            	PHONE_ArrayList.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.KEY_PHONE)));
            	
            	PRICE_ArrayList.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.KEY_PRICE)));

            } while (cursor.moveToNext());
        }
        
        ListAdapter = new SQLiteListAdapter(PenjualanActivity.this,
        		
        		ID_ArrayList,
        		NAME_ArrayList,
        		PHONE_ArrayList,
        		PRICE_ArrayList 
        		
        		);
        
        LISTVIEW.setAdapter(ListAdapter);
        
        cursor.close();
    }
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				onBackPressed();
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
}
