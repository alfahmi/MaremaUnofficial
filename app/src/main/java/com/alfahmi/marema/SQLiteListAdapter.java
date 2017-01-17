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
    ArrayList<String> userNO;
    ArrayList<String> UserNAMA;
    ArrayList<String> UserNOMOR;
    ArrayList<String> UserHARGA;
    

    public SQLiteListAdapter(
    		Context context2,
    		ArrayList<String> NO,
    		ArrayList<String> NAMA,
    		ArrayList<String> NOMOR,
    		ArrayList<String> HARGA
    		) 
    {
        	
    	this.context = context2;
        this.userNO = NO;
        this.UserNAMA = NAMA;
        this.UserNOMOR = NOMOR;
        this.UserHARGA = HARGA ;
    }

    public int getCount() {
        // TODO Auto-generated method stub
        return userNO.size();
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
            child = layoutInflater.inflate(R.layout.alfahmi_data_penjualan, null);
            
            holder = new Holder();
            
            holder.textviewid = (TextView) child.findViewById(R.id.alfahmi__penjualan_no);
            holder.textviewname = (TextView) child.findViewById(R.id.alfahmi__penjualan_nama);
            holder.textviewphone_number = (TextView) child.findViewById(R.id.alfahmi__penjualan_nomor);
            holder.textviewsubject = (TextView) child.findViewById(R.id.alfahmi__penjualan_harga);
            
            child.setTag(holder);
            
        } else {
        	
        	holder = (Holder) child.getTag();
        }
        holder.textviewid.setText(userNO.get(position));
        holder.textviewname.setText(UserNAMA.get(position));
        holder.textviewphone_number.setText(UserNOMOR.get(position));
        holder.textviewsubject.setText(UserHARGA.get(position));

        return child;
    }

    public class Holder {
        TextView textviewid;
        TextView textviewname;
        TextView textviewphone_number;
        TextView textviewsubject;
    }

}
