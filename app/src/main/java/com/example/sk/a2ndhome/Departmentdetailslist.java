package com.example.sk.a2ndhome;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class Departmentdetailslist extends ArrayAdapter<DepartmentDetails> {

    private Activity context;
    private List<DepartmentDetails>departmentDetailsList;

    public  Departmentdetailslist(Activity context,List<DepartmentDetails> departmentDetailsList)
    {
        super(context,R.layout.list_layout,departmentDetailsList);
        this.context=context;
        this.departmentDetailsList=departmentDetailsList;
    }

    @NonNull
    @Override
    public View getView(int position,View convertView,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View listviewitem=inflater.inflate(R.layout.list_layout,null,true);

        TextView textviewname=(TextView) listviewitem.findViewById(R.id.textViewname);
        TextView textviewaddress=(TextView) listviewitem.findViewById(R.id.textViewaddress);
        TextView textviewphone=(TextView) listviewitem.findViewById(R.id.textViewphone);
        TextView textviewrent=(TextView) listviewitem.findViewById(R.id.textViewprice);

        DepartmentDetails departmentDetails=departmentDetailsList.get(position);
        textviewname.setText(departmentDetails.getName());
        textviewaddress.setText(departmentDetails.getAddress());
        textviewphone.setText(departmentDetails.getPhonenumber());
        textviewrent.setText(departmentDetails.getRent());

        return listviewitem;


    }
}
