package com.example.sk.a2ndhome;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Viewapartment extends AppCompatActivity {

    private ListView listviewdepartment;
    DatabaseReference databasedepartment;
    List<DepartmentDetails>departmentDetailsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewapartment);
        databasedepartment=FirebaseDatabase.getInstance().getReference("department");
        departmentDetailsList=new ArrayList<>();

        listviewdepartment=(ListView)findViewById(R.id.listviewdepartmentdetails);


    }

    @Override
    protected void onStart() {
        super.onStart();

        databasedepartment.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                departmentDetailsList.clear();
                for(DataSnapshot departmentsnapshot:dataSnapshot.getChildren())
                {
                    DepartmentDetails departmentDetails=departmentsnapshot.getValue(DepartmentDetails.class);
                    departmentDetailsList.add(departmentDetails);
                }
                Departmentdetailslist adapter=new Departmentdetailslist(Viewapartment.this,departmentDetailsList);
                listviewdepartment.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
