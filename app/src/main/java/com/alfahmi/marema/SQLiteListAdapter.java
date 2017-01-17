package com.alfahmi.marema;

import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SQLiteListAdapter extends BaseAdapter {
	
    Context context;
<<<<<<< HEAD
    ArrayList<String> userNO;
    ArrayList<String> UserNAMA;
    ArrayList<String> UserNOMOR;
    ArrayList<String> UserHARGA;
=======
    ArrayList<String> userID;
    ArrayList<String> UserNAME;
    ArrayList<String> UserPHONE;
    ArrayList<String> UserPRICE ;
>>>>>>> origin/master
    

    public SQLiteListAdapter(
    		Context context2,
<<<<<<< HEAD
    		ArrayList<String> NO,
    		ArrayList<String> NAMA,
    		ArrayList<String> NOMOR,
    		ArrayList<String> HARGA
=======
    		ArrayList<String> ID,
    		ArrayList<String> NAME,
    		ArrayList<String> PHONE,
    		ArrayList<String> PRICE
>>>>>>> origin/master
    		) 
    {
        	
    	this.context = context2;
<<<<<<< HEAD
        this.userNO = NO;
        this.UserNAMA = NAMA;
        this.UserNOMOR = NOMOR;
        this.UserHARGA = HARGA ;
=======
        this.userID = ID;
        this.UserNAME = NAME;
        this.UserPHONE = PHONE;
        this.UserPRICE = PRICE ;
>>>>>>> origin/master
    }

    public int getCount() {
        // TODO Auto-generated method stub
<<<<<<< HEAD
        return userNO.size();
=======
        return userID.size();
>>>>>>> origin/master
    }

    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    public View getView(int position, View child, ViewGroup parent) {
    	
        Holder holder;
        
        LayoutInflater layoutInflater;
        
        if (child == null) {
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
<<<<<<< HEAD
            child = layoutInflater.inflate(R.layout.alfahmi_data_penjualan, null);
            
            holder = new Holder();
            
            holder.textviewid = (TextView) child.findViewById(R.id.alfahmi__penjualan_no);
            holder.textviewname = (TextView) child.findViewById(R.id.alfahmi__penjualan_nama);
            holder.textviewphone_number = (TextView) child.findViewById(R.id.alfahmi__penjualan_nomor);
            holder.textviewsubject = (TextView) child.findViewById(R.id.alfahmi__penjualan_harga);
=======
            child = layoutInflater.inflate(R.layout.listviewdatalayout, null);
            
            holder = new Holder();
            
            holder.textviewid = (TextView) child.findViewById(R.id.textViewID);
            holder.textviewname = (TextView) child.findViewById(R.id.textViewNAME);
            holder.textviewphone_number = (TextView) child.findViewById(R.id.textViewPHONE_NUMBER);
            holder.textviewsubject = (TextView) child.findViewById(R.id.textViewSUBJECT);
>>>>>>> origin/master
            
            child.setTag(holder);
            
        } else {
        	
        	holder = (Holder) child.getTag();
        }
<<<<<<< HEAD
        holder.textviewid.setText(userNO.get(position));
        holder.textviewname.setText(UserNAMA.get(position));
        holder.textviewphone_number.setText(UserNOMOR.get(position));
        holder.textviewsubject.setText(UserHARGA.get(position));
=======
        holder.textviewid.setText(userID.get(position));
        holder.textviewname.setText(UserNAME.get(position));
        holder.textviewphone_number.setText(UserPHONE.get(position));
        holder.textviewsubject.setText(UserPRICE.get(position));
>>>>>>> origin/master

        return child;
    }

    public class Holder {
        TextView textviewid;
        TextView textviewname;
        TextView textviewphone_number;
        TextView textviewsubject;
    }

}
