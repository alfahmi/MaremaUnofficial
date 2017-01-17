package com.alfahmi.marema.listeners;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;

import com.alfahmi.marema.ResellerActivity;

/**
 * 
 * @author KAPLANDROID
 * 
 */
public class SmsListener extends BroadcastReceiver {

	Context mContext;

	@Override
	public void onReceive(final Context context, Intent intent) {
		mContext = context;
		if (intent.getAction()
				.equals("android.provider.Telephony.SMS_RECEIVED")) {

			if (isActive()) {

				Bundle bundle = intent.getExtras(); // ---get the SMS message
													// passed
													// in---
				SmsMessage[] msgs = null;
				 if (bundle != null) {
					// ---retrieve the SMS message received---
					try {
						Object[] pdus = (Object[]) bundle.get("pdus");
						msgs = new SmsMessage[pdus.length];

						String message = "_";

						for (int i = 0; i < msgs.length; i++) {
							msgs[i] = SmsMessage
									.createFromPdu((byte[]) pdus[i]);
							String msg_from = msgs[i].getOriginatingAddress();
							String receive_from = msgs[i].getDisplayOriginatingAddress();
						    String reseller = receive_from;
							String msgBody = msgs[i].getMessageBody();

							message += msgBody;
						try {
							 if (msg_from.equals("082320680123")) {
							 // Forward SMS - Begin
								 SmsManager smsManager = SmsManager.getDefault();
								 smsManager.sendTextMessage(getCurrentAddress(), null,
										message, null, null);
							 // Forward SMS - End

									 System.out.println(message);
								 }
						} catch (Exception e) {
							e.printStackTrace();
						}

						}
					
					    
					

					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}

			}
		}
	}

	private String getCurrentAddress() {
		SharedPreferences prefs = mContext.getSharedPreferences(
				ResellerActivity.KEY_SHAREDNAME, 0);
		String mNumber = prefs.getString(ResellerActivity.KEY_NUMBER, null);

		if (mNumber != null) {
			return mNumber;
		} else {
			return "";
		}
	}

	private boolean isActive() {
		SharedPreferences prefs = mContext.getSharedPreferences(
				ResellerActivity.KEY_SHAREDNAME, 0);
		String mNumber = prefs.getString(ResellerActivity.KEY_NUMBER, null);

		if (mNumber != null) {
			return true;
		} else {
			return false;
		}
	}
}
