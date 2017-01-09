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
    ArrayList<String> userID;
    ArrayList<String> UserNAME;
    ArrayList<String> UserPHONE;
    ArrayList<String> UserPRICE ;
    

    public SQLiteListAdapter(
    		Context context2,
    		ArrayList<String> ID,
    		ArrayList<String> NAME,
    		ArrayList<String> PHONE,
    		ArrayList<String> PRICE
    		) 
    {
        	
    	this.context = context2;
        this.userID = ID;
        this.UserNAME = NAME;
        this.UserPHONE = PHONE;
        this.UserPRICE = PRICE ;
    }

    public int getCount() {
        // TODO Auto-generated method stub
        return userID.size();
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
            child = layoutInflater.inflate(R.layout.listviewdatalayout, null);
            
            holder = new Holder();
            
            holder.textviewid = (TextView) child.findViewById(R.id.textViewID);
            holder.textviewname = (TextView) child.findViewById(R.id.textViewNAME);
            holder.textviewphone_number = (TextView) child.findViewById(R.id.textViewPHONE_NUMBER);
            holder.textviewsubject = (TextView) child.findViewById(R.id.textViewSUBJECT);
            
            child.setTag(holder);
            
        } else {
        	
        	holder = (Holder) child.getTag();
        }
        holder.textviewid.setText(userID.get(position));
        holder.textviewname.setText(UserNAME.get(position));
        holder.textviewphone_number.setText(UserPHONE.get(position));
        holder.textviewsubject.setText(UserPRICE.get(position));

        return child;
    }

    public class Holder {
        TextView textviewid;
        TextView textviewname;
        TextView textviewphone_number;
        TextView textviewsubject;
    }

}
