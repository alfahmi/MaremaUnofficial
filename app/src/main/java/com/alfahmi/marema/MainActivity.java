package com.alfahmi.marema;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.content.*;
import android.net.*;
import android.os.*;
import android.provider.*;
import android.app.*;
import android.util.*;
import android.view.View;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View.*;
import android.widget.ImageButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Button;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import android.widget.AdapterView;

import android.telephony.*;
import android.database.*;
import android.database.sqlite.SQLiteDatabase;
import android.text.*;
import android.widget.ImageView;
import android.graphics.*;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener {
	
	EditText eName, ePhone, eSmsCenter, tPin;
	TextView outletName, outletMoto, tMaremaCenter;
	String Name, PhoneNumber, Price ;
	ImageView Account, Phone, Chart, Send;
	
	ImageButton iFab;
    Spinner spNominal;
	Button bSubmit;
	SQLiteDatabase SQLITEDATABASE;

	String SQLiteQuery;
	Boolean CheckEditTextEmpty ;
	
	private final int PICK = 1;
	
	String Nominal[] = { "1", "2", "3", "4", "5", "10", "15", "20", "25", "50", "100"};
    ArrayAdapter<String> adapterNominal;

    // local members
    String snominal,scountry;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		SharedPreferences sharedPreferences = getSharedPreferences("alfahmi.marema_preferences",Context.MODE_PRIVATE); 
		String smsCenter = sharedPreferences.getString("smsCenter","telkomsel");
		String empin = sharedPreferences.getString("pin","1234");
		String otlet = sharedPreferences.getString("outletName","Fahmi Cell");
		String otletmoto = sharedPreferences.getString("outletMoto","Ngutang? Are you kidding me?");
		
		setContentView(R.layout.content_main);
		
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		toolbar.setTitle("");
		toolbar.setLogo(R.drawable.ic_material_ui);
        setSupportActionBar(toolbar);
		
		// FindViewById elemen
		
		eName = (EditText) findViewById(R.id.textView1);
		ePhone = (EditText) findViewById(R.id.editText1);
		bSubmit = (Button) findViewById(R.id.submit);
		tPin = (EditText) findViewById(R.id.alf_pin);
		iFab = (ImageButton) findViewById(R.id.alfahmi_fab);
		eSmsCenter = (EditText) findViewById(R.id.alf_sms_center);
		outletName = (TextView) findViewById(R.id.alf_outlet);
		outletMoto = (TextView) findViewById(R.id.alf_outlet_moto);
		tMaremaCenter = (TextView) findViewById(R.id.alf_marema_center);
		
		// ImageView (icon)
		Account = (ImageView) findViewById(R.id.alfahmi_ic_account);
		Phone = (ImageView) findViewById(R.id.alfahmi_ic_phone);
		Chart = (ImageView) findViewById(R.id.alfahmi_ic_chart);
		Send = (ImageView) findViewById(R.id.alfahmi_ic_send);
		
		// Tint ImageView following ColorAccent Color
		int color = getResources().getColor(R.color.warnaAksen);
		Account.setColorFilter(color);
		Phone.setColorFilter(color);
		Chart.setColorFilter(color);
		Send.setColorFilter(color);
		
		// SMS Center Option 
		if ("telkomsel".equals(smsCenter))
		{
			eSmsCenter.setText("Telkomsel Center");
			tMaremaCenter.setText("085321400040");
		} else if ("indosat".equals(smsCenter))
		{
			eSmsCenter.setText("Indosat Center");
			tMaremaCenter.setText("085798561111");
		}
		// Outlet Name, Outlet Moto, Pin, FindContack Button and Price Configuration
		outletName.setText(otlet);
		outletMoto.setText(otletmoto);
		tPin.setText(empin);
		iFab.setOnClickListener(this);
		spNominal = (Spinner) findViewById(R.id.spinner1);

        // Initialize and set Adapter
        adapterNominal = new ArrayAdapter<String>(this,
	    android.R.layout.simple_spinner_item, Nominal);
        adapterNominal.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spNominal.setAdapter(adapterNominal);
		// Tombol Submit
		bSubmit.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					AlfahmiDBCreate();

					String phoneNo = ePhone.getText().toString();
					String sTitik = ".";
					String spnominal = spNominal.getSelectedItem().toString();
					String tpin = tPin.getText().toString();
					String tsmscenter = tMaremaCenter.getText().toString();
					String sms = phoneNo+sTitik+spnominal+sTitik+tpin;

					try {
						SmsManager smsManager = SmsManager.getDefault();
						smsManager.sendTextMessage(tsmscenter, null, sms, null, null);
						SubmitData2SQLiteDB();
						Toast.makeText(getApplicationContext(), "SMS Sent!",
									   Toast.LENGTH_LONG).show();
					} catch (Exception e) {
						Toast.makeText(getApplicationContext(),
									   "SMS faild, please try again later!",
									   Toast.LENGTH_LONG).show();
						e.printStackTrace();
					}
					

				}
			});

        // Country Item Selected Listener

        spNominal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> adapter, View v,
										   int position, long id) {
					// On selecting a spinner item
					snominal = adapter.getItemAtPosition(position).toString();

				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
					// TODO Auto-generated method stub

				}
			});
	}
	public void AlfahmiDBCreate(){

    	SQLITEDATABASE = openOrCreateDatabase("AlfahmiDataBase", Context.MODE_PRIVATE, null);

    	SQLITEDATABASE.execSQL("CREATE TABLE IF NOT EXISTS transactionTable(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, NAME VARCHAR, PHONE VARCHAR, PRICE VARCHAR);");
    }

    public void SubmitData2SQLiteDB(){

        Name = eName.getText().toString();

        PhoneNumber = ePhone.getText().toString();

        Price = spNominal.getSelectedItem().toString();

        CheckEditTextIsEmptyOrNot( Name,PhoneNumber, Price);

        if(CheckEditTextEmpty == true)
        {

			SQLiteQuery = "INSERT INTO transactionTable (NAME,PHONE,PRICE) VALUES('"+Name+"', '"+PhoneNumber+"', '"+Price+"');";

			SQLITEDATABASE.execSQL(SQLiteQuery);
			ePhone.setText("");

        }
		else {

			Toast.makeText(MainActivity.this,"Please Fill All the Fields", Toast.LENGTH_LONG).show();
		}
    }

    public void CheckEditTextIsEmptyOrNot(String Name,String PhoneNumber, String Price ){

		if(TextUtils.isEmpty(Name) || TextUtils.isEmpty(PhoneNumber) || TextUtils.isEmpty(Price)){

         	CheckEditTextEmpty = false ;

		}
    	else {
			CheckEditTextEmpty = true ;
		}
    }

	   
	public void onClick(View v) 
	{
		Intent contactPickerIntent = new Intent(Intent.ACTION_PICK,
		ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
	    startActivityForResult(contactPickerIntent, PICK);
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
            case PICK:
                contactPicked(data);
                break;
            }

        } else {
        	Log.e("MainActivity", "Failed to pick contact");
        }
    }

	private void contactPicked(Intent data) {
		Cursor cursor = null;
        try {
        	String phoneNo = null ;
        	String name = null;
        	Uri uri = data.getData();
        	cursor = getContentResolver().query(uri, null, null, null, null);
        	cursor.moveToFirst();

        	int  phoneIndex =cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
        	int  nameIndex =cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
        	
        	phoneNo = cursor.getString(phoneIndex);
        	name = cursor.getString(nameIndex);
        	
        	eName.setText(name);
        	ePhone.setText(phoneNo);
        } catch (Exception e) {
        	e.printStackTrace();
        }
		
		
	}
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.alfahmi_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
			Intent settingsIntent = new Intent();
			settingsIntent.setClass(this, com.alfahmi.marema.SettingsActivity.class);
			startActivity(settingsIntent);
			finish();

            return true;
        } else if (id == R.id.action_penjualan) {
			Intent penjualanIntent = new Intent();
			penjualanIntent.setClass(this, com.alfahmi.marema.PenjualanActivity.class);
			startActivity(penjualanIntent);

            return true;
        } else if (id == R.id.action_reseller) {
			Intent resellerIntent = new Intent();
			resellerIntent.setClass(this, com.alfahmi.marema.ResellerActivity.class);
			startActivity(resellerIntent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
	
}
