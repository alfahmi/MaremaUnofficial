package com.alfahmi.marema;

import android.content.*;
import android.support.v4.preference.PreferenceFragment;
import android.content.SharedPreferences.*;
import android.os.*;
import android.preference.*;
import android.preference.Preference.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.widget.ShareActionProvider.*;




public class SettingsFragment extends PreferenceFragment
{
	
	Context context;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		SharedPreferences sharedPreferences = getActivity().getSharedPreferences("alfahmi.marema_preferences",context.MODE_PRIVATE); 

		super.onCreate(savedInstanceState);
<<<<<<< HEAD
		addPreferencesFromResource(R.xml.alfahmi__preference);
=======
		addPreferencesFromResource(R.xml.alfahmi__settings);
>>>>>>> origin/master

		String smsCenter = sharedPreferences.getString("smsCenter","telkomsel");
		((ListPreference)findPreference("smsCenter")).setSummary(smsCenter);
		((ListPreference)findPreference("smsCenter")).setOnPreferenceChangeListener(new OnPreferenceChangeListener() {

				@Override
				public boolean onPreferenceChange(Preference preference, Object newValue) {
					String type = (String.valueOf(newValue));
					Intent i = new Intent();
					i.putExtra("smsCenter",type);
					preference.setSummary(type);
					SharedPreferences sharedPreferences = getActivity().getSharedPreferences("alfahmi.marema_preferences",context.MODE_PRIVATE);
					SharedPreferences.Editor editor = sharedPreferences.edit();
					editor.putString("smsCenter", type);
					editor.commit();
					return true;
				}
			});


		String empin = sharedPreferences.getString("pin","1234");
		((EditTextPreference)findPreference("pin")).setSummary(empin);
		((EditTextPreference)findPreference("pin")).setOnPreferenceChangeListener(new OnPreferenceChangeListener() {

				@Override
				public boolean onPreferenceChange(Preference preference, Object newValue) {
					String type = (String.valueOf(newValue));
					Intent i = new Intent();
					i.putExtra("pin",type);
					preference.setSummary(type);
					SharedPreferences sharedPreferences = getActivity().getSharedPreferences("alfahmi.marema_preferences",context.MODE_PRIVATE);
					SharedPreferences.Editor editor = sharedPreferences.edit();
					editor.putString("pin", type);
					editor.commit();
					return true;
				}
			});

		String otlet = sharedPreferences.getString("outletName","Fahmi Cell");
		((EditTextPreference)findPreference("outletName")).setSummary(otlet);
		((EditTextPreference)findPreference("outletName")).setOnPreferenceChangeListener(new OnPreferenceChangeListener() {

				@Override
				public boolean onPreferenceChange(Preference preference, Object newValue) {
					String type = (String.valueOf(newValue));
					Intent i = new Intent();
					i.putExtra("outletName",type);
					preference.setSummary(type);
					SharedPreferences sharedPreferences = getActivity().getSharedPreferences("alfahmi.marema_preferences",context.MODE_PRIVATE);
					SharedPreferences.Editor editor = sharedPreferences.edit();
					editor.putString("outletName", type);
					editor.commit();
					return true;
				}
			});

		String otletmoto = sharedPreferences.getString("outletMoto","Ngutang? Are you kidding me?");
		((EditTextPreference)findPreference("outletMoto")).setSummary(otletmoto);
		((EditTextPreference)findPreference("outletMoto")).setOnPreferenceChangeListener(new OnPreferenceChangeListener() {

				@Override
				public boolean onPreferenceChange(Preference preference, Object newValue) {
					String type = (String.valueOf(newValue));
					Intent i = new Intent();
					i.putExtra("outletMoto",type);
					preference.setSummary(type);
					SharedPreferences sharedPreferences = getActivity().getSharedPreferences("alfahmi.marema_preferences",context.MODE_PRIVATE);
					SharedPreferences.Editor editor = sharedPreferences.edit();
					editor.putString("outletMoto", type);
					editor.commit();
					return true;
				}
			});
		}
}
