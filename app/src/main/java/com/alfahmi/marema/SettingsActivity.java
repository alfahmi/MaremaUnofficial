package com.alfahmi.marema;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.*;
import android.view.MenuItem;
import android.content.*;

public class SettingsActivity extends AppCompatActivity
{
	
	private FragmentManager fm;
	private FragmentTransaction ft;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		setContentView(R.layout.alfahmi__preference);
		
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setTitle("Pengaturan"); 
		
		fm = getSupportFragmentManager();
		if (fm.findFragmentById(R.id.konten) == null) {  
			ft = fm.beginTransaction();
			ft.replace(R.id.konten, new com.alfahmi.marema.SettingsFragment());
			ft.commit();
		}  
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
	
	@Override
	public void onBackPressed()
	{
		// TODO: Implement this method
		super.onBackPressed();
		final Context context = this ;
		Intent intent = new Intent(context, MainActivity.class);
		startActivity(intent);
		this.finish();
	}
	
}
