package com.alfahmi.marema;

import android.support.v7.app.ActionBarActivity;
import android.content.*;
import android.net.*;
import android.os.*;
import android.provider.*;
import android.app.*;
import android.util.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;

import android.telephony.*;
import android.database.*;

public class MainActivity extends ActionBarActivity  implements View.OnClickListener {
	
	EditText eName;
	EditText ePhone;
	EditText eSmsCenter;
	EditText tPin;
	
	ImageButton iFab;
    Spinner spNominal;
	Button bSubmit;
	
	TextView outletName;
	TextView outletMoto;
	TextView tMaremaCenter;
	static EditText tResponMarema;
	
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
		
		
		eName = (EditText) findViewById(R.id.textView1);
		ePhone = (EditText) findViewById(R.id.editText1);
		bSubmit = (Button) findViewById(R.id.submit);
		tPin = (EditText) findViewById(R.id.alf_pin);
		iFab = (ImageButton) findViewById(R.id.alfahmi_fab);
		eSmsCenter = (EditText) findViewById(R.id.alf_sms_center);
		outletName = (TextView) findViewById(R.id.alf_outlet);
		outletMoto = (TextView) findViewById(R.id.alf_outlet_moto);
		tMaremaCenter = (TextView) findViewById(R.id.alf_marema_center);
		//tResponMarema = (EditText) findViewById(R.id.alf_respon_marema);
		
		if ("telkomsel".equals(smsCenter))
		{
			eSmsCenter.setText("Telkomsel Center");
			tMaremaCenter.setText("085321400040");
		} else if ("indosat".equals(smsCenter))
		{
			eSmsCenter.setText("Indosat Center");
			tMaremaCenter.setText("085798561111");
		}
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

					String phoneNo = ePhone.getText().toString();
					String sTitik = ".";
					String spnominal = spNominal.getSelectedItem().toString();
					String tpin = tPin.getText().toString();
					String tsmscenter = tMaremaCenter.getText().toString();
					String sms = phoneNo+sTitik+spnominal+sTitik+tpin;

					try {
						SmsManager smsManager = SmsManager.getDefault();
						smsManager.sendTextMessage(tsmscenter, null, sms, null, null);
						ePhone.setText("");
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
	
	public void recivedSms(String message) 
        {
       try 
         {
          tResponMarema.setText(message);
         } 
         catch (Exception e) 
             {         
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
        }

        return super.onOptionsItemSelected(item);
    }
	
}
