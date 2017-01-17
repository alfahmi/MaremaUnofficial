package com.alfahmi.marema.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
//import android.preference.PreferenceManager;

public class SharedPreference {

	public static final String PREFS_NAME = "AFAHMI_PREFERENCE";
	public static final String PREFS_SMS_CENTER = "SMS_CENTER";
	public static final String PREFS_PIN = "PIN";
	public static final String PREFS_OUTLET_NAME = "OUTLET_NAME";
	public static final String PREFS_OUTLET_MOTO = "OUTLET_MOTO";
	
	public SharedPreference() {
		super();
	}

	public void save(Context context, String text) {
		SharedPreferences settings;
		Editor editor;
		
		//settings = PreferenceManager.getDefaultSharedPreferences(context);
		settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE); //1
		editor = settings.edit(); //2

		editor.putString(PREFS_SMS_CENTER, text); 
		editor.putString(PREFS_PIN, text); //3
		editor.putString(PREFS_OUTLET_NAME, text); //3
		editor.putString(PREFS_OUTLET_MOTO, text); //3

		editor.commit(); //4
	}

	public String getSMS_CENTER(Context context) {
		SharedPreferences settings;
		String sms_center;

		//settings = PreferenceManager.getDefaultSharedPreferences(context);
		settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
		sms_center = settings.getString(PREFS_SMS_CENTER, null);
		return sms_center;
	}
	
	public String getPIN(Context context) {
		SharedPreferences settings;
		String pin;

		//settings = PreferenceManager.getDefaultSharedPreferences(context);
		settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
		pin = settings.getString(PREFS_PIN, null);
		return pin ;
	}
	
	public String getOUTLET_NAME(Context context) {
		SharedPreferences settings;
		String outlet_name;

		//settings = PreferenceManager.getDefaultSharedPreferences(context);
		settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
		outlet_name = settings.getString(PREFS_OUTLET_NAME, null);
		return outlet_name;
	}
	
	public String getOUTLET_MOTO(Context context) {
		SharedPreferences settings;
		String outlet_moto;

		//settings = PreferenceManager.getDefaultSharedPreferences(context);
		settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
		outlet_moto = settings.getString(PREFS_OUTLET_MOTO, null);
		return outlet_moto;
	}
	
	public void clearSharedPreference(Context context) {
		SharedPreferences settings;
		Editor editor;

		//settings = PreferenceManager.getDefaultSharedPreferences(context);
		settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
		editor = settings.edit();

		editor.clear();
		editor.commit();
	}

	public void removeValue(Context context) {
		SharedPreferences settings;
		Editor editor;

		settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
		editor = settings.edit();

		editor.remove(PREFS_SMS_CENTER);
		editor.commit();
	}	
}
